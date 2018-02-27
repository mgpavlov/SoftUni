function solve(arr) {
    let result ={};
    for (let str of arr) {
        let words = str.split(/[^\w]/g).filter(a=>a!=='')
        for (let w of words) {
            if(!result.hasOwnProperty(w)){
                result[w] = 0;
            }
            result[w]++;
        }
    }
    console.log(JSON.stringify(result));
}
solve(['Far too slow, you\'re far too slow.', 'JS devs use Node.js for server-side JS.-- JS for devs']);

/*
function solve(arr) {
    let tokens = arr[0].split(/\W+/g).filter(e => e !== '');

    let record = {};

    for (let word of tokens) {
        if (!record.hasOwnProperty(word)){
            record[word] = 0;
        }

        record[word]++;
    }

    console.log(JSON.stringify(record));
}
*/
