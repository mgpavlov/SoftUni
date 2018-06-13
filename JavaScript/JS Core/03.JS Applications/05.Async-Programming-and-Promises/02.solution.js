/*
function attachEvents(){
    let btnLoad = $('#btnLoadPosts').click(loadPosts);
    let btnView = $('#btnViewPost').click(viewPosts);
    const kinveyUsername = "peter";
    const kinveyPassword = "p";
    const base64auth = btoa(kinveyUsername + ":" + kinveyPassword);
    const authHeaders = { "Authorization": "Basic " + base64auth };
    let posts = $('#posts');
    let comments = $('#post-comments');
    let body = $('#post-body');
    let postTitle = $('#post-title');
    let baseUrl = 'https://baas.kinvey.com/appdata/kid_ry4WUEU9M/';

    function loadPosts() {
        //comments.empty();
        //body.empty();
        //postTitle.text("Post Details");
        $.ajax({
            url: baseUrl+'posts',
            headers: authHeaders,
            success: fillSelectData,
            error: handleError
        });
        function fillSelectData(res) {
                posts.empty();
            for (let post of res) {
                posts.append($('<option>').text(post.title).val(post._id));
            }
        }
    }

    function viewPosts() {
        //let postId = posts.find(':selected').val();
        let selectedPostId = posts.val();

        let requestComments = $.ajax({
            url: baseUrl + `/comments/?query={"post_id":"${selectedPostId}"}`,
            headers: authHeaders
        });

        let requestPosts = $.ajax({
            url: baseUrl + "posts/" + selectedPostId,
            headers: authHeaders
        });

        Promise.all([requestPosts, requestComments])
            .then(displayPostsWithComments)
            .catch(handleError);
        function displayPostsWithComments([post, postComments]) {
            postTitle.text(post.title);
            //body.empty();
            body.text(post.body);
            comments.empty();
            for (let comment of postComments) {
                 comments.append($(`<li>${comment.text}</li>`));
            }
        }
    }

    function handleError(err) {
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
}*/

//JUDGE :
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
/*
function attachEvents() {
    const kinveyAppId = 'kid_ByQVWW9UZ';
    const serviceUrl = 'https://baas.kinvey.com/appdata/' + kinveyAppId;
    const kinveyUsername = 'peter';
    const kinveyPassword = 'p';
    const base64auth = btoa(kinveyUsername + ':' + kinveyPassword);
    const authHeaders = {
        'Authorization': 'Basic ' + base64auth
    };
    $('#btnLoadPosts').click(loadPosts);
    $('#btnViewPost').click(viewPost);


    // ajax request
    function request(endpoint) {
        return $.ajax({
            method: 'GET',
            url: serviceUrl + endpoint,
            headers: authHeaders
        })
    }

    // Load all of the posts in a select with options
    function loadPosts() {
        request('/posts/')
            .then(displayPosts)
            .catch(handleError)
    }

    // View a selected post and it's comments
    function viewPost() {
        let selectedPostId = $('#posts').val();
        if(!selectedPostId){
            return; // If there is no selected option we shouldn't do anything
        }

        let loadPostPromise = request('/posts/' + selectedPostId);
        let loadPostCommentsPromise = request('/comments/' + `?query={"post_id":"${selectedPostId}"}`);

        // The execution is syncronous (the promises are executed from first to last)
        Promise.all([loadPostPromise, loadPostCommentsPromise])
            .then(displayPostAndComments)
            .catch(handleError);
    }

    function displayPosts(postsArr) {
        let select = $('#posts');
        select.empty();
        for(let postObj of postsArr) {
            let option = $('<option>')
                .attr('value', postObj['_id'])
                .text(postObj['title']);
            select.append(option);
        }
    }

    function displayPostAndComments(data) {
        let [postData, commentsData] = data;
        $('#post-title').text(postData['title']);
        $('#post-body').text(postData['body']);
        let commentsList = $('#post-comments');
        commentsList.empty();
        for(let comment of commentsData) {
            commentsList.append($('<li>')
                .text(comment['text']))
        }
    }

    // If the AJAX call fails an error pops up at the top of the body
    // and after a few seconds it fades out
    function handleError(err) {
        let errorDiv = $("<div>").text("Error: " +
            err.status + ' (' + err.statusText + ')');
        $(document.body).prepend(errorDiv);
        setTimeout(function() {
            $(errorDiv).fadeOut(function() {
                $(errorDiv).remove();
            });
        }, 3000);
    }
}
*/
