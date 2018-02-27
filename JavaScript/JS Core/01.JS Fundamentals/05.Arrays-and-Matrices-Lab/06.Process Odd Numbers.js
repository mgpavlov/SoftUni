function oddNumbers(arr) {
    let newArr = arr.filter((e, i) => i%2 !== 0).map(el => el=2*el).reverse();
    console.log(newArr.join(' '));
}
oddNumbers([30, 15, 50, 5]);