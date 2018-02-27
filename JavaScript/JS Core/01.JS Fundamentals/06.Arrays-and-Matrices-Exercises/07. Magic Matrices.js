function magic(matrix) {
    let matrixLength = matrix.length;
    let sum = matrix[0].reduce((a,b) => a+b);

    for (let i = 0; i < matrixLength; i++) {
        if( matrix[i].reduce((a,b) => a+b) !== sum)
            return false;
        for (let j = 0; j < matrix[i].length; j++) {
            let sumCol = 0;
            for (let k = 0; k < matrixLength; k++) {
                sumCol += matrix[j][k];
            }
            if (sumCol !== sum){
                return false
            }
        }
    }
    return true;
}

console.log(magic([[11, 32, 45],
    [21, 0, 1],
    [21, 1, 1]]

));