function numbersCooking(arr) {
    let num = Number(arr[0]);
    for (let i = 1; i < 6; i++) {
        switch (arr[i]){
            case 'chop': num = num/2;
            break;
            case 'dice': num = Math.sqrt(num);
                break;
            case 'spice': num = num+1;
                break;
            case 'bake': num = 3*num;
            break;
            case 'fillet': num = num*0.8;
                break;
        }
        console.log(num);
    }
}
numbersCooking([9, 'dice', 'spice', 'chop', 'bake', 'fillet'])