function solve(arr) {
    let products = arr.filter((e,i) => i%2 === 0);
    let price = arr.filter((e,i) => i%2 !== 0).map(Number).reduce((a, b) => a = a+b);
    console.log(`You purchased ${products.join(', ')} for a total sum of ${price}`);
}

solve(['Beer Zagorka', '2.65', 'Tripe soup', '7.80','Lasagna', '5.69'])