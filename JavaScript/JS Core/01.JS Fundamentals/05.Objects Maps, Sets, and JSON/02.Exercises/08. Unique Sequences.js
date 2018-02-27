function solve(input) {
    let set = new Set();
    for (let line of input) {
        let arr = JSON.parse(line);
        arr.sort((a,b)=>b-a);
        set.add(JSON.stringify(arr));
    }
    let result = Array.from(set);
    let print = [];
    for (let el of result) {
        print.push(JSON.parse(el));
    }
    print.sort((a,b)=> a.length-b.length)
    for (let obj of print) {
        let str = JSON.stringify(obj).split(',').join(', ');
        console.log(str);
    }
}
solve(['[-3, -2, -1, 0, 1, 2, 3, 4]',
    '[10, 1, -17, 0, 2, 13]',
    '[4, -3, 3, -2, 2, -1, 1, 0]'
]);

/*
function solve(arr) {

    let record = new Set();

    for (let json of arr) {
        let array = JSON.parse(json).sort((a, b) => {
            if (a < b) return 1;
            if (a > b) return -1;
        });

        let toString = JSON.stringify(array);

        record.add(toString);
    }

    record = [...record].sort((a, b) => {
        let arr1 = JSON.parse(a);
        let arr2 = JSON.parse(b);
        if (arr1.length < arr2.length) return -1;
        if (arr1.length > arr2.length) return 1;
    });

    for (let array of record) {
        let toNumbers = JSON.parse(array);

        console.log('[' + toNumbers.join(', ') + ']');
    }
}*/
