function string(arr) {
    // let result = arr.map(s => s.split('@'));
    //
    // result = result.map(([a, b]) => a + '.' + b.split('.').map(c => c[0]).join(''));
    //
    // console.log(result.join(', '));

    let result = arr.map(e => e.split('@'));
    result = result.map(([a, b]) => a + '.'+ b.split('.').map(c=>c[0]).join(''));
    console.log(result.join(', '));

}

string(['peshoo@gmail.com', 'todor_43@mail.dir.bg', 'foo@bar.com'])