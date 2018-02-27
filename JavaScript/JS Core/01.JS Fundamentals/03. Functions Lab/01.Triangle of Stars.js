function triangleStars(n){
    function printStars(n){
        console.log('*'.repeat(n));
    }
    let str = '*';
    for (let i = 1; i <= n; i++) {
        printStars(i);
    }
    for (let i = n-1; i > 0; i--) {
       printStars(i);
    }
}

triangleStars(5)