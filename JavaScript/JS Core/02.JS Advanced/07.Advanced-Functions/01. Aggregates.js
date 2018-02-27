function aggregates(arr) {
    let sum =
    console.log('Sum = '+  arr.map(Number).reduce((a, b) => a + b));
    console.log('Min = '+  arr.reduce((a, b) => Math.min(a,b)));
    console.log('Max = '+  arr.reduce((a, b) => Math.max(a,b)));
    console.log('Product = '+  arr.map(Number).reduce((a, b) => a*b));
    console.log('Join = '+  arr.reduce((a, b) => a + b));

}
aggregates(['2','3','10','5']);