function wordsUppercase(sentence){
    let pattern = new RegExp ('\\W+');
    let print = sentence.split(pattern).filter(e => e !=='').join(', ');
    console.log(print.toUpperCase());

}

wordsUppercase('9099asdasdasd')