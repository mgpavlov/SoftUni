function rotateArr(arr) {
    let amount = Number(arr.pop())%arr.length;
    for (let i = 0; i < amount; i++) {
        arr.unshift(arr.pop());
    }
    console.log(arr.join(' '));
}
rotateArr([1, 2, 3, 4, 2]);