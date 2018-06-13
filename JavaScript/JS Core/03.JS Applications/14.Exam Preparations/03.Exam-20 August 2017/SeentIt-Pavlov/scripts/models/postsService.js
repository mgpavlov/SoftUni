let postsService = (() => {
    function listAllPosts() {
        let endpoint = `posts?query={}&sort={"_kmd.ect": -1}`;

        return requester.get('appdata', endpoint, 'kinvey');
    }


    function editPost(postId, author, title, description, url, imageUrl) {
        let endpoint = 'posts/' + postId;
        let editedPostData = {
            author,
            title,
            description,
            url,
            imageUrl
        };
        return requester.update('appdata', endpoint, 'kinvey', editedPostData);
    }


    function deletePost(postId) {
        let endpoint = 'posts/' + postId;

        return requester.remove('appdata', endpoint, 'kinvey');
    }


    function createPost(author, title, description, url, imageUrl) {
        let postData = {
            author,
            title,
            description,
            url,
            imageUrl
        };

        return requester.post('appdata', 'posts', 'kinvey', postData);
    }


    function listMyPosts(username) {
        let endpoint = `posts?query={"author":"${username}"}&sort={"_kmd.ect": -1}`;

        return requester.get('appdata', endpoint, 'kinvey');
    }


    function postDetails(postId) {
        let endpoint = 'posts/' + postId;

        return requester.get('appdata', endpoint, 'kinvey');
    }


    return {
        listAllPosts,
        editPost,
        deletePost,
        createPost,
        listMyPosts,
        postDetails
    }
})();