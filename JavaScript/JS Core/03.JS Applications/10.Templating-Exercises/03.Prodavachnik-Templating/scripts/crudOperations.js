//Define all app settings:
const APP_KEY = 'kid_r1Jm48Aqf';
const APP_SECRET = 'e4b9f1944c39443a86e306a65617eff7';
const BASE_URL = 'https://baas.kinvey.com';
const APP_DATA = '/appdata';
const APP_USER = '/user';
const DB = '/posts';
const AUTH_HEADERS = { 'Authorization': 'Basic ' + btoa(APP_KEY + ":" + APP_SECRET) };

//DOM Elements:


let request = (function () {
    function login(userData, successfulExecution, handleError) {
        let req = {
            url: BASE_URL + APP_USER + '/' + APP_KEY + '/login',
            method: 'POST',
            contentType: 'application/json',
            headers: AUTH_HEADERS,
            data: JSON.stringify(userData),
            success: successfulExecution,
            error: handleError
        };
        return $.ajax(req);
    }

    function logout(successfulExecution, handleError) {
        let req = {
            url: BASE_URL + APP_USER + '/' + APP_KEY + '/_logout',
            method: 'POST',
            contentType: 'application/json',
            headers: {
                'Authorization': 'Kinvey ' + sessionStorage.getItem('authToken')
            },
            success: successfulExecution,
            error: handleError
        };
        return $.ajax(req);
    }

    function register(userData, successfulExecution, handleError) {
        let req = {
            url: BASE_URL + APP_USER + '/' + APP_KEY,
            method: 'POST',
            headers: AUTH_HEADERS,
            contentType: 'application/json',
            data: JSON.stringify(userData),
            success: successfulExecution,
            error: handleError
        };

        return $.ajax(req);
    }

    function listAdd(sessionId, successfulExecution, handleError) {
        let req = {
            url: BASE_URL + APP_DATA + '/' + APP_KEY + DB,
            method: 'GET',
            headers: { 'Authorization': 'Kinvey ' + sessionId },
            contentType: 'application/json',
            success: successfulExecution,
            error: handleError
        };

        return $.ajax(req);
    }

    function createAdd(userData, sessionId, successfulExecution, handleError) {
        let req = {
            url: BASE_URL + APP_DATA + '/' + APP_KEY + DB,
            method: 'POST',
            headers: { 'Authorization': 'Kinvey ' + sessionId },
            contentType: 'application/json',
            data: JSON.stringify(userData),
            success: successfulExecution,
            error: handleError
        };
        return $.ajax(req);
    }

    function deleteAdd(sessionId, Id, successfulExecution, handleError) {
        let req = {
            url: BASE_URL + APP_DATA + '/' + APP_KEY + DB + '/' + Id,
            method: 'DELETE',
            headers: { 'Authorization': 'Kinvey ' + sessionId },
            contentType: 'application/json',
            success: successfulExecution,
            error: handleError
        };
        return $.ajax(req);
    }

    function updateAdd(sessionId, id, userData, successfulExecution, handleError) {
        let req = {
            url: BASE_URL + APP_DATA + '/' + APP_KEY + DB + '/' + id,
            method: 'PUT',
            headers: { 'Authorization': 'Kinvey ' + sessionId },
            contentType: 'application/json',
            data: JSON.stringify(userData),
            success: successfulExecution,
            error: handleError
        };
        return $.ajax(req);
    }

    return { login, logout, register, listAdd, createAdd, deleteAdd, updateAdd }
})();


function displayAdvert(ad) {
    $('#viewDetailsAd').html($('<div>').append(
        $('<img>').attr('src', ad.uri),
        $('<br>'),
        $('<label>').text('Title:'),
        $('<h1>').text(ad.title),
        $('<label>').text('Description:'),
        $('<p>').text(ad.description),
        $('<label>').text('Publisher:'),
        $('<div>').text(ad.publisher),
        $('<label>').text('Date:'),
        $('<div>').text(ad.date)
    ));

    showView('viewDetailsAd');

let counterIncr = ad.counter*1 +1

let session = sessionStorage.getItem('authToken');
let adData = {
    title: ad.title,
    description: ad.description,
    publisher: sessionStorage.getItem('username'),
    date: ad.date,
    price: ad.price,
    counter: counterIncr,
    uri: ad.uri

};
    request.updateAdd(session, ad._id, adData, counterIncrease, handleError)
function counterIncrease(response) {
}

}

function showError(description) {
    let errBox = $('#errorBox').on('click', () => errBox.hide());
    errBox.text('Error: ' + description);
    errBox.show();
    setTimeout(function () {
        $('#errorBox').fadeOut()
    }, 3000)
}

function showSuccess(desc) {
    let infoBox = $('#infoBox').on('click', () => infoBox.hide());
    infoBox.text(desc);
    infoBox.show();
    setTimeout(function () {
        $('#infoBox').fadeOut()
    }, 2000)
}

function handleError(response) {
    let errorMsg = JSON.stringify(response)
    if (response.readyState === 0)
        errorMsg = "Cannot connect due to network error."
    if (response.responseJSON && response.responseJSON.description)
        errorMsg = response.responseJSON.description
    showError(errorMsg);
    displayAllHiddenLinks();
}

