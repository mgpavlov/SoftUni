function solve(car) {
    let smallEngine = { power: 90, volume: 1800 };
    let normalEngine = { power: 120, volume: 2400 };
    let monsterEngine = { power: 200, volume: 3500 };
    let wheel = car.wheelsize;
    return {
        model: car.model,
        engine: car.power<=105? smallEngine: car.power<=160? normalEngine: monsterEngine,
        carriage: {type: car.carriage, color:car.color},
        wheels: wheel % 2 === 0?[wheel-1, wheel-1, wheel-1, wheel-1]: [wheel, wheel, wheel, wheel]
    };
}
console.log(solve({
    model: 'Brichka',
    power: 65,
    color: 'white',
    carriage: 'hatchback',
    wheelsize: 16
}));
console.log(solve({
    model: 'Opel Vectra',
    power: 110,
    color: 'grey',
    carriage: 'coupe',
    wheelsize: 17
}));