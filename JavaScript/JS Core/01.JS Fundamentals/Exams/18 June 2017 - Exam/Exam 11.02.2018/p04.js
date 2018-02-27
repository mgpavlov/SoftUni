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
    let winsTracker = new Map();
    let losses = new Map();
    for (let [attackingKingdom, attackingGeneral, defendingKingdom, defendingGeneral] of commands) {
        if(army.get(attackingKingdom).get(attackingGeneral) > army.get(defendingKingdom).get(defendingGeneral)){
            army.get(attackingKingdom).set(attackingGeneral, Math.floor(army.get(attackingKingdom).get(attackingGeneral)*1.1));
            army.get(defendingKingdom).set(defendingGeneral, Math.floor(army.get(defendingKingdom).get(defendingGeneral)*0.9));
            if(!winsTracker.has(attackingKingdom)){
                winsTracker.set(attackingKingdom, new Map());
            }
            if(!winsTracker.get(attackingKingdom).has(attackingGeneral)){
                winsTracker.get(attackingKingdom).set(attackingGeneral, 0);
            }
            let number = winsTracker.get(attackingKingdom).get(attackingGeneral);
            winsTracker.get(attackingKingdom).set(attackingGeneral, number+1);
            if (!losses.has(defendingGeneral))
                losses.set(defendingGeneral, 0);
            losses.set(defendingGeneral, losses.get(defendingGeneral)+1);
        }else{
            army.get(attackingKingdom).set(attackingGeneral, Math.floor(army.get(attackingKingdom).get(attackingGeneral)*0.9));
            army.get(defendingKingdom).set(defendingGeneral, Math.floor(army.get(defendingKingdom).get(defendingGeneral)*1.1));
            if(!winsTracker.has(defendingKingdom)){
                winsTracker.set(defendingKingdom, new Map());
            }
            if(!winsTracker.get(defendingKingdom).has(defendingGeneral)){
                winsTracker.get(defendingKingdom).set(defendingGeneral, 0);
            }
            let number = winsTracker.get(defendingKingdom).get(defendingGeneral);
            winsTracker.get(defendingKingdom).set(defendingGeneral, 1 + number);
            if (!losses.has(attackingGeneral))
            losses.set(attackingGeneral, 0);
            losses.set(attackingGeneral, losses.get(attackingGeneral)+1);
        }

    }

    console.log(
    [...winsTracker][Symbol.iterator] = function* () {
        yield* [...this.entries()].sort((a, b) => a[1] - b[1]);
    });
}
solve([ { kingdom: "Stonegate", general: "Ulric", army: 5000 },
        { kingdom: "YorkenShire", general: "Quinn", army: 5000 },
        { kingdom: "Maiden Way", general: "Berinon", army: 1000 } ],
    [ ["YorkenShire", "Quinn", "Stonegate", "Ulric"],
        ["Maiden Way", "Berinon", "YorkenShire", "Quinn"] ]
);