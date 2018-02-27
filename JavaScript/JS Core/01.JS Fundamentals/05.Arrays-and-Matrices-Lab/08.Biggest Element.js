function matrixBiggestNumber(matrix) {
    let num = Number.NEGATIVE_INFINITY;
    matrix.forEach(row => row.forEach(column => num = Math.max(column, num)));
    console.log(num);
}
matrixBiggestNumber([[20, 50, 10],
    [8, 33, 145]]
)
