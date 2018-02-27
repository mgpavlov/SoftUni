function digit(arr) {
    let regex = /\d+/g;
    let result = [];
    for (let str of arr) {
        result.push(str.match(regex));
    }
    console.log(result.filter(a=>a!= null).map(e => e.join(' ')).join(' '));
}

digit(['The300',
    'What is that?',
        'I think it’s the 3rd movie.',
    'Lets watch it at 22:45'
]);