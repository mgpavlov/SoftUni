function subtract() {
    let result = document.getElementById('result');
    let firstNum = Number(document.getElementById('firstNumber').value);
    let secondNum = Number(document.getElementById('secondNumber').value);
    result.textContent = firstNum - secondNum;
}
