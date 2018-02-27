function solve(availableRooms, guests) {
    let rooms = new Map();
    let guestsWithoutRooms = 0;
    //правим нов мап и "складираме" в него стаите + празните легла;
    for (let currentRoom of availableRooms) {
        let roomSpace = currentRoom.type === 'double-bedded' ? 2 : 3;
        rooms.set(currentRoom.number, { type: currentRoom.type, emptyBeds: roomSpace });
    }
/*    console.log(rooms); Map {
    '101A' => { type: 'double-bedded', emptyBeds: 2 },
    '104' => { type: 'triple', emptyBeds: 3 },
    '101B' => { type: 'double-bedded', emptyBeds: 2 },
    '102' => { type: 'triple', emptyBeds: 3 } }*/
    //обхождаме всички двойки гости:
    for (let currentPair of guests) {
        let roomFound = false; // в случай че за двойката се намери стая roomFound = true;
        //проверяваме дали двойката е от различни полове:
        if (currentPair.first.gender !== currentPair.second.gender) {
            //ако е вярно горното обхождаме стаите
            for (let [key, value] of rooms) {
                // ако типа е "двойна" и има 2 свободни места:
                if (value.type === 'double-bedded' && value.emptyBeds === 2) {
                   // създаваме в стойността нa rooms пропърти guests-масив;
                    value.guests = []; // { type: 'double-bedded', emptyBeds: 2, guests:[] }
                    value.guests = [currentPair.first, currentPair.second]; // вкарваме в масива гостите: guest: [{ name: 'Sushi & Chicken', gender: 'female', age: 15 }, ...]
                    value.emptyBeds = 0; // и зануляваме  броя на свободните легла
                    roomFound = true; // намерена е стая следователно променяме на true
                    break; //минаваме на следващата двойка
                }
            }
            // ако двойката е с еднакъв пол:
        } else {
            //обхождаме стаите
            for (let [key, value] of rooms) {
                //ако стаята е 'тройка' и има 2 или 3 свободни места:
                if (value.type === 'triple' && value.emptyBeds > 1) {
                    //ако в стаята няма създадено пропърти guests го създаваме
                    if (value.guests === undefined) {
                        value.guests = [];
                        //ако има значи има един гост в него и проверяваме дали е с различен пол от сегашната двойка която гледаме
                    } else if (value.guests[0].gender !== currentPair.second.gender) {
                        continue;//ако са различни  излизаме и продължаваме с проверката на друга стая => for (let [key, value] of rooms)
                    }
                    if (currentPair.first !== undefined) {//проверяваме дали госта е коректно зададена (дали го има, защото може входа да  е некоректен);
                        value.guests.push(currentPair.first);//добавяме в guests първият гост от двойката;
                        value.emptyBeds -= 1; // и вадим 1 легло от свободните легла в стаята
                    }
                    value.guests.push(currentPair.second);//после добавяме вторият гост от двойката
                    value.emptyBeds -= 1; // и вадим 1 легло от свободните легла в стаята
                    roomFound = true; // имаме стая променяме на true
                    break;// минаваме към следваща стая!!!
                    //вече сме обиколили всички празни стаи и остават тройки с по 1 легло
                } else if (value.type === 'triple' && value.emptyBeds === 1) {
                    if (value.guests[0].gender === currentPair.second.gender) {//проверяваме дали настанените гости са със същия пол като настоящия втори гост
                        value.guests.push(currentPair.first === undefined ? currentPair.second : currentPair.first);//втрои защото ако е първи, може вече да го няма да е настанен на друго място и да е === undefined
                        value.emptyBeds -= 1;//ако има първи гост настаняваме него, ако не настаняваме вторият след което вадим едно свободно легло;
                        currentPair.first = undefined; // и казваме че първият гост от двойката е undefined
                    }
                }
            }
        }
        if (!roomFound) {
            guestsWithoutRooms += currentPair.first === undefined ? 1 : 2;// ако от първият от двойката е undefined имаме 1 ненастанен гост иначе 2ма
        }
    }

    //сортиране, правим мапа на масиви и го сортираме(става азбучно сортиране)
    for (let [room, value] of [...rooms].sort()) {
        //принтираме номера на стаята
        console.log(`Room number: ${room}`);
        if (value.guests !== undefined) {
            //сортираме гостите по азбучен ред
            for (let guest of value.guests.sort((a, b) =>
            {return a.name.toLowerCase().localeCompare(b.name.toLowerCase());
            })) {
                console.log(`--Guest Name: ${guest.name}`);
                console.log(`--Guest Age: ${guest.age}`);
            }
        }
        console.log(`Empty beds in the room: ${value.emptyBeds}`);
    }
    console.log(`Guests moved to the tea house: ${guestsWithoutRooms}`);
}

solve([ { number: '101A', type: 'double-bedded' },
        { number: '104', type: 'triple' },
        { number: '101B', type: 'double-bedded' },
        { number: '102', type: 'triple' } ],
    [ { first: { name: 'Sushi & Chicken', gender: 'female', age: 15 },
        second: { name: 'Salisa Debelisa', gender: 'female', age: 25 } },
        { first: { name: 'Daenerys Targaryen', gender: 'female', age: 20 },
            second: { name: 'Jeko Snejev', gender: 'male', age: 18 } },
        { first: { name: 'Pesho Goshov', gender: 'male', age: 20 },
            second: { name: 'Gosho Peshov', gender: 'male', age: 18 } },
        { first: { name: 'Conor McGregor', gender: 'male', age: 29 },
            second: { name: 'Floyd Mayweather', gender: 'male', age: 40 } } ]
);
