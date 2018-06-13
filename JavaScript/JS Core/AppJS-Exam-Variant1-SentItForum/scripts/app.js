$(() => {
    let header = $('#profile');

    function validateRegisterFields(username, pass, repeatPass) {
        if (!/^[A-Za-z]{5,}$/g.test(username)) {
            showError(`Username should be at least 5 characters long and contain only letters!`);
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

    // Attach event handlers
    (() => {
        $('#overview').click(loadOwnReceipts);
        $('#editor').click(loadActiveReceipt);
        $('#register-form').submit(registerUser);//01 ready
        $('#login-form').submit(loginUser); //02 ready
        $('#addItemBtn').click(addEntryOrReceipt); //04
        $('.logout').click(logoutUser);//03 ready

        $('.notification').click(function () {
            $(this).hide();
        })
    })();

    if (sessionStorage.getItem('authtoken') === null) {
        userLoggedOut();
    } else {
        userLoggedIn();

    }

    //  06 LOGIC TO VIEW MY POSTS
    function loadOwnReceipts() {
        let userId = sessionStorage.getItem('userId');

        receiptService.getOwnReceipts(userId)
            .then((myOwnReceipts) => {
                //console.log(myOwnReceipts);
                displayMyOwnReceipts(myOwnReceipts);
                showView('all-receipt-view')
            }).catch(handleError);
    }

    function displayMyOwnReceipts(myOwnReceipts) {
        let receiptsContainer = $('#myReceipts');
        receiptsContainer.empty();

        let tableHead = $('<div class="table-head">')
            .append($('<div class="col wide">Creation Date</div>'))
            .append($('<div class="col wide">Items</div>'))
            .append($('<div class="col wide">Total</div>'))
            .append($('<div class="col">Actions</div>'));
        receiptsContainer.append(tableHead);
        /*if (myOwnReceipts.length === 0) {
            receiptsContainer.text('No receipts in database.');
        }*/

        for (let receipt of myOwnReceipts) {
            let receiptId = receipt['_id'];

            let timeCreated = calcTime(receipt._kmd.ect);
            let items = receipt['items'];
            let total = receipt['total'];


            let detailsLink = $(`<a  href="#" data-id="${receiptId}">Details</a>`)
                .click(receiptDetails);
            let recDiv = $('<div class="row">')
                .append($('<div class="col wide">')
                    .text(timeCreated))
                .append($('<div class="col wide">')
                    .text(items))
                .append($('<div class="col">')
                    .text(total))
                .append($('<div class="col">')
                    .append(detailsLink));
            receiptsContainer.append(recDiv);
        }
    }



    function loadActiveReceipt() {
        receiptService.getActiveReceipt()
            .then((receiptData) => {
                showView('create-receipt-view');
                displayProducts(receiptData);
            }).catch(handleError);
    }


    function displayProducts(receiptData) {
        let receiptId = receiptData._id;
        entriesService.getEntries(receiptId).then(
            (enties)=>{
                $('#addItemBtn').attr(`"data-id", "${enties._id}"`);
                let productsContainer = $('#bodyTable');
                productsContainer.empty();


                for (let product of enties) {
                    let productId = product['_id'];
                    let productName = product['type'];
                    let productQuantity = Number(product['qty']);
                    let productPrice = Number(product['price']);
                    let total = productPrice * productQuantity;
                    let timeCreated = calcTime(product._kmd.ect);
                    console.log(productId);
                    console.log(productName);
                    console.log(productQuantity);
                    console.log(productPrice);


                    let removeBtn = $(`<a  href="#" data-id="${productId}">&#10006;</a>`)
                        .click(removeReciept);
                    let recDiv = $('<div class="row">')
                        .append($('<div class="col wide">')
                            .text(productName))
                        .append($('<div class="col wide">')
                            .text(productQuantity))
                        .append($('<div class="col wide">')
                            .text(productPrice))
                        .append($('<div class="col wide">')
                            .text(total))
                        .append($('<div class="col right">')
                            .append(removeBtn));

                    productsContainer.append(recDiv);
                }
            }
        )
    }

    // 04 LOGIC TO CREATE POST
    function addEntryOrReceipt(ev) {
        ev.preventDefault();
        let receiptId = $(this).attr('data-id');
        console.log(receiptId);
        if(receiptId = undefined){
            let active = true;
            let productCount = 0;
            let total = 0;
            receiptService.createReceipt(active, productCount, total)
                .then((receiptData) => {
                    receiptId = receiptData._id;
                    let createEntry = $('#create-entry-form');
                    let type = createEntry.find('input[name="type"]');
                    let qty = createEntry.find('input[name="qty"]');
                    let price = createEntry.find('input[name="price"]');


                    if (type.val() === "" || qty.val() === "" || price.val() ==="") {
                        showError('Type/Quantity/Price cannot be empty!');
                        return;
                    }

                    let typeVal = type.val();
                    let qtyVal = qty.val();
                    let priceVal = price.val();


                    entriesService.addEntry(typeVal, qtyVal, priceVal, receiptId)
                        .then(() => {
                            type.val('');
                            qty.val('');
                            price.val('');
                            showInfo('Entry Added.');
                            loadActiveReceipt();
                            showView('create-receipt-view');
                        }).catch(handleError);
                }).catch(handleError)
        }else{
            let createEntry = $('#create-entry-form');
            let type = createEntry.find('input[name="type"]');
            let qty = createEntry.find('input[name="qty"]');
            let price = createEntry.find('input[name="price"]');


            if (type.val() === "" || qty.val() === "" || price.val() ==="") {
                showError('Type/Quantity/Price cannot be empty!');
                return;
            }

            let typeVal = type.val();
            let qtyVal = qty.val();
            let priceVal = price.val();

            entriesService.addEntry(typeVal, qtyVal, priceVal, receiptId)
                .then(() => {
                    type.val('');
                    qty.val('');
                    price.val('');
                    showInfo('Entry Added.');
                    loadActiveReceipt();
                    showView('create-receipt-view');
                }).catch(handleError);
        }

    }

    // 03
    function logoutUser() {
        auth.logout()
            .then(() => {
                sessionStorage.clear();
                showInfo('Logout successful.');
                userLoggedOut();
                showView('welcome-section');
            }).catch(handleError);
    }

    // 02
    function loginUser(ev) {
        ev.preventDefault();
        let loginForm = $('#welcome-login-form');
        let usernameInput = $('#username-login');
        let passInput = $('#password-login');

        let usernameVal = usernameInput.val();
        let passVal = passInput.val();

        let allIsValid = validateRegisterFields(usernameVal, passVal, passVal);
        if (allIsValid) {
            auth.login(usernameVal, passVal)
                .then((userInfo) => {
                    usernameInput.val('');
                    passInput.val('');
                    saveSession(userInfo);
                    showInfo('User login successful.');
                    loadActiveReceipt();
                    showView('create-receipt-view');
                }).catch(handleError);
        }
    }

    // 01
    function registerUser(ev) {
        ev.preventDefault();
        let registerForm = $('#register-form');
        let usernameInput = registerForm.find('#username-register');
        let passInput = registerForm.find('#password-register');
        let repeatPassInput = registerForm.find('#password-register-check');

        let usernameVal = usernameInput.val();
        let passVal = passInput.val();
        let repeatPassVal = repeatPassInput.val();

        let allIsValid = validateRegisterFields(usernameVal, passVal, repeatPassVal);
        if (allIsValid) {
            auth.register(usernameVal, passVal)
                .then((userInfo) => {
                    saveSession(userInfo);
                    showInfo('User registration successful.');
                    usernameInput.val('');
                    passInput.val('');
                    repeatPassInput.val('');
                    loadActiveReceipt();
                    showView('create-receipt-view');
                }).catch(handleError);
        }
    }

//Standard function
    function navigateTo() {
        let viewName = $(this).attr('data-target');
        showView(viewName);
    }

    function showView(viewName) {
        $('#container > section').hide();
        $('#' + viewName).show();
    }

    function userLoggedOut() {
        $('#profile').hide();
        $('#usernameCashier').text('');
        showView('welcome-section');
    }

    function userLoggedIn() {
        $('#profile').show();
        $('#usernameCashier').text(`${sessionStorage.getItem('username')}`);
        loadActiveReceipt();
        showView('create-receipt-view');
        //loadAllPosts();
    }

    function saveSession(userInfo) {
        let userAuth = userInfo._kmd.authtoken;
        sessionStorage.setItem('authtoken', userAuth);
        let username = userInfo.username;
        sessionStorage.setItem('username', username);
        let userId = userInfo._id;
        sessionStorage.setItem('userId', userId);
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

    // HELPER FUNCTION FOR TIME
    function calcTime(dateIsoFormat) {
        let diff = new Date - (new Date(dateIsoFormat));
        diff = Math.floor(diff / 60000);
        if (diff < 1) return 'less than a minute';
        if (diff < 60) return diff + ' minute' + pluralize(diff);
        diff = Math.floor(diff / 60);
        if (diff < 24) return diff + ' hour' + pluralize(diff);
        diff = Math.floor(diff / 24);
        if (diff < 30) return diff + ' day' + pluralize(diff);
        diff = Math.floor(diff / 30);
        if (diff < 12) return diff + ' month' + pluralize(diff);
        diff = Math.floor(diff / 12);
        return diff + ' year' + pluralize(diff);

        function pluralize(value) {
            if (value !== 1) return 's';
            else return '';
        }
    }

    // Handle notifications
    $(document).on({
        ajaxStart: () => $("#loadingBox").show(),
        ajaxStop: () => $('#loadingBox').fadeOut()
    });
})