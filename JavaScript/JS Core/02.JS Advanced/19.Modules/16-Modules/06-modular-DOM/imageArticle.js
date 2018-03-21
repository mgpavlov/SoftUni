let Article = require('./article');

class ImageArticle extends Article {
    constructor(title, content, image) {
        super(title, content);
        this.image = image;
    }

    getElementString() {
        let html = $(super.getElementString());
        html.find('.article-title')
            .after(
                $('<div>')
                    .addClass('image')
                    .append($('<img>')
                        .attr('src', this.image)));
        return html;
    }
}

module.exports = ImageArticle;