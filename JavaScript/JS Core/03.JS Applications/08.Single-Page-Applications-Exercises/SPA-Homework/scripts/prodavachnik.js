function startApp() {
    let menu = $('#menu');
    let loggedInUser =  $('#loggedInUser');
    let adsTable = $('#ads').find('table').find('tbody');

    //1. Правим видими менютата
    menu.find('a').show();

    //2.1
    function showView(view) {
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
    }

    /*function navigateTo(e) {
        $('section').hide();
        let target = $(e.target).attr('data-target'); //взимаме елемента който е кликнат и има атрибут data-target
        $('#' + target).show(); //показваме го
    }*/

    //Attach event listeners
    //2.2 Показваме sections при клик
    $('#linkHome').click(()=>showView('home'));
    $('#linkListAds').click(()=>showView('ads'));
    $('#linkLogin').click(()=>showView('login'));
    $('#linkRegister').click(()=>showView('register'));
    $('#linkCreateAd').click(()=>showView('createAd'));
    //10.1
    $('#linkLogout').click(logout);
    //5.1.
    $('#buttonLoginUser').click(login);
    //7.1.
    $('#buttonRegisterUser').click(register);
    //12.1
    $('#buttonCreateAd').click(createAd);


    /*3.  в index.html ред 25 и 27 слагаме клас notification на loadingBox & infoBox
    * и в prodavachnik.css добавяме в края редове 165-176, и закоментираме 90-96*/
    $('.notification').click((e)=>{
        $(e.target).hide();
    });
    
    //4. правим модул на заявките:
    let requester = (()=>{
        const appKey = 'kid_ByvJ6UJiG';
        const appSecret = '655386e5e08344768c5d3e91ccb38a1f';
        const baseUrl = 'https://baas.kinvey.com/';

        function makeAuth(type) {
            return type === 'basic'
                ?  'Basic ' + btoa(appKey + ':' + appSecret)
                :  'Kinvey ' + localStorage.getItem('authtoken');
        }
        function makeRequest(method, module, url, auth) {
            return {
                method: method,
                url: baseUrl + module + '/' + appKey + '/' + url,
                headers: {
                    'Authorization': makeAuth(auth)
                }
            };
        }
        function get(module, url, auth) {
            return $.ajax(makeRequest('GET', module, url, auth));
        }
        function post(module, url, auth, data) {
            let req = makeRequest('POST', module, url, auth);
            req.data = data;
            return $.ajax(req);
        }
        function update (module, url, auth, data) {
            let req = makeRequest('PUT', module, url, auth);
            req.data = data;
            return $.ajax(req);
        }
        function del (module, url, auth) {
            return $.ajax(makeRequest('DELETE', module, url, auth));
        }

        return {
            get,
            post,
            update,
            del
        }
    })();
    //8
    function userLoggedIn() {
        loggedInUser.text(`Welcome, ${localStorage.getItem('username')}!`);
        loggedInUser.show();
        $('#linkLogout').show();
        $('#linkHome').show();
        $('#linkListAds').show();
        $('#linkCreateAd').show();
        $('#linkLogin').hide();
        $('#linkRegister').hide();
    }
    //9
    function userLogout() {
        loggedInUser.text(``);
        loggedInUser.hide();
        $('#linkLogout').hide();
        $('#linkHome').show();
        $('#linkListAds').hide();
        $('#linkCreateAd').hide();
        $('#linkLogin').show();
        $('#linkRegister').show();
    }
    
    //8.2
    if (localStorage.getItem('authtoken') !== null && localStorage.getItem('username') !== null) {
        userLoggedIn();
    }else{
        userLogout();
    }
    showView('home');
    //6
    function saveSession(data) {
        localStorage.setItem('username', data.username);
        localStorage.setItem('id', data._id);
        localStorage.setItem('authtoken', data._kmd.authtoken);
        //8.1
        userLoggedIn();
    }
    //5.login
    async function login() {
        let form = $('#formLogin');
        let username = form.find('input[name="username"]').val();
        let password = form.find('input[name="passwd"]').val();

        try {
            let response = await requester.post('user', 'login', 'basic', {username, password});
            saveSession(response);
            showView('ads');
            showInfo('Login')
        }catch (reason) {
            handleError(reason);
        }
    }

    //7.Register
    async function register() {
        let form = $('#formRegister');
        let username = form.find('input[name="username"]').val();
        let password = form.find('input[name="password"]').val();

        try {
            let response = await requester.post('user', '', 'basic', {username, password});
            saveSession(response);
            showView('ads');
            showInfo('Register')
        }catch (reason) {
            handleError(reason);
        }
    }

    //10
    async function logout() {
        try {
            await requester.post('user', '_logout', 'kinvey',{authtoken: localStorage.getItem('authtoken')});
            localStorage.clear(); // Clears all session storage on logout
            userLogout();
            showView('home');
            showInfo('Logged out')
           // showInfo('Logout successful!')
        } catch (reason) {
            handleError(reason);
        }
    }


    //11 infoBox, loadingBox && errorBox => handleError
    $(document).on({
        ajaxStart: function() { $("#loadingBox").show() },
        ajaxStop: function() { $("#loadingBox").fadeOut() }
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
    function handleError(reason) {
        showError(reason.responseJSON.description);
    }
    
    //12
    async function loadAds() {
        adsTable.empty();
        let data = await requester.get('appdata', 'posts');

        if (data.length === 0){
            showError('No ads in database');
            return;
        }
        for (let ad of data) {
            let tr = $('<tr>');
            tr.addClass('ad-box'); // css =>
            let title = $(`<td>${ad.title}</td>`);
            let publisher = $(`<td>${ad.publisher}</td>`);
            let description = $(`<td>${ad.description}</td>`);
            let price = $(`<td>${Number(ad.price).toFixed(2)}</td>`);
            let date = $(`<td>${ad.date}</td>`);
            let image = $(`<td><img src="${ad.imageUrl}"></td>`);
            let actions = $(`<td>`);
            //закачаме бутони edit delete само ако потребителя е собственик на обявата;
            if (ad._acl.creator === localStorage.getItem('id')) {
                let delBtn = $(`<button>Delete</button>`).click(()=>deleteAd(ad._id));
                let editBtn = $(`<button>Edit</button>`).click(()=>openEditAd(ad));
                actions.append(delBtn).append(editBtn);
            }
            tr.append(title).append(publisher).append(description).append(price).append(date).append(image).append(actions);
           adsTable.append(tr);
        }
    }
    //12.1 Delete an add
    async function deleteAd(id) {
        try{
            await requester.del('appdata', `posts/${id}`);
            showView('ads');
            showInfo('Ad successfully removed!');
        } catch (error) {
            handleError(error)
        }
    }

    //12.2 Open edit view
    async function openEditAd(ad) {
        let form = $('#formEditAd');
        form.find('input[name="title"]').val(ad.title);
        form.find('textarea[name="description"]').val(ad.description);
        form.find('input[name="price"]').val(Number(ad.price).toFixed(2));
        form.find('input[name="image"]').val(ad.imageUrl);

        let publisher = ad.publisher;
        let id = ad._id;

        $('#buttonEditAd').click(()=> editAd(id, publisher));
        showView('editAd');
    }
    //12.3 Edit
    async function editAd(id, publisher) {
        let form = $('#formEditAd');
        let title = form.find('input[name="title"]').val();
        let description = form.find('textarea[name="description"]').val();
        let price = form.find('input[name="price"]').val();
        let imageUrl = form.find('input[name="image"]').val();
        let date = formatDate(new Date());

        // Validations edit
        if (title.length === 0){
            showError('Title cannot be empty');
            return;
        }
        if (price.length === 0){
            showError('Price cannot be empty');
            return;
        }

        let editedAd = {title, description, price, imageUrl, publisher, date};

        try{
            await requester.update('appdata', `posts/${id}`,'kinvey', editedAd);
            showInfo('Ad successfully edited!');
            showView('ads');

            /*// clear all input values
            form.find('input[name="title"]').val('');
            form.find('textarea[name="description"]').val('');
            form.find('input[name="price"]').val('');
            form.find('input[name="image"]').val('');*/
        }catch (error) {
            handleError(error)
        }
    }

    //13
    async function createAd() {
        let form = $('#formCreateAd');
        let title = form.find('input[name="title"]').val();
        let description = form.find('textarea[name="description"]').val();
        let price = form.find('input[name="price"]').val();
        let imageUrl = form.find('input[name="image"]').val();
        let date = formatDate(new Date());
        let publisher = localStorage.getItem('username');

        // Validations
        if (title.length === 0){
            showError('Title cannot be empty');
            return;
        }
        if (price.length === 0){
            showError('Price cannot be empty');
            return;
        }

        let newAd = {
            title, description, price, imageUrl, date, publisher
        };
        try{
            await requester.post('appdata', 'posts','', newAd);
            showView('ads');
            showInfo('Ad successfully created!');

            // clear all input values
            form.find('input[name="title"]').val('');
            form.find('textarea[name="description"]').val('');
            form.find('input[name="price"]').val('');
            form.find('input[name="image"]').val('');
        }catch (error) {
            handleError(error)
        }
    }

    function formatDate(date) {
        var monthNames = [
            "January", "February", "March",
            "April", "May", "June", "July",
            "August", "September", "October",
            "November", "December"
        ];

        var day = date.getDate();
        var monthIndex = date.getMonth();
        var year = date.getFullYear();

        return day + ' ' + monthNames[monthIndex] + ' ' + year;
    }
}