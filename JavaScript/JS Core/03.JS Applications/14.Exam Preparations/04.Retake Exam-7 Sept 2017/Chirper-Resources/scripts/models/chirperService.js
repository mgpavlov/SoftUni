let chirperService = (() => {
    function getAllChirpsFromSubscriptions (subs) {
        let endpoint = `chirps?query={"author":{"$in": [${subs}]}}&sort={"_kmd.ect": 1}`;
        return requester.get('appdata', endpoint, 'kinvey');
    }

    function getAllChirpsFromUser (username) {
        let endpoint = `chirps?query={"author":"${username}"}&sort={"_kmd.ect": 1}`;
        return requester.get('appdata', endpoint, 'kinvey');
    }
    
    function createChirp(text, author) {
        let chirpObj = {
            text,
            author
        };

        return requester.post('appdata', 'chirps', 'kinvey', chirpObj);
    }

    function deleteChirp(chirpId) {
        return requester.remove('appdata', `chirps/${chirpId}`, 'kinvey');
    }

    function countChirps (username) {
        let endpoint = `chirps?query={"author":"${username}"}`;
        return requester.get('appdata', endpoint, 'kinvey');
    }
    
    function countFollowing(username) {
        let endpoint = `?query={"username":"${username}"}`;
        return requester.get('user', endpoint, 'kinvey');
    }

    function countFollowers(username) {
        let endpoint = `?query={"subscriptions":"${username}"}`;
        return requester.get('user', endpoint, 'kinvey');
    }

    function discoverPage() {
        return requester.get('user','', 'kinvey');
    }

    function followUnFollow(userId, subscriptions ) {

        return requester.update('user', userId, 'kinvey', subscriptions);
    }


    return {
        getAllChirpsFromSubscriptions,
        getAllChirpsFromUser,
        createChirp,
        deleteChirp,
        countChirps,
        countFollowing,
        countFollowers,
        discoverPage,
        followUnFollow
    }
})()