function solve(arr) {
    let result = (function () {
        let list = [];

        function add(string) {
            list.push(string);
        }

        function remove(string) {
            list = list.filter(a => a !== string);
        }

        function print() {
            console.log(list.toString());
        }
        return {
            add,
            remove,
            print
        }
    })();
    for (let commandStr of arr) {
        let tokens = commandStr.split(' ');
        let func = tokens[0];
        let string = tokens[1];
        result[func](string);
        /*if (func==='print')
            result.print();
        if (func === 'add')
            result.add(string);
        if (func === 'remove')
            result.remove(string);*/
    }
}

solve(['add pesho', 'add gosho', 'add pesho', 'remove pesho','print']);

/*
function solve(commands) {
    let processor = (function () {
        let list = [];
        return function (command) {
            let [type, str] = command.split(' ');
            switch (type) {
                case 'add':
                    list.push(str);
                    break;
                case 'remove':
                    list = list.filter(el => el !== str);
                    break;
                case 'print':
                    console.log(list.toString());
                    break;
            }
        }
    })();

    for(let command of commands) {
        processor(command);
    }
}*/
