class MailBox {
    constructor(){
        this.mailBox = [];
    }

    addMessage(subject, text){
        this.mailBox.push({subject: subject, text: text});

        return this;
    }

    get messageCount(){
        return this.mailBox.length;
    }

    deleteAllMessages(){
        this.mailBox = [];
    }

    findBySubject(substr){
        let arrFindBySubject = [];
        for (let mail of this.mailBox) {
            if (mail['subject'].includes(substr)){
                arrFindBySubject.push(mail)
            }
        }
        return arrFindBySubject;
    }
    toString(){
        let result = [];
        if (this.mailBox.length > 0) {
            for (let mail of this.mailBox) {
               result.push(` * [${mail.subject}] ${mail.text}`)
            }
            return result.join('\n');
        }
        return ' * (empty mailbox)';
    }
}
