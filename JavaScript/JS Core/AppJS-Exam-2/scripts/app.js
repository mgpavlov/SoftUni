$(() => {
    let header = $('#profile');


    function validateRegisterFields(username, pass, repeatPass) {
        if (!/^.{5,}$/g.test(username)) {
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
        $('#register-form').submit(registerUser);
        $('#login-form').submit(loginUser);
        $('#addItemBtn').click(addEntry);
        $('#checkoutBtn').click(checkoutReceipt);
        $('.logout').click(logoutUser);

        $('.notification').click(function () {
            $(this).hide();
        })
    })();

    if (sessionStorage.getItem('authtoken') === null) {
        userLoggedOut();
    } else {
        userLoggedIn();

    }

    function checkoutReceipt(ev) {
        ev.preventDefault();
        let receiptId = $(this).attr('data-id');
        let productCount = $(this).attr('data-items');
        let total = $(this).attr('data-total');
        if (productCount !== 0) {
            receiptService.commitReceipts(receiptId, 'false', productCount, total)
                .then(() => {
                    showInfo('Receipt checked out.');
                    createActiveReceipt();
                }).catch(handleError)
        }

    }

    function loadOwnReceipts() {
        let userId = sessionStorage.getItem('userId');
        let total = 0.00;

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

        let totalTotal = 0;

        for (let receipt of myOwnReceipts) {
            let receiptId = receipt['_id'];

            let timeCreated = receipt._kmd.ect.slice(0, 10);
            let items = receipt['productCount'];
            let total = Number(receipt['total']);


            let detailsLink = $(`<a  href="#" data-id="${receiptId}">Details</a>`)
                .click(receiptDetails);
            let recDiv = $('<div class="row">')
                .append($('<div class="col wide">')
                    .text(timeCreated))
                .append($('<div class="col wide">')
                    .text(items))
                .append($('<div class="col wide">')
                    .text(total))
                .append($('<div class="col">')
                    .append(detailsLink));
            receiptsContainer.append(recDiv);
            totalTotal += total
        }
        $('#totalReceipts').text(totalTotal)
    }

    function receiptDetails() {
        let receiptId = $(this).attr('data-id');
        entriesService.getEntries(receiptId).then((entries) => {
            showView("receipt-details-view");
            displayCurrentReceipt(entries)
        })
    }

    function displayCurrentReceipt(entries) {

        let entriesContainer = $('#detailsBody');
        entriesContainer.empty();
        for (let entry of entries) {
            console.log(entry);
            let productName = entry['type'];
            let productQuantity = Number(entry['qty']);
            let productPrice = Number(entry['price']);
            let subTotal = productPrice * productQuantity;

            let receiptDiv = $('<div class="row">')
                .append($('<div class="col wide">')
                    .text(productName))
                .append($('<div class="col wide">')
                    .text(productQuantity))
                .append($('<div class="col wide">')
                    .text(productPrice))
                .append($('<div class="col wide">')
                    .text(subTotal));

            entriesContainer.append(receiptDiv);
        }
    }

    function loadActiveReceipt() {
        let userId = sessionStorage.getItem('userId');

        receiptService.getActiveReceipt(userId)
            .then((receiptData) => {
                console.log(receiptData);
                let addBtn = $('#addItemBtn');
                let checkoutBtn = $('#checkoutBtn');
                let receiptId = receiptData[0]._id;
                addBtn.attr("data-id", receiptId);
                checkoutBtn.attr("data-id", receiptId);
                showView('create-receipt-view');
                displayEntries(receiptData[0]);
            }).catch(handleError);
    }

    function displayEntries(receiptData) {
        console.log(receiptData);
        let receiptId = receiptData._id;
        let total = $('#total');
        let totalVal = 0;
        let items = 0;
        entriesService.getEntries(receiptId).then(
            (entries) => {
                console.log(entries);
                let checkoutBtn = $('#checkoutBtn');
                let productsContainer = $('#bodyTable');
                productsContainer.empty();
                for (let entry of entries) {
                    console.log(entry);
                    let entryId = entry['_id'];
                    let productName = entry['type'];
                    let productQuantity = Number(entry['qty']);
                    let productPrice = Number(entry['price']);
                    let subTotal = productPrice * productQuantity;

                    totalVal += subTotal;
                    items += productQuantity;
                    console.log(entryId);
                    console.log(productName);
                    console.log(productQuantity);
                    console.log(productPrice);
                    console.log(subTotal);

                    let removeBtn = $(`<a  href="#" data-id="${entryId}">&#10006;</a>`)
                        .click(removeEntry);
                    let receiptDiv = $('<div class="row">')
                        .append($('<div class="col wide">')
                            .text(productName))
                        .append($('<div class="col wide">')
                            .text(productQuantity))
                        .append($('<div class="col wide">')
                            .text(productPrice))
                        .append($('<div class="col wide">')
                            .text(subTotal))
                        .append($('<div class="col right">')
                            .append(removeBtn));

                    productsContainer.append(receiptDiv);
                }

                total.text(totalVal);
                checkoutBtn.attr("data-items", items);
                checkoutBtn.attr("data-total", totalVal);
            }
        ).catch(handleError)
    }

    function removeEntry() {
        let entryId = $(this).attr('data-id');
        entriesService.deleteEntry(entryId)
            .then(() => {
                loadActiveReceipt();
            })
            .catch()

    }

    function addEntry(ev) {
        ev.preventDefault();
        let total = $('#total');
        let totalVal = Number(total.text()).toFixed(2);
        let receiptId = $(this).attr('data-id');
        console.log(receiptId);
        let createEntry = $('#create-entry-form');
        let type = createEntry.find('input[name="type"]');
        let qty = createEntry.find('input[name="qty"]');
        let price = createEntry.find('input[name="price"]');


        if (type.val() === "" || qty.val() === "" || price.val() === "") {
            showError('Type/Quantity/Price cannot be empty!');
            return;
        }
        if (isNaN(qty.val()) || isNaN(price.val())) {
            showError('Quantity/Price must be numbers!');
            return;
        }

        let typeVal = type.val();
        let qtyVal = Number(qty.val()).toFixed(2);
        let priceVal = Number(price.val()).toFixed(2);
        let totalPrice = qtyVal * priceVal;
        total.text(totalVal + totalPrice); // трябва да се дооправи

        entriesService.addEntry(typeVal, qtyVal, priceVal, receiptId)
            .then(() => {
                type.val('');
                qty.val('');
                price.val('');
                showInfo('Entry Added.');
                loadActiveReceipt();
            }).catch(handleError);
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
                    showView('create-receipt-view');
                    loadActiveReceipt();
                }).catch(handleError);
        }
    }

    function createActiveReceipt() {
        receiptService.createReceipt('true', 0, 0)
            .then(() => {
                    loadActiveReceipt()
                }
            )
            .catch(handleError);
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
                    createActiveReceipt();
                    showView('create-receipt-view');
                }).catch(handleError);
        }
    }

//Standard function

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


    // Handle notifications
    $(document).on({
        ajaxStart: () => $("#loadingBox").show(),
        ajaxStop: () => $('#loadingBox').fadeOut()
    });
})