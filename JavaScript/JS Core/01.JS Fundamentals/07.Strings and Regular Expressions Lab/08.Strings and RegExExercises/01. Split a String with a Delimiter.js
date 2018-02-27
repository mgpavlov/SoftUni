function split(str, deli) {
    let result = str.split(deli).filter(a=>a!='');
    console.log(result.join('\n'));
}
