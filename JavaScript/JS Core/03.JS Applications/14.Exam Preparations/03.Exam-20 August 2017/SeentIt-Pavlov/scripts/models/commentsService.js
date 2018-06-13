let commentsService  = (() => {
    function listPostComments(postId) {
        let endpoint = `comments?query={"postId":"${postId}"}&sort={"_kmd.ect": -1}`;

        return requester.get('appdata', endpoint, 'kinvey');
    }

    function createComment( postId, content, author ) {
        let commentData = {
            postId,
            content,
            author
        };

        return requester.post('appdata', 'comments', 'kinvey', commentData);
    }

    function deleteComment(commentId) {
        let endpoint = `comments/${commentId}`;

        return requester.remove('appdata', endpoint, 'kinvey');
    }


    return {
        listPostComments,
        createComment,
        deleteComment
    }
})();