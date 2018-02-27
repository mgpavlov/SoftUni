function underscore(str) {
    let regex = /_[a-zA-Z0-9]+/g;
    if(regex.test(str)){
        let result = str.match(regex).filter(a => a !== null);
        console.log(result.map(a => a.slice(1)).join(','));
    }
}

underscore('The _id and _age variables are both integers.')
