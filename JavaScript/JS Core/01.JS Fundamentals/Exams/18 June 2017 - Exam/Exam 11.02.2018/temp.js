function solve(data, commands) {

    let army = new Map();
    for (let line of data) {
        if(!army.has(line.kingdom)){
            army.set(line.kingdom, new Map());
        }
        if(!army.get(line.kingdom).has(line.general)){
            army.get(line.kingdom).set(line.general, 0);
        }
        let number = army.get(line.kingdom).get(line.general);
        army.get(line.kingdom).set(line.general, line.army + number);

    }
    let winsTracker = {};
    for (let [attackingKingdom, attackingGeneral, defendingKingdom, defendingGeneral] of commands) {
        if(army.get(attackingKingdom).get(attackingGeneral) > army.get(defendingKingdom).get(defendingGeneral)){
            army.get(attackingKingdom).set(attackingGeneral, Math.floor(army.get(attackingKingdom).get(attackingGeneral)*1.1));
            army.get(defendingKingdom).set(defendingGeneral, Math.floor(army.get(defendingKingdom).get(defendingGeneral)*0.9));
            if(!winsTracker.hasOwnProperty(attackingKingdom)){
                winsTracker[attackingKingdom] = {};
            }
            if(!winsTracker[attackingKingdom].hasOwnProperty(attackingGeneral)){
                winsTracker[attackingKingdom][attackingGeneral] = {'wins':0, 'losses':0};
            }
            let winn = winsTracker[attackingKingdom][attackingGeneral]['wins'];
            winsTracker[attackingKingdom][attackingGeneral]['wins'] = 1+winn;
            //losses
            if(!winsTracker.hasOwnProperty(defendingKingdom)){
                winsTracker[defendingKingdom] = {};
            }
            if(!winsTracker[defendingKingdom].hasOwnProperty(defendingGeneral)){
                winsTracker[defendingKingdom][defendingGeneral] = {'wins':0, 'losses':0};
            }
            let lost = winsTracker[defendingKingdom][defendingGeneral]['losses'];
            winsTracker[defendingKingdom][defendingGeneral]['losses'] = 1+lost;
        }else{
            army.get(attackingKingdom).set(attackingGeneral, Math.floor(army.get(attackingKingdom).get(attackingGeneral)*0.9));
            army.get(defendingKingdom).set(defendingGeneral, Math.floor(army.get(defendingKingdom).get(defendingGeneral)*1.1));
            if(!winsTracker.hasOwnProperty(defendingKingdom)){
                winsTracker[defendingKingdom] = {};
            }
            if(!winsTracker[defendingKingdom].hasOwnProperty(defendingGeneral)){
                winsTracker[defendingKingdom][defendingGeneral] = {'wins':0, 'losses':0};
            }
            let winn = winsTracker[defendingKingdom][defendingGeneral]['wins'];
            winsTracker[defendingKingdom][defendingGeneral]['wins'] = 1+winn;
            //losses
            if(!winsTracker.hasOwnProperty(attackingKingdom)){
                winsTracker[attackingKingdom] = {};
            }
            if(!winsTracker[attackingKingdom].hasOwnProperty(attackingGeneral)){
                winsTracker[attackingKingdom][attackingGeneral] = {'wins':0, 'losses':0};
            }
            let lost = winsTracker[attackingKingdom][attackingGeneral]['losses'];
            winsTracker[attackingKingdom][attackingGeneral]['losses'] = 1+lost;
        }
    }
    console.log(winsTracker);
/*    for (let kingdom of winsTracker) {
        
    }*/
}
solve([ { kingdom: "Stonegate", general: "Ulric", army: 5000 },
        { kingdom: "YorkenShire", general: "Quinn", army: 5000 },
        { kingdom: "Maiden Way", general: "Berinon", army: 1000 } ],
    [ ["YorkenShire", "Quinn", "Stonegate", "Ulric"],
        ["Maiden Way", "Berinon", "YorkenShire", "Quinn"] ]
);