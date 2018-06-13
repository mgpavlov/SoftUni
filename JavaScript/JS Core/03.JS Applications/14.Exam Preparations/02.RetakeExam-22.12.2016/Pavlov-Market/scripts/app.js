$(() => {
    showView('AppHome');
    $('#loadingBox').hide();
    $('#infoBox').hide();
    $('#errorBox').hide();
    // Attach event handlers//01
    (() => {
        $('header').find('a[data-target]').click(navigateTo);// 2.1 скриваме
        $('#formRegister').submit(registerUser);// 07.1
        $('#formLogin').submit(loginUser);// 08.1
        $('#linkMenuLogout').click(logoutUser);// 09.1

        $('#linkUserHomeShop').click(() => {//***допълнителни менюта в UserHome
            showView('Shop');
            loadProducts();//10.1
        });

        $('#linkUserHomeCart').click(() => {//***допълнителни менюта в UserHome
            showView('Cart');
            loadMyCart(); //12.1
        });


        $('#linkMenuShop').click(loadProducts);//10.2
        $('#linkMenuCart').click(loadMyCart); //11.2


        // 13 да скрива box-овете при клик
        $('.notification').click(function () {
            $(this).hide();
        })
    })();

    //02
    function navigateTo() {
        let viewName = $(this).attr('data-target');
        showView(viewName);
    }

    //03 Shows one view/section at a time
    function showView(viewName) {
        $('main > section').hide();
        $('#view' + viewName).show();
    }

    //04
    function userLoggedOut() {
        $('.anonymous').show();
        $('.useronly').hide();
        $('#spanMenuLoggedInUser').text('');
        showView('AppHome');
    }

    //05
    function userLoggedIn() {
        $('.anonymous').hide();
        $('.useronly').show();
        let username = sessionStorage.getItem('username');
        $('#spanMenuLoggedInUser').text(`Welcome, ${username}!`);
        $('#viewUserHomeHeading').text(`Welcome, ${username}!`);
        showView('UserHome');
    }

    //06
    if (sessionStorage.getItem('authtoken') === null) {
        userLoggedOut();
    } else {
        userLoggedIn();
    }

    // 07   LOGIC TO REGISTER USER
    function registerUser(ev) {
        ev.preventDefault();
        let registerUsername = $('#registerUsername');
        let registerName = $('#registerName');
        let registerPassword = $('#registerPasswd');

        let usernameVal = registerUsername.val();
        let nameVal = registerName.val();
        let passVal = registerPassword.val();

        auth.register(usernameVal, passVal, nameVal)
            .then((userInfo) => {
                saveSession(userInfo);
                registerUsername.val("");
                registerName.val("");
                registerPassword.val("");
                showInfo('User registration successful.');
            }).catch(handleError);
    }

    // 08  LOGIC TO LOGIN USER
    function loginUser(ev) {
        ev.preventDefault();
        let inputUsername = $('#loginUsername');
        let inputPassword = $('#loginPasswd');

        let usernameVal = inputUsername.val();
        let passwdVal = inputPassword.val();

        auth.login(usernameVal, passwdVal)
            .then((userInfo) => {
                saveSession(userInfo);
                inputUsername.val('');
                inputPassword.val('');
                showInfo('Login successful.');
            }).catch(handleError);
    }


    // 09  LOGIC TO LOGOUT USER
    function logoutUser() {
        auth.logout()
            .then(() => {
                sessionStorage.clear();
                showInfo('Logout successful.');
                userLoggedOut();
            }).catch(handleError);
    }


    // 10  LOGIC TO LOAD RECEIVED MESSAGES За да има съобщения през Postman пращаме съобщения;
    function loadProducts() {
        marketService.getAllProducts()
            .then((products) => {
                console.log(products); //за да видим как да вземем данните
                displayAllProducts(products);
            }).catch(handleError);
    }

    //10.3
    function displayAllProducts(products) {
        let userId = sessionStorage.getItem('userId')
        let productsContainer = $('#shopProducts');
        productsContainer.empty();
        let productsTable = $('<table>');
        productsTable.append($('<thead>')
            .append($('<tr>')
                .append('<th>Product</th>')
                .append('<th>Description</th>')
                .append('<th>Price</th>')
                .append('<th>Actions</th>')));

        let tableBody = $('<tbody>');
        for (let product of products) {
            let tableRow = $('<tr>');
            let productName = product.name;
            let description = product.description; // 125 ред /console.log(myMessages); за да видим как да вземем данните
            let price = product.price;
            let actionBtn = $(`<input type="button" id="buttonPurchase" value="Purchase" data-userId="${userId}" data-productId="${product._id}"/>`)
                .click(addToCart.bind(null, product));
            tableRow.append($('<td>').text(productName));
            tableRow.append($('<td>').text(description));
            tableRow.append($('<td>').text(Number(price).toFixed(2)));
            tableRow.append($('<td>').append(actionBtn));
            tableBody.append(tableRow);
        }
        productsTable.append(tableBody);
        productsContainer.append(productsTable);
    }

    function addToCart(product) {
        let userId = sessionStorage.getItem('userId');
        let productId = product._id;

        marketService.getUser(userId)
            .then((userInfo)=>{
                updateUserInfo(userInfo)

            })
            .catch(handleError);

        function updateUserInfo(userInfo) {
            let cart = {};
            if (userInfo.hasOwnProperty('cart')) {
                cart = userInfo.cart;
            }

            cart[productId] = {
                "quantity": cart.hasOwnProperty(productId) ? Number(cart[productId]['quantity'])+1 : 1,
                "product": {
                    "name": product.name,
                    "description": product.description,
                    "price": product.price
                }
            }
            userInfo.cart = cart;

            marketService.updateUser(userInfo)
                .then(()=>{
                        showView('Cart');
                        loadMyCart();
                    }
                )
                .catch(handleError)
        }
    }


    // 11  LOGIC TO LOAD SENT MESSAGES
    function loadMyCart() {
        let userId = sessionStorage.getItem('userId');
        marketService.getUser(userId)
            .then((userInfo) => {
                displayMyCart(userInfo);
            }).catch(handleError);
    }

    // 11.3
    function displayMyCart(userInfo) {
        let cart = userInfo.cart;

        let cartContainer = $('#cartProducts');
        cartContainer.empty();
        let cartTable = $('<table>');
        cartTable.append($('<thead>')
            .append($('<tr>')
                .append('<th>Product</th>')
                .append('<th>Description</th>')
                .append('<th>Quantity</th>')
                .append('<th>Price</th>')
                .append('<th>Total Price</th>')
                .append('<th>Actions</th>')));
        let tableBody = $('<tbody>');
        let ids = Object.keys(cart);

        for (let productId of ids) {
            console.log(productId);
            let productInfo = cart[productId]['product'];
            let name = productInfo.name;
            let description = productInfo.description;
            let price = productInfo.price;
            let quantity = cart[productId]['quantity'];
            let totalPrice = price * quantity;
            let tableRow = $('<tr>');
            let discardBtn =  $(`<input type="button" id="buttonDiscard" value="Discard"/>`)//$(`<button value="${productId}">Discard</button>`)
                .click(discardProduct.bind(null, userInfo, productId));
            tableRow.append($('<td>').text(name));
            tableRow.append($('<td>').text(description));
            tableRow.append($('<td>').text(quantity));
            tableRow.append($('<td>').text(Number(price)));
            tableRow.append($('<td>').text(Number(totalPrice).toFixed(2)));
            tableRow.append($('<td>').append(discardBtn));
            tableBody.append(tableRow);
        }

        cartTable.append(tableBody);
        cartContainer.append(cartTable);
    }

    // 11.4 DELETE A MESSAGE
    function discardProduct(userInfo, productId) {
        /*let productId = $(this).attr('data-productId');*/
        console.log(productId);
        delete userInfo.cart[productId];
        marketService.updateUser(userInfo).then(
            ()=>{
                showView('Cart');
                loadMyCart();
            }
        )
    }


////// Готови функции;

    // Handle notifications
    $(document).on({
        ajaxStart: () => $("#loadingBox").show(),
        ajaxStop: () => $('#loadingBox').fadeOut()
    });

    function saveSession(userInfo) {
        let userAuth = userInfo._kmd.authtoken;
        sessionStorage.setItem('authtoken', userAuth);
        let userId = userInfo._id;
        sessionStorage.setItem('userId', userId);
        let username = userInfo.username;
        sessionStorage.setItem('username', username);
        let name = userInfo['name'];
        sessionStorage.setItem('name', name);
        let password = userInfo.password;
        sessionStorage.setItem('password', password);
/*
        let cart = userInfo['cart'];
        sessionStorage.setItem('cart', cart);
*/
        userLoggedIn();
    }

    function handleError(reason) {
        showError(reason.responseJSON.description);
    }

    function showError(message) {
        let errorBox = $('#errorBox');
        errorBox.text(message);
        errorBox.show();
        setTimeout(() => errorBox.fadeOut(), 3000);
    }

    function showInfo(message) {
        let infoBox = $('#infoBox');
        infoBox.text(message);
        infoBox.show();
        setTimeout(() => infoBox.fadeOut(), 3000);
    }

    //Помощни функции по задание:
    function formatDate(dateISO8601) {
        let date = new Date(dateISO8601);
        if (Number.isNaN(date.getDate()))
            return '';
        return date.getDate() + '.' + padZeros(date.getMonth() + 1) +
            "." + date.getFullYear() + ' ' + date.getHours() + ':' +
            padZeros(date.getMinutes()) + ':' + padZeros(date.getSeconds());

        function padZeros(num) {
            return ('0' + num).slice(-2);
        }
    }

    function formatSender(name, username) {
        if (!name)
            return username;
        else
            return username + ' (' + name + ')';
    }

})