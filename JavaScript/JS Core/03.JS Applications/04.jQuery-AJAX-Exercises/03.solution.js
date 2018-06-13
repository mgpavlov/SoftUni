function attachEvents() {
    let messages = $('#messages');
    let name = $('#author');
    let message = $('#content');
    let sendBtn = $('#submit');
    let refreshBtn = $('#refresh');

    sendBtn.click(submitMsg);
    refreshBtn.click(refreshData);

    function submitMsg() {
        let messageJson = {
            "author": name.val(),
            "content": message.val(),
            "timestamp": Date.now()
        };

        $.ajax({
            method:'POST',
            url: 'https://messenger-d4d97.firebaseio.com/messenger.json',
            data: JSON.stringify(messageJson),
            success: refreshData,
            error: ()=>{messages.text('Error')}
        });
    }

    function refreshData() {
        $.ajax({
            method: 'GET',
            url: 'https://messenger-d4d97.firebaseio.com/messenger.json',
            success: handleSuccess,
            error: ()=>{messages.text('Error')}
        });
        function handleSuccess(res) {
            let msg = '';
            let sorter = Object.keys(res).sort((a,b)=>res[a]['timestamp'] - res[b]['timestamp']);
            for (let key of sorter) {
                msg += res[key]['author']+': '+res[key]['content']+'\n';
            }
            messages.text(msg);
        }
    }

}