function solve(string) {
    let pattern = /([\-]?[0-9]+)\s*\*\s*([\-]?[0-9]+\.?[0-9]*)/g;
    let countMatches = 0;
    while(pattern.exec(string) !== null) countMatches++;
    let arr = pattern.exec(string);
    for (let i = 0; i < countMatches; i++) {
        let multiply = Number(arr[1])*Number(arr[2]);
        string = string.replace(arr[0], multiply);
        arr = pattern.exec(string);
    }
    console.log(string);
}

/*function solve(text) {

    let result = text.replace(/(-?\d+)\s*\*\s*(-?\d*\.?\d+)/g, func);

    console.log(result);

    function func(match, gr1, gr2) {
        return Number(gr1) * Number(gr2);
    }
}*/

solve('My bill: 2*2.50 (beer); 2* 1.20 (kepab); -2  * 0.5 (deposit).');