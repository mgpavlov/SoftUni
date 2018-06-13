function showView(viewName) {
    $('main > section').hide(); // Hide all views
    $('#' + viewName).show() // Show the selected view only
}

function displayAllHiddenLinks() {

    if(sessionStorage.getItem('authToken')===null){ // no logged in user
        $('#viewHome').show();
        $('#linkHome').show();
        $('#linkLogin').show();
        $('#linkRegister').show();
        $('#linkListAds').hide();
        $('#linkCreateAd').hide();
        $('#linkLogout').hide()
    }else{
        $('#linkHome').show();
        $('#linkLogin').hide();
        $('#linkRegister').hide();
        $('#linkListAds').show();
        $('#linkCreateAd').show();
        $('#linkLogout').show();
    }

}

function showLoginForm() {
    showView('viewLogin');
}

function showRegisterForm(){
    showView('viewRegister')
}
function showHome(){
    showView('viewHome')
}

function showCreateAd() {
    showView('viewCreateAd')
  }