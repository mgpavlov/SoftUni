$(() => {

    function validateRegisterFields(username, pass, repeatPass) {
        if (!/^[A-Za-z]{3,}$/g.test(username)) {
            showError(`Username should be at least 3 characters long and contain only letters!`);
            return false;
        }

        if (pass !== repeatPass) {
            showError('Passwords should match!');
            return false;
        }

        if (!/^[A-Za-z\d]{6,}$/.test(pass)) {
            showError('Password should be at least 3 characters long and contain alphanumerical characters!');
            return false;
        }

        return true;
    }

    // Attach event handlers
    (() => {
        $('#menu').find('a[data-target]').click(navigateTo);
        $('#registerForm').submit(registerUser);//01
        $('#loginForm').submit(loginUser); //02
        $('#createPostForm').submit(createPost); //04
        $('#createCommentForm').submit(createComment);//07
        $('#editPostForm').submit(editPost);//08
        $('#linkMenuLogout').click(logoutUser);//03
        $('#linkCatalog').click(loadAllPosts);//05
        $('#linkMyPosts').click(loadMyPosts);//06 правим най-накрая, копираме loadAllPosts
        $('.notification').click(function () {
            $(this).hide();
        })
    })();

    if (sessionStorage.getItem('authtoken') === null) {
        userLoggedOut();
    } else {
        userLoggedIn();
        //loadAllPosts(); може да го няма тъй като се вика от userLoggedIn
    }

    //  06 LOGIC TO VIEW MY POSTS
    function loadMyPosts() {
        let username = sessionStorage.getItem('username');

        flightsService.loadOwnPosts(username)
            .then((myOwnPosts) => {
                //console.log(myOwnPosts);
                displayMyOwnPosts(myOwnPosts);
            }).catch(handleError);
    }

    function displayMyOwnPosts(myOwnPosts) {
        let postsContainer = $('#myForumPosts');
        postsContainer.empty();
        if (myOwnPosts.length === 0) {
            postsContainer.text('No posts in database.');
        }

        let counter = 1;
        for (let postObj of myOwnPosts) {
            let postId = postObj['_id'];
            let rank = counter++;
            let timeCreated = calcTime(postObj._kmd.ect);
            let url = postObj['url'];
            let imageUrl = postObj['imageUrl'];
            let author = postObj['author'];
            let title = postObj['title'];
            let description = postObj['description'] === ""
                ? "No description."
                : postObj['description'];

            let detailsLink = $(`<a  href="#" data-id="${postId}">Details</a>`)
                .click(loadPostDetails);
            let postDiv = $('<div class="post">')
                .append($('<div class="col rank">')
                    .append('<span>').text(rank))
                .append($('<div class="col thumbnail">')
                    .append($(`<a href="${url}">`)
                        .append($(`<img src="${imageUrl}">`))))
                .append($('<div class="post-content">')
                    .append($('<div class="title">')
                        .append($(`<a href="${url}">`)
                            .text(title)))
                    .append($('<div class="details">')
                        .append($('<div class="info">')
                            .text(`submitted ${timeCreated} ago by ${author}`))
                        .append($('<div class="controls">')
                            .append($('<ul>')
                                .append($('<li class="action">').append(detailsLink))))));

            if (author === sessionStorage.getItem('username')) {
                let controls = postDiv
                    .find('.controls')
                    .find('ul');

                controls.append($('<li class="action">')
                    .append($(`<a href="#" data-id="${postId}">Delete</a>`)
                        .click(deletePost)));
                controls.append($('<li class="action">')
                    .append($(`<a href="#" data-id="${postId}">Edit</a>`)
                        .click(displayEditForm)));
            }

            postsContainer.append(postDiv);
        }
    }

    // 05 LOGIC TO VIEW CATALOG
    function loadAllPosts() {
        flightsService.loadAllPosts()
            .then((allPosts) => {
                displayCatalog(allPosts);
            }).catch(handleError);
    }
    //05
    function displayCatalog(allPosts) {
        let postsContainer = $('#allForumPosts');
        postsContainer.empty();

        if (allPosts.length === 0) {
            postsContainer.text('No posts in database.');
        }

        let counter = 1;
        for (let postObj of allPosts) {
            let postId = postObj['_id'];
            let rank = counter++;
            let timeCreated = calcTime(postObj._kmd.ect);
            let url = postObj['url'];
            let imageUrl = postObj['imageUrl'];
            let author = postObj['author'];
            let title = postObj['title'];
            let description = postObj['description'] === ""
                ? "No description."
                : postObj['description'];

            let detailsLink = $(`<a  href="#" data-id="${postId}">Details</a>`)
                  .click(loadPostDetails);                    //05.1
            let postDiv = $('<div class="post">')
                .append($('<div class="col rank">')
                    .append('<span>').text(rank))
                .append($('<div class="col thumbnail">')
                    .append($(`<a href="${url}">`)
                        .append($(`<img src="${imageUrl}">`))))
                .append($('<div class="post-content">')
                    .append($('<div class="title">')
                        .append($(`<a href="${url}">`)
                            .text(title)))
                    .append($('<div class="details">')
                        .append($('<div class="info">')
                            .text(`submitted ${timeCreated} ago by ${author}`))
                        .append($('<div class="controls">')
                            .append($('<ul>')
                                .append($('<li class="action">').append(detailsLink))))));

            if (author === sessionStorage.getItem('username')) {
                let controls = postDiv
                    .find('.controls')
                    .find('ul');

                controls.append($('<li class="action">')
                    .append($(`<a href="#" data-id="${postId}">Delete</a>`)
                        .click(deletePost)));//05.2
                controls.append($('<li class="action">')
                    .append($(`<a href="#" data-id="${postId}">Edit</a>`)
                        .click(displayEditForm)));//05.3
            }

            postsContainer.append(postDiv);
        }
    }

    // 04 LOGIC TO CREATE POST
    function createPost(ev) {
        ev.preventDefault();
        let author = sessionStorage.getItem('username');
        let urlInput = $(this).find('input[name="url"]');
        let titleInput = $(this).find('input[name="title"]');
        let imgInput = $(this).find('input[name="image"]');
        let descInput = $(this).find('textarea[name="description"]');

        if (urlInput.val() === "" || titleInput.val() === "") {
            showError('Url/Title cannot be empty!');
            return;
        }

        if (!urlInput.val().startsWith('http')) {
            showError('Url should start with http!');
            return;
        }

        let title = titleInput.val();
        let description = descInput.val();
        let url = urlInput.val();
        let imageUrl = imgInput.val();

        flightsService.createPost(author, title, description, url, imageUrl)
            .then(() => {
                urlInput.val('');
                titleInput.val('');
                imgInput.val('');
                descInput.val('');
                showInfo('Post created.');
                loadAllPosts();
                showView('Catalog');
            }).catch(handleError);
    }

    // 05.2 LOGIC TO DELETE POST
    function deletePost() {
        let postId = $(this).attr('data-id');

        flightsService.deletePost(postId)
            .then(() => {
                showInfo('Post deleted.');
                loadAllPosts();
                showView('Catalog');
            }).catch(handleError);
    }

    // 05.3 LOGIC TO EDIT POST
    function displayEditForm() {
        let postId = $(this).attr('data-id');//задаваме го от 05
        let editForm = $('#editPostForm');

        flightsService.loadPostById(postId)
            .then((postInfo) => {
                editForm.find('input[name="url"]').val(postInfo['url']);
                editForm.find('input[name="title"]').val(postInfo['title']);
                editForm.find('input[name="image"]').val(postInfo['imageUrl']);
                editForm.attr('data-id', postInfo._id); //=> 08
                editForm.find('textarea[name="description"]').val(postInfo['description']);
                showView('PostEdit');
            }).catch(handleError);

    }

    //08
    function editPost(ev) {
        ev.preventDefault();
        let postId = $(this).attr('data-id'); // задаваме го в 05.33
        let author = sessionStorage.getItem('username');
        let urlInput = $(this).find('input[name="url"]');
        let titleInput = $(this).find('input[name="title"]');
        let imgInput = $(this).find('input[name="image"]');
        let descInput = $(this).find('textarea[name="description"]');

        if (urlInput.val() === "" || titleInput.val() === "") {
            showError('Url/Title cannot be empty!');
            return;
        }

        if (!urlInput.val().startsWith('http')) {
            showError('Url should start with http!');
            return;
        }

        let title = titleInput.val();
        let description = descInput.val();
        let url = urlInput.val();
        let imageUrl = imgInput.val();

        flightsService.editPost(postId, author, title, description, url, imageUrl)
            .then(() => {
                urlInput.val('');
                titleInput.val('');
                imgInput.val('');
                descInput.val('');
                showInfo(`Post ${title} created.`);
                loadAllPosts();
                showView('Catalog');
            }).catch(handleError);
    }

    // 05.1.1   LOGIC TO LOAD POST DETAILS
    function loadPostDetails(pId) { // слагаме id за да може при изтриване на коментар да се зареди отново страницата като сложим функцияТА 344 red
        let postId = $(this).attr('data-id');

        if(!postId){
            postId = pId;
        }

        let postPromise =  flightsService.loadPostById(postId);
        let commentsPromise = commentsService.loadAllCommentsInPost(postId);
        Promise.all([postPromise, commentsPromise])
            .then(([postInfo, comments]) => {
                displayPostDetails([postInfo, comments]);
                showView('PostDetails');
            })
            .catch(handleError)
    }

    // 05.1.1.00
    function displayPostDetails([postInfo, comments]) {
        $('#createCommentForm').attr('data-id', postInfo._id); // =>07 => вмъкваме id за да можем когато пишем коментар да е закъчен към конкретния пост
        let postContainer = $('#postDetails');
        postContainer.empty();
        let description = postInfo['description'] === ""
            ? "No description"
            : postInfo['description'];
        postContainer.append($('<div class="col thumbnail">')
            .append($(`<img src="${postInfo['imageUrl']}">`))
            .append($('<div class="post-content">')
                .append($('<div class="title">')
                    .append($('<strong>').text(postInfo['title'])))
                .append($('<div class="details">').text(description))));

        let commentsContainer = $('#allComments');
        commentsContainer.empty();

        if(comments.length === 0){
            commentsContainer.append($('<div>No comments yet.</div>'));
            console.log('Here');
        }

        for(let comment of comments) {
            let commentAuthor = comment['author'];
            let content = comment['content'];
            let curComment = $('<article class="comment">');
            curComment.append($('<div class="comment-content">').text(content));

            if(commentAuthor === sessionStorage.getItem('username')){
                let deleteBtn = $(`<a href="#" data-target="${postInfo._id}" data-id="${comment._id}" class="action">[Delete]</a>`)
                    .click(deleteComment); //05.1.1.1
                curComment.append(deleteBtn);
            }

            commentsContainer.append(curComment);
        }
    }

    // 05.1.1.1  LOGIC TO DELETE A COMMENT
    function deleteComment() {
        let postId = $(this).attr('data-target');
        let commentId = $(this).attr('data-id');

        commentsService.deleteComment(commentId)
            .then(() => {
                showInfo('Comment deleted.');
                loadPostDetails(postId); // => 280 ред
            }).catch(handleError);
    }

    // 07 LOGIC TO CREATE A COMMENT
    function createComment(ev) {
        ev.preventDefault();
        let contentInput = $('#cmtContent');
        let postId = $(this).attr('data-id'); // взимаме id от 5.1.1.00 ред 299
        let author = sessionStorage.getItem('username');
        let content = contentInput.val();

        commentsService.createComment(author, content, postId)
            .then(() => {
                showInfo('Comment created.');
                loadPostDetails(postId);// => 280 ред
                contentInput.val('');
            }).catch(handleError);
    }

    // 03 LOGIC TO LOGOUT USER
    function logoutUser() {
        auth.logout()
            .then(() => {
                sessionStorage.clear();
                showInfo('Logout successful.');
                userLoggedOut();
                showView('SignIn');
            }).catch(handleError);
    }

    // 02 LOGIC TO LOGIN USER
    function loginUser(ev) {
        ev.preventDefault();
        let loginForm = $('#loginForm');
        let usernameInput = loginForm.find('input[name="username"]');
        let passInput = loginForm.find('input[name="password"]');

        let usernameVal = usernameInput.val();
        let passVal = passInput.val();

        let allIsValid = validateRegisterFields(usernameVal, passVal, passVal);
        if (allIsValid) {
            auth.login(usernameVal, passVal)
                .then((userInfo) => {
                    usernameInput.val('');
                    passInput.val('');
                    saveSession(userInfo);
                    showInfo('User login successful.');
                }).catch(handleError);
        }
    }

    // 01 LOGIC TO REGISTER USER
    function registerUser(ev) {
        ev.preventDefault();
        let registerForm = $('#registerForm');
        let usernameInput = registerForm.find('input[name="username"]');
        let passInput = registerForm.find('input[name="password"]');
        let repeatPassInput = registerForm.find('input[name="repeatPass"]');

        let usernameVal = usernameInput.val();
        let passVal = passInput.val();
        let repeatPassVal = repeatPassInput.val();

        let allIsValid = validateRegisterFields(usernameVal, passVal, repeatPassVal);
        if (allIsValid) {
            auth.register(usernameVal, passVal)
                .then((userInfo) => {
                    usernameInput.val('');
                    passInput.val('');
                    repeatPassInput('');
                    saveSession(userInfo);
                    showInfo('User registration successful.');
                }).catch(handleError);
        }
    }

//Standart function
    function navigateTo() {
        let viewName = $(this).attr('data-target');
        showView(viewName);
    }

    function showView(viewName) {
        $('main > section').hide();
        $('#view' + viewName).show();
    }

    function userLoggedOut() {
        $('#menu').hide();
        $('#profile').hide();
        $('#username').text('');
        showView('SignIn');
    }

    function userLoggedIn() {
        $('#menu').show();
        $('#profile').show();
        $('#username').text(`Hello, ${sessionStorage.getItem('username')}!`);
        showView('Catalog');
        loadAllPosts();
    }

    function saveSession(userInfo) {
        let userAuth = userInfo._kmd.authtoken;
        sessionStorage.setItem('authtoken', userAuth);
        let username = userInfo.username;
        sessionStorage.setItem('username', username);
        userLoggedIn();
    }

    function handleError(reason) {
        showError(reason.responseJSON.description);
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

    // HELPER FUNCTION FOR TIME
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

    // Handle notifications
    $(document).on({
        ajaxStart: () => $("#loadingBox").show(),
        ajaxStop: () => $('#loadingBox').fadeOut()
    });
})