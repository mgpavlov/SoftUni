let auth = (() => {
    function saveSession(data) {
        let userAuth = data._kmd.authtoken;
        sessionStorage.setItem('authtoken', userAuth);
        let userId = data._id;
        sessionStorage.setItem('userId', userId);
        let username = data.username;
        sessionStorage.setItem('username', username);
        let name = data.name;
        sessionStorage.setItem('name', name);
        //sessionStorage.setItem('teamId', data.teamId);
    }

    // user/login
    function login(username, password) {
        let userData = {
            username,
            password
        };

        return requester.post('user', 'login', 'basic', userData);
    }

    // user/register
    function register(username, password, repeatPassword) {
        let userData = {
            username,
            password
        };

        return requester.post('user', '', 'basic', userData);
    }

    // user/logout
    function logout() {
        let logoutData = {
            authtoken: sessionStorage.getItem('authtoken')
        };

        return requester.post('user', '_logout', 'kinvey', logoutData);
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

    return {
        login,
        register,
        logout,
        saveSession,
        showInfo,
        showError,
        handleError
    }
})();