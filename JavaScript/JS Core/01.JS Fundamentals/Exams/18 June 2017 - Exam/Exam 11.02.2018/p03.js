function solve(str) {
    let pattern = /.*<svg><cat><text>.*\[(.*)]<\/text><\/cat>\s*<cat><g>(.*<val>([1-9][0-9]*)<\/val>([0-9]+).*)+<\/g><\/cat><\/svg>.*/gm;
    let validationSurvey = /<svg>.*?<\/svg>/g;
    if (!validationSurvey.test(str)){
        return 'No survey found';
    }
    if(!pattern.test(str)){
        return 'Invalid format';
    }


    let match = pattern.exec(str);
    let label = match[1];
    let votesText = match[0];

    let votesPattern = /<val>([1-9][0-9]*)<\/val>([0-9]+)/gm;
    let voteMatch = votesPattern.exec(votesText);

    let totalVotes = 0;
    let counter = 0;
    while (voteMatch){
        totalVotes += Number(voteMatch[1])*Number(voteMatch[2]);
        counter+=Number(voteMatch[2]);
        voteMatch = votesPattern.exec(votesText);
    }
    let voteCounter = totalVotes/counter;

    console.log(`${label}: ${Math.round((voteCounter + 0.00001) * 100) / 100}`);
}

solve('<p>Our guests may enjoy a special menu of freshly caught seafood.</p>\n' +
    '<br>\n' +
    '<svg>\n' +
    '<cat><text>How do you rate the special menu? [Food - Special]</text></cat> \n' +
    '<cat>\n' +
    '<g><val>1</val>5</g>\n' +
    '<g><val>5</val>13</g>\n' +
    '<g><val>10</val>22</g>\n' +
    '</cat>\n' +
    '</svg>');