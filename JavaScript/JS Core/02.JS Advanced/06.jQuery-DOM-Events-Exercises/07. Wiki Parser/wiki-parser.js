/*function wikiParser(selector) {
    let encodedText = $(selector).text();

    encodedText = encodedText.replace(/'''([^'=\[]*?)'''/g, '<b>$1</b>')
        .replace(/''([^'=\[]*?)''/g, '<i>$1</i>')
        .replace(/===([^'=\[]*?)===/g, '<h3>$1</h3>')
        .replace(/==([^'=\[]*?)==/g, '<h2>$1</h2>')
        .replace(/=([^'=\[]*?)=/g, '<h1>$1</h1>')
        .replace(/\[\[([^'=\[\|]*?)]]/g, '<a href="/wiki/$1">$1</a>')
        .replace(/(\[\[([^'=\[]*?)\|([^'=\[]*)]])/g, '<a href="/wiki/$2">$3</a>');

    $(selector).html(encodedText);
}*/
/*=Document title=
==First segment==
'''bold 1''' word ''italics 1'' '''bold2'''
    [[hyper first]] '''Bold three''' ''italics2'''
word [[hyper2]] [[hyperlink2|with Label]]
==Second segment==
===Third segment===
word '''word'''[[pipe|bomb]]*/


/*function wikiParser(selector) {
    let text = $(selector).text()
        .replace(/'''(.+?)'''/g, (m, g) => `<b>${g}</b>`)
        .replace(/''(.+?)''/g, (m, g) => `<i>${g}</i>`)
        .replace(/===(.+?)===/g, (m, g) => `<h3>${g}</h3>`)
        .replace(/==(.+?)==/g, (m, g) => `<h2>${g}</h2>`)
        .replace(/=(.+?)=/g, (m, g) => `<h1>${g}</h1>`)
        .replace(/\[\[([^\]\[]+?)\|([^\[\]]+?)]]/g, (m, g1, g2) => `<a href="/wiki/${g1}">${g2}</a>`)
        .replace(/\[\[([^\]\[]+?)]]/g, (m, g) => `<a href="/wiki/${g}">${g}</a>`);
    $(selector).html(text);
}*/




function wikiParser(selector) {
    // Capture text
    let text = $(selector).text();

    // Parse basic markup consecutively
    text = parseBasic(text, /'''(.+?)'''/, 'b'); // Bold
    text = parseBasic(text, /''(.+?)''/, 'i'); // Italics
    text = parseBasic(text, /===(.+?)===/, 'h3'); // Heading 3
    text = parseBasic(text, /==(.+?)==/, 'h2'); // Heading 2
    text = parseBasic(text, /=(.+?)=/, 'h1'); // Heading 1

    // Parse hyperlinks
    text = parseLink(text);

    $(selector)[0].innerHTML = text;

    function parseBasic(input, pattern, tag) {
        let regex = new RegExp(pattern);
        while (input.search(regex) > -1) {
            input = input.replace(regex, '<' + tag + '>' + input.match(regex)[1] + '</' + tag + '>');
        }
        return input;
    }

    function parseLink(input) {
        // First pass, piped links
        let regex = new RegExp(/\[\[([^\[\]]+?)\|(.+?)\]\]/);
        while (input.search(regex) > -1) {
            let link = input.match(regex)[1];
            let desc = input.match(regex)[2];
            input = input.replace(regex, '<a href="/wiki/' + link + '">' + desc + '</a>');
        }
        // Second pass, non-piped links
        regex = new RegExp(/\[\[([^\[\]]+?)\]\]/);
        while (input.search(regex) > -1) {
            let group = input.match(regex)[1];
            input = input.replace(regex, '<a href="/wiki/' + group + '">' + group + '</a>');
        }
        return input;

    }
}
