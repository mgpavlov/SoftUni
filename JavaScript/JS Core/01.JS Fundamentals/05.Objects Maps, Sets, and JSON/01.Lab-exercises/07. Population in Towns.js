function solution(arr) {
    let result = new Map();
    for (let line of arr) {
        let town = line.split('<->')[0].trim();
        let population = Number(line.split('<->')[1].trim());
        if(!result.has(town)){
            result.set(town, 0);
        }
        result.set(town, result.get(town)+population);
    }
    result = [...result];
    result.forEach(a => console.log(a[0]+' : '+a[1]));
}
solution(['Sofia <-> 1200000',
'Montana <-> 20000',
'New York <-> 10000000',
'Washington <-> 2345000',
'Las Vegas <-> 1000000']
);
solution(['Istanbul <-> 100000',
'Honk Kong <-> 2100004',
'Jerusalem <-> 2352344',
'Mexico City <-> 23401925',
'Istanbul <-> 1000']
);

/*
function solve(arr) {

    let record = new Map();

    for (let kvp of arr) {
        let [town, population] = kvp.split(' <-> ');

        if (!record.has(town)){
            record.set(town, 0);
        }

        record.set(town, record.get(town) + Number(population));
    }

    for (let [town, pop] of record) {
        console.log(`${town} : ${pop}`);
    }
}
*/
