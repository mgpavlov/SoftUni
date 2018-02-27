function solve(string) {
    //regexSolve
    // let regex = /(?<=\().*?(?=\))/g;
    // if (regex.test(string)){
    //     let result = string.match(regex);
    //     console.log(result.join(', '));
    // }else
    //     console.log('')

    let indexLeft = string.indexOf('(', 0);
    let indexRight = string.indexOf(')', 0);
    if (indexLeft !== -1 && indexRight !== -1 && indexRight > indexLeft){
    let result = [];


    while(indexRight >-1){

        result.push(string.substring(indexLeft+1, indexRight));
        indexLeft = string.indexOf('(', indexRight+1);
        indexRight = string.indexOf(')', indexRight+1);

    }

        console.log(result.join(', '));
    }
    else
        console.log('')
}
solve('Rakiya (Bulgarian brandy) is self-made liquor (alcoholic drink)');