function equalNeighbors(matrix) {
    let counter = 0;

    let a = matrix.length;
    for (let i = 0; i < a-1; i++) {
        for (let j = 0; j < matrix[i].length; j++) {

            if (matrix[i][j] === matrix [i][j+1] || matrix[i][j] === matrix [i+1][j]){
                counter++;
            }
            if (matrix[i+1][j] === matrix [i+1][j+1]){
                counter++;
            }
        }
    }
    console.log(counter);
}
equalNeighbors([['test', 'yes', 'yo', 'ho'],
                ['well', 'done', 'yo', '6'],
                ['not', 'done', 'yet', '5']]

);
