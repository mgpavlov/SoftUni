function kNumbers(arr) {

    let n = arr.shift();
    let frontArr = [];
    let backArr = [];
    frontArr = arr.slice(0, n);
    backArr = arr.slice(arr.length-n);
    console.log(frontArr.join(' '));
    console.log(backArr.join(' '));
}

kNumbers([2, 7, 8, 9])