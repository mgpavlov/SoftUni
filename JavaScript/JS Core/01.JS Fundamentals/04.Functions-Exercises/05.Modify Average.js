function average (num) {
    num = num.toString();
    let n = num.length;
    let sum = 0;
    for (let i = 0; i < n; i++) {
        sum += Number(num[i]);
    }
    let averageSum = sum/n;
    while(averageSum <= 5){
        sum += 9;
        n++;
        averageSum = sum/n;
        num = num +'9';
    }
    console.log(num);
}

average(101)