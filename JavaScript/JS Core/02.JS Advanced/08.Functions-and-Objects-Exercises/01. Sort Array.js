function solve(arr, orderType) {
    if(orderType === 'asc')
    return arr.sort(function (a,b) {
        return a-b
    });
    else{
       return (arr.sort((a, b) => {
            return b - a
        }));
    }
}

console.log(solve([14, 7, 17, 6, 8], 'asc'));
console.log(solve([14, 7, 17, 6, 8], 'desc'));