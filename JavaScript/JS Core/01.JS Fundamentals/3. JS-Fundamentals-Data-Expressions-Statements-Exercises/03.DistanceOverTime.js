function distOverTime(arr) {
    let time = arr[2]/3600;

    let s = Math.abs(arr[0]-arr[1])*time*1000;
    console.log(s);
}

distOverTime([5, -5, 40])