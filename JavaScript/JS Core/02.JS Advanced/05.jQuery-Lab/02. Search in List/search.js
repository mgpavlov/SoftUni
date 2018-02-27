function search() {
    let pattern = $('#searchText').val();
    let list = $(`#towns li:contains(${pattern})`)
    list.css('font-weight', 'bold');
    $(`#towns li:not(:contains(${pattern}))`).css('font-weight', '');
    $('#result').text(list.length + ' matches found');
}