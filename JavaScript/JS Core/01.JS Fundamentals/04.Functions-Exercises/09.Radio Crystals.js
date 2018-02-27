function solution(arr) {
    let target = arr[0];
    for (let i = 1; i < arr.length; i++) {
        radioCrystal(target, arr[i]);
    }
    function radioCrystal(target, base) {
        let counter = 0;
        console.log(`Processing chunk ${base} microns`);
        if(base === target) return console.log(`Finished crystal ${target} microns`);
        while (cut(base) >= target){
            counter++;
            base = cut(base);
            if (cut(base) <= target){
                console.log(`Cut x${counter}`);
                console.log('Transporting and washing');
                base = transportingAndWashing(base);
                if(base === target) return console.log(`Finished crystal ${target} microns`);
                counter = 0;
            }
        }
        while (lap(base) >= target){
            counter++;
            base = lap(base);
            if (lap(base) <= target){
                console.log(`Lap x${counter}`);
                console.log('Transporting and washing');
                base = transportingAndWashing(base);
                if(base === target) return console.log(`Finished crystal ${target} microns`);
                counter = 0;
            }
        }

        while (grind(base) >= target){
            counter++;
            base = grind(base);
            if (grind(base) <= target){
                console.log(`Grind x${counter}`);
                console.log('Transporting and washing');
                base = transportingAndWashing(base);
                if(base === target) return console.log(`Finished crystal ${target} microns`);
                counter = 0;
            }
        }
        while (etch(base)+ 1 >= target){
            counter++;
            base = etch(base);
            if (base + 1 <= target){
                console.log(`Etch x${counter}`);
                console.log('Transporting and washing');
                base = transportingAndWashing(base);
                if(base === target) return console.log(`Finished crystal ${target} microns`);
                counter = 0;
            }
            if (base === target){
                console.log(`Etch x${counter}`);
                console.log('Transporting and washing');
                base = transportingAndWashing(base);
                if(base === target) return console.log(`Finished crystal ${target} microns`);
                counter = 0;
            }
        }

        if(base + 1 === target){
            console.log('X-ray x1');
            return console.log(`Finished crystal ${target} microns`);
        }
    }

    function cut(a) {
        return a*0.25;
    }
    function lap(a) {
        return a*0.80;
    }
    function grind(a) {
        return a-20;
    }
    function etch(a) {
        return a-2;
    }
    function transportingAndWashing(a) {
        return Math.floor(a);
    }
}



solution([1000, 4000, 8100]);