function addRemove(arr) {

    let length = arr.length;
    let newArr = [];
    for (let i = 1, j = 0; i <= length, j < length ; i++, j++) {

        if (arr[j] === 'add'){
            newArr.push(i);
        }
        if(arr[j] === 'remove'){
            if(newArr.length !== 0)
            newArr.pop()
        }
    }

    if (newArr.length === 0) return 'Empty';
    else console.log(newArr.join('\n'));
}

addRemove(['add', 'add', 'remove', 'add', 'add']);
