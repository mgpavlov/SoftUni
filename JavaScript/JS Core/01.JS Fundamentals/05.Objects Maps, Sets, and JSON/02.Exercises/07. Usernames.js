function solve(arr) {
    let set = new Set();
    for (let name of arr) {
        set.add(name)
    }

    //може и result = [...result], прави масив;
    let result = Array.from(set).sort((a,b)=>{
        if(a.length > b.length) return 1;
        if(a.length < b.length) return -1;
        if(a>b) return 1;
        if(a<b) return -1;
    });
    console.log(result.join('\n'));
}

solve(['Denise',
    'Ignatius',
    'Iris',
    'Isacc',
    'Indie',
    'Dean',
    'Donatello',
    'Enfuego',
    'Benjamin',
    'Biser',
    'Bounty',
    'Renard',
    'Rot'
]);