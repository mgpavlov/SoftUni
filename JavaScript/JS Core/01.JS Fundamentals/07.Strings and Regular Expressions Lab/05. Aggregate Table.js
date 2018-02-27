function solve(arr) {
    let sum = 0;
    let towns = [];
    for(let town of arr){
        let result = town.split('|').map(e => e.trim());
        towns.push(result[1]);
        sum += Number(result[2]);
    }
    console.log(towns.join(', '));
    console.log(sum);
}
solve(['| Sofia           | 300',
    '| Veliko Tarnovo  | 500',
    '| Yambol          | 275']
)