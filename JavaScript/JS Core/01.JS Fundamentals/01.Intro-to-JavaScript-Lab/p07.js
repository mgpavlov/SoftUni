function nextDay(year, month, day) {

    let date = new Date(year, month-1, day);
    let oneDay = 24*60*60*1000;

    let nextDay = new Date(date.getTime()+oneDay);
    let print = nextDay.getFullYear() + '-' + (nextDay.getMonth()+1) + '-' + nextDay.getDate();

    console.log(print);
}

nextDay(1990, 4, 30)