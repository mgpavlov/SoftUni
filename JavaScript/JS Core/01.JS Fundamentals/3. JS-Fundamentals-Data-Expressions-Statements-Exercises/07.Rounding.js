function rounding (arr) {
    let presision = arr[1];
    if(arr[1]>15){presision=15;}
    let number = Number(arr[0].toFixed(presision));
    console.log(number);
}

rounding([3.1, 2]);