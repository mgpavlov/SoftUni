function attachEvents() {
    //Link events
    $('#linkLogin').click(showLoginForm);
    $('#linkRegister').click(showRegisterForm);
    $('#linkHome').click(showHome);
    $('#linkLogout').click(logoutUser);
    $('#linkCreateAd').click(showCreateAdForm);
    $('#linkListAds').click(listAds);

    //Button events
    $('#buttonRegisterUser').click(registerUser);
    $('#buttonLoginUser').click(loginUser);
    $('#buttonCreateAd').click(createAd);
    $('#buttonEditAd').click(editAd);
}