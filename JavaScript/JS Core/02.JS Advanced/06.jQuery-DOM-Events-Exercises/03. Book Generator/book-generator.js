function createBook(selector, titleName, authorName, isbnNum) {

    let bookGenerator = (function () {
        let id = 1;
        return function (selector, titleName, authorName, isbnNum) {
            let container = $(selector);
            /*let fragment = document.createDocumentFragment();*/
            let book = $('<div style="border: medium none"></div>');
            book.attr('id', `book${id}`);
            /* book.css('border', '2px solid blue');*/
            /*/medium none*/
            let title = $(`<p class = "title">${titleName}</p>`);
            let author = $(`<p class = "author">${authorName}</p>`);
            let isbn = $(`<p class = "isbn">${isbnNum}</p>`);
            let select = $(`<button>Select</button>`);
            let deselect = $(`<button>Deselect</button>`);

            title.appendTo(book);
            author.appendTo(book);
            isbn.appendTo(book);
            select.appendTo(book);
            deselect.appendTo(book);
            console.log(book);
            book.appendTo(container);

            select.click(selected);

            function selected() {
                    book.css('border', '2px solid blue');
            }
            deselect.click(deselected);
            function deselected() {
                    book.css('border', 'none');
            }
            id++;
        }
    }());
    bookGenerator(selector, titleName, authorName, isbnNum);
}
