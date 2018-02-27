function solve(arr) {

    let regex = /\b([0-9]{1,2})-([A-Z][a-z]{2})-([0-9]{4})\b/g;
    let dates = [];
    let match = [];

    for (let obj of arr) {
        if (match = regex.exec(obj)){
            dates.push(`${match[0]} (Day: ${match[1]}, Month: ${match[2]}, Year: ${match[3]})`);
        }
    }

    console.log(dates.join('\n'));
}

solve(['1-Jan-1999 is a valid date.',
    'So is 01-July-2000.',
    'I am an awful liar, by the way',
   '-- Ivo, 28-Sep-2016.'
]);