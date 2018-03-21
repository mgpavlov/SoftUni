class Contact{
    constructor(firstName, lastName, phone, email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.element = this.article();
        this.online = false;
    }
    get online() {
        return this._online;
    }
    set online(value) {
        if(value){
            this.element.find('.title').addClass('online')
        } else {
            this.element.find('.title').removeClass('online')
        }
        this._online = value;
    }
    render (id){
        let jqueryId = '#'+id;
        $(jqueryId).append(this.element);
    }
    article(){
        let article = $('<article>');
        let button = $('<button>').html('&#8505;').click( () => infoDiv.toggle());
        let titleDiv = $('<div>').addClass('title').text(`${this.firstName} ${this.lastName}`).append(button);
        let infoDiv = $('<div>').addClass('info').css('style', 'display: block').hide();
        let phoneSpan = $('<span>').html('&phone;').text(this.phone);
        let emailSpan = $('<span>').html('&#9993;').text(this.email);

        infoDiv.append(phoneSpan).append(emailSpan);
        article.append(titleDiv).append(infoDiv);
        return article
    }
}
let contacts = [
    new Contact("Ivan", "Ivanov", "0888 123 456", "i.ivanov@gmail.com"),
    new Contact("Maria", "Petrova", "0899 987 654", "mar4eto@abv.bg"),
    new Contact("Jordan", "Kirov", "0988 456 789", "jordk@gmail.com")
];
contacts.forEach(c => c.render('main'));
