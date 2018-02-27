function hungryProgrammer(meals, commands) {

    let counter = 0;
    for (let order of commands) {
        switch (order.split(' ')[0]) {
            case ('Serve'):
                serve(meals);
                break;
            case ('Add'):
                if(order.split(' ')[1]!== undefined)
                add(meals, order.split(' ')[1]);
                break;
            case ('Shift'):
                if (order.split(' ')[1] !== undefined && order.split(' ')[2] !== undefined)
                shift(Number(order.split(' ')[1]), Number(order.split(' ')[2]), meals);
                break;
            case ('Eat'):
                if(meals.length>0){
                    console.log((meals.shift() + ' eaten'));
                    counter++;
                }

                break;
            case ('Consume'):
                if (order.split(' ')[1] !== undefined && order.split(' ')[2] !== undefined){
                    let token = consume(Number(order.split(' ')[1]), Number(order.split(' ')[2]), meals);
                    if(token !== undefined){
                        counter += token[0];
                        console.log(token[1]);
                    }
                }

                break;
            case ('End'):
                end(meals, counter);
                return;
            default: break;
        }

        function serve(arr) {
            if (arr.length > 0) {
                console.log(arr.pop() + ' served!');
            }
        }

        function eat(arr) {
            if (arr.length > 0) {
                console.log((arr.shift() + ' eaten'));
            }
        }

        function add(arr, meal) {
            arr.unshift(meal);
        }

        function consume(startIndex, endIndex, arr) {
            if (arr.length > endIndex) {
                let counter = Math.min((endIndex+1), (arr.length)) - startIndex;
                arr.splice(startIndex, (Math.min(endIndex+1, (arr.length)) - startIndex));

                return [counter, 'Burp!']

            }
        }

        function shift(firstIndex, secondIndex, arr) {
            if (arr.length > firstIndex && arr.length > secondIndex) {
                let temp = arr[firstIndex];
                arr[firstIndex] = arr[secondIndex];
                arr[secondIndex] = temp;
            }
        }

        function end(arr, counter) {
            if (arr.length === 0) {
                console.log('The food is gone');
                console.log(`Meals eaten: ${counter}`);

            } else {
                console.log(`Meals left: ${arr.join(', ')}`);
                console.log(`Meals eaten: ${counter}`);

            }
        }
    }
}

hungryProgrammer(['bacon', 'veggies', 'chicken', 'turkey', 'eggs'], ['Shift 2 9',
    'Eat',
    'Serve',
    'End',
    'Serve']);