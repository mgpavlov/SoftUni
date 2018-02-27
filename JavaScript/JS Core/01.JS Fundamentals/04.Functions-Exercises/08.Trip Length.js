function tripLength([x1, y1, x2, y2, x3, y3]) {

    let dist12 = getDistance(x1, y1, x2, y2);
    let dist13 = getDistance(x1, y1, x3, y3);
    let dist23 = getDistance(x2, y2, x3, y3);

    if(dist12<=dist13 && dist13 >= dist23){
        let b = dist12 + dist23;
        console.log(`1->2->3: ${b}`);
    } else if(dist12<=dist23 && dist23 > dist13) {
        let a = dist12 + dist13;
        console.log(`2->1->3: ${a}`);
    }else {
        let c = dist13 + dist23;
        console.log(`1->3->2: ${c}`);
    }
    function getDistance(a1, h1, a2, h2) {
        let x = Math.abs(a1-a2);
        let y = Math.abs(h1-h2);
        return Math.sqrt(x*x+y*y);
    }
}

tripLength([-1, -2, 3.5, 0, 0, 2]);