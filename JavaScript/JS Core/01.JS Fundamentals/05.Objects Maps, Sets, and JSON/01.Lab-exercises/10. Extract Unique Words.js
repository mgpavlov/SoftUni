function solve (arr) {
    let result = new Set();
    let pattern = /[^\w]/g;
    for (let str of arr) {
        let words = str.split(pattern).filter(a=>a !=='').map(w=>w.toLowerCase());
        for (let w of words) {
            result.add(w);
        }
    }
    console.log(Array.from(result).join(', '));
}
solve([
    'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque quis hendrerit dui.',
    'Quisque fringilla est urna, vitae efficitur urna vestibulum fringilla.',
    'Vestibulum dolor diam, dignissim quis varius non, fermentum non felis.',
    'Vestibulum ultrices ex massa, sit amet faucibus nunc aliquam ut.',
    'Morbi in ipsum varius, pharetra diam vel, mattis arcu.',
    'Integer ac turpis commodo, varius nulla sed, elementum lectus.',
    'Vivamus turpis dui, malesuada ac turpis dapibus, congue egestas metus.'
]);

/*

function solve(arr) {

    let record = new Set();

    for (let sentence of arr) {
        let words = sentence.split(/\W+/g).filter(w => w !== '').map(w => w.toLowerCase());

        for (let word of words) {
            record.add(word);
        }
    }

    console.log(Array.from(record).join(', '));
}
*/
