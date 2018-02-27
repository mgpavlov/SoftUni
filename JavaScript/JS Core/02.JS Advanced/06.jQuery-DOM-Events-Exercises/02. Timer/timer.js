function timer() {
    $('#start-timer').click(startCount);
    $('#stop-timer').click(stopCount);
    let sec = 0;
    let timer = null;
    let hours = $('#hours').text();
    let minutes = $('#minutes').text();
    let seconds = $('#seconds').text();

    function startCount() {
        if(timer){
            clearInterval(timer);
        }
        $('#stop-timer').attr('disabled', false);
        $('#start-timer').attr('disabled', true);
        timer = setInterval(tick, 1000);
    }
    function tick(seconds) {
        sec++;
        outputTimer(sec)
    }
    function stopCount() {
        $('#stop-timer').attr('disabled', true);
        $('#start-timer').attr('disabled', false);
        clearInterval(timer);
    }

    function outputTimer(value) {
        $('#hours').text(`0${Math.floor(sec / 3600)}`.slice(-2));
        $('#minutes').text(`0${Math.floor((sec % 3600) / 60)}`.slice(-2));
        $('#seconds').text(`0${Math.floor((sec % 3600) % 60)}`.slice(-2));
    }
}
