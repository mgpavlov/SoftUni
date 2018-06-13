class PaymentManager {
    constructor(title){
        this.title = title;
    }

    render(id){
        let container = $('#'+id);
        let table = $('<table>');
        let caption = $('<caption>').text(`${this.title} Payment Manager`);

        let thead = $('<thead>');
        thead.append($('<tr>')
            .append($('<th class="name">Name</th>'))
            .append($('<th class="category">Category</th>'))
            .append($('<th class="price">Price</th>'))
            .append($('<th>Actions</th>')));

        let tbody = $('<tbody>').addClass('payments');

        let tfoot = $('<tfoot>').addClass('input-data');
        tfoot.append($('<tr>')
            .append($(`<td><input name="name" type="text"></td>`))
            .append($(`<td><input name="category" type="text"></td>`))
            .append($(`<td><input name="price" type="number"></td>`))
            .append($(`<td>`)
                .append($('<button>')
                    .text('Add')
                    .click((event)=>{
                        let name = $(event.target).parent().parent().find("input[name ='name']").val();
                        let category = $(event.target).parent().parent().find("input[name ='category']").val();
                        let price = $(event.target).parent().parent().find("input[name ='price']").val();
                        if (name === '' ||
                            category === '' ||
                            price === ''
                        ){
                            return;
                        }
                        tbody.append($('<tr>')
                            .append($(`<td>`).text(name))
                            .append($(`<td>`).text(category))
                            .append($(`<td>`).text(Number(price)))
                            .append($(`<td>`)
                                .append($('<button>')
                                    .text('Delete')
                                    .click((ev)=>{
                                        $(ev.target).parent().parent().remove()
                                    }))));
                        $(event.target).parent().parent().find("input[name ='name']").val('');
                        $(event.target).parent().parent().find("input[name ='category']").val('');
                        $(event.target).parent().parent().find("input[name ='price']").val('');
                    }))));


        container.append(table.append(caption).append(thead).append(tbody).append(tfoot));
    }
}