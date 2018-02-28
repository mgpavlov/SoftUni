function solve(commands) {
    let result = (function () {
        let cars = new Map();

        function create(name) {
            cars.set(name, {});
        }

        function inherit(name, parentName) {
            cars.set(name, Object.create(cars.get(parentName)));
        }

        function set(name, key, value) {
            cars.get(name)[key] =  value;
        }

        function print(name) {
            let current = cars.get(name);
            let props = [];
            for (let prop in current) {
                props.push(`${prop}:${current[prop]}`);
            }
            console.log(props.join(', '));
        }
        return{
            create,
            inherit,
            set,
            print
        }
    })();
    for (let command of commands) {
        let commandTokens = command.split(' ');
        switch (commandTokens[0]) {
            case 'create':
                if (commandTokens[2] === 'inherit') {
                    result.inherit(commandTokens[1], commandTokens[3])
                } else {
                    result.create(commandTokens[1]);
                }
                break;
            case 'set':
                result.set(commandTokens[1], commandTokens[2], commandTokens[3])
                break;
            case 'print':
                result.print(commandTokens[1]);
                break;
        }
    }
}

solve(['create c1',
    'create c2 inherit c1',
    'set c1 color red',
    'set c2 model new',
    'print c1',
    'print c2']
);
