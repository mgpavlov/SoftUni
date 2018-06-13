$(() => {
    showView('AppHome');

    // Attach event handlers//01
    (() => {
        $('header').find('a[data-target]').click(navigateTo);// 2.1 скриваме
        $('#formRegister').submit(registerUser);// 07.1
        $('#formLogin').submit(loginUser);// 08.1
        $('#linkMenuLogout').click(logoutUser);// 09.1
        $('#formSendMessage').submit(sendMessage);//12.4
        $('#linkUserHomeMyMessages').click(() => {//***допълнителни менюта в UserHome
            showView('MyMessages');
            loadReceivedMessages();//10.1
        });

        $('#linkUserHomeSendMessage').click(() => {//***допълнителни менюта в UserHome
            showView('SendMessage');
            loadAllUsers(); //12.1
        });

        $('#linkUserHomeArchiveSent').click(() => {//***допълнителни менюта в UserHome
            showView('ArchiveSent');
            loadSentMessages(); //11.1
        });

        $('#linkMenuMyMessages').click(loadReceivedMessages);//10.2
        $('#linkMenuArchiveSent').click(loadSentMessages); //11.2
        $('#linkMenuSendMessage').click(loadAllUsers);//12.2

        // 13 да скрива box-овете при клик
        $('.notification').click(function() {
            $(this).hide();
        })
    })();

    //02
    function navigateTo() {
        let viewName = $(this).attr('data-target');
        showView(viewName);
    }
    //03 Shows one view/section at a time
    function showView(viewName) {
        $('main > section').hide();
        $('#view' + viewName).show();
    }

    //04
    function userLoggedOut() {
        $('.anonymous').show();
        $('.useronly').hide();
        $('#spanMenuLoggedInUser').text('');
        showView('AppHome');
    }

    //05
    function userLoggedIn() {
        $('.anonymous').hide();
        $('.useronly').show();
        let username = sessionStorage.getItem('username');
        $('#spanMenuLoggedInUser').text(`Welcome, ${username}!`);
        $('#viewUserHomeHeading').text(`Welcome, ${username}!`);
        showView('UserHome');
    }
    //06
    if(sessionStorage.getItem('authtoken') === null){
        userLoggedOut();
    } else {
        userLoggedIn();
    }

    // 07   LOGIC TO REGISTER USER
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

    // 08  LOGIC TO LOGIN USER
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


    // 09  LOGIC TO LOGOUT USER
    function logoutUser() {
        auth.logout()
            .then(() => {
                sessionStorage.clear();
                showInfo('Logout successful.');
                userLoggedOut();
            }).catch(handleError);
    }


    // 10  LOGIC TO LOAD RECEIVED MESSAGES За да има съобщения през Postman пращаме съобщения;
    function loadReceivedMessages() {
        let username = sessionStorage.getItem('username');
        messagesService.loadMyMessages(username)
            .then((myMessages) => {
                //console.log(myMessages); за да видим как да вземем данните
                displayAllMessages(myMessages);
            }).catch(handleError);
    }
    //10.3
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
            let sender = formatSender(msg['sender_name'], msg['sender_username']); //използваме помощна функция formatSender
            let msgText = msg['text']; // 125 ред /console.log(myMessages); за да видим как да вземем данните
            let msgDate = formatDate(msg['_kmd']['lmt']);
            tableRow.append($('<td>').text(sender));
            tableRow.append($('<td>').text(msgText));
            tableRow.append($('<td>').text(msgDate));
            tableBody.append(tableRow);
        }
        messagesTable.append(tableBody);
        messagesContainer.append(messagesTable);
    }


    // 11  LOGIC TO LOAD SENT MESSAGES
    function loadSentMessages() {
        let username = sessionStorage.getItem('username');
        messagesService.loadArchiveMessages(username)
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
        messagesService.deleteMessage(messageId)
            .then(() => {
                showInfo('Message deleted.');
                loadSentMessages(); //пак зарежда всички съобщения
            }).catch(handleError);
    }


    // 12 SEND MESSAGE FORM
    function loadAllUsers() {
        messagesService.loadAllUsers()
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

        messagesService.sendMessage(senderUsername, senderName, recipientUsername, msgText)
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
        let name = userInfo['name'];
        sessionStorage.setItem('name', name);
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

    //Помощни функции по задание:
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

})