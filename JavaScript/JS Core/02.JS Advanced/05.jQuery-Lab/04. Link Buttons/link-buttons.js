function attachEvents() {
    $('a.button').click(buttonClicked);

    function buttonClicked() {
        $('.button').removeClass('selected');
        $(this).addClass('selected');
    }
}
