function solve(match, text) {

    let regEx = new RegExp (`(?=(${match}))`, 'g');
    let result = [];
    if(regEx.test(text)){

        result = text.match(regEx);
    }

    console.log(result.length);
}
// function solve(searchStr, text) {
//
//     let count = 0;
//
//     let index = text.indexOf(searchStr, 0);
//
//     while (index !== -1){
//
//         count++;
//         index = text.indexOf(searchStr, index + 1);
//     }
//
//     console.log(count);
// }
solve('mamn', 'Marine mamamal training is the training');