function solution(arr) {
    let result = new Map();
    for (let line of arr) {
        let data = line.split(' | ').filter(a=>a!=='');
        let town = data[0].trim();
        let product = data[1].trim();
        let price = Number(data[2].trim());
        if(!result.has(product)){
            result.set(product, new Map());
        }
        if(!result.get(product).has(town)){
            result.get(product).set(town, Number.POSITIVE_INFINITY)
        }
        result.get(product).set(town, price);
    }

    for (let [product, towns] of result) {
        let minPrice = Number.POSITIVE_INFINITY;
        let theTown = '';
        for (let [town, price] of towns) {
            if(price<minPrice){
                minPrice = price;
                theTown = town;
            }
        }
        console.log(`${product} -> ${minPrice} (${theTown})`)
    }
}
solution(['Sample Town | Sample Product | 1000',
    'Sample Town | Orange | 2',
    'Sample Town | Peach | 1',
    'Sofia | Orange | 3',
    'Sofia | Peach | 2',
    'New York | Sample Product | 1000.1',
    'New York | Burger | 10'
]);