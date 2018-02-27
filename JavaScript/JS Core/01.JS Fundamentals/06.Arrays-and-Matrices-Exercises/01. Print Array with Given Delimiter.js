function delimiter(arr) {
    let delimiter = arr.pop();
    console.log(arr.join(`${delimiter}`));
}

delimiter(['one', 'two', 'three', '-'])