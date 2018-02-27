function date (arr) {
    let day = arr[0];
    let month = arr[1];
    let year = arr[2];

    var d = new Date(year, month-1, 0);
    console.log(d.getDate());
}
date([17, 3, 2002])