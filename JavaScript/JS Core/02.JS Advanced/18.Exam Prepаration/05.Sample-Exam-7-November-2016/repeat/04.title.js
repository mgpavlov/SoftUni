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
