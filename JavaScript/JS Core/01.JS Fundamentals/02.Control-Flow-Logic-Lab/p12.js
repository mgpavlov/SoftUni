function primeNumberChecker(n) {
    if (n>1){
        for (var i = 2; i <= Math.sqrt(n); i++) {
            if(n%i == 0){
                console.log('false');
                return;
            }
        }
        console.log('true')
        return;
    }
    else{
        console.log('false');
    }

}

primeNumberChecker(1)