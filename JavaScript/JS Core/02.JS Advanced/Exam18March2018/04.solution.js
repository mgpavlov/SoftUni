class PaymentManager{
    constructor(title){
        this.title = title;
        this.element = this.payment();
    }
    render (id){
        let jqueryId = '#'+id;
        $(jqueryId).append(this.element);
    }
    payment(){
        let table = $('<table>');

        let caprion = $(`<caption>${this.title} Payment Manager</caption>`);

        let thead = $('<thead>');
        let tr1 = $('<tr>');
        let name = $('<th class="name">Name</th>');
        let category = $('<th class="category">Category</th>');
        let price = $('<th class="price">Price</th>');
        let action = $('<th>Actions</th>');

        tr1.append(name).append(category).append(price).append(action);
        thead.append(tr1);

        let tbody = $('<tbody class="payments">');

        let tfoot = $('<tfoot class="input-data">');
        let tr3 = $('<tr>');
        let ipName = $(`<td><input name="name" type="text"></td>`);
        let ipCategory = $(`<td><input name="category" type="text"></td>`);
        let ipPrice = $(`<td><input name="price" type="number"></td>`);
        let btnAdd = $('<td><button>Add</button></td>');
        btnAdd.click(()=>{
            if(ipName.find('input').val()!=='' && ipCategory.find('input').val()!=='' && ipPrice.find('input').val()!==''){
                let tr2 = $('<tr>');
                let pName = $(`<td>${ipName.find('input').val()}</td>`);
                let pCategory = $(`<td>${ipCategory.find('input').val()}</td>`);
                let pPrice = $(`<td>${Number(ipPrice.find('input').val()).toFixed(2)}</td>`);
                let tdDel = $('<td>');
                let btnDel = $("<button>Delete</button>");
                btnDel.click(()=> {
                    /*this._del().bind(this)*/
                    tr2.remove();
                });
                tdDel.append(btnDel);
                tr2.append(pName).append(pCategory).append(pPrice).append(tdDel);
                tbody.append(tr2);

                ipName.find('input').val('');
                ipCategory.find('input').val('');
                ipPrice.find('input').val('');
            }
        });

        tr3.append(ipName).append(ipCategory).append(ipPrice).append(btnAdd);
        tfoot.append(tr3);

        table.append(caprion).append(thead).append(tbody).append(tfoot);

        return table;
    }
/*    _del() {
        $(this.element).find('.payments tr td').remove();
    }*/

}
