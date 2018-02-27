function solve(base, increment) {

    let lenght = base;
    let stone = 0;
    let marble = 0;
    let lapis = 0;
    let gold = 0;
    let steps = 0;

    while (true){
        if (lenght === 1 || lenght === 2){
            gold += (lenght ** 2) * increment;
            steps++;
            break;
        }

        steps++;
        stone += (lenght - 2) ** 2 * increment;

        if (steps % 5 === 0){
            lapis += ((lenght ** 2) - (lenght - 2) ** 2) * increment;
        }else {
            marble += ((lenght ** 2) - ((lenght - 2) ** 2)) * increment;
        }
        lenght -= 2;
    }

    console.log(`Stone required: ${Math.ceil(stone)}`);
    console.log(`Marble required: ${Math.ceil(marble)}`);
    console.log(`Lapis Lazuli required: ${Math.ceil(lapis)}`);
    console.log(`Gold required: ${Math.ceil(gold)}`);
    console.log(`Final pyramid height: ${Math.floor(steps * increment)}`);
}

//solve(11, 1);
//solve(11, 0.75);
//solve(12, 1);
//solve(23, 0.5);
solve(22, 0.25);