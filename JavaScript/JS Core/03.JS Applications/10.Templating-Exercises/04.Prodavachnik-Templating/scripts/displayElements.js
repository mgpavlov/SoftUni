function loadMenuLinks() {
    $('#linkHome').show();

    if (sessionStorage.getItem('authToken') === null) {
        $('#linkListAds').hide();
        $('#linkLogin').show();
        $('#linkLogout').hide();
        $('#linkRegister').show();
        $('#linkCreateAd').hide();
        $('#loggedInUser').text('');
    }
    else {
        $('#linkListAds').show();
        $('#linkLogin').hide();
        $('#linkLogout').show();
        $('#linkRegister').hide();
        $('#linkCreateAd').show();
        $('#loggedInUser').text(`Welcome, ${sessionStorage.getItem('username')}!`);
        $('#loggedInUser').show();
    }
}

function showView(id) {
    $('main > section').hide();
    $('#' + id).show();
}

function showLoginForm() {
    $('#formLogin').trigger('reset');
    showView('viewLogin')
}

function showRegisterForm() {
    $('#formRegister').trigger('reset');
    showView('viewRegister');
}

function showCreateAdForm() {
    $('#formCreateAd').trigger('reset');
    showView('viewCreateAd');
}

function showHome() {
    showView('viewHome');
}

function showErrorBox(msg) {
    let errorBox = $('#errorBox');
    errorBox.text(msg);
    errorBox.click(function () {
        errorBox.hide();
    });
    errorBox.show();
}

function showInfoBox(msg) {
    let infoBox = $('#infoBox');
    infoBox.text(msg);
    infoBox.show();
    setTimeout(function () {
        infoBox.fadeOut();
    }, 3000);
}

$(document).on({
    ajaxStart: function() { $("#loadingBox").show(); },
    ajaxStop: function() { $("#loadingBox").hide(); }
});