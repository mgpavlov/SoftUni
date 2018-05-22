/*function tableBuilder(selector) {
    let container = $(selector);
    
    function createTable(columnNames) {
        let table = $('<table>');
        let tableRow = $('<tr>');
        for(let column of columnNames) {
            tableRow.append($('<th>').text(column));
        }
        tableRow.append($('<th>Action</th>'));
        table.append(tableRow);
        container.html(""); //Note that the table column names and table data cells may hold special characters that should be displayed as text.
        container.append(table);
    }

    function fillData(dataRows) {
        let table = $(`${selector} table`);
        for(let dataRow of dataRows) {
            let tableRow = $('<tr>');
            for(let el of dataRow) {
                tableRow.append($(`<td>`).text(el));
            }

            let deleteBtn = $('<td><button>Delete</button></td>');
            deleteBtn.on('click', function () {
                $(this).parent().remove();
            });
            tableRow.append(deleteBtn);
            table.append(tableRow);
        }
    }

    return {
        createTable,
        fillData
    }
}*/

function tableBuilder(selector) {

    let container = $(selector);
    container.empty();
    let table = $('<table>');
    container.append(table);
    let tableHead = $('<tr>');

    function createTable(columnNames){
        for (let columnName of columnNames) {
            tableHead.append($('<th>').text(columnName))
        }
        tableHead.append($('<th>').text('Action'));
        table.append(tableHead);
    }

    function fillData(dataRows) {
        for (let dataRow of dataRows) {
            let tr = $('<tr>');
            for (let dataRowElement of dataRow) {
                tr.append($('<td>').text(dataRowElement))
            }
            tr.append($('<td>').append($('<button>').text('Delete').click((ev)=>{
                $(ev.target).parent().parent().remove();
            })));
            table.append(tr);
        }
    }

    return{
        createTable,
        fillData
    }
}