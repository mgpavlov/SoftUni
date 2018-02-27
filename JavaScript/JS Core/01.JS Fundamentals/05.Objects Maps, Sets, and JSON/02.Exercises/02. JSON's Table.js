function solve(arr) {
    let result = '<table>\n';
    for (let line of arr) {
        let data = JSON.parse(line);
        let [name, position, salary] = [data.name, data.position, Number(data.salary)];
        result += ' <tr>\n';
        result += `    <td>${htmlEscaping(name)}</td>\n`;
        result += `    <td>${htmlEscaping(position)}</td>\n`;
        result += `    <td>${htmlEscaping(salary)}</td>\n`;
        result += ' <tr>\n';
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
solve(['{"name":"Pesho","position":"Promenliva","salary":100000}',
    '{"name":"Teo","position":"Lecturer","salary":1000}',
    '{"name":"Georgi","position":"Lecturer","salary":1000}'
]);

/*
function solve(arr) {

    let html = '<table>\n';

    for (let obj of arr) {

        html += '   <tr>\n';

        let employee = JSON.parse(obj);

        html += `       <td>${escapeHTML(employee.name)}</td>\n`;
        html += `       <td>${escapeHTML(employee.position)}</td>\n`;
        html += `       <td>${Number(employee.salary)}</td>\n`;

        html += '   <tr>\n';
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
