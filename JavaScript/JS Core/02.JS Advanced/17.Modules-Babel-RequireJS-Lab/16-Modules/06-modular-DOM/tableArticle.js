let Article = require('./article');

class TableArticle extends Article {
    constructor(title, content) {
        super(title, content);
        this.headings = null;
        this.data = null;
    }

    loadData(headings, data) {
        this.headings = headings;
        this.data = data;
    }

    getElementString() {
        let table = $('<table>')
            .addClass('data');
        let tableHeadings = $('<tr>');
        for (let heading of this.headings) {
            tableHeadings.append(
                $('<th>').text(heading));
        }
        table.append(tableHeadings);

        for (let data of this.data) {
            let row = $('<tr>');
            for (let heading of this.headings) {
                let prop = heading
                    .replace(/\s+/g, '')
                    .toLowerCase();
                row.append(
                    $('<td>').text(data[prop]));
            }
            table.append(row);
        }
        let html = $(super.getElementString());
        html.append(
            $('<div>')
                .addClass('table')
                .append(table));
        return html;
    }
}

module.exports = TableArticle;