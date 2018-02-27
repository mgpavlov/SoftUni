function occurrences(str, pattern) {

    let regex = new RegExp(`\\b${pattern}[!?.,:]*\\b`, 'gi');

    let counter = 0;
    while (regex.exec(str) !== null){
        counter++;
    }

    console.log(counter);
}

occurrences('There was one. Therefore I bought it. I wouldn�t buy it otherwise.', 'there')