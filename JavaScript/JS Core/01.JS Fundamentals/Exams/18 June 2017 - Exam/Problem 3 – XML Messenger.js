function solve(str) {
    let pattern = /^<message( ([a-z]+)="([a-zA-Z0-9 .]+)")+>((.+)(\n.+)*)<\/message>$/;
    if (!pattern.test(str)) return 'Invalid message format';
    let patternAttrib = /([a-z]+)="([a-zA-Z0-9 .]+)"/g;
    let sender = '';
    let recipient = '';

    let attributes = patternAttrib.exec(str);
     while(attributes){
         if(attributes[1] === 'from') sender +=attributes[2];
         else if (attributes[1] === 'to') recipient +=attributes[2];
         attributes = patternAttrib.exec(str);
     }

    if (sender === '' || recipient === '') {
        console.log("Missing attributes");
        return;
    }
        let message = pattern.exec(str)[4];
        message = message.replace(/\n/g, '</p>\n    <p>');

        let html = `<article>\n  <div>From: <span class="sender">${sender}</span></div>\n`;
        html += `  <div>To: <span class="recipient">${recipient}</span></div>\n`;
        html += `  <div>\n    <p>${message}</p>\n  </div>\n</article>`;
        console.log(html);
}
solve('<message to="Bob" from="Alice" timestamp="1497254092">Hey man,  \n what\'s up?</message>');