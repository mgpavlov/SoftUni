function solve(commands) {
    let text = '';
    function processor(commandString) {
        let commandTokens = commandString.split(' ');
        switch (commandTokens[0]){
            case 'print':
                console.log(text);
                break;
            case 'append':
                text += commandTokens[1];
                break;
            case 'removeStart':
                text = text.slice(+commandTokens[1]);
                break;
            case 'removeEnd':
                text = text.substring(0, text.length - Number(commandTokens[1]));
                break;
        }
    }
    for (let command of commands) {
        processor(command);
    }
}

solve(['append hello',
    'append again',
    'removeStart 3',
    'removeEnd 4',
    'print']
);