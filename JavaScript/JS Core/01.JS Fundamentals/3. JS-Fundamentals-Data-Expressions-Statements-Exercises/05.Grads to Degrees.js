function grToDeg(gr) {
    let deg = 0;
    if(gr<0){
        deg = 360 + ((gr*360)/400)%360;
        }
        else{
        deg = ((gr*360)/400)%360;
        }

    console.log(deg);
}
grToDeg(100)