function calculator(a, b, operator) {
    switch (operator){
        case '+': return a+b;
        case '-': return a-b;
        case '*': return a*b;
        case '/': return a/b;
    }
}

console.log(calculator(18, -1, '*'));
