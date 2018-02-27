function solve(arr) {

    let result = 0;
    let newArray = [];
    let countOperands = 0;
    for (let i = 0; i < arr.length; i++) {
        if (Number(arr[i])) {
            newArray.push(arr[i]);
            countOperands++;
        } else if (countOperands >= 2) {
            result = doOperation(arr[i]);
            newArray.pop();
            newArray.pop();
            newArray.push(result);
            countOperands = newArray.length;
        } else if (countOperands <= 1){
            console.log('Error: not enough operands!');
            return;
        }
    }

    if (newArray.length > 1){
        console.log('Error: too many operands!');
        return
    }

    console.log(result);

    function doOperation(operand) {

        let firstOperand = newArray[newArray.length - 2];
        let secondOperand = newArray[newArray.length - 1];

        switch (operand) {
            case '+': return firstOperand + secondOperand;
            case '-': return firstOperand - secondOperand;
            case '*': return firstOperand * secondOperand;
            case '/': return firstOperand / secondOperand;
        }
    }
}

solve([-1,
    1,
    '+',
    101,
    '*',
    18,
    '+',
    3,
    '/']);