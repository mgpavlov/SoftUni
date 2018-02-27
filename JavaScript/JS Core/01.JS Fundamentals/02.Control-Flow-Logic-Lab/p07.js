function oddEven(n) {
    if (n%2 == 0){
        console.log("even");
    }else if (n != Math.ceil(n)){
        console.log('invalid');
    }else{
        console.log('odd');
    }
}

oddEven(0);