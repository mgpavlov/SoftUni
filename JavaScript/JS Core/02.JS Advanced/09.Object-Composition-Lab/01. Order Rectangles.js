function solve(array) {
    let sortArr = array.sort((a,b) => (b[0]*b[1])-(a[0]*a[1]));
    console.log(sortArr);
}

solve([[3, 4], [5, 3], [3, 4], [3, 5], [12, 1]
]);