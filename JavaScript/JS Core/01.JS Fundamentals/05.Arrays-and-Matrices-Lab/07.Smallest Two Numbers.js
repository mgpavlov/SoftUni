function smallest2(arr) {
    let newArr = arr.sort((a, b) => a - b).slice(0, 2);

    console.log(newArr.join(' '));
}
smallest2([30, -5, 8, 9])