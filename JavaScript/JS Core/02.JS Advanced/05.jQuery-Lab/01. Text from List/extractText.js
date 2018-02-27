function extractText() {
    let result = [];
    $('ul li').each((index, el) => result.push(el.textContent));
    $('#result').text(result.join(', '));
}
