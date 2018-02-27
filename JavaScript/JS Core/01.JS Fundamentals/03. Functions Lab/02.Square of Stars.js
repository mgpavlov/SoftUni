function squareStars(n) {
    function printStars(n){
        console.log('* '.repeat(n));
    }
    for (let i = 1; i <= n; i++) {
        printStars(n)
    }
}

squareStars(5)