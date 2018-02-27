function solve(arr) {
    let result =new Map();
    for (let str of arr) {
        let words = str.split(/[^\w]/g).filter(a=>a!=='');
        for (let w of words) {
            let word = w.toLowerCase();
            if(!result.has(word)){
                result.set(word, 0);
            }
            result.set(word, result.get(word)+1);
        }
    }

    result = [...result].sort();

    for (let obj of result) {
        console.log(`'${obj[0]}' -> ${obj[1]} times`)
    }
}

solve(['Far too slow, you\'re far too slow.']);

/*
function solve(str) {
    let words = str[0].split(/\W+/g).filter(e => e !== '').map(e => e.toLowerCase());

    let record = new Map();

    for (let word of words) {
        if (!record.has(word)) {
            record.set(word, 0);
        }

        record.set(word, record.get(word) + 1);
    }

    record = [...record.entries()].sort();
    for (let [word, count] of record) {
        console.log(`'${word}' -> ${count} times`)
    }
}

solve(['Far too slow, you\'re far too slow.']);*/
