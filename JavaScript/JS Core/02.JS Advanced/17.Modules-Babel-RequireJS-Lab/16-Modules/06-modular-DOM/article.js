let BaseElement = require('./baseElement');

class Article extends BaseElement {
    constructor(title, content) {
        super();
        this.title = title;
        this.content = content;
        this.timestamp = new Date();
    }

    getElementString() {
        let html =
            $('<div>')
                .addClass('article')
                .append($('<div>')
                    .addClass('article-title')
                    .text(this.title))
                .append($('<p>')
                    .text(this.content));
        return html;
    }
}

module.exports = Article;