function loginUser() {
    showView('formLogin');
    let formLogin = $('#formLogin');

    let userData = {
        username: formLogin.find('input[name=username]').val(),
        password: formLogin.find('input[name=passwd]').val()
    };

    request.login(userData, loginSucceed, handleError);

    function loginSucceed(resp) {
        $('#loggedInUser').val()
        $('#loggedInUser').show();
        showSuccess('Login Succeed');
        sessionStorage.setItem('authToken', resp._kmd.authtoken);
        sessionStorage.setItem('username', resp.username);
        $('#loggedInUser').text('Welcome, ' + resp.username);
        sessionStorage.setItem('_id', resp._id);
        formLogin.trigger('reset');
        displayAllHiddenLinks()
    }
}

function logoutUser() {
    request.logout(logoutSucceed, handleError);
    function logoutSucceed(resp) {
        sessionStorage.clear();
        $('#loggedInUser').text();
        $('#loggedInUser').hide();
        showView('viewHome');
        displayAllHiddenLinks();
    }
}

function registerUser() {
    showView('formRegister');
    let registerForm = $('#formRegister');
    let username = registerForm.find('input[name=username]').val();
    let password = registerForm.find('input[name=passwd]').val();
    if (username.length === 0) {
        showError('Username cannot be empty!');
        return;
    }
    if (password.length === 0) {
        showError('Password cannot be empty!')
    }
    let userData = { username, password };
    request.register(userData, registrationSucceed, handleError);

    function registrationSucceed(resp) {
        showSuccess('Registration Succeed');
        sessionStorage.setItem('authToken', resp._kmd.authtoken);
        sessionStorage.setItem('_id', resp._id);
        registerForm.trigger('reset');
        $('#viewRegister').hide();
        displayAllHiddenLinks()
    }

}

function showAdds() {
    let table = $('#ads').find('table').empty();
    request.listAdd(sessionStorage.getItem('authToken'), retrieveSucceed, handleError);
    showView('viewAds');
    function retrieveSucceed(resp) {
        resp.sort((a,b) => Number(b.counter) - Number(a.counter) );
        table.append($('<tr>')
            .append('<th>Title</th><th>Description</th><th>Publisher</th><th>Date Published</th><th>Price</th><th>Actions</th>'));
        for (let row of resp) {
            let currentRow = $('<tr>')
            currentRow
                .append(`<td>${row.title}</td>`)
                .append(`<td>${row.description}</td>`)
                .append(`<td>${row.publisher}</td>`)
                .append(`<td>${row.date}</td>`)
                .append(`<td>${row.price}</td>`)

            let td = $('<td>');
            if (sessionStorage.getItem('_id') === row._acl.creator) {
                td.append($(`<a href="#">[Delete]</a>`).on('click', function () { deleteAdd(row) }))
                td.append($(`<a href="#">[Edit]</a>`).on('click', function () { editAdd(row) }))
            }
            td.append($(`<a href="#">[Read More]</a>`).on('click', function () { displayAdvert(row) }))
            currentRow.append(td);
            table.append(currentRow)
        }
    }
}

function deleteAdd(addv) {
    request.deleteAdd(sessionStorage.getItem('authToken'), addv._id, deleteSucceed, handleError);
    function deleteSucceed(resp) {
        showAdds()
    }
}

function editAdd(addv) {

    let req = {
        url: BASE_URL + APP_DATA + '/' + APP_KEY + DB + '/' + addv._id,
        method: 'GET',
        headers: { 'Authorization': 'Kinvey ' + sessionStorage.getItem('authToken') },
        contentType: 'application/json',
        success: retrieveAddData,
        error: handleError
    };

    return $.ajax(req);
    function retrieveAddData(resp) {

        let createForm = $('#formEditAd');
        createForm.find('input[name=title]').val(resp.title);
        createForm.find('textarea[name=description]').val(resp.description);
        createForm.find('input[name=price]').val(resp.price);
        createForm.find('input[name=id]').val(addv._id + '__' + resp.counter);
        createForm.find('input[name=image]').val(addv.uri);
        showView('viewEditAd');
    }
}

function finalEditAdd() {

    let session = sessionStorage.getItem('authToken');
    let idData = $('#formEditAd input[name=id]').val().split('__');
    let id = idData[0]
    let counter = Number(idData[1]);
    let date = new Date();
    let result = "" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()
    let form = $('#formEditAd')
    let adData = {
        title: $('#formEditAd input[name=title]').val(),
        description: $('#formEditAd textarea[name=description]').val(),
        publisher: sessionStorage.getItem('username'),
        date: result,
        price: Number(Number($('#formEditAd input[name=price]').val()).toFixed(2)),
        counter: counter,
        uri: $('#formEditAd input[name=image]').val()

    };
    request.updateAdd(session, id, adData, editAdSuccess, handleError)
    function editAdSuccess(response) {
        showAdds()
        form.trigger('reset');
        displayAllHiddenLinks()
    }
}

function createAdd() {
    let createForm = $('#formCreateAd');
    let title = createForm.find('input[name=title]').val();
    let description = createForm.find('textarea[name=description]').val();
    let price = createForm.find('input[name=price]').val();
    let uri = createForm.find('input[name=image]').val();

    let date = new Date();
    let result = "" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()
    let userData = {
        title: title,
        description: description,
        publisher: sessionStorage.getItem('username'),
        date: result,
        price: Number(Number(price).toFixed(2)),
        counter: 0,
        uri: uri
    }


    request.createAdd(userData, sessionStorage.getItem('authToken'), createSucceed, handleError);

    function createSucceed(resp) {
        createForm.trigger('reset');
        showAdds()
    }
}