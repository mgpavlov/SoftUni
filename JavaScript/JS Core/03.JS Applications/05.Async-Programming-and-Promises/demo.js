function attachEvents() {
    const kinveyAppId = "kid_r17TPYmGx";
    const serviceUrl = "https://baas.kinvey.com/appdata/" + kinveyAppId;
    const kinveyUsername = "peter";
    const kinveyPassword = "p";
    const base64auth = btoa(kinveyUsername + ":" + kinveyPassword);
    const authHeaders = {"Authorization": "Basic " + base64auth};

    let postOptionsContainer = $('#posts');

    $('#btnLoadPosts').click(loadPostsInDropDown);
    $('#btnViewPost').click(loadSelectedPost);

    function loadPostsInDropDown() {
        let getPostsRequest = {
            method: "GET",
            url: serviceUrl + "/posts",
            headers: authHeaders
        };
        $.ajax(getPostsRequest)
            .then(displayPostOptions)
            .catch(displayError);

        function displayPostOptions(posts) {
            postOptionsContainer.empty();
            for (let post of posts) {
                $('<option>').text(post.title)
                    .val(post._id)
                    .appendTo(postOptionsContainer);
            }
        }
    }

    function loadSelectedPost() {
        let selectedPost_Id = postOptionsContainer.val(); // NB selected option
        let postRequest = $.ajax({
            method: "GET",
            url: serviceUrl + "/posts/" + selectedPost_Id,
            headers: authHeaders
        });
        let postCommentsRequest = $.ajax({
            method: "GET",
            url: serviceUrl + `/comments/?query={"post_id":"${selectedPost_Id}"}`, // NB query post's comments
            headers: authHeaders
        });
        Promise.all([postRequest, postCommentsRequest])
            .then(displayPostWithComments)
            .catch(displayError);

        function displayPostWithComments([post, postComments]) {
            // console.dir(post);
            // console.dir(postComments);
            $('#post-title').text(post.title);
            $('#post-body').text(post.body);
            $('#post-comments').empty();
            for (let comment of postComments) {
                $('<li>').text(comment.text)
                    .appendTo($('#post-comments'));
            }
        }
    }

    function displayError(error) {
        // console.dir(error);
        let errorMsg = 'Error: ';
        if (error.status && error.statusText)
            errorMsg += `${error.status} (${error.statusText})`;
        else if (error.name && error.message)
            errorMsg += `${error.name} (${error.message})`;

        let errorDiv = $('<div>').text(errorMsg)
            .prependTo($('body'));
        setTimeout(function () {
            errorDiv.fadeOut(function () {
                errorDiv.remove();
            });
        }, 2000);
    }
}