function solve(str) {
    let regex = /[^\s().;,]+/g;

    let result = str.match(regex).filter(a=>a!=='');
    console.log(result.join('\n'));
}
solve('let sum = 1 + 2;if(sum > 2){\tconsole.log(sum);}');