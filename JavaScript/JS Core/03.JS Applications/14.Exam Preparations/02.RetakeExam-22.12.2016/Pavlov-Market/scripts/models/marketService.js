let marketService = (() => {
    function getAllProducts() {
        let endpoint = 'products';
        return requester.get('appdata', endpoint, 'kinvey');
    }

    function getUser(userId) {
        let endpoint = userId;

        return requester.get('user', endpoint, 'kinvey');
    }

    function updateUser(userInfo) {

        return requester.update('user', userInfo._id, 'kinvey', userInfo);
    }

    function getProduct(productId) {
        return requester.get('appdata', 'products/' + productId, 'kinvey');
    }

    return {
        getAllProducts,
        getUser,
        updateUser,
        getProduct
    }
})();