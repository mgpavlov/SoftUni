function currencyFormatter(separator, symbol, symbolFirst, value) {
    let result = Math.trunc(value) + separator;
    result += value.toFixed(2).substr(-2,2);
    if (symbolFirst) return symbol + ' ' + result;
    else return result + ' ' + symbol;
}
let dollarFormatter = getDollarFormatter(currencyFormatter);
function getDollarFormatter(formatter) {
    return function dollarFormatter (value) {
        return formatter(',', '$', true, value)
    }
}


console.log(dollarFormatter(5435));
console.log(dollarFormatter(345345.6776876878));
