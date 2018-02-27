function radar([speed, area]) {
    let limit = getLimit(area);
    let infraction = getInfraction(speed, limit);

    if (infraction){
        console.log(infraction);
    }
    // let limit = 0;
    // switch (area){
    //     case 'motorway':
    //         limit = 130;
    //         break;
    //     case 'interstate':
    //         limit = 90;
    //         break;
    //     case 'city':
    //         limit = 50;
    //         break;
    //     case 'residential':
    //         limit = 20;
    //         break;
    // }
    // if (speed >limit && speed<= limit+20){
    //     console.log('speeding');
    // }else if (speed >limit+20 && speed<= limit+40){
    //     console.log('excessive speeding');
    // }else if (speed >limit+40){
    //     console.log('reckless driving');
    // }

    function getLimit(area) {
        switch (area){
            case 'motorway': return 130;
            case 'interstate': return 90;
            case 'city': return 50;
            case 'residential': return 20;
        }
    }
    function getInfraction(speed, limit) {
        let overSpeed = speed-limit;
        if (overSpeed<=0){
            return false;
        }else{
            if(overSpeed <=20){
                return 'speeding';
            }else if(overSpeed <=40){
                return 'excessive speeding';
            }else {
                return 'reckless driving';
            }
        }
    }
}
radar([21, 'residential']);