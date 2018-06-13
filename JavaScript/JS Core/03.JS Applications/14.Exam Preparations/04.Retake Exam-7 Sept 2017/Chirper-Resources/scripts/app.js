$(() => {
    let header = $('#profile');


    function validateRegisterFields(username, pass, repeatPass) {
        if (!/^[A-Za-z]{5,}$/g.test(username)) {
            showError(`Username should be at least 5 characters long and contain only letters!`);
            return false;
        }

        if (pass !== repeatPass) {
            showError('Both passwords should match.');
            return false;
        }
        if (pass === '') {
            showError('Password should be at least 1 characters long!');
            return false;
        }
        return true;
    }

    // Attach event handlers
    (() => {
        $('#login').click(() => {
            showView('viewLogin')
        });
        $('#register').click(() => {
            showView('viewRegister')
        });
        $('#formLogin').click(loginUser);
        $('#logout').click(logoutUser);
        $('#home').click(() => {
            showView('viewFeed');
            loadChirpsFromSubscription();
        });
        $('#btnSubmitChirp').click(createChirp);
        $('#btnSubmitChirpMy').click(createChirpMy);
        $('#discover').click(() => {
            showView('viewDiscover');
            loadDiscoverPage();
        });
        $('#btnFollow').click(unFollowUsers);
        $('#me').click(() => {
            showView('viewMe');
            loadOwnChirps();
        });


        $('.notification').click(function () {
            $(this).hide();
        })
    })();

    if (sessionStorage.getItem('authtoken') === null) {
        userLoggedOut();
    } else {
        userLoggedIn();
    }

    function loadDiscoverPage() {
        chirperService.discoverPage()
            .then((users) => {
                console.log(users);
                let userBox = $('#userlist');
                userBox.empty();
                for (let user of users) {
                    let followers = $(user['subscriptions']).length;
                    console.log(followers);
                    userBox
                        .append($('<div class="userbox">')
                            .append($(`<div>`).append($(`<a href="#" class="chirp-author" data-name=${user}>${user['username']}</a>`).click(thisUserPage)))
                            .append('\n')
                            .append($(`<div class="user-details"><span>${followers} followers</span></div>`)))
                }
            }).catch(handleError)
    }


    function loadOwnChirps() {
        let username = sessionStorage.getItem('username');
        //$('.chirper .titlebar').text(username);
        let statusBar = $('#myStats span');
        let chirpsCell = $(statusBar[0]);
        let followingCell = $(statusBar[1]);
        let followersCell = $(statusBar[2]);

        let countChirpsPromise = chirperService.countChirps(username);
        let countFollowingPromise = chirperService.countFollowing(username);
        let countFollowersPromise = chirperService.countFollowers(username);
        let getOwnChirps = chirperService.getAllChirpsFromUser(username);

        Promise.all([countChirpsPromise, countFollowingPromise, countFollowersPromise, getOwnChirps])
            .then(([allChirps, following, followers, ownChirps]) => {
                console.log(allChirps);
                console.log(following[0]);
                console.log(followers);
                chirpsCell.text(`chirps ${allChirps.length}`);
                followingCell.text(`following ${following[0]["subscriptions"].length}`);
                followersCell.text(`followers ${followers.length}`);
                displayMyOwnChirps(ownChirps);
                showView('viewMe')
            })
            .catch(handleError)
    }

    function displayMyOwnChirps(ownChirps) {
        let container = $('#myChirps');
        container.empty();

        for (let chirp of ownChirps) {
            console.log(chirp);
            let author = chirp.author;
            let text = chirp.text;
            let time = calcTime(chirp._kmd.ect);
            let chirpId = chirp['_id'];

            let delBtn = $(`<button data-id="${chirpId}">delete</button>`).click(deleteChirp);
            container.append($('<article class="chirp">')
                .append($('<div class="titlebar">')
                    .append($(`<a href="#" class="chirp-author">${author}</a>`))
                    .append($(`<span class="chirp-time">`).text(`${time}  `).append(delBtn))
                )
                .append($(`<p>${text}</p>`))
            )
        }

    }


    function deleteChirp() {
        let chirpId = $(this).attr('data-id');
        chirperService.deleteChirp(chirpId)
            .then(() => {
                showView('viewMe');
                loadOwnChirps();
            })
            .catch(handleError)
    }


    //5
    function createChirp(ev) {
        ev.preventDefault();
        let author = sessionStorage.getItem('username');
        let textArea = $('#textAreaFeed');
        console.log(textArea.val());


        if (textArea.val() === "" || textArea.val().length > 150) {
            showError('A chirp text shouldn’t be empty and shouldn’t contain more than 150 symbols.');
            showView('viewFeed');
        } else {
            chirperService.createChirp(textArea.val(), author)
                .then(() => {
                    textArea.val('');
                    showInfo('Chirp published.');
                    loadOwnChirps();
                })
                .catch(handleError);
        }

    }

    function createChirpMy(ev) {
        ev.preventDefault();
        let author = sessionStorage.getItem('username');
        let textArea = $('#textArea');
        console.log(textArea.val());


        if (textArea.val() === "" || textArea.val().length > 150) {
            showError('A chirp text shouldn’t be empty and shouldn’t contain more than 150 symbols.');
            showView('viewMe');
        } else {
            chirperService.createChirp(textArea.val(), author)
                .then(() => {
                    textArea.val('');
                    showInfo('Chirp published.');
                    loadOwnChirps();
                })
                .catch(handleError);
        }

    }

    //04

    function loadChirpsFromSubscription() {

        let username = sessionStorage.getItem('username');
        $('.chirper .titlebar').text(username);
        let statusBar = $('#userStats span');
        let chirpsCell = $(statusBar[0]);
        let followingCell = $(statusBar[1]);
        let followersCell = $(statusBar[2]);

        let countChirpsPromise = chirperService.countChirps(username);
        let countFollowingPromise = chirperService.countFollowing(username);
        let countFollowersPromise = chirperService.countFollowers(username);

        Promise.all([countChirpsPromise, countFollowingPromise, countFollowersPromise])
            .then(([allChirps, fallowing, followers]) => {
                console.log(allChirps);
                console.log(fallowing[0]);
                console.log(followers);
                chirpsCell.text(`chirps ${allChirps.length}`);
                followingCell.text(`following ${fallowing[0]["subscriptions"].length}`);
                followersCell.text(`followers ${followers.length}`);
                displayChirpsBySubscriptions(fallowing);
            })
            .catch(handleError)
    }

    function displayChirpsBySubscriptions(fallowing) {
        let subs = fallowing[0]["subscriptions"];
        let container = $('#chirps');
        container.empty();
        container.append($('<h2 class="titlebar">Chirps</h2>'));
        if (subs.length === 0) {
            container.append($('<div class="chirp"><span class="loading">No chirps in database</span></div>'))
        }
        else {
            chirperService.getAllChirpsFromSubscriptions(subs)
                .then((chirps) => {
                    for (let chirp of chirps) {
                        let author = chirp.author;
                        let text = chirp.text;
                        let time = calcTime(chirp._kmd.ect);
                        container.append($('<article class="chirp">')
                            .append($('<div class="titlebar">')
                                .append($(`<a href="#" class="chirp-author" data-name="${author}">${author}</a>`).click(thisUserPage))
                                .append($(`<span class="chirp-time">${time}</span>`))
                            )
                            .append($(`<p>${text}</p>`))
                        )
                    }
                }).catch(handleError)
        }
    }

    function thisUserPage() {
        showView('viewProfile');
        let username = $(this).text();
        let theUsername = sessionStorage.getItem('username');
        $('#profileName').text(username);
        let statusBar = $('#userProfileStats span');
        let chirpsCell = $(statusBar[0]);
        let followingCell = $(statusBar[1]);
        let followersCell = $(statusBar[2]);

        let countChirpsPromise = chirperService.getAllChirpsFromUser(username);
        let countFollowingPromise = chirperService.countFollowing(username);
        let countFollowingPromise1 = chirperService.countFollowing(theUsername);
        let countFollowersPromise = chirperService.countFollowers(username);

        Promise.all([countChirpsPromise, countFollowingPromise, countFollowingPromise1, countFollowersPromise])
            .then(([allChirps, following, following2, followers]) => {
                console.log(allChirps);
                console.log(following[0]);
                console.log(followers);
                chirpsCell.text(`chirps ${allChirps.length}`);
                followingCell.text(`following ${following[0]["subscriptions"].length}`);
                followersCell.text(`followers ${followers.length}`);
                displayChirpsBySubscriptionsProfile(allChirps, following2, username);
            })
            .catch(handleError)
    }

    function displayChirpsBySubscriptionsProfile(allChirps, following2, usernameProfile) {
        console.log(usernameProfile);
        let container = $('#profileChirps');
        container.empty();
        let followBtn = $('#btnFollow');
        let subs = following2[0]["subscriptions"];
        followBtn.attr('data-username', usernameProfile);
        if (subs.includes(usernameProfile)) {
            followBtn.text('Unfollow');
        }
        container.append($('<h2 class="titlebar">Chirps</h2>'));
        if (allChirps.length === 0) {
            container.append($('<div class="chirp"><span class="loading">No chirps in database</span></div>'))
        }
        else {
            for (let chirp of allChirps) {
                let author = chirp.author;
                let text = chirp.text;
                let time = calcTime(chirp._kmd.ect);
                container.append($('<article class="chirp">')
                    .append($('<div class="titlebar">')
                        .append($(`<a href="#" class="chirp-author" data-name="${author}">${author}</a>`).click(thisUserPage))
                        .append($(`<span class="chirp-time">${time}</span>`))
                    )
                    .append($(`<p>${text}</p>`))
                )
            }
        }
    }

    function unFollowUsers() {
        let username = $(this).attr('data-username');
        console.log(username);
        let theUsername = sessionStorage.getItem('username');
        let followBtn = $('#btnFollow');
        if (followBtn.text() === 'Follow') {
            showInfo(`Subscribed to ${username}`);
            followBtn.text('Unfollow');
            chirperService.followUnFollow()

        } else {
            showInfo(`Unsubscribed to ${username}`);
            followBtn.text('Follow');
        }
    }

    // 03
    function logoutUser() {
        auth.logout()
            .then(() => {
                sessionStorage.clear();
                showInfo('Logout successful.');
                userLoggedOut();
            }).catch(handleError);
    }

    // 02
    function loginUser(ev) {
        ev.preventDefault();
        let loginForm = $('#formLogin');
        let usernameInput = loginForm.find('input[name="username"]');
        let passInput = loginForm.find('input[name="password"]');
        let usernameVal = usernameInput.val();
        let passVal = passInput.val();
        console.log(usernameVal);
        let allIsValid = validateRegisterFields(usernameVal, passVal, passVal);
        if (allIsValid) {
            auth.login(usernameVal, passVal)
                .then((userInfo) => {
                    console.log(userInfo);
                    usernameInput.val('');
                    passInput.val('');
                    saveSession(userInfo);
                    showInfo('User login successful.');
                    showView('viewFeed');
                    loadChirpsFromSubscription();
                }).catch(handleError);
        }
    }

    function createActiveReceipt() {
        chirperService.createReceipt('true', 0, 0)
            .then(() => {
                    loadActiveReceipt()
                }
            )
            .catch(handleError);
    }

    // 01
    function registerUser(ev) {
        ev.preventDefault();
        let registerForm = $('#formRegister');
        let usernameInput = registerForm.find('input[name="username"]');
        let passInput = registerForm.find('input[name="password"]');
        let repeatPassInput = registerForm.find('input[name="repeatPass"]');

        //console.log(usernameInput.val());
        let usernameVal = usernameInput.val();
        let passVal = passInput.val();
        let repeatPassVal = repeatPassInput.val();

        let allIsValid = validateRegisterFields(usernameVal, passVal, repeatPassVal);
        if (allIsValid) {
            auth.register(usernameVal, passVal)
                .then((userInfo) => {
                    saveSession(userInfo);
                    showInfo('User registration successful.');
                    usernameInput.val('');
                    passInput.val('');
                    repeatPassInput.val('');
                    //createActiveReceipt();
                    showView('viewFeed');
                }).catch(handleError);
        }
    }

//Standard function

    function showView(viewName) {
        $('#main > section').hide();
        $('#' + viewName).show();
    }

    function userLoggedOut() {
        $('#main .menu').hide();
        showView('viewLogin');
    }

    function userLoggedIn() {
        $('#main .menu').show();
        loadChirpsFromSubscription();
        showView('viewFeed')
    }

    function saveSession(userInfo) {
        let userAuth = userInfo._kmd.authtoken;
        sessionStorage.setItem('authtoken', userAuth);
        let username = userInfo.username;
        sessionStorage.setItem('username', username);
        let userId = userInfo._id;
        sessionStorage.setItem('userId', userId);
        userLoggedIn();
    }

    function handleError(reason) {
        console.log(reason);
        showError(reason);
        //showError(reason.responseJSON.description);
    }

    function showInfo(message) {
        let infoBox = $('#infoBox');
        infoBox.text(message);
        infoBox.show();
        setTimeout(() => infoBox.fadeOut(), 3000);
    }

    function showError(message) {
        let errorBox = $('#errorBox');
        errorBox.text(message);
        errorBox.show();
        setTimeout(() => errorBox.fadeOut(), 3000);
    }


    // Handle notifications
    $(document).on({
        ajaxStart: () => $("#loadingBox").show(),
        ajaxStop: () => $('#loadingBox').fadeOut()
    });

    //helper function
    function calcTime(dateIsoFormat) {
        let diff = new Date - (new Date(dateIsoFormat));
        diff = Math.floor(diff / 60000);
        if (diff < 1) return 'less than a minute';
        if (diff < 60) return diff + ' minute' + pluralize(diff);
        diff = Math.floor(diff / 60);
        if (diff < 24) return diff + ' hour' + pluralize(diff);
        diff = Math.floor(diff / 24);
        if (diff < 30) return diff + ' day' + pluralize(diff);
        diff = Math.floor(diff / 30);
        if (diff < 12) return diff + ' month' + pluralize(diff);
        diff = Math.floor(diff / 12);
        return diff + ' year' + pluralize(diff);

        function pluralize(value) {
            if (value !== 1) return 's';
            else return '';
        }
    }

})