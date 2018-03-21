function generateSummary(selector) {
    $(selector).click(tick);
    function tick() {
        let summaryText  = $('#content strong').text();
        let parent = $('#content').parent();
        let div = $('<div>').attr('id', 'summary');
        div.append($('<h2>').text('Summary')).append($('<p>').text(summaryText));
        parent.append(div);
    }
}