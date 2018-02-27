function solve(arr) {
    let result = {};
    let sort = new Set();
    for (let line of arr) {
        let[juice, quantity] = line.split(' => ').filter(a=>a!=='')
        if(!result.hasOwnProperty(juice)){
            result[juice] = 0;
        }
        result[juice] += Number(quantity);
        if(result[juice] >= 1000){
            sort.add(juice);
        }
    }
    for (let juice of sort) {
        console.log(juice+' => '+ Math.floor(result[juice]/1000))
    }
}
solve(['Orange => 2000',
    'Peach => 1432',
    'Banana => 450',
    'Peach => 600',
    'Strawberry => 549'
]);


/*
function solve(arr) {
    let record = new Map();
    let output = new Map();

    for (let juice of arr) {
        let [fruit, quantity] = juice.split(' => ');

        quantity = Number(quantity);

        if (!record.has(fruit)) {
            record.set(fruit, 0);
        }

        let oldQuantity = record.get(fruit);

        record.set(fruit, oldQuantity + quantity);

        if (record.get(fruit) >= 1000) {
            output.set(fruit, record.get(fruit));
        }
    }

    for (let [fruit, quantity] of output) {
        console.log(`${fruit} => ${Math.floor(quantity / 1000)}`);
    }
}
*/
