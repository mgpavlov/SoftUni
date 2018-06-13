function generateSummary(selector) {
    let div = $('.wrapper');
    $(selector).click(displaySummary);
    let divSummary = $('<div id="summary">').append($('<h2>Summary</h2>'));
    let paragraph = $('<p>');
    function displaySummary() {
        let strongs = $('#content strong').text();
        div.append(divSummary.append(paragraph.text(strongs)));
    }
}

