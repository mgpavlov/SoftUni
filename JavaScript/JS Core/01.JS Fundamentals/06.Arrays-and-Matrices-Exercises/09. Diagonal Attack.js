function diagonal (arr) {
    let matrix = [];

    for (let i = 0; i < arr.length; i++) {
       matrix.push([]);
    }
    let n = matrix.length;
    for (let i = 0; i < n; i++) {
        matrix[i] = arr[i].split(' ').map(e => Number(e));

    }
    let leftSum = 0;
    let rightSum = 0;

    for (let i = 0; i < n; i++) {
        leftSum += matrix[i][i];
        rightSum += matrix[i][n-1-i];
    }
    if(leftSum === rightSum){
        for (let i = 0; i < n; i++) {
            for (let j = 0; j < n; j++) {
                if(i !== j && j !== (n-1-i)){
                    matrix[i][j] = leftSum;
                }
            }
        }
    }
    matrix.forEach(e => {console.log(e.join(' '))})
}
diagonal(['1 1 1',
    '1 1 1',
    '1 1 0']


)