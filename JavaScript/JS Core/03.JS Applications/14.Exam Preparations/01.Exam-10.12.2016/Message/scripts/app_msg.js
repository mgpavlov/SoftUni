/*
function startApp(){
    showView('AppHome');
    /!*(() => {
        $('header').find('a[data-target]').click(navigateTo);
        $('#formRegister').submit(registerUser);
        $('#formLogin').submit(loginUser);
        $('#linkMenuLogout').click(logoutUser);
        $('#formSendMessage').submit(sendMessage);
        $('#linkUserHomeMyMessages').click(() => {
            showView('MyMessages');
            loadReceivedMessages();
        });

        $('#linkUserHomeSendMessage').click(() => {
            showView('SendMessage');
            loadAllUsers();
        });

        $('#linkUserHomeArchiveSent').click(() => {
            showView('ArchiveSent');
            loadSentMessages();
        });

        $('#linkMenuMyMessages').click(loadReceivedMessages);
        $('#linkMenuArchiveSent').click(loadSentMessages);
        $('#linkMenuSendMessage').click(loadAllUsers);
        $('.notification').click(function() {
            $(this).hide();
        })
    })();*!/
    let loggedInUser = $('#spanMenuLoggedInUser');
    let menu = $('#menu');

    userLogout();
    function userLoggedIn() {
        loggedInUser.text(`Welcome, ${localStorage.getItem('username')}!`);
        loggedInUser.show();
        $('#linkMenuAppHome').hide();
        $('#linkMenuLogin').hide();
        $('#linkMenuRegister').hide();
    }
    function userLogout() {
        loggedInUser.text('');
        loggedInUser.hide();
        menu.find('a').hide();
        $('#linkMenuAppHome').show();
        $('#linkMenuLogin').show();
        $('#linkMenuRegister').show();
    }


    $('.notification').click((e)=>{
        $(e.target).hide();
    });
    //1. в html, скриваме всичко
    //1.1. скиваме бутоните, на html ред 33,35,37 като добавяме в div след id-то: style="display: none" class="notification";
    //1.2. Правим Функция showView, ако са видими всички секции ги скриваме след което показваме само зададената секция:
    function showView(viewName) {
        $('main > section').hide();
        $('#view' + viewName).show();
    }
    //ако има секций без логика в името:
    /!*function showView(view) {
        $('section').hide();
        switch (view) {
            case 'home':
                $('#viewHome').show();
                break;
            case 'login':
                $('#viewLogin').show();
                break;
            case 'register':
                $('#viewRegister').show();
                break;
            case 'ads':
                loadAds();
                $('#viewAds').show();
                break;
            case 'createAd':
                $('#viewCreateAd').show();
                break;
            case 'editAd':
                $('#viewEditAd').show();
                break;
        }
    }*!/
    //1.3. ако са скрити менютата ги правим видими:
    /!*let menu = $('#menu');
    menu.find('a').show();*!/

    //Attach event listeners
    //2 Показваме section при клик:
    $('#linkMenuAppHome').click(()=>showView('AppHome'));
    $('#linkMenuLogin').click(()=>showView('Login'));
    $('#linkMenuRegister').click(()=>showView('Register'));
    $('#linkMenuUserHome').click(()=>showView('UserHome'));
    $('#linkMenuMyMessages').click(()=>showView('MyMessages'));
    $('#linkMenuArchiveSent').click(()=>showView('ArchiveSent'));
    $('#linkMenuSendMessage').click(()=>showView('SendMessage'));
    //--2

    //3
    let formRegister = $('#formRegister');
    formRegister.submit(registerUser);
    async function registerUser (ev){
        ev.preventDefault();
        let username = formRegister.find('input[name="username"]').val();
        let password = formRegister.find('input[name="password"]').val();
        let name = formRegister.find('input[name="name"]').val();
        try {
            let response = await requester.post('user', '', 'basic', {username, password});
            auth.saveSession(response);
            userLoggedIn();
            showView('UserHome');
            auth.showInfo('User registration successful.');
        }catch (reason) {
            auth.handleError(reason);
        }
    }
    //--3

    //4
    let formLogin = $('#formLogin');
    formLogin.submit(loginUser);
    async function loginUser(ev) {
        ev.preventDefault();
        let username = formLogin.find('input[name="username"]').val();
        let password = formLogin.find('input[name="password"]').val();
        try {
            let response = await requester.post('user', 'login', 'basic', {username, password});
            auth.saveSession(response);
            userLoggedIn();
            showView('UserHome');
            auth.showInfo('Login');
        }catch (reason) {
            auth.handleError(reason);
        }
    }


    //--4

    //13 logout
    $('#linkMenuLogout').click(logout);
    async function logout() {
        try {
            await requester.post('user', '_logout', 'kinvey',{authtoken: localStorage.getItem('authtoken')});
            localStorage.clear(); // Clears all session storage on logout
            userLogout();
            showView('AppHome');
            auth.showInfo('Logged out');
        } catch (reason) {
            auth.handleError(reason);
        }
    }

    //--13

    //3


}*/
function startApp() {
    showView('AppHome');

    // Attach event handlers
    (() => {
        $('header').find('a[data-target]').click(navigateTo);
        $('#formRegister').submit(registerUser);
        $('#formLogin').submit(loginUser);
        $('#linkMenuLogout').click(logoutUser);
        $('#formSendMessage').submit(sendMessage);
        $('#linkUserHomeMyMessages').click(() => {
            showView('MyMessages');
            loadReceivedMessages();
        });

        $('#linkUserHomeSendMessage').click(() => {
            showView('SendMessage');
            loadAllUsers();
        });

        $('#linkUserHomeArchiveSent').click(() => {
            showView('ArchiveSent');
            loadSentMessages();
        });

        $('#linkMenuMyMessages').click(loadReceivedMessages);
        $('#linkMenuArchiveSent').click(loadSentMessages);
        $('#linkMenuSendMessage').click(loadAllUsers);
        $('.notification').click(function() {
            $(this).hide();
        })
    })();

    if(sessionStorage.getItem('authtoken') === null){
        userLoggedOut();
    } else {
        userLoggedIn();
    }

    // SEND MESSAGE FORM
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
            if(username !== sessionStorage.getItem('username')){
                usersContainer.append($(`<option value="${username}">${fullName}</option>`))
            }
        }
    }

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


    // LOGIC TO LOAD SENT MESSAGES
    function loadSentMessages() {
        let username = sessionStorage.getItem('username');
        postsService.loadArchiveMessages(username)
            .then((myMessages) => {
                displayArchivedMessages(myMessages);
            }).catch(handleError);
    }

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

    // DELETE A MESSAGE
    function deleteMsg() {
        let messageId = $(this).val();
       postsService.deleteMessage(messageId)
            .then(function del() {
                showInfo('Message deleted.');
                loadSentMessages();
            }).catch(handleError);
    }

    // LOGIC TO LOAD RECEIVED MESSAGES
    function loadReceivedMessages() {
        let username = sessionStorage.getItem('username');
        postsService.loadMyMessages(username)
            .then((myMessages) => {
                displayAllMessages(myMessages);
            }).catch(handleError);
    }

    function displayAllMessages(myMessages) {
        let messagesContainer = $('#myMessages');
        messagesContainer.empty();
        let messagesTable = $('<table>');
        messagesTable.append($('<thead>')
            .append($('<tr>')
                .append('<th>From</th>')
                .append('<th>Message</th>')
                .append('<th>Date Received</th>')));
        let tableBody = $('<tbody>');

        for(let msg of myMessages) {
            let tableRow = $('<tr>');
            let sender = formatSender(msg['sender_name'], msg['sender_username']);
            let msgText = msg['text'];
            let msgDate = formatDate(msg['_kmd']['lmt']);
            tableRow.append($('<td>').text(sender));
            tableRow.append($('<td>').text(msgText));
            tableRow.append($('<td>').text(msgDate));
            tableBody.append(tableRow);
        }

        messagesTable.append(tableBody);
        messagesContainer.append(messagesTable);
    }

    // LOGIC TO LOGOUT USER
    function logoutUser() {
        auth.logout()
            .then(() => {
                sessionStorage.clear();
                showInfo('Logout successful.');
                userLoggedOut();
            }).catch(handleError);
    }

    // LOGIC TO LOGIN USER
    function loginUser(ev) {
        ev.preventDefault();
        let inputUsername = $('#loginUsername');
        let inputPassword = $('#loginPasswd');

        let usernameVal = inputUsername.val();
        let passwdVal = inputPassword.val();

        auth.login(usernameVal, passwdVal)
            .then((userInfo) => {
                saveSession(userInfo);
                inputUsername.val('');
                inputPassword.val('');
                showInfo('Login successful.');
            }).catch(handleError);
    }

    // LOGIC TO REGISTER USER
    function registerUser(ev) {
        ev.preventDefault();
        let registerUsername = $('#registerUsername');
        let registerName = $('#registerName');
        let registerPassword = $('#registerPasswd');

        let usernameVal = registerUsername.val();
        let nameVal = registerName.val();
        let passVal = registerPassword.val();

        auth.register(usernameVal, passVal, nameVal)
            .then((userInfo) => {
                saveSession(userInfo);
                registerUsername.val("");
                registerName.val("");
                registerPassword.val("");
                showInfo('User registration successful.');
            }).catch(handleError);
    }

    function navigateTo() {
        let viewName = $(this).attr('data-target');
        showView(viewName);
    }

    // Shows one view/section at a time
    function showView(viewName) {
        $('main > section').hide();
        $('#view' + viewName).show();
    }

    function userLoggedOut() {
        $('.anonymous').show();
        $('.useronly').hide();
        $('#spanMenuLoggedInUser').text('');
        showView('AppHome');
    }

    function userLoggedIn() {
        $('.anonymous').hide();
        $('.useronly').show();
        let username = sessionStorage.getItem('username');
        $('#spanMenuLoggedInUser').text(`Welcome, ${username}!`);
        $('#viewUserHomeHeading').text(`Welcome, ${username}!`);
        showView('UserHome');
    }

    function saveSession(userInfo) {
        let userAuth = userInfo._kmd.authtoken;
        sessionStorage.setItem('authtoken', userAuth);
        let userId = userInfo._id;
        sessionStorage.setItem('userId', userId);
        let username = userInfo.username;
        sessionStorage.setItem('username', username);
        sessionStorage.setItem('name', userInfo['name']);
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

    function formatDate(dateISO8601) {
        let date = new Date(dateISO8601);
        if (Number.isNaN(date.getDate()))
            return '';
        return date.getDate() + '.' + padZeros(date.getMonth() + 1) +
            "." + date.getFullYear() + ' ' + date.getHours() + ':' +
            padZeros(date.getMinutes()) + ':' + padZeros(date.getSeconds());

        function padZeros(num) {
            return ('0' + num).slice(-2);
        }
    }

    function formatSender(name, username) {
        if (!name)
            return username;
        else
            return username + ' (' + name + ')';
    }


    // Handle notifications
    $(document).on({
        ajaxStart: () => $("#loadingBox").show(),
        ajaxStop: () => $('#loadingBox').fadeOut()
    });
}