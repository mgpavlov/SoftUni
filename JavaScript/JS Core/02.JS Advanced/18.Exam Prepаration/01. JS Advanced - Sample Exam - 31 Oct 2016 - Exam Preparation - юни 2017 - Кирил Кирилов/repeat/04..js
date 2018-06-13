class TitleBar {
    constructor(title){
        this.title = title;
        this.menu = []
    }
    addLink(href, name){
        //let link = $('<a>').attr('href', href).addClass('menu-link').text(name);
        let link = $('<a>')
            .attr('href', href)
            .addClass('menu-link')
            .text(name);
        this.menu.push(link)
    }

    appendTo(selector){
        let header = $('<header>').addClass('header');

        let headerRow =  $("<div>").addClass('header-row')
                .append($('<a>').addClass("button").html("&#9776;").click(showMenu))
                .append($(`<span class="title">${this.title}</span>`));

        let drawer = $('<div>').addClass('drawer');
        let menu = $('<nav>').addClass('menu');

        for (let link of this.menu) {
            menu.append(link)
        }
        function showMenu() {
            $('.drawer').toggle();
        }

        $(selector).append(header.append(headerRow).append(drawer.append(menu)))
    }
}

/*class TitleBar {
    constructor(title) {
        this.title = title;
        this.links = [];
    }

    addLink(href, name) {
        let link = $(`<a class="menu-link" href=${href}>${name}</a>`);
        this.links.push(link);
    }

    appendTo(selector) {
        let mainDiv = $(selector);

        let header = $('<header>').addClass('header');
        let headerRow = $('<div>').addClass('header-row');
        let button = $('<a class="button">&#9776;</a>');
        button.click(function () {
            $('div.drawer').toggle();
        });
        let span = $(`<span class="title">${this.title}</span>`);

        let drawer = $('<div>').addClass('drawer');
        let nav = $('<nav>').addClass('menu');

        this.links.forEach(link => nav.append(link));
        drawer.append(nav);
        header.append(drawer);

        headerRow.append(button);
        headerRow.append(span);
        header.append(headerRow);

        mainDiv.append(header);
    }
}*/


let header = new TitleBar('Title Bar Problem');
header.addLink('/', 'Home');
header.addLink('about', 'About');
header.addLink('results', 'Results');
header.addLink('faq', 'FAQ');
header.appendTo('#container');
