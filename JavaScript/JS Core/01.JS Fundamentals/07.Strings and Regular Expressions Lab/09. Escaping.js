function escaping(arr) {

    let text = '<ul>\n';
    for (let str of arr) {
        text += '<li>';
        text += str.replace(/&/g, '&amp;')
              .replace(/</g, '&lt;')
              .replace(/>/g, '&gt;')
              .replace(/"/g, '&quot;');
        text += '</li>\n';
    }
    text += '</ul>';
    console.log(text);
}

escaping(['<b>unescaped text</b>', 'normal text']);