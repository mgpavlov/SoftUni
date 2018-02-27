function negativePositive (arr) {
    let newArr = [];

    arr.forEach(e =>{ if(e<0)newArr.unshift(e);
                        else newArr.push(e);
    })

    console.log(newArr.join('\n'))
}

negativePositive([3, -2, 0, -1]);