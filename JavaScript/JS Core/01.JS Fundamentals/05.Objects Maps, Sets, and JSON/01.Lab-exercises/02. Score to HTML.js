function solve(str) {
    let arr = JSON.parse(str);
    let result = '<table>\n';
    result += ' <tr><th>name</th><th>score</th></tr>\n';
    for (let obj of arr) {
        let name = obj['name'];
        let score = obj['score'];

        result += ` <tr><td>${htmlEscaping(name)}</td><td>${htmlEscaping(score)}</td></tr>\n`;
    }
    result += '</table>';

    console.log(result);
    function htmlEscaping(text) {
        text =''+text;
        return text
            .replace(/&/gm, '&amp;')
            .replace(/</gm, '&lt;')
            .replace(/>/gm, '&gt;')
            .replace(/"/gm, '&quot;')
            .replace(/'/gm, '&#39;');

    }
}

solve('[{"name":"Pesho","score":479},{"name":"Gosho","score":205}]');

/*
function solve(json) {
    let str = JSON.parse(json);

    let html = '<table>\n';
    html += '   <tr><th>name</th><th>score</th></tr>\n';

    for (let obj of str) {
        html+= '   <tr>';
        html += `<td>${escapeHTML(obj.name)}</td><td>${Number(obj.score)}</td>`;
        html += '</tr>\n';
    }

    html += '</table>';

    console.log(html);

    function escapeHTML(text) {
        let map = {
            '"': '&quot;',
            '&': '&amp;',
            "'": '&#39;',
            '<': '&lt;',
            '>': '&gt;',
        };

        return text.replace(/[\\"&'<>]/g, c => map[c]);
    }
}

solve('[{"name":"Pesho","score":479},{"name":"Gosho","score":205}]');
solve('[{"name":"Pesho & Kiro","score":479},{"name":"Gosho, Maria & Viki","score":205}]');*/
