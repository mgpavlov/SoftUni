function capitalize(str) {
    let arr = str.split(' ').filter(a => a!=='');
    let result = [];
    for (let str of arr) {
        str = str.toLowerCase();
        str = str[0].toUpperCase()+str.slice(1);
        result.push(str);
        /*str[0] = str[0].capitalize()*/
    }
    console.log(result.join(' '));
}
capitalize('Was that Easy? tRY thIs onE for SiZe!')