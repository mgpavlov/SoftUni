let messagesService = (() => {
    function loadMyMessages (username){
        return requester.get('appdata', `messages?query={"recipient_username":"${username}"}`, 'kinvey')
    }
    function loadArchiveMessages(username){
        return requester.get('appdata', `messages?query={"sender_username":"${username}"}`, 'kinvey')
    }
    function loadAllUsers(){
        return requester.get('user', ``, 'kinvey')
    }
    function sendMessage (sender_username, sender_name, recipient_username, text ){
        let messageBody = {
            "sender_username": sender_username,
            "sender_name": sender_name,
            "recipient_username": recipient_username,
            "text": text
        };
        return requester.post('appdata', 'messages', 'kinvey', messageBody);
    }
    function deleteMessage(messageId){
        /*let id = sessionStorage.getItem('_id');*/
        requester.remove('appdata', 'messages/' + messageId, 'kinvey');
    }

    return {
        loadMyMessages,
        loadArchiveMessages,
        deleteMessage,
        loadAllUsers,
        sendMessage
    }
})();