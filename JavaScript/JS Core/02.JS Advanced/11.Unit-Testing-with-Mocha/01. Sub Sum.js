function subSum(arr, startIndex, endIndex) {
    let sum = 0;
    if(!Array.isArray(arr)){return NaN}
    let start = Math.max(startIndex, 0);
    let end = Math.min(endIndex, arr.length-1)
    for (let i = start; i <=end ; i++) {
        sum += Number(arr[i]);
    }
    return sum;
}
