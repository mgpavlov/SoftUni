function attachEvents() {
    $('#items li').on('click', clickedTown);
    let listTowns = [];
    function clickedTown() {
        if ($(this).attr('data-selected')){
            $(this).removeAttr('data-selected');
            $(this).css('background', '');
            listTowns.pop();
        }else{
            $(this).attr('data-selected', true);
            $(this).css('background', '#DDD');
            listTowns.push($(this).text());
        }
    }

    $('#showTownsButton').click(showTowns);

    function showTowns() {
        $('#selectedTowns').text('Selected towns: '+listTowns.join(', '));
    }
}
