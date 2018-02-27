function elements(arr) {
    let sum = arr.reduce((a, b) => a + b, 0);
    let inverseSum = 0;
    for (let i = 0; i < arr.length; i++) {
        inverseSum += 1/arr[i];
    }

    let string = arr.join('');

    console.log(sum);
    console.log(inverseSum);
    console.log(string);
}

elements([1, 2, 3]);