function initializeTable() {
    $('#createLink').click(addCountry);
    createCountry('Bulgaria', 'Sofia');
    createCountry('Germany', 'Berlin');
    createCountry('Russia', 'Moscow');

    fixLinks();

    function fixLinks() {
          $('tr a').show();
          $('tr:last-child a:contains(Down)').hide();
          $('tr:eq(2) a:contains(Up)').hide();
    }

    function addCountry() {
        let country = $('#newCountryText').val();
        let capital = $('#newCapitalText').val();
        createCountry(country, capital);
        fixLinks();
        $('#newCountryText').val('');
        $('#newCapitalText').val('');
    }

    function createCountry(country, capital) {
        let tr = $('<tr>');
        tr.append(
            `<td>${country}</td>
            <td>${capital}</td>`)
            .append($('<td>')
                .append($('<a href="#">[Up]</a>').click(moveUp))
                .append($('<a href="#">[Down]</a>').click(moveDown))
                .append($('<a href="#">[Delete]</a>').click(deleteRow)));
        tr.css('display', 'none');
        tr.appendTo($('#countriesTable'));
        tr.fadeIn();

        function deleteRow() {
            tr.fadeOut(()=>{
                tr.remove();
                //$(this).parent().parent().remove();
                fixLinks();
                tr.fadeIn();
            });
        }
        function moveUp() {
            tr.fadeOut(()=> {
                tr.insertBefore(tr.prev());
                fixLinks();
                tr.fadeIn();
            });
        }
        function moveDown() {
            tr.fadeOut(()=> {
                tr.insertAfter(tr.next());
                fixLinks();
                tr.fadeIn();
            });
        }
    }
}