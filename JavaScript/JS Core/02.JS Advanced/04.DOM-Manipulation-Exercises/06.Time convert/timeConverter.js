function attachEventsListeners() {
    let dayBtn = document.getElementById('days');
    let hourBtn = document.getElementById('hours');
    let minBtn = document.getElementById('minutes');
    let secBtn = document.getElementById('seconds');

    let daysBtn = document.getElementById('daysBtn');
    let hoursBtn = document.getElementById('hoursBtn');
    let minsBtn = document.getElementById('minutesBtn');
    let secsBtn = document.getElementById('secondsBtn');

    function convertFromDays() {
        hourBtn.value = (Number(dayBtn.value)*24);
        minBtn.value = (Number(dayBtn.value)*1440);
        secBtn.value = (Number(dayBtn.value)*86400);
    }

    daysBtn.addEventListener("click", convertFromDays);

    function convertFromHours() {
        dayBtn.value = (Number(hourBtn.value)/24);
        minBtn.value = (Number(dayBtn.value)*1440);
        secBtn.value = (Number(dayBtn.value)*86400);
    }

    hoursBtn.addEventListener("click", convertFromHours);

    function convertFromMinutes() {
        dayBtn.value = (Number(minBtn.value)/1440);
        hourBtn.value = (Number(dayBtn.value)*24);
        secBtn.value = (Number(dayBtn.value)*86400);
    }

    minsBtn.addEventListener("click", convertFromMinutes);

    function convertFromSeconds() {
        dayBtn.value = (Number(secBtn.value)/86400);
        hourBtn.value = (Number(dayBtn.value)*24);
        minBtn.value = (Number(dayBtn.value)*1440);
    }
    secsBtn.addEventListener("click", convertFromSeconds);
}
