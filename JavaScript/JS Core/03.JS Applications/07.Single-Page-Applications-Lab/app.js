$(() => {
    $('#linkLogin').show();
    $('#linkRegister').show();
    $('#linkBooks').hide();
    $('#linkCreate').hide();
    $('#linkLogout').hide();

    function greetingUser() {
        let username = localStorage.getItem('username');
        if(username !== null){
            $('#loggedInUser').text(`Welcome, ${username}!`);
            $('#linkLogin').hide();
            $('#linkRegister').hide();
            $('#linkBooks').show();
            $('#linkCreate').show();
            $('#linkLogout').show();

        }else {
            $('#loggedInUser').text('');
            $('#linkLogin').show();
            $('#linkRegister').show();
            $('#linkBooks').hide();
            $('#linkCreate').hide();
            $('#linkLogout').hide();
        }
    }

    const appKey = 'kid_Hkfgw8o9M';
    const appSecret = '9f4b7dbfc9b542b6864e56683c162cb8';
    const baseUrl = 'https://baas.kinvey.com/';

    $('#linkHome').click(() => showView('home'));
    $('#linkLogin').click(() => showView('login'));
    $('#linkRegister').click(() => showView('register'));
    $('#linkBooks').click(() => showView('books'));
    $('#linkCreate').click(() => showView('create'));
    $('#linkLogout').click(() => logout());

    /*$('form > input[type=submit]').click(e=>e.preventDefault());
    // хващаме всички форми от страницата и в тях хващаме всички input от type = submit;
    // при клик му слагаме евент който да предотврати стандартното поведение на бутона, т.е. да не рефрешва страницата;*/

    $('#viewLogin').find('form').submit(login);
    $('#viewRegister').find('form').submit(register);
    $('#viewCreate').find('form').submit(createBook);

    $(document).on({
        ajaxStart: function() { $("#loadingBox").show() },
        ajaxStop: function() { $("#loadingBox").hide() }
    });

    $('#infoBox').click((ev)=> $(ev.target).hide());
    $('#errorBox').click((ev)=> $(ev.target).hide());

    function showInfo(message) {
        $('#infoBox').text(message);
        $('#infoBox').show();
        setTimeout(()=> $('#infoBox').fadeOut(), 1500)
    }
    function showError(message) {
        $('#errorBox').text(message);
        $('#errorBox').show();
    }


    function showView(name) {
        $('section').hide();
        switch (name) {
            case 'home':
                $('#viewHome').show();
                break;
            case 'login':
                $('#viewLogin').show();
                break;
            case 'register':
                $('#viewRegister').show();
                break;
            case 'books':
                getBooks();
                $('#viewBooks').show();
                break;
            case 'create':
                $('#viewCreate').show();
                break;
            case 'logout':
                $('#viewHome').show();
                break;
            case 'edit':
                $('#viewEdit').show();
                break;
        }
    }

   /* function setStorage(data) {
        localStorage.setItem('authtoken', data._kmd.authtoken);
        localStorage.setItem('username', data.username);
        localStorage.setItem('userId', data._id);
        $('#loggedInUser').text(`Welcome, ${data.username}!`);
        greetingUser();
        showView('books');
    }*/

    function login(e) {
        e.preventDefault(); // при клик му слагаме евент който да предотврати стандартното поведение на бутона, т.е. да не рефрешва страницата;*/
        let username = $('#inputUsername').val();
        let password = $('#inputPassword').val();
        let req = {
            url: baseUrl + 'user/' + appKey + '/login',
            method: 'POST',
            headers: {
                'Authorization': 'Basic '+btoa(appKey+':'+appSecret),
                    'Content-Type': 'application/json'
                },
            data: JSON.stringify({
                username: username,
                password: password
            })
        };
        $.ajax(req).then(loginSuccess).catch(handleError);

        function loginSuccess(data) {
            showInfo('Loading successful');
            localStorage.setItem('authtoken', data._kmd.authtoken);
            localStorage.setItem('username', data.username);
            localStorage.setItem('userId', data._id);
            greetingUser();
            showView('books');
        }
    }

    function register(e) {
        e.preventDefault(); // при клик му слагаме евент който да предотврати стандартното поведение на бутона, т.е. да не рефрешва страницата;*/
        let username = $('#inputNewUsername').val();
        let password = $('#inputNewPassword').val();
        let confirmPassword = $('#confirmNewPassword').val();

        if(password.length === 0){
            showError("Passwords  cannot be empty");
            return;
        }
        if(username.length === 0){
            showError("Username  cannot be empty");
            return;
        }
        if(password !== confirmPassword){
            showError("Passwords don't match");
            return;
        }
        let req = {
            url: baseUrl + 'user/' + appKey,
            method: 'POST',
            headers: {
                'Authorization': 'Basic '+ btoa(appKey+':'+appSecret),
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({
                "username": `${username}`,
                "password": `${password}`
            })
        };
        $.ajax(req).then(registerSuccess).catch(handleError);

        function registerSuccess(data) {
            showInfo('Registration successful');
            localStorage.setItem('authtoken', data._kmd.authtoken);
            localStorage.setItem('username', data.username);
            localStorage.setItem('userId', data._id);
            greetingUser();
            showView('books');
        }
    }
    function logout() {
        let req = {
            url: baseUrl + 'user/' + appKey + '/_logout',
            method: 'POST',
            headers: {
                'Authorization': 'Kinvey '+ localStorage.getItem('authtoken')
            }
        };
        $.ajax(req).then(logoutSuccess).catch(handleError);

        function logoutSuccess(data) {
            localStorage.clear();
            greetingUser();
            showView('home');
        }
    }
    function handleError(error) {
       showError(error.responseJSON.description);
    }

    function createBook() {
        let title = $('#inputNewTitle').val();
        let author = $('#inputNewAuthor').val();
        let description = $('#inputNewDescription').val();

        if(title.length === 0){
            showError("Title  cannot be empty");
            return;
        }
        if(author.length === 0){
            showError("Author  cannot be empty");
            return;
        }
        let req = {
            url: baseUrl + 'appdata/' + appKey + '/books',
            method: 'POST',
            headers: {
                'Authorization': 'Kinvey '+ localStorage.getItem('authtoken'),
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({
                title,
                author,
                description
            })
        };
        $.ajax(req).then(createSuccess).catch(handleError);

        function createSuccess(data) {
            $('#viewCreate').find('form').trigger('reset'); // изчистваме формата
            showInfo('Book is created');
            showView('books')
        }
    }

    function getBooks() {
        let req = {
            url: baseUrl + 'appdata/' + appKey + '/books',
            method: 'GET',
            headers: {
                'Authorization': 'Kinvey '+ localStorage.getItem('authtoken'),
                'Content-Type': 'application/json'
            }
        };
        $.ajax(req).then(displayBooks).catch(handleError);
        function displayBooks (data) {
            let tbody = $('#viewBooks').find('tbody');
            tbody.empty();
            for (let book of data) {
                let editBtn = '';
                let delBtn = '';
                if(book._acl.creator === localStorage.getItem('userId')){
                    editBtn = $('<button>').html('&#9998;').click(()=> editBook(book));
                    delBtn = $('<button>').html('&#10006;').click(()=>deleteBook(book._id));
                }
                let row = $('<tr>');
                row.append(`<td>${book.title}</td>`)
                    .append(`<td>${book.author}</td>`)
                    .append(`<td>${book.description}</td>`)
                    .append($(`<td>`).append(editBtn).append(delBtn))
                    .appendTo(tbody);
            }
        }
        function deleteBook(id){
            let req = {
                url: baseUrl + 'appdata/' + appKey + '/books/' + id,
                method: 'DELETE',
                headers: {
                    'Authorization': 'Kinvey '+ localStorage.getItem('authtoken'),
                    'Content-Type': 'application/json'
                }
            };
            $.ajax(req).then(deleteBookSuccess).catch(handleError);
            function deleteBookSuccess(data) {
                showInfo('Book deleted');
                showView('books');
                $(this).parent().clear()
            }
        }
        function editBook(book) {
            showView('edit');
            $('#inputTitle').val(book.title);
            $('#inputAuthor').val(book.author);
            $('#inputDescription').val(book.description);
            
            $('#viewEdit').find('form').submit(edit);
            
            function edit() {
                let bookObj = {
                    "title": $('#inputTitle').val(),
                    "author": $('#inputAuthor').val(),
                    "description": $('#inputDescription').val()
                };
                if(bookObj.title.length === 0){
                    showError("Title  cannot be empty");
                    return;
                }
                if(bookObj.author.length === 0){
                    showError("Author  cannot be empty");
                    return;
                }
                let req = {
                    url: baseUrl + 'appdata/' + appKey + '/books/' + book._id,
                    method: 'PUT',
                    headers: {
                        'Authorization': 'Kinvey '+ localStorage.getItem('authtoken'),
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify(bookObj)
                };
                $.ajax(req).then(editSuccess).catch(handleError);
                
                function editSuccess(data) {
                    showInfo('Book edited');
                    showView('books')
                }
            }
        }
    }
});
