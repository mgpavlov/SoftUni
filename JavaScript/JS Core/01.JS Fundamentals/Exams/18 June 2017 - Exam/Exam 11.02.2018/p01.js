function solve(shifts) {

    let bitcoinPrice = 11949.16;
    let goldPrice = 67.51;
    let totalGoldAmount = 0;
    let firstBitcoinDaySet = [];

    for (let i = 1; i <= shifts.length; i++) {
        if (i%3 === 0){
            shifts[i-1]= 0.70*Number(shifts[i-1]);
        }
        totalGoldAmount+=Number(shifts[i-1]);
        if (totalGoldAmount*goldPrice >= bitcoinPrice){
            firstBitcoinDaySet.push(i);
        }
    }
    let totalBitcoins = Math.floor(totalGoldAmount*goldPrice/bitcoinPrice);
    let moneyLeft = 0;

    if(totalBitcoins >0){
        moneyLeft = (totalGoldAmount*goldPrice/bitcoinPrice - totalBitcoins)*bitcoinPrice;
    }else{
        moneyLeft = totalGoldAmount*goldPrice;
    }


    console.log(`Bought bitcoins: ${totalBitcoins}`);
    if(totalBitcoins !== 0){
        console.log(`Day of the first purchased bitcoin: ${firstBitcoinDaySet[0]}`);
    }
    console.log(`Left money: ${moneyLeft.toFixed(2)} lv.`);
}
/*solve(['100', '200', '300']);
solve(['3124.15', '504.212', '2511.124']);*/
solve(['50', '100']);