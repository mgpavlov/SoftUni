function attachEventListeners(){
    "use strict";
    let loginBtn=$('#linkLogin').on('click',showLoginForm);
    $('#buttonLoginUser').on('click',loginUser);
    $('#linkLogout').on('click',logoutUser);
    $('#linkRegister').on('click',showRegisterForm);
    $('#buttonRegisterUser').on('click',registerUser)
    $('#linkHome').on('click',showHome);
    $('#linkListAds').on('click',showAdds)
    $('#linkCreateAd').on('click',showCreateAd);
    $('#buttonCreateAd').on('click',createAdd)
    $('#buttonEditAd').on('click',finalEditAdd)

}

// Attach AJAX "loading" event listener
$(document).on({
    ajaxStart: function() { $("#loadingBox").show() },
    ajaxStop: function() { $("#loadingBox").hide() }
});