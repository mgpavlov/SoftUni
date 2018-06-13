const URL = 'https://baas.kinvey.com';
const APPKEY = 'kid_HyYycH0qM';
const APPSECRET = 'a7d532cac5874fc89b2962cfe455cdcd';
const AUTH_HEADERS = {'Authorization': `Basic ${btoa(APPKEY + ':' + APPSECRET)}`};

function registerUser() {
    let username = $('#formRegister input[name=username]').val();
    let password = $('#formRegister input[name=passwd]').val();

    $.ajax({
        method: 'POST',
        url: `${URL}/user/${APPKEY}/`,
        headers: AUTH_HEADERS,
        data: {username, password}
    }).then(function (res) {
        sessionStorage.setItem('authToken', res._kmd.authtoken);
        sessionStorage.setItem('username', res.username);
        sessionStorage.setItem('userId', res._id);
        loadMenuLinks();
        showHome();
        showInfoBox('Registration successful!')
    }).catch(handleRegistrationError);
}

function loginUser() {
    let username = $('#formLogin input[name=username]').val();
    let password = $('#formLogin input[name=passwd]').val();

    $.ajax({
        method: 'POST',
        url: `${URL}/user/${APPKEY}/login`,
        headers: AUTH_HEADERS,
        data: {username, password}
    }).then(function (res) {
        sessionStorage.setItem('authToken', res._kmd.authtoken);
        sessionStorage.setItem('username', res.username);
        sessionStorage.setItem('userId', res._id);
        loadMenuLinks();
        showHome();
        showInfoBox('Login successful!');
    }).catch(handleLoginError)
}

function logoutUser() {
    sessionStorage.clear();
    loadMenuLinks();
    showHome();
    showInfoBox('Logout successful!')
}

function createAd() {
    let title = $('#formCreateAd input[name=title]').val();
    let description = $('#formCreateAd textarea[name=description]').val();
    let datePublished = $('#formCreateAd input[name=datePublished]').val();
    let price = Number($('#formCreateAd input[name=price]').val());
    let image = $('#formCreateAd input[name=image]').val();
    let publisher = sessionStorage.getItem('username');

    $.ajax({
        method: 'POST',
        url: `${URL}/appdata/${APPKEY}/ads`,
        headers: {'Authorization': `Kinvey ${sessionStorage.getItem('authToken')}`},
        data: {title, description, datePublished, price, publisher, image}
    }).then(function (res) {
        listAds();
        showInfoBox(`Advertisement ${title} was successfully created!`);
    }).catch(handleError)
}

function deleteAd(ad) {
    $.ajax({
        method: 'DELETE',
        url: `${URL}/appdata/${APPKEY}/ads/${ad._id}`,
        headers: {'Authorization': `Kinvey ${sessionStorage.getItem('authToken')}`}
    }).then(function (res) {
        listAds();
        showInfoBox(`Advertisement ${ad.title} was successfully deleted!`);
    }).catch(handleError);
}

function setupForEdit(ad) {
    $('#formEditAd input[name=id]').val(ad._id);
    $('#formEditAd input[name=publisher]').val(ad.publisher);
    $('#formEditAd input[name=title]').val(ad.title);
    $('#formEditAd textarea[name=description]').val(ad.description);
    $('#formEditAd input[name=price]').val(ad.price);
    $('#formEditAd input[name=datePublished]').val(ad.datePublished);
    $('#formEditAd input[name=image]').val(ad.image);
    showView('viewEditAd');
}

function editAd() {
    let title = $('#formEditAd input[name=title]').val();
    let description = $('#formEditAd textarea[name=description]').val();
    let datePublished = $('#formEditAd input[name=datePublished]').val();
    let price = Number($('#formEditAd input[name=price]').val());
    let publisher = $('#formEditAd input[name=publisher]').val();
    let image = $('#formEditAd input[name=image]').val();
    let id = $('#formEditAd input[name=id]').val();

    $.ajax({
        method: 'PUT',
        url: `${URL}/appdata/${APPKEY}/ads/${id}`,
        headers: {'Authorization': `Kinvey ${sessionStorage.getItem('authToken')}`},
        data: {title, description, datePublished, price, publisher, image}
    }).then(function (res) {
        listAds();
        showInfoBox(`Advertisement ${title} was successfully edited!`);
    }).catch(handleError);
}

function listAds() {
    $('#ads > table tr').each((index, element) => {
        if (index > 0) {
            $(element).remove();
        }
    });

    $.ajax({
        method: 'GET',
        url: `${URL}/appdata/${APPKEY}/ads`,
        headers: {'Authorization': `Kinvey ${sessionStorage.getItem('authToken')}`}
    }).then(displayAds).catch(handleError);

    function displayAds(res) {
        let table = $('#ads > table');

        for (let ad of res) {
            let tr = $('<tr>');
            tr.append($(`<td>${ad.title}</td>`))
                .append($(`<td>${ad.publisher}</td>`))
                .append($(`<td>${ad.description}</td>`))
                .append($(`<td>${ad.price}</td>`))
                .append($(`<td>${ad.datePublished}</td>`));

            let linkTD = $('<td>');
            let readMoreAnchor = $(`<a href="#">[Read More]</a>`).click(function () {
                $('#adTitle').text(ad.title);
                $('#adDatePublished').text(ad.datePublished);
                $('#adDescription').text(ad.description);
                $('#adPublisher').text(ad.publisher);
                console.log(ad);
                if (ad.image === '') {
                    $('#adImage').html(`<h4><i>No Image Added</i></h4>`);
                }
                else
                    $('#adImage').html($(`<img src="${ad.image}"/>`));
                showView('viewSingleAd');

            });
            linkTD.append(readMoreAnchor);
            if (ad.publisher === sessionStorage.getItem('username')) {
                let editAnchor = $(`<a href="#">[Edit]</a>`).click(function () {
                    setupForEdit(ad);
                });
                let deleteAnchor = $(`<a href="#">[Delete]</a>`).click(function () {
                    deleteAd(ad);
                });

                linkTD.append(editAnchor).append(deleteAnchor);

            }
            tr.append(linkTD);
            table.append(tr);
        }
    }
    showView('viewAds');
}

function handleLoginError(err) {
    showErrorBox('Wrong username or password!');
}

function handleRegistrationError(err) {
    showErrorBox('This username has already been taken!');
}

function handleError(err) {
    showErrorBox('Error');
}