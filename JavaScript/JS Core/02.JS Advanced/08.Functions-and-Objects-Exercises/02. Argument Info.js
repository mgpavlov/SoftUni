function result() {
    let result = {};
    for (let arg of arguments) {
        console.log(typeof arg+': '+arg);
        if(!result[typeof arg]) result[typeof arg] = 0;
        result[typeof arg]++;
    }
    let sortedArr = [];
    for (let el in result) {
        sortedArr.push([el, result[el]]);
    }
    sortedArr.sort((a,b)=> b[1]-a[1]);
    for (let [type, value] of sortedArr) {
        console.log(type +' = '+ value);
    }
}
result(42, 'cat', 15, 'kitten', 'tomcat');

/*
function solve() {
    let typesCount = new Map;

    for(let arg of arguments) {
        let type = typeof arg;
        if(!typesCount.has(type)){
            typesCount.set(type, 0);
        }

        let oldValue = typesCount.get(type);
        typesCount.set(type, oldValue + 1);
        console.log(`${type}: ${arg}`);
    }

    [...typesCount]
        .sort((a, b) => b[1] - a[1])
        .forEach(entry => console.log(`${entry[0]} = ${entry[1]}`))
}*/


/*
function argumentInfo(input) {
    let typeCounts = {}; // type, count
    for (let argument of arguments) {
        let type = typeof argument;
        if (!typeCounts[type])
            typeCounts[type] = 0;
        typeCounts[type]++;
        console.log(`${type}: ${argument}`);
    }
    let sortedTypes = [...Object.keys(typeCounts)]
        .sort((a, b) => typeCounts[b] - typeCounts[a]);
    for (let type of sortedTypes)
        console.log(`${type} = ${typeCounts[type]}`);
}

argumentInfo('cat', 42, function () { console.log('Hello world!'); });*/


/*
function argumentInfo() {
    let typeCounts = {};
    for(let arg of arguments){
        console.log(`${typeof(arg)}: ${arg}`);
        if(! typeCounts[typeof(arg)]){
            typeCounts[typeof(arg)] = 1;
        } else {
            typeCounts[typeof(arg)]++;
        }
    }

    Object.keys(typeCounts).sort((a, b) => typeCounts[b] - typeCounts[a]).forEach(k => console.log(`${k} = ${typeCounts[k]}`))
}*/

/*
function solve() {
    let counter = new Map();
    for (let i = 0; i < arguments.length; i++) {
        const ar = arguments[i];
        console.log(`${typeof ar}: ${ar}`)
        if (!counter.has(typeof ar)) {
            counter.set(typeof ar, 0);
        }
        counter.set(typeof ar, counter.get(typeof ar) + 1);
    }
    Array.from(counter.entries()).sort((a, b) => b[1] - a[1]).forEach(entry => {
        console.log(`${entry[0]} = ${entry[1]}`);
    });
}*/
