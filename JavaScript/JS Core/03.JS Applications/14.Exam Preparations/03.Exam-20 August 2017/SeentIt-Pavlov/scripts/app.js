$(() => {


    // Attach event handlers
    //01  добавяме на <а> data-target="Submit"
    (() => {
        $('#menu').find('a[data-target]').click(navigateTo);
        $('#registerForm').submit(registerUser);
        $('#loginForm').submit(loginUser);
        $('#createPostForm').submit(createPost);
        $('#commentForm').submit(loadPostComments);
        $('#editPostForm').submit(displayEdit);
        $('#profile > a').click(logoutUser);
        $('#linkCatalog').click(loadCatalog);
        $('#linkMyPosts').click(loadMyPosts);

        $('.notification').click(function() {
            $(this).hide();
        })
    })();
    //06
    if(sessionStorage.getItem('authtoken') === null){
        userLoggedOut();
    } else {
        userLoggedIn();
        loadCatalog();
        showView('Catalog');
    }

    //02
    function navigateTo() {
        let viewName = $(this).attr('data-target');
        showView(viewName);
    }
    //03 Shows one view/section at a time
    function showView(viewName) {
        $('.content > section').hide();
        $('#view' + viewName).show();
    }

    //04
    function userLoggedOut() {
        showView('Welcome');
        $('#menu').hide();
        $('#profile').hide();
        $('#profile > span').text('');
    }

    //05
    function userLoggedIn() {
        let username = sessionStorage.getItem('username');
        $('#menu').show();
        $('#profile').show();
        $('#profile > span').text(`Welcome, ${username}!`);
        showView('Catalog');
    }


    // 07   LOGIC TO REGISTER USER
    function registerUser(ev) {
        ev.preventDefault();
        let form = $('#registerForm');
        let registerUsername = form.find('input[name="username"]');
        let registerPassword = form.find('input[name="password"]');
        let repeatPassword = form.find('input[name="repeatPass"]');

        let usernameVal = registerUsername.val();
        let passwordVal = registerPassword.val();
        let repeatPasswordVal = repeatPassword.val();

        let regExUsername = /^[a-zA-z]{3,}$/; //username should be at least 3 characters long and should contain only english alphabet letters.
        let regExPass = /^[a-zA-z0-9]{6,}$/; //user‘s password should be at least 6 characters long and should contain only english alphabet letters and digits.

        if (!regExUsername.test(usernameVal)) {
            return showError('A username should be at least 3 characters long and should contain only english alphabet letters.')
        }
        if (!regExPass.test(passwordVal)) {
            return showError('A user‘s password should be at least 6 characters long and should contain only english alphabet letters and digits.')
        }
        if (passwordVal !== repeatPasswordVal){
            return showError("Passwords dosen't match")
        }
        auth.register(usernameVal, passwordVal)
            .then((userInfo) => {
                registerUsername.val("");
                registerPassword.val("");
                repeatPassword.val("");
                saveSession(userInfo);
                saveSession(userInfo) //!!!! Пропуск
                showInfo('User registration successful.');
            }).catch(handleError);
    }

    // 08  LOGIC TO LOGIN USER
    function loginUser(ev) {
        ev.preventDefault();
        let form = $('#loginForm');
        let inputUsername = form.find('input[name="username"]');
        let inputPassword = form.find('input[name="password"]');

        let usernameVal = inputUsername.val();
        let passwordVal = inputPassword.val();

        let regExUsername = /^[a-zA-z]{3,}$/; //username should be at least 3 characters long and should contain only english alphabet letters.
        let regExPass = /^[a-zA-z0-9]{6,}$/; //user‘s password should be at least 6 characters long and should contain only english alphabet letters and digits.

        if (!regExUsername.test(usernameVal)) {
            return showError('A username should be at least 3 characters long and should contain only english alphabet letters.')
        }
        if (!regExPass.test(passwordVal)) {
            return showError('A user‘s password should be at least 6 characters long and should contain only english alphabet letters and digits.')
        }
        auth.login(usernameVal, passwordVal)
            .then((userInfo) => {
                inputUsername.val('');
                inputPassword.val('');
                saveSession(userInfo);
                showInfo('Login successful.');
                //userLoggedIn(); //пропуск не тр   бва да го има ?? или ?
            }).catch(handleError);
    }


    // 09  LOGIC TO LOGOUT USER
    function logoutUser() {
        auth.logout()
            .then(() => {
                sessionStorage.clear();
                showInfo('Logout successful.');
                userLoggedOut();
            }).catch(handleError);
    }


    // 10  LOGIC TO LOAD Catalog ;
    function loadCatalog() {
        postsService.listAllPosts()
            .then((allPosts) => {
                //console.log(allPosts);
                displayAllPosts(allPosts);
            }).catch(handleError);
    }

    //10.3
    function displayAllPosts(allPosts) {
        if (allPosts.length === 0){return showInfo('No posts in database')}
        let catalogContainer = $('#viewCatalog');
        catalogContainer.empty();
        let postsDiv = $('<div>').addClass('posts');
        let index = 1;

        for(let post of allPosts) {
            let postArticle = $('<article>').addClass('post');

            let rank = $('<div>')
                .addClass('col rank')
                .append($('<span>').text(index));

            let thumbnail = $('<div>')
                .addClass('col thumbnail')
                .append($(`<a href="${post.url}"><img src="${post.imageUrl}"></a>`));

            let postContent = $('<div>');
            let title = $('<div>')
                .addClass('title')
                .append($(`<a href="${post.url}">${post.title}</a>`));
            let details = $('<div>');
                let info = $('<div>')
                    .addClass('info').text(`submitted ${calcTime(post._kmd.ect)} ago by ${post.author}`);
            let commentsBtn = $(`<a class="commentsLink" href="#" data-target="${post._id}">comments</a>`).click(loadPostComments);
            let editBtn = $(`<a class="editLink" href="#" data-target="${post._id}">edit</a>`).click(displayEdit);
            let deleteBtn = $(`<a class="deleteLink" href="#" data-target="${post._id}">delete</a>`).click(delComment);
                let controls = $('<div>')
                    .addClass('controls');

                    let ul = $('<ul>').append($('<li>').addClass("action").append(commentsBtn));
            if (post.author === sessionStorage.getItem('username')) {
                ul.append($('<li>')
                    .addClass("action")
                    .append(editBtn)
                    )
                    .append($('<li>')
                        .addClass("action")
                        .append(deleteBtn)
                    )
            }
            controls.append(ul);

            details.append(controls);
            postArticle
                .append(rank)
                .append(thumbnail)
                .append(postContent
                    .append(title)
                    .append(details
                        .append(info)
                    )
                );
            postArticle.appendTo(postsDiv);
            index++;
        }
        catalogContainer.append(postsDiv);
    }

    function delComment(){
        let postId = $(this).attr('data-target');
        //console.log(postId);
        postsService.deletePost(postId)
            .then(() => {
                showInfo('Message deleted.');
                showView('Catalog');
                loadCatalog(); //пак зарежда всички съобщения
            }).catch(handleError);
    }

    function displayEdit() {
        let postId = $(this).attr('data-target');
        $('#btnEditPost').attr("data-target", postId);
        let form = $('#editPostForm');
        //console.log(postId);
        let urlLink = form.find('input[name="url"]');
        let titleLink = form.find('input[name="title"]');
        let thumbnailLink = form.find('input[name="image"]');
        let descriptionLink = form.find('textarea[name="description"]');

        showView('Edit');
        postsService.postDetails(postId)
            .then((myPost)=>{
                //console.log(myPost);
                urlLink.val(myPost.url);
                titleLink.val(myPost.title);
                thumbnailLink.val(myPost.imageUrl);
                descriptionLink.val(myPost.description);
                let author = myPost.author;
                $('#btnEditPost').attr("author", author);
            })
            .catch(handleError);
    }

    function editPost(ev) {
        ev.preventDefault();
        let postId = $(this).attr('data-target');
        let author = $(this).attr('author');
        let form = $('#editPostForm');
        //console.log(postId);
        console.log(author);
        let urlLink = form.find('input[name="url"]');
        let titleLink = form.find('input[name="title"]');
        let thumbnailLink = form.find('input[name="image"]');
        let descriptionLink = form.find('textarea[name="description"]');

        let url = urlLink.val();
        let title = titleLink.val();
        let imageUrl = thumbnailLink.val();
        let description = descriptionLink.val();

        let editedPostData = {
            author,
            title,
            description,
            url,
            imageUrl
        };
        postsService.editPost(postId, author, title, description, url, imageUrl)
            .then(()=>{
                showView('Catalog');
                loadCatalog(); //не се зарежда !!! ?????
                showInfo(`Post ${title} updated.`)
            }
            ).catch(handleError)

    }

    function createPost(ev) {
        ev.preventDefault();
        let form = $('#submitForm');
        let urlLink = form.find('input[name="url"]');
        let titleLink = form.find('input[name="title"]');
        let thumbnailLink = form.find('input[name="image"]');
        let descriptionLink = form.find('textarea[name="comment"]');


        if (urlLink.val() === "" || titleLink.val() === "") {
            showError('Url/Title cannot be empty!');
            return;
        }

        if (!urlLink.val().startsWith('http')) {
            showError('Url should start with http!');
            return;
        }

        let url = urlLink.val();
        let title = titleLink.val();
        let imageUrl = thumbnailLink.val();
        let description = descriptionLink.val();
        let author = sessionStorage.getItem('username');

        postsService.createPost(author, title,description,url,imageUrl)
            .then(()=>{
                urlLink.val('');
                titleLink.val('');
                thumbnailLink.val('');
                descriptionLink.val('');
                showInfo('Post created.');
                showView('Catalog');
                loadCatalog();
            })
            .catch(handleError)
    }

    function loadMyPosts() {
        let username = sessionStorage.getItem('username');
        postsService.listMyPosts(username)
            .then((myPosts) => {
                //console.log(myPosts);
                displayMyPosts(myPosts);
            }).catch(handleError);
    }

    function displayMyPosts(myPosts) {
        if (myPosts.length === 0){return showInfo('No posts in database')}
        let catalogContainer = $('#viewMyPosts');
        catalogContainer.empty();
        let postsDiv = $('<div>').addClass('posts post-content');
        postsDiv.append($('<h1>Your Posts</h1>'));
        let index = 1;

        for(let post of myPosts) {
            let postArticle = $('<article>').addClass('post');

            let rank = $('<div>')
                .addClass('col rank')
                .append($('<span>').text(index));

            let thumbnail = $('<div>')
                .addClass('col thumbnail')
                .append($(`<a href="${post.url}"><img src="${post.imageUrl}"></a>`));

            let postContent = $('<div>');
            let title = $('<div>')
                .addClass('title')
                .append($(`<a href="${post.url}">${post.title}</a>`));
            let details = $('<div>');
            let info = $('<div>')
                .addClass('info').text(`submitted ${calcTime(post._kmd.ect)} ago by ${post.author}`);
            let commentsBtn = $(`<a class="commentsLink" href="#" data-target="${post._id}">comments</a>`).click(loadPostComments);
            let editBtn = $(`<a class="editLink" href="#" data-target="${post._id}">edit</a>`).click(displayEdit);
            let deleteBtn = $(`<a class="deleteLink" href="#" data-target="${post._id}">delete</a>`).click(delComment);
            let controls = $('<div>')
                .addClass('controls')
                .append($('<ul>')
                    .append($('<li>')
                        .addClass("action")
                        .append(commentsBtn)
                    )
                    .append($('<li>')
                        .addClass("action")
                        .append(editBtn)
                    )
                    .append($('<li>')
                        .addClass("action")
                        .append(deleteBtn)
                    )
                );
            if (post.author === sessionStorage.getItem('username')) {
                details.append(controls);
            }
            postArticle
                .append(rank)
                .append(thumbnail)
                .append(postContent
                    .append(title)
                    .append(details
                        .append(info)
                    )
                );
            postArticle.appendTo(postsDiv);
            index++;
        }
        catalogContainer.append(postsDiv);
    }


    function loadPostComments(ev) {
        ev.preventDefault();
        let postId = $(this).attr('data-target');
        postsService.postDetails(postId)
            .then((postDetails)=>{
                displayPostDetails(postDetails);
                getComments(postDetails);
                showView('Comments')
            }).catch(handleError)

    }

    function displayPostDetails(post) {

        let commentsContainer = $('#viewComments');
        let postDiv = $('#listedPost');
        postDiv.empty();

            let thumbnail = $('<div>')
                .addClass('col thumbnail')
                .append($(`<a href="${post.url}"><img src="${post.imageUrl}"></a>`));

            let postContent = $('<div>').addClass('post-content');
            let title = $('<div>')
                .addClass('title')
                .append($(`<a href="${post.url}">${post.title}</a>`));
            let details = $('<div>').append($('<p>').text(`${post.description}`));
            let info = $('<div>')
                .addClass('info').text(`submitted ${calcTime(post._kmd.ect)} ago by ${post.author}`);

            let editBtn = $(`<a class="editLink" href="#" data-target="${post._id}">edit</a>`).click(displayEdit);
            let deleteBtn = $(`<a class="deleteLink" href="#" data-target="${post._id}">delete</a>`).click(delComment);
            let controls = $('<div>')
                .addClass('controls')
                .append($('<ul>')
                    .append($('<li>')
                        .addClass("action")
                        .append(editBtn)
                    )
                    .append($('<li>')
                        .addClass("action")
                        .append(deleteBtn)
                    )
                );

                details.append(controls);

            postDiv
                .append(thumbnail)
                .append(postContent
                    .append(title)
                    .append(details
                        .append(info)
                    )
                );
            postDiv.append($('</div>').addClass('clear'));

        $(`#btnPostComment`).attr('data-id', post._id).attr('data-author', post.author);
/*        let btnPostComment = $(`<input type="submit" value="Add Comment" id="btnPostComment" data-target="${post._id}" data-author ="${post.author}">`).click(addComment);
        commentsContainer.append($(` <div class="post post-content">`).append($(`<form id="commentForm">
                               <label>Comment</label>
                                <textarea name="content" type="text"></textarea>
                            </form>`).append(btnPostComment)
        ))*/


    }
    
    function getComments(postDetails) {
        let postId = postDetails._id;
        commentsService.listPostComments(postId)
            .then((comments)=>{
                displayComments(comments)
            }).catch(handleError)
    }
    function displayComments(comments) {
        let commentsContainer = $('#viewComments');
        for (let comment of comments) {
            if (sessionStorage.getItem('username') === comment.author){
                commentsContainer
                    .append($('<article class="post post-content">')
                        .append($('<p>').text(`${comment.content}`))
                        .append($('<div>')
                            .addClass('info').text(`submitted ${calcTime(comment._kmd.ect)} ago by ${comment.author}`)
                            .append($('<a href="#" class="deleteLink">delete</a>'))
                        )
                    )
            } else{
                commentsContainer
                    .append($('<article class="post post-content">')
                        .append($('<p>').text(`${comment.content}`))
                        .append($('<div>')
                            .addClass('info').text(`submitted ${calcTime(comment._kmd.ect)} ago by ${comment.author}`)
                        )
                    )
            }

        }
    }

    function addComment(ev) {
        ev.preventDefault();
        let postId = $(this).attr('data-id');
        let author = $(this).attr('data-author');
        let content = $('#commentForm').find('textarea[name="content"]');
        let contentVal = content.val();

        commentsService.createComment(postId, contentVal, author).then(()=>{
            showInfo('Comment created.');
            content.val('');
            getComments();
            showView('Comments')
        })
    }
















    // 11  LOGIC TO LOAD SENT MESSAGES
    function loadSentMessages() {
        let username = sessionStorage.getItem('username');
        postsService.loadArchiveMessages(username)
            .then((myMessages) => {
                displayArchivedMessages(myMessages);
            }).catch(handleError);
    }
    // 11.3
    function displayArchivedMessages(myMessages) {
        let messagesContainer = $('#sentMessages');
        messagesContainer.empty();
        let messagesTable = $('<table>');
        messagesTable.append($('<thead>')
            .append($('<tr>')
                .append('<th>To</th>')
                .append('<th>Message</th>')
                .append('<th>Date Sent</th>')
                .append('<th>Actions</th>')));
        let tableBody = $('<tbody>');

        for(let msg of myMessages) {
            let tableRow = $('<tr>');
            let recipient = msg['recipient_username'];
            let msgText = msg['text'];
            let msgDate = formatDate(msg['_kmd']['lmt']);
            let deleteBtn = $(`<button value="${msg._id}">Delete</button>`)
                .click(deleteMsg);
            tableRow.append($('<td>').text(recipient));
            tableRow.append($('<td>').text(msgText));
            tableRow.append($('<td>').text(msgDate));
            tableRow.append($('<td>').append(deleteBtn));
            tableBody.append(tableRow);
        }

        messagesTable.append(tableBody);
        messagesContainer.append(messagesTable);
    }

    // 11.4 DELETE A MESSAGE
    function deleteMsg() {
        let messageId = $(this).val();
        //console.log(messageId);
        postsService.deleteMessage(messageId)
            .then(() => {
                showInfo('Message deleted.');
                loadSentMessages(); //пак зарежда всички съобщения
            }).catch(handleError);
    }


    // 12 SEND MESSAGE FORM
    function loadAllUsers() {
        postsService.loadAllUsers()
            .then((allUsers) => {
                displayUsersInList(allUsers);
            })
    }

    function displayUsersInList(allUsers) {
        let usersContainer = $('#msgRecipientUsername');
        usersContainer.empty();
        for(let user of allUsers) {
            let username = user['username'];
            let fullName = formatSender(user['name'], username);
            if(username !== sessionStorage.getItem('username')){// за да не пращаме на себе си съобщения
                usersContainer.append($(`<option value="${username}">${fullName}</option>`))
            }
        }
    }

    //12.4 SEND MESSAGE FORM
    function sendMessage(ev) {
        ev.preventDefault();
        let rUsernameInput = $('#msgRecipientUsername');
        let mTextInput = $('#msgText');
        let senderName = sessionStorage.getItem('name');
        let senderUsername = sessionStorage.getItem('username');
        let recipientUsername = rUsernameInput.val();
        let msgText = mTextInput.val();

        postsService.sendMessage(senderUsername, senderName, recipientUsername, msgText)
            .then(() => {
                mTextInput.val('');
                showInfo('Messaged sent.');
                showView('ArchiveSent');
                loadSentMessages();
            }).catch(handleError);
    }





////// Готови функции;

    // Handle notifications
    $(document).on({
        ajaxStart: () => $("#loadingBox").show(),
        ajaxStop: () => $('#loadingBox').fadeOut()
    });

    function saveSession(userInfo) {
        let userAuth = userInfo._kmd.authtoken;
        sessionStorage.setItem('authtoken', userAuth);
        let userId = userInfo._id;
        sessionStorage.setItem('userId', userId);
        let username = userInfo.username;
        sessionStorage.setItem('username', username);
        /*let name = userInfo['name'];
        sessionStorage.setItem('name', name);*/
        userLoggedIn();
    }

    function handleError(reason) {
        showError(reason.responseJSON.description);
    }

    function showError(message) {
        let errorBox = $('#errorBox');
        errorBox.text(message);
        errorBox.show();
        setTimeout(() => errorBox.fadeOut(), 3000);
    }

    function showInfo(message) {
        let infoBox = $('#infoBox');
        infoBox.text(message);
        infoBox.show();
        setTimeout(() => infoBox.fadeOut(), 3000);
    }

    //Helper function

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