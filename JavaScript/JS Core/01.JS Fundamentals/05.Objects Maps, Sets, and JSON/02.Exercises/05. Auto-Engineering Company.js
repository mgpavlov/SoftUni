function solve(arr) {
    let result = new Map();
    for (let line of arr) {
        let[brand, model, quantity] = line.split(' | ').filter( a => a !=='' );
        if(!result.has(brand)){
            result.set(brand, new Map())
        }
        if (!result.get(brand).has(model)){
            result.get(brand).set(model, 0)
        }
        result.get(brand).set(model, result.get(brand).get(model) + Number(quantity));
    }
    for (let [brand, modelQuantity] of result) {
        console.log(brand)
        for (let [model, quantity] of modelQuantity) {
            console.log(`###${model} -> ${quantity}`);
        }
    }
}

solve(['Audi | Q7 | 1000',
    'Audi | Q6 | 100',
    'BMW | X5 | 1000',
    'BMW | X6 | 100',
    'Citroen | C4 | 123',
    'Volga | GAZ-24 | 100000',
    'Lada | Niva | 1000000',
    'Lada | Jigula | 1000000',
    'Citroen | C4 | 22',
    'Citroen | C5 | 10'
]);


/*
function solve(arr) {

    let record = new Map();

    for (let line of arr) {
        let [brand, model, quantity] = line.split(' | ')

        quantity = Number(quantity);

        if (!record.has(brand)){
            record.set(brand, new Map());
        }

        if (!record.get(brand).has(model)){
            record.get(brand).set(model, 0);
        }

        let oldQuantity = record.get(brand).get(model);

        record.get(brand).set(model, quantity + oldQuantity);
    }

    for (let [brand, models] of record) {
        console.log(brand);

        for (let [model, quantity] of models) {
            console.log(`###${model} -> ${quantity}`);
        }
    }
}
*/
