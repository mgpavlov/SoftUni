function solve(main, second, overlayCoordinate, start) {
    let [y, x] = start;
    let quadrant = 0;
    let mainH = main.length;
    let mainL = main[0].length;
    for (let coordinate of overlayCoordinate) {
        overlayMatrix(main, second, coordinate);
    }
    let counter = 1;
    //правим първи ход;
    if (main[y][x + 1] === 0 && x + 1 < main[0].length) {
        main[y][x] = 1;
        x = x + 1;
        counter++;
    } else if (main[y][x - 1] === 0 && x - 1 > -1) {
        main[y][x] = 1;
        x = x - 1;
        counter++;
    } else if (main[y + 1][x] === 0 && y + 1 < main.length) {
        main[y][x] = 1;
        y = y + 1;
        counter++;
    } else if (main[y - 1][x] === 0 && y - 1 > -1) {
        main[y][x] = 1;
        y = y - 1;
        counter++;
    } else {
        console.log(counter);
    }
    while (y + 1 < main.length && x + 1 < main[0].length && x - 1 > -1 && y - 1 > -1) {
        if (main[y][x + 1] === 0) {
            main[y][x] = 1;
            x = x + 1;
            counter++;
        } else if (main[y][x - 1] === 0) {
            main[y][x] = 1;
            x = x - 1;
            counter++;
        } else if (main[y + 1][x] === 0) {
            main[y][x] = 1;
            y = y + 1;
            counter++;
        } else if (main[y - 1][x] === 0) {
            main[y][x] = 1;
            y = y - 1;
            counter++;
        } else {
            if (x < mainL/2){
                if(y<mainH/2) quadrant = 2;
                else quadrant = 3;
            }else{
                if(y<mainH/2) quadrant = 1;
                else quadrant = 4;
            }
            console.log(counter);
            console.log('Dead end '+ quadrant)
            return;
        }
    }
    function overlayMatrix(primary, secondary, coordinate) {
        let y = coordinate[0];
        let x = coordinate[1];
        for (let i = 0; i < secondary.length; i++) {
            for (let j = 0; j < secondary[i].length; j++) {
                if (i + y < primary.length && j + x < primary[0].length && secondary[i][j] === 1)
                    primary[i + y][j + x] = primary[i + y][j + x] - 1;
            }
        }
        for (let row of main) {
            for (let i = 0; i < row.length; i++) {
                if (row[i] === -1)
                    row[i] = 1;
            }
        }
    }
        let exit = '';
            if(x===0) exit = 'Left';
            if(x===main[0].length-1) exit = 'Right';
            if(y===0) exit = 'Top';
            if(y===main.length-1) exit = 'Bottom';

    console.log(counter);
       console.log(exit);
}

solve([[1, 1, 0, 1],
            [0, 1, 1, 0],
            [0, 0, 1, 0],
            [1, 0, 1, 0]],
        [[0, 0, 1, 0, 1],
            [1, 0, 0, 1, 1],
            [1, 0, 1, 1, 1],
            [1, 0, 1, 0, 1]],
        [[0, 0],
            [2, 1],
            [1, 0]],
        [2, 0]
);