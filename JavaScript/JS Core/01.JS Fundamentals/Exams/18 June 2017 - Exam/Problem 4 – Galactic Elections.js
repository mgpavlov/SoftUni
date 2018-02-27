function solve(ballots) {
    let election = new Map();

    for (let i = 0; i < ballots.length; i++) {
        let system = ballots[i].system;
        let candidate = ballots[i].candidate;
        let votes = Number(ballots[i].votes);

        if (!election.has(system)) {
            election.set(system, new Map());
        }
        if (!election.get(system).has(candidate)) {
            election.get(system).set(candidate, 0);
        }
        let oldVOtes = election.get(system).get(candidate);
        election.get(system).set(candidate, oldVOtes + votes);
    }

    let result = new Map();
    [...election].map(([s, c]) =>
        [s, [...c].sort((a, b) =>
            b[1] - a[1]).reduce((a, b) =>
            [a[0], a[1] + b[1]])])
        .map(([s, [c, v]]) => [c, s, v])
        .forEach(([c, s, v], i, arr) => result.has(c) ? result.get(c).set(s, v) :
            result.set(c, new Map([[s, v]])));

    let ranking = [...result].map(([c, s]) =>
        [c, [...s].map(([s, v]) => v)
            .reduce((a, b) => a + b)])
        .sort(([c1, v1], [c2, v2]) => v2 - v1);

    let total = ranking.map(([c, v]) => v).reduce((a, b) => a + b);

    if (ranking[0][1] > total / 2) {
        console.log(`${ranking[0][0]} wins with ${ranking[0][1]} votes`);
        if (ranking.length > 1) {
            let runnerup = ranking[1][0];
            console.log(`Runner up: ${runnerup}`);
            [...result.get(runnerup)].sort(([s1, v1], [s2, v2]) => v2 - v1).forEach(s => console.log(`${s[0]}: ${s[1]}`))
        } else {
            console.log(`${ranking[0][0]} wins unopposed!`);
        }
    } else {
        console.log(`Runoff between ${ranking[0][0]} with ${Math.floor(ranking[0][1] / total * 100)}% and ${ranking[1][0]} with ${Math.floor(ranking[1][1] / total * 100)}%`);
    }

}



/*function solve(arr) {

    let systems = new Map();
    let finalResult = [];
    for (let ballot  of arr) {
        if(!systems.has(ballot['system'])) systems.set(ballot['system'], []);
        systems.get(ballot['system']).push(ballot['candidate']+'='+ballot['votes'] )
    }
    let allVotes = 0;
    for (let system of systems) {
        let [systemName, heroes] = system;
        let winnerName = '';
        let systemVote = 0;
        let tempVotes = 0;

            for (let hero of heroes) {
                let[heroName, votes] = hero.split('=');
                if (Number(votes) > tempVotes){
                    winnerName = heroName;
                    tempVotes = Number(votes);
                }
                systemVote += Number(votes);
            }
            allVotes += systemVote;
             let winnerData={system: systemName, name: winnerName, votes: systemVote};
            finalResult.push(winnerData);
    }

    finalResult.sort((a,b) => {
        a = a.votes; b= b.votes;
        if(a>b) return -1;
        if(b>a)return 1;
    });

    let theResult = new Map();
    for (let system of finalResult) {
        if(!theResult.has(system.name))
            theResult.set(system.name, 0);
        theResult.set(system.name, theResult.get(system.name) + system.votes);
    }
    let result = [...theResult].sort((a,b) => {
        a = a[1]; b= b[1];
        if(a>b) return -1;
        if(b>a)return 1;
    });
    if(result.length === 1){
        console.log(`${winner} wins with ${firstResult} votes`);
        console.log(`${winner} wins unopposed!`);
        return;
    }
    let winner = result[0][0];
    let runnerUp = result[1][0]
    let firstResult = Number(result[0][1]);
    let secResult = Number(result[1][1]);

    let percentFirst = (firstResult/finalResult)*100;
    let percentSec = (secResult/finalResult)*100;
    let runnerUpList =[];
    for (let line of finalResult) {
        if(line.name === runnerUp){
            runnerUpList.push({name:line.system, votes: line.votes} );
        }
    }
    runnerUpList.sort((a,b)=> b.votes-a.votes);
    if (firstResult>allVotes/2){
        console.log(`${winner} wins with ${firstResult} votes`);
        console.log(`Runner up: ${runnerUp}`);
        for (let el of runnerUpList) {
            console.log(`${el.name}: ${el.votes}`)
        }
        console.log();
    }else {
        console.log(`Runoff between ${winner} with ${Math.floor(percentFirst)} and ${runnerUp} with ${Math.floor(percentSec)}`);
    }
}*/

solve([ { system: 'Aheta', candidate: 'Flying Shrimp', votes: 10 },
    { system: 'Sigma', candidate: 'Space Cow',     votes: 200 },
    { system: 'Sigma', candidate: 'Flying Shrimp', votes: 120 },
    { system: 'Tau',   candidate: 'Space Cow',     votes: 15 },
    { system: 'Sigma', candidate: 'Space Cow',     votes: 60 },
    { system: 'Tau',   candidate: 'Flying Shrimp', votes: 150 } ]
);