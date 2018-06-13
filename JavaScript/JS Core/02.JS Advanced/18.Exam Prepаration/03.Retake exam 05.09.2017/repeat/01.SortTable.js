function sort(colIndex, descending) {
    let columns = $('#products tbody td');
    let rows = $('#products tbody tr');
    let productPrice = [];
    for (let i = 0; i < columns.length; i+=2) {
        productPrice[$(columns[i]).text()] = $(columns[i+1]).text();
    }

    console.log(productPrice);
    /*let names = columns.filter((i, e) => i % 2 === 0);
    let prices = columns.filter((i, e) => i % 2 !== 0);*/

    if (colIndex === 0) {
        if (descending === true) {
            productPrice.sort((a,b)=> a.key > b.key)


        } else {
            Array.from(productPrice).sort((a, b) => a.key > b.key);
        }
    } else {
        if (descending === true) {
            Array.from(productPrice).sort((a, b) => a.value < b.value);
        } else {
            Array.from(productPrice).sort((a, b) => a.value > b.value);
        }
    }


}

function sort(colIndex, descending) {
    let col =  $('#products tbody tr td');
    let columns = col.toArray().map(t=>t.textContent);
    let names = columns.filter((e, i)=>i%2===0);
    let price = columns.filter((e, i)=>i%2!==0).map(Number);
    let obj1 = {};
    for (let i = 0; i < columns.length-1; i+=2) {
        obj1[columns[i]] = columns[i+1];
    }
    let obj2 = {};
    for (let i = 0; i < columns.length-1; i+=2) {
        obj2[Number(columns[i+1])] = columns[i];
    }

    if(colIndex === 0){
        if(descending === true){
            names.sort((a,b)=>b>a);
        }else{
            names.sort((a,b)=>a>b);
        }
        for (let i = 0, j=0; i < names.length; i++, j+=2) {
            col[j].textContent = names[i];
            col[j+1].textContent = obj1[names[i]];
        }
    }else{
        if(descending === true){
            price.sort((a,b)=>b-a);
        }else{
            price.sort((a,b)=>a-b);
        }
        price.map(toString);
        for (let i = 0, j=0; i < price.length; i++, j+=2) {
            col[j].textContent = obj2[price[i]];
            col[j+1].textContent = price[i].toFixed(2);
        }
    }
    let tableRows = $('tbody > tr');
    console.log(tableRows);
}
