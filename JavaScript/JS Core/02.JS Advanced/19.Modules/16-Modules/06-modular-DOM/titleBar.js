let BaseElement = require('./baseElement');

class TitleBar extends BaseElement {
    constructor(title) {
        super();
        this.title = title;
        this.links = [];
    }

    addLink(href, name) {
        let link =
            $('<a>')
                .addClass('menu-link')
                .attr('href', href)
                .text(name);
        this.links.push(link);
    }

    getElementString() {
        let nav = $('<nav>').addClass('menu');
        for (let i = 0; i < this.links.length; i++) {
            nav.append(this.links[i]);
            if (i != this.links.length - 1)
                nav.append('|');
        }
        let html =
            $('<header>')
                .addClass('header')
                .append($('<div>')
                    .append($('<span>')
                        .addClass('title')
                        .text(this.title)))
                .append($('<div>')
                    .append(nav));
        return html;
    }
}

module.exports = TitleBar;