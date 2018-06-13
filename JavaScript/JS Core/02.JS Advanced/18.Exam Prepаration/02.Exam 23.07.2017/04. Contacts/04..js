class Contact {
    get online() {
        return this._online;
    }

    set online(value) {
        if (value){
            this.element.find('.title').addClass('online')
        }else {
            this.element.find('.title').removeClass('online')
        }
        this._online = value;
    }
    constructor(firstName, lastName, phone, email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.element = this.article();
        this._online = false;
    }
    render(id) {
        let mainDiv = $('#' + id);
        mainDiv.append(this.element);
    }

    article (){
        let article = $('<article>');
        let button = $('<button>').html('&#8505;')//.click( () => infoDiv.toggle());
        let title = $(`<div class="title">${this.firstName} ${this.lastName}</div>`).append(button.click(appendToggle));
        let infoDiv = $('<div>').addClass('info').css('style','display: block').hide();
        let phone = $('<span>').html("&phone;").text(this.phone);
        let email = $('<span>').html("&#9993;").text(this.email);

        function appendToggle(){
            infoDiv.toggle();
        }
        infoDiv.append(phone).append(email);
        article.append(title).append(infoDiv);
        return article
    }
}

let contacts = [
    new Contact("Ivan", "Ivanov", "0888 123 456", "i.ivanov@gmail.com"),
    new Contact("Maria", "Petrova", "0899 987 654", "mar4eto@abv.bg"),
    new Contact("Jordan", "Kirov", "0988 456 789", "jordk@gmail.com")
];
contacts.forEach(c => c.render('main'));

// After 1 second, change the online status to true
setTimeout(() => contacts[1].online = true, 2000);
