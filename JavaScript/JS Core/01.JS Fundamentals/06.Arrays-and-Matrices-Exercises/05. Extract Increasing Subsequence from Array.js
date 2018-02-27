function increasing(arr) {
    let biggestNum = Number.NEGATIVE_INFINITY;
    let arrLength = arr.length;
    let newArr = [];
    for (let i = 0; i < arrLength; i++) {
        if (arr[i] >= biggestNum){
            newArr.push(arr[i]);
            biggestNum = arr[i];
        }
    }
    console.log(newArr.join('\n'));
}

increasing([1,
    3,
    8,
    4,
    10,
    12,
    3,
    2,
    24
])