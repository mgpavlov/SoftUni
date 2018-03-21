class MailBox {
    constructor() {
        this.messages = [];
    }

    addMessage(subject, text) {
        this.messages.push({
            subject: subject,
            text: text
        });

        return this; //Returns the mailbox itself to allow chaining.
    }

    get messageCount() {
        return this.messages.length;
    }

    deleteAllMessages() {
        this.messages = [];
        //this.nessages.length = 0;
    }

    findBySubject(substr) {
        return this.messages
            .filter(m => m.subject.includes(substr));
    }

    toString() {
        if(this.messages.length > 0){
            return this.messages
                .map(m => ` * [${m.subject}] ${m.text}`)
                .join('\n');
        }

        return ' * (empty mailbox)';
    }
}

let mb = new MailBox();
console.log("Msg count: " + mb.messageCount);
console.log('Messages:\n' + mb);
mb.addMessage("meeting", "Let's meet at 17/11");
mb.addMessage("beer", "Wanna drink beer tomorrow?");
mb.addMessage("question", "How to solve this problem?");
mb.addMessage("Sofia next week", "I am in Sofia next week.");
console.log("Msg count: " + mb.messageCount);
console.log('Messages:\n' + mb);
console.log("Messages holding 'rakiya': " +
    JSON.stringify(mb.findBySubject('rakiya')));
console.log("Messages holding 'ee': " +
    JSON.stringify(mb.findBySubject('ee')));

mb.deleteAllMessages();
console.log("Msg count: " + mb.messageCount);
console.log('Messages:\n' + mb);

console.log("New mailbox:\n" +
    new MailBox()
        .addMessage("Subj 1", "Msg 1")
        .addMessage("Subj 2", "Msg 2")
        .addMessage("Subj 3", "Msg 3")
        .toString());
