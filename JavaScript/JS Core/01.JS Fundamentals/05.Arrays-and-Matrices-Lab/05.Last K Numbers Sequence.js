function sumKel(n, k) {
    let arr = [1];
    for (let i = 1; i < n; i++) {
        arr[i] = 0;
        for (let j = 1; j <= k; j++) {
            if (i-j>=0)
                arr[i] += arr[i-j]
        }
    }
    console.log(arr)
}

sumKel(6, 3);