function solve(blocks, commands) {
    let matrix = [];
    for (let line of blocks) {
        let block = line.split(' ');
        block = block.map(Number);
        matrix.push(block);
    }
    let breezEffect = -15;
    let galeEffect = -20;
    let [breezeValue, galeValue, smogValue] = commands;

    for (let order of commands) {
        let [command, value] = order.split(' ');
        value = Number(value);
        effects(matrix, command, value)
    }
    function effects(matrix, command, value) {
        for (let i = 0; i < 5; i++) {
            if (command === 'breeze' && value !== undefined && value >= 0 && value < 5) {
                matrix[value][i] += - 15;
                if (matrix[value][i] < 0) {
                    matrix[value][i] = 0;
                }
            } else if (command === 'gale' && value !== undefined && value >= 0 && value < 5) {
                matrix[i][value] += galeEffect;
                if (matrix[i][value] < 0) {
                    matrix[i][value] = 0;
                }
            } else if (command === 'smog' && value !== undefined) {
                for (let j = 0; j < 5; j++) {
                    matrix[i][j] += value
                }
            }
        }
    }
 let result = [];
    let counter = 0;
    for (let i = 0; i < 5; i++) {
        for (let j = 0; j < 5; j++) {
            if(matrix[i][j] >= 50){
                result.push(`[${i}-${j}]`);
                counter++;
            }
        }
    }
    if(counter === 0 ){
        console.log('No polluted areas');
    }else {
        console.log('Polluted areas: '+result.join(', '));
    }
}

solve([
        "5 7 72 14 4",
        "41 35 37 27 33",
        "23 16 27 42 12",
        "2 20 28 39 14",
        "16 34 31 10 24",
    ],
    ["breeze 1", "gale 2", "smog 25"]
);