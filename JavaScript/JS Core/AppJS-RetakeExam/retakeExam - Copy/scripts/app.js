$(() => {

    function validateRegisterFields(username, pass, repeatPass) {
        if (!/^.{5,}$/g.test(username)) {
            showError(`Username should be at least 5 characters long!`);
            return false;
        }

        if (pass !== repeatPass) {
            showError('Passwords should match!');
            return false;
        }
        if (pass === '') {
            showError('Password should be at least 1 characters long!');
            return false;
        }
        return true;
    }


    (() => {
        $('.left-container').find('li[data-target]').click(navigateTo);
        $('#formRegister').submit(registerUser);
        $('#formLogin').submit(loginUser);
        $('#viewCatalog .add-flight').click(()=>showView('AddFlight'));
        $('#formAddFlight .create').click(addFlight);
        $('#viewMyFlights .create').click(loadMyFlights);
        $('#formEditFlight').submit(editFlights);
        $('.log-out').click(logoutUser);

        $('.notification').click(function () {
            $(this).hide();
        })
    })();

    if (sessionStorage.getItem('authtoken') === null) {
        userLoggedOut();
    } else {
        userLoggedIn();
    }


    function loadMyFlights() {
        let userId = sessionStorage.getItem('userId');

        flightsService.loadMyFlights(userId)
            .then((myOwnFlights) => {
                displayMyOwnFlights(myOwnFlights);
            }).catch(handleError);
    }

    function displayMyOwnFlights(myOwnFlights) {
        let container = $('#viewMyFlights');
        container.empty();
        if (myOwnFlights.length === 0) {
            container.text('No flights in database.');
        }

        for (let flightsObj of myOwnFlights) {

        }
    }


    function loadAllFlights() {
        flightsService.getPublishedFlights()
            .then((allFlights) => {
                displayCatalog(allFlights);
            }).catch(handleError);
    }
    //05
    function displayCatalog(allFlights) {
        let flightsContainer = $('.added-flights');
        flightsContainer.empty();

        if (allFlights.length === 0) {
            flightsContainer.text('No flights in database.');
        }

        for (let flightObj of allFlights) {
            let flightId = flightObj['_id'];
            let departureDate = flightObj['departureDate'];
            let imageUrl = flightObj['image'];
            let destination = flightObj['destination'];
            let origin = flightObj['origin'];

            let detailsLink = $(`<a  href="#" data-id="${flightId}">`)
                .click(loadFlightDetails);                    //05.1
            detailsLink
                .append($(`<img src="${imageUrl}" alt="" class="picture-added-flight">`))
                .append($(`<h3>${destination}</h3>`))
                .append($(`<span>from ${origin}</span><span>${departureDate}</span>`));

            flightsContainer.append(detailsLink);
        }
    }


    function addFlight(ev) {
        ev.preventDefault();
        let formAddFlight = $('#formAddFlight');
        console.log('sdsdfsd');
        let author = sessionStorage.getItem('username');
        let destination = formAddFlight.find('input[name="destination"]');
        let origin = formAddFlight.find('input[name="origin"]');
        let departureDate = formAddFlight.find('input[name="departureDate"]');
        let departureTime = formAddFlight.find('input[name="departureTime"]');
        let seats = formAddFlight.find('input[name="seats"]');
        let cost = formAddFlight.find('input[name="cost"]');
        let img = formAddFlight.find('input[name="img"]');
        let publicCheck = formAddFlight.find('input[name="public"]');
        console.log(publicCheck);

        if (destination.val() === "" || origin.val() === "") {
            showError('Destination and origin station should be non-empty strings!');
            return;
        }
        if (Number(seats.val()) !== parseInt(seats.val(), 10) || Number(seats.val()) <= 0) {
            showError('Seats should be a positive number!');
            return;
        }
        if(Number(cost.val()) <= 0){
            showError('Coast should be a positive number!');
            return;
        }


        let destinationVal = destination.val();
        let originVal = origin.val();
        let departureDateVal = departureDate.val();
        let departureTimeVal = departureTime.val();
        let seatsVal = Number(seats.val());
        let costVal = Number(cost.val());
        let imgVal = img.val();
        let publicCheckVal = publicCheck[0]['checked'];

        console.log(seatsVal);
        console.log(costVal);

        flightsService.createFlight(destinationVal, originVal, departureDateVal, departureTimeVal, seatsVal, costVal, imgVal, publicCheckVal)
            .then(() => {
                destination.val('');
                origin.val('');
                departureDate.val('');
                departureTime.val('');
                seats.val('');
                cost.val('');
                img.val('');
                publicCheckVal = false;
                showInfo('Created flight.');
                loadAllFlights();
                showView('Catalog');
            }).catch(handleError);
    }


    function editFlights() {
        showView('EditFlight');
        let flightId = $(this).attr('data-target');
        console.log(flightId);
        let formEditFlight = $('#formEditFlight');
        let destination = formEditFlight.find('input[name="destination"]');
        let origin = formEditFlight.find('input[name="origin"]');
        let departureDate = formEditFlight.find('input[name="departureDate"]');
        let departureTime = formEditFlight.find('input[name="departureTime"]');
        let seats = formEditFlight.find('input[name="seats"]');
        let cost = formEditFlight.find('input[name="cost"]');
        let img = formEditFlight.find('input[name="img"]');
        let publicCheck = formEditFlight.find('input[name="public"]');

        let destinationVal = destination.val();
        let originVal = origin.val();
        let departureDateVal = departureDate.val();
        let departureTimeVal = departureTime.val();
        let seatsVal = Number(seats.val());
        let costVal = Number(cost.val());
        let imgVal = img.val();
        let publicCheckVal = publicCheck[0]['checked'];

        flightsService.editFlight(flightId, destinationVal, originVal, departureDateVal, departureTimeVal, seatsVal, costVal, imgVal, publicCheckVal)

    }

    function loadFlightDetails() {
        let flightId = $(this).attr('data-id');

        flightsService.flightDetails(flightId)
            .then((flightInfo)=>{
            displayFlightDetails(flightInfo);
            showView('FlightDetails');
        }).catch(handleError);
    }

    function displayFlightDetails(flightInfo) {
        console.log(flightInfo);
        let flightId = flightInfo['_id']
        let userId = sessionStorage.getItem('userId')
        let creatorId = flightInfo['_acl']['creator'];
        let destination = flightInfo['destination'];
        let origin = flightInfo['origin'];
        let departureDate = flightInfo['departureDate'];
        let departureTime = flightInfo['departureTime'];
        let seats = Number(flightInfo['seats']);
        let cost = Number(flightInfo['cost']);
        let imageUrl = flightInfo['image'];

        console.log(creatorId);
        console.log(userId);

        let container = $('#viewFlightDetails');
        container.empty();
        container.append($('<div class="ticket-area">')
            .append($('<div class="ticket-area-left">')
                .append($(`<img src=${imageUrl} alt="">`)))
            .append($('<div class="ticket-area-right">')
                .append($(`<h3>${destination}</h3>`))
                .append($(`<div>from ${origin}</div>`))
                .append($(` <div class="data-and-time">`).text(`${departureDate} ${departureTime}\n`)
                    .append($(`<a href="#" data-target="${flightId}" id="editFormId" class="edit-flight-detail"></a>`)))
                .append($(`<div>${seats} Seats (${cost} per seat)</div>`))
            ));

        if (userId === creatorId) {
            container.find('#editFormId').click(editFlights)
        }

    }


    function logoutUser() {
        auth.logout()
            .then(() => {
                sessionStorage.clear();
                showInfo('Logout successful.');
                userLoggedOut();
                showView('Login');
            }).catch(handleError);
    }

    function loginUser(ev) {
        ev.preventDefault();
        let loginForm = $('#formLogin');
        let usernameInput = loginForm.find('input[name="username"]');
        let passInput = loginForm.find('input[name="pass"]');

        let usernameVal = usernameInput.val();
        let passVal = passInput.val();

        let allIsValid = validateRegisterFields(usernameVal, passVal, passVal);
        if (allIsValid) {
            auth.login(usernameVal, passVal)
                .then((userInfo) => {
                    usernameInput.val('');
                    passInput.val('');
                    saveSession(userInfo);
                    showInfo('Login successful.');
                    loadAllFlights();
                }).catch(handleError);
        }
    }

    function registerUser(ev) {
        ev.preventDefault();
        let registerForm = $('#formRegister');
        let usernameInput = registerForm.find('input[name="username"]');
        let passInput = registerForm.find('input[name="pass"]');
        let repeatPassInput = registerForm.find('input[name="checkPass"]');

        let usernameVal = usernameInput.val();
        let passVal = passInput.val();
        let repeatPassVal = repeatPassInput.val();

        let allIsValid = validateRegisterFields(usernameVal, passVal, repeatPassVal);
        if (allIsValid) {
            auth.register(usernameVal, passVal)
                .then((userInfo) => {
                    usernameInput.val('');
                    passInput.val('');
                    repeatPassInput.val('');
                    saveSession(userInfo);
                    showInfo('User registration successful.');
                    showView('Catalog')
                    loadAllFlights();
                }).catch(handleError);
        }
    }

    function navigateTo() {
        let viewName = $(this).attr('data-target');
        showView(viewName);
    }

    function showView(viewName) {
        $('#container > section').hide();
        $('#view' + viewName).show();
    }

    function userLoggedOut() {
        $('#login').show();
        $('#register').show();
        $('#catalog').hide();
        $('#flights').hide();
        $('.right-container').hide();
        $('#welcomeUsername').text('');
        showView('Login');
    }

    function userLoggedIn() {
        $('#login').hide();
        $('#register').hide();
        $('#catalog').show();
        $('#flights').show();
        $('.right-container').show();
        $('#welcomeUsername').text(`Welcome, ${sessionStorage.getItem('username')}!`);
        showView('Catalog');
        loadAllFlights();
    }

    function saveSession(userInfo) {
        let userAuth = userInfo._kmd.authtoken;
        sessionStorage.setItem('authtoken', userAuth);
        let userId = userInfo._id;
        sessionStorage.setItem('userId', userId);
        let username = userInfo.username;
        sessionStorage.setItem('username', username);
        userLoggedIn();
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

    $(document).on({
        ajaxStart: () => $("#loadingBox").show(),
        ajaxStop: () => $('#loadingBox').fadeOut()
    });
})