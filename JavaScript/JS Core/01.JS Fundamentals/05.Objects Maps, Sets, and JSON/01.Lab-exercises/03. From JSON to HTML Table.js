function solve(json) {
    let arr = JSON.parse(json);
    let titles = Object.keys(arr[0]);

    let result = '<table>\n';
    result += ' <tr>';
    for (let name of titles) {
        result += `<th>${name}</th>`
    }
    result +='</tr>\n';

    for (let obj of arr) {
        result += ` <tr>`;
        for (let name of titles) {
            result += `<td>${htmlEscaping(obj[name])}</td>`;
        }
        result += `</tr>\n`;
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

solve('[{"Name":"Pesho <div>-a","Age":20,"City":"Sofia"},' +
    '{"Name":"Gosho","Age":18,"City":"Plovdiv"},' +
    '{"Name":"Angel","Age":18,"City":"Veliko Tarnovo"}]'
);

/*
function fromJSONToHTMLTable(str) {
    let objs = JSON.parse(str);
    let html = '<table>\n';

    html += '   <tr>';

    for (let key of Object.keys(objs[0])) {
        html += `<th>${key}</th>`;
    }

    html += '</tr>\n';

    for (let obj of objs) {
        html += '   <tr>';

        let keys = Object.keys(obj);

        for (let key of keys) {

            if (Number(obj[key])){
                html += `<td>${Number(obj[key])}</td>`;
            }else {
                html += `<td>${escapeHTML(obj[key])}</td>`;
            }
        }

        html += '</tr>\n'
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
*/
