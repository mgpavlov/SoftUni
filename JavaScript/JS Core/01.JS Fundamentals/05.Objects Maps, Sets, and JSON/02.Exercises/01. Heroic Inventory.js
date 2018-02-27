function solve (arr) {
    let print =[];
    for (let line of arr) {
        let result = {};
        let data = line.split(' / ').map(a=>a.trim()).filter(a=>a!== '');
        let [name, level, items] = data;
        if (items === undefined){
            result = {
                'name': name,
                'level': Number(level),
                'items': []
            };
            }
        if (data.length === 3){
            result.name = name;
            result.level = Number(level);
            result.items = items.split(', ').map(a=>a.trim());

            }
        print.push(result);
    }
    console.log(JSON.stringify(print));
}

/*solve([
'Isacc / 25 / Apple, GravityGun',
'Derek / 12 / BarrelVest, DestructionSword',
'Hes / 1 / Desolator, Sentinel, Antara',
]);
solve(['Jake / 1000 / Gauss, HolidayGrenade']);*/

/*
solve(['Jake / 1000 ']);

function solve(arr) {

    let record = [];

    for (let heroes of arr) {
        let [hero, level, items] = heroes.split(/\s\/\s?/g);

        let currentHero = {};

        if (items === undefined){
            currentHero = {
                'name': hero,
                'level': Number(level),
                'items': []
            };
        }else {
            currentHero = {
                'name': hero,
                'level': Number(level),
                'items': items.split(', ')
            };
        }


        record.push(currentHero);
    }

    let json = JSON.stringify(record);

    console.log(json);
}*/
