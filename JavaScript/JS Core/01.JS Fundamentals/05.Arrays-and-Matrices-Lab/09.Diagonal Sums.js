function diagonal(matrix) {
    let mainDiagonalSum = 0;
    let backDiagonalSum = 0;
    let l = matrix.length;

    for (let i = 0; i < l; i++) {
        mainDiagonalSum += matrix[i][i];
        backDiagonalSum += matrix[i][l-1-i];
    }
    console.log(mainDiagonalSum+' '+backDiagonalSum);
}

diagonal([[20, 40],
          [10, 60]]);