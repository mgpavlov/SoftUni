function solve(arr) {
    let result = new Map();
    for (let line of arr) {
        let[system, component, subComponent] = line.split(' | ').filter( a => a !=='' );
        if(!result.has(system)){
            result.set(system, new Map())
        }
        if (!result.get(system).has(component)){
            result.get(system).set(component, [])
        }
        let existSubComponent = result.get(system).get(component);
        result.get(system).get(component, existSubComponent.push(subComponent));
    }

/*правим мапа на масив [string, map] и сортираме*/
        result = [...result].sort((a,b) => {
        if (a[1].size > b[1].size) return -1; //сортираме по низходящ ред на броя компоненти;
        if (a[1].size < b[1].size) return 1; //сортираме по низходящ ред на броя компоненти;
        if (a[0].toLowerCase() > b[0].toLowerCase())return 1; //сортираме по азбучен ред на типа система;
        if (a[0].toLowerCase() < b[0].toLowerCase())return -1; //сортираме по азбучен ред на типа система;
    });

    for (let [system, components] of result) {
        console.log(system);

//правим components на масив [string, [string, string...]]

        components = [...components].sort((a,b)=>{
            if(a[1].length > b[1].length) return -1; //сортираме по низходящ ред по броя subcomponents(което е дължината на масива);
            if(a[1].length < b[1].length) return 1; //сортираме по низходящ ред по броя subcomponents(което е дължината на масива);
        });
        for (let [component, subComponents] of components) {
            console.log('|||'+component);
            for (let subComp of subComponents) {
                console.log('||||||'+subComp);
            }
        }
    }
}

solve(['SULS | Main Site | Home Page',
    'SULS | Main Site | Login Page',
    'SULS | Main Site | Register Page',
    'SULS | Judge Site | Login Page',
    'SULS | Judge Site | Submittion Page',
    'Lambda | CoreA | A23',
    'SULS | Digital Site | Login Page',
    'Lambda | CoreB | B24',
    'Lambda | CoreA | A24',
    'Lambda | CoreA | A25',
    'Lambda | CoreC | C4',
    'Indice | Session | Default Storage',
    'Indice | Session | Default Security'
]);


/*
function solve(arr) {

    let record = new Map();

    for (let obj of arr) {
        let [systemName, component, subcomponent] = obj.split(' | ');

        if (!record.has(systemName)) {
            record.set(systemName, new Map());
        }
        if (!record.get(systemName).has(component)) {
            record.get(systemName).set(component, []);
        }

        record.get(systemName).get(component).push(subcomponent);
    }

    record = [...record].sort((a, b) => {
        if (a[1].size > b[1].size) return -1;
        if (a[1].size < b[1].size) return 1;
        if(a[0].toLowerCase() < b[0].toLowerCase()) return -1;
        if(a[0].toLowerCase() > b[0].toLowerCase()) return 1;
    });

    for (let [system, components] of record) {
        console.log(system);

        components = [...components].sort((a, b) => {
            if (a[1].length > b[1].length) return -1;
            if (a[1].length < b[1].length) return 1;
        });

        for (let [component, subs] of components) {
            console.log(`|||${component}`);

            for (let sub of subs) {
                console.log(`||||||${sub}`);
            }
        }
    }
}
*/
