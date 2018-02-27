function solve(username, email, number, arr) {

    let patternUsername = /(<!)[a-zA-Z]+!>/g;
    let patternEmail = /(<@)[a-zA-Z]+@>/g;
    let patternNumber = /(<\+)[a-zA-Z]+\+>/g;
    let result = [];
    for (let str of arr) {
        if(patternEmail.test(str))str = str.replace(patternEmail, email);
        if(patternUsername.test(str))str = str.replace(patternUsername, username);
        if(patternNumber.test(str))str = str.replace(patternNumber, number);
        result.push(str);
    }
    console.log(result.join('\n'));
}

solve('Pesho', 'pesho@softuni.bg', '90-60-90', ['Hello, <!username!>!', 'Welcome to your Personal profile.',
        'Here you can modify your profile freely.',
        'Your current username is: <!fdsfs!>. Would you like to change that? (Y/N)',
        'Your current email is: <@DasEmail@>. Would you like to change that? (Y/N)',
        'Your current phone number is: <+number+>. Would you like to change that? (Y/N)']
);