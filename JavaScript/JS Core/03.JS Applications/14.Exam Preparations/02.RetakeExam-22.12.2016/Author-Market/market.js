function startApp() {
    showHideMenuLinks();
    showCorrectHomeView();

    $('#loadingBox').hide();
    $('#errorBox').hide();
    $('#infoBox').hide();

    // Bind the navigation menu links
    $("#linkMenuAppHome").click(showAppHomeView);
    $("#linkMenuLogin").click(showLoginView);
    $("#linkMenuRegister").click(showRegisterView);

    $("#linkMenuUserHome").click(showUserHomeView);
    $("#linkMenuShop").click(showShopView);
    $("#linkMenuCart").click(showCartView);
    $("#linkMenuLogout").click(logoutUser);

    // Bind user home links
    $("#linkUserHomeShop").click(showShopView);
    $("#linkUserHomeCart").click(showCartView);

    // Bind the form submit buttons
    $("#formLogin").submit(loginUser);
    $("#formRegister").submit(registerUser);
    $("form").submit(function(event) { event.preventDefault() });

    // Bind the info / error boxes
    $("#infoBox, #errorBox").click(function() {
        $(this).fadeOut();
    });

    // Attach AJAX "loading" event listener
    $(document).on({
        ajaxStart: function() { $("#loadingBox").show() },
        ajaxStop: function() { $("#loadingBox").hide() }
    });

    // Attach a global AJAX error handler
    $(document).ajaxError(handleAjaxError);

    const kinveyBaseUrl = "https://baas.kinvey.com/";
    const kinveyAppKey = "kid_S1Oq7JU4g";
    const kinveyAppSecret = "6beb769dd7e64592b7e25d319cf916e0";
    const kinveyAppAuthHeaders = {
        'Authorization': "Basic " + btoa(kinveyAppKey + ":" + kinveyAppSecret),
    };

    function getKinveyUserAuthHeaders() {
        return {
            'Authorization': "Kinvey " + sessionStorage.getItem('authtoken'),
        };
    }

    function showView(viewName) {
        // Hide all views and show the selected view only
        $('main > section').hide();
        $('#' + viewName).show();
    }

    function showHideMenuLinks() {
        if (sessionStorage.getItem('authtoken') == null) {
            // No logged in user
            $('#menu .anonymous').show();
            $('#menu .useronly').hide();
            $('#spanMenuLoggedInUser').text("");
        } else {
            // We have logged in user
            $('#menu .anonymous').hide();
            $('#menu .useronly').show();
            $('#spanMenuLoggedInUser').text("Welcome, " +
                sessionStorage.getItem('username') + "!");
        }
    }

    function showInfo(message) {
        $('#infoBox').text(message);
        $('#infoBox').show();
        setTimeout(function() {
            $('#infoBox').fadeOut();
        }, 3000);
    }

    function showError(errorMsg) {
        $('#errorBox').text("Error: " + errorMsg);
        $('#errorBox').show();
    }

    function handleAjaxError(event, response) {
        let errorMsg = JSON.stringify(response);
        if (response.readyState === 0)
            errorMsg = "Cannot connect due to network error.";
        if (response.responseJSON && response.responseJSON.description)
            errorMsg = response.responseJSON.description;
        showError(errorMsg);
    }

    function showCorrectHomeView() {
        if (sessionStorage.getItem('username'))
            showUserHomeView();
        else
            showAppHomeView();
    }

    function showAppHomeView() {
        showView('viewAppHome');
    }

    function showLoginView() {
        showView('viewLogin');
        $('#formLogin').trigger('reset');
    }

    function loginUser() {
        let userData = {
            username: $('#formLogin input[name=username]').val(),
            password: $('#formLogin input[name=passwd]').val()
        };
        $.ajax({
            method: "POST",
            url: kinveyBaseUrl + "user/" + kinveyAppKey + "/login",
            headers: kinveyAppAuthHeaders,
            data: userData,
            success: loginSuccessful
        });

        function loginSuccessful(userInfo) {
            saveAuthInSession(userInfo);
            showHideMenuLinks();
            showUserHomeView();
            showInfo('Login successful.');
        }
    }

    function saveAuthInSession(userInfo) {
        sessionStorage.setItem('username', userInfo.username);
        sessionStorage.setItem('name', userInfo.name);
        sessionStorage.setItem('userId', userInfo._id);
        sessionStorage.setItem('authtoken', userInfo._kmd.authtoken);
    }

    function showRegisterView() {
        $('#formRegister').trigger('reset');
        showView('viewRegister');
    }

    function registerUser() {
        let userData = {
            username: $('#formRegister input[name=username]').val(),
            password: $('#formRegister input[name=passwd]').val(),
            name: $('#formRegister input[name=name]').val()
        };
        $.ajax({
            method: "POST",
            url: kinveyBaseUrl + "user/" + kinveyAppKey + "/",
            headers: kinveyAppAuthHeaders,
            data: userData,
            success: registerSuccessful
        });

        function registerSuccessful(userInfo) {
            saveAuthInSession(userInfo);
            showHideMenuLinks();
            showUserHomeView();
            showInfo('User registration successful.');
        }
    }

    function showUserHomeView() {
        $('#viewUserHome h1').text('Welcome, ' +
            sessionStorage.getItem('username') + '!');
        showView('viewUserHome');
    }

    function showShopView() {
        showView('viewShop');

        $.ajax({
            method: "GET",
            url: kinveyBaseUrl + "appdata/" + kinveyAppKey +
                '/products',
            headers: getKinveyUserAuthHeaders(),
            success: renderShop
        });

        function renderShop(products) {
            $('#viewShop .products').empty();
            let table = $("<table><thead><tr><th>Product</th><th>Description</th><th>Price</th><th>Actions</th></tr></thead></table>");
            let tbody = $("<tbody>");
            table.append(tbody);
            for (let product of products) {
                let purchaseButton = $("<button>Purchase</button>")
                    .click(purchaseProduct.bind(null, product));
                let tr = $("<tr>")
                    .append($("<td>").text(product.name))
                    .append($("<td>").text(product.description))
                    .append($("<td>").text(Number(product.price).toFixed(2)))
                    .append($("<td>").append(purchaseButton));
                tbody.append(tr);
            }
            $('#viewShop .products').append(table);
            showInfo('Shop loaded successfully.');
        }
    }

    function purchaseProduct(product) {
        $.ajax({
            method: "GET",
            url: kinveyBaseUrl + "user/" + kinveyAppKey + '/' + sessionStorage.getItem('userId'),
            headers: getKinveyUserAuthHeaders(),
            success: updateCart
        });

        function updateCart(userInfo) {
            userInfo.cart = userInfo.cart || {};

            if(userInfo.cart.hasOwnProperty(product._id)) {
                userInfo.cart[product._id].quantity = Number(userInfo.cart[product._id].quantity) + 1;
            } else {
                userInfo.cart[product._id] = {
                    quantity: 1,
                    product: {
                        name: product.name,
                        description: product.description,
                        price: product.price
                    }
                }
            }

            $.ajax({
                method: "PUT",
                url: kinveyBaseUrl + "user/" + kinveyAppKey + '/' + sessionStorage.getItem('userId'),
                headers: getKinveyUserAuthHeaders(),
                data: userInfo,
                success: productPurchased
            });
        }

        function productPurchased() {
            showInfo('Product purchased.');
            showCartView();
        }
    }

    function showCartView() {
        showView('viewCart');

        $.ajax({
            method: "GET",
            url: kinveyBaseUrl + "user/" + kinveyAppKey + '/' + sessionStorage.getItem('userId'),
            headers: getKinveyUserAuthHeaders(),
            success: function (userInfo) {
                let cart = userInfo.cart || {};
                renderCart(cart);
            }
        });

        function renderCart(cart) {
            $('#viewCart .products').empty();
            let table = $("<table><thead><tr><th>Product</th><th>Description</th><th>Quantity</th><th>Total Price</th><th>Actions</th></tr></thead></table>");
            let tbody = $("<tbody>");
            table.append(tbody);
            console.log(cart);
            for (let product in cart) {

                let discardButton = $("<button>Discard</button>")
                    .click(discardProduct.bind(null, product));
                let tr = $("<tr>")
                    .append($("<td>").text(cart[product].product.name))
                    .append($("<td>").text(cart[product].product.description))
                    .append($("<td>").text(cart[product].quantity))
                    .append($("<td>").text((Number(cart[product].quantity) * Number(cart[product].product.price)).toFixed(2)))
                    .append($("<td>").append(discardButton));
                tbody.append(tr);
            }
            $('#viewCart .products').append(table);
            // showInfo('Cart loaded successfully.');
        }
    }

    function discardProduct(productId) {
        $.ajax({
            method: "GET",
            url: kinveyBaseUrl + "user/" + kinveyAppKey + '/' + sessionStorage.getItem('userId'),
            headers: getKinveyUserAuthHeaders(),
            success: updateCart
        });

        function updateCart(userInfo) {
            userInfo.cart = userInfo.cart || {};
            let newCart = {};
            for(let id in userInfo.cart) {
                if(id != productId) {
                    newCart[id] = userInfo.cart[id];
                }
            }
            userInfo.cart = newCart;

            $.ajax({
                method: "PUT",
                url: kinveyBaseUrl + "user/" + kinveyAppKey + '/' + sessionStorage.getItem('userId'),
                headers: getKinveyUserAuthHeaders(),
                data: userInfo,
                success: productDiscarded
            });
        }
 b
        function productDiscarded() {
            console.log("shit");
            showInfo('Product discarded.');
            showCartView();
        }
    }

    function logoutUser() {
        $.ajax({
            method: "POST",
            url: kinveyBaseUrl + "user/" + kinveyAppKey + "/_logout",
            headers: getKinveyUserAuthHeaders()
        });
        sessionStorage.clear();
        showHideMenuLinks();
        showAppHomeView();
        showInfo('Logout successful.');
    }
}
