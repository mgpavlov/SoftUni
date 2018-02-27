function solution(arr) {
    let result = new Map();
    for (let line of arr) {
        let data = line.split(/[\->:]/g).filter(a=>a!=='');
        let town = data[0].trim();
        let product = data[1].trim();
        let amount = Number(data[2].trim());
        let price = Number(data[3].trim());
        if(!result.has(town)){
            result.set(town, new Map());
        }
        if(!result.get(town).has(product)){
            result.get(town).set(product, 0)
        }
        result.get(town).set(product, result.get(town).get(product) + amount*price);
    }
    for (let [town, products] of result) {
        console.log(`Town - ${town}`);
        for (let [product, price] of products) {
            console.log(`$$$${product} : ${price}`)
        }
    }
}
solution(['Sofia -> Laptops HP -> 200 : 2000',
'Sofia -> Raspberry -> 200000 : 1500',
'Sofia -> Audi Q7 -> 200 : 100000',
'Montana -> Portokals -> 200000 : 1',
'Montana -> Qgodas -> 20000 : 0.2',
'Montana -> Chereshas -> 1000 : 0.3'
]);
