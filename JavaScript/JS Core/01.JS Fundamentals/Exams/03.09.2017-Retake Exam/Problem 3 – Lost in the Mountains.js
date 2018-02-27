function solve(keyWord, text) {
    let coordinatesPattern = /(north|east)[^0-9]*([0-9]{2})[^,]*,[^0-9]*([0-9]{6})/gi;
    let messagePattern = new RegExp(`${keyWord}(.+?)${keyWord}`, 'g');
    let lat ='';
    let long = '';
    let message = messagePattern.exec(text)[1];

    let match = coordinatesPattern.exec(text);
    while (match){
        if(match[1].toLowerCase() === 'north') lat = match[2]+'.'+match[3]+ ' N';
        else long = match[2]+'.'+match[3]+ ' E';
        match = coordinatesPattern.exec(text);
    }
    console.log(lat);
    console.log(long);
    console.log(`Message: ${message}`);
}


/*solve('4ds', 'eaSt 19,432567noRt north east 53,123456north 43,3213454dsNot all those who wander are lost.4dsnorth 47,874532');*/
solve('<>', "o u%&lu43t&^ftgv><nortH4276hrv756dcc,  jytbu64574655k <>ThE sanDwich is iN the refrIGErator<>yl i75evEAsTer23,lfwe 987324tlblu6b");
