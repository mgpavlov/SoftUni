function solve(arr) {
    let pattern = /www\.[a-zA-Z0-9\-]+(\.[a-z]+)+/gm;
    let result = [];

    for (let line of arr) {

        let match = pattern.exec(line);

        while ( match !== null){
            result.push(match[0]);
            match = pattern.exec(line);
        }
    }

    console.log(result.join('\n'));
}
solve(['Join WebStars now for free, at www.web-stars.com',
'You can also support our partners:',
    'Internet - www.internet.com',
'WebSpiders - www.webspiders101.com',
'Sentinel - www.sentinel.-ko'
]);