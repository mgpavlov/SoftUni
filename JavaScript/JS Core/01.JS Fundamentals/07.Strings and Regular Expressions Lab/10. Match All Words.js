function solve(str) {
    let regex = /\w+/g;
    let result = [];
    if (regex.test(str)){
        result = str.match(regex).filter(a=>a !=='');
    }
    console.log(result.join('|'));
}

solve('A Regular Expression needs to have the global flag in order to match all occurrences in the text')