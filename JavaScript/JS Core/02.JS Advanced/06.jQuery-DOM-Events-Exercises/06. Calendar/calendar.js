function calendar([day, month, year]) {
    let monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

    let captionContent = monthNames[month-1] + " " + year;

    $('#content')
        .append($('<table>').append($('<caption>').text(captionContent))
            .append($('<tbody>')));
    let numberOfDays = 0;
    let lastDayInMonth = new Date(year, month, 0);
    numberOfDays += lastDayInMonth.getDate();// взимаме броят на дните от настоящият месец;
    let previousMonthDays = (new Date(year, month-1, 0)).getDay(); // взимаме денят(пон=1, вт=2, ср=3, четв=4, пет=5, съб=6, нед=0) в който приключва предния месец, съответно това са дните от предния месец които влизат в първата седмица на настоящия;
    let nextMonthDays = (7 - new Date(year, month, 0).getDay() % 7)%7;//от 7 вадим денят(пон=1, вт=2, ср=3, четв=4, пет=5, съб=6, нед=0) в който приключва настоящия месец, така взимаме дните от следващия месец които влизат в последната седмица на настоящия месец;
    numberOfDays += previousMonthDays + nextMonthDays; // към дните на месеца прибавяме дните от предния месец които влизат в първата седмица на настоящия месец и съотетно дните от следващия месец които влизат в последната седмица на настоящия месец;
    // така получаваме общо дни кратни на 7 които определят броя седмиците които виждаме в календара

    $('tbody').append($('<tr>').append($('<th>').text("Mon")).append($('<th>').text("Tue")).append($('<th>').text("Wed")).append($('<th>').text("Thu")).append($('<th>').text("Fri")).append($('<th>').text("Sat")).append($('<th>').text("Sun")));

    let daysCounter = 1;

    let count = lastDayInMonth.getDay(); // ако последната дата от месеца се пада неделя lastDayInMonth.getDay() === 0 и влизаме в проверката на ред 34, затова правим тази операция
    if(lastDayInMonth.getDay() === 0){
        count = 7;
    }
    let currentDay;
    for(let i=0; i<numberOfDays/7; i++){
        $('tbody').append($('<tr>'));
        for(let j=0; j<7; j++){
            if(i === 0 && j< previousMonthDays){
                currentDay = "";
            }else if (i===numberOfDays/7-1 && j>= count){
                currentDay = "";
            }else {
                currentDay = daysCounter;
                daysCounter++;
            }
            if(currentDay === day){
                $('tbody tr:last-child').append($('<td>').addClass('today').text(currentDay));
            } else {
                $('tbody tr:last-child').append($('<td>').text(currentDay));
            }
        }
    }
}