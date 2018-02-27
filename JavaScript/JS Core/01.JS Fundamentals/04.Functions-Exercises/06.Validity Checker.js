function validityChecker(arr) {
    let x1 = arr[0];
    let y1 = arr[1];
    let x2 = arr[2];
    let y2 = arr[3];

    let x = Math.abs(x1-x2);
    let y = Math.abs(y1-y2);


    let dist01 = Math.sqrt(x1*x1+y1*y1);
    let dist02 = Math.sqrt(x2*x2+y2*y2);
    let dist12 = Math.sqrt(x*x+y*y);

    if(dist01 === parseInt(dist01, 10)) {
        console.log(`{${x1}, ${y1}} to {0, 0} is valid`)
    }else {
        console.log(`{${x1}, ${y1}} to {0, 0} is invalid`)
    }

    if(dist02 === parseInt(dist02, 10)) {
        console.log(`{${x2}, ${y2}} to {0, 0} is valid`)
    }else {
        console.log(`{${x2}, ${y2}} to {0, 0} is invalid`)
    }

    if(dist12 === parseInt(dist12, 10)) {
        console.log(`{${x1}, ${y1}} to {${x2}, ${y2}} is valid`)
    }else {
        console.log(`{${x1}, ${y1}} to {${x2}, ${y2}} is invalid`)
    }
}
validityChecker([2, 1, 1, 1])