function attachEvents() {
    $('#submit').click(getForecast);
    let weatherSymbols = {
        "Sunny": "&#x2600", // ☀
        "Partly sunny": "&#x26C5;", // ⛅
        "Overcast": "&#x2601;", // ☁
        "Rain": "&#x2614;", // ☂
        "Degrees": "&#176;" // °
    };

    function request(endpoint){
        return $.ajax({
            method: 'Get',
            url: 'https://judgetests.firebaseio.com/' + endpoint,
        });
    }
    function getForecast() {
        request('locations.json')
            .then(getData)
            .catch(handleError);
        function getData(data) {
            let city = $('#location').val();
            let code = data.filter(e=>e.name === city).map(e=>e=e.code)[0];
            if(!code){handleError()}
            let todayEndpoint = `forecast/today/${code}.json`;
            let upcomingEndpoint = `forecast/upcoming/${code}.json `;
            let todayForecast = request(todayEndpoint);
            let upcomingForecast = request(upcomingEndpoint);

            Promise.all([todayForecast, upcomingForecast]).then(displayData).catch(handleError);
            function displayData([todayData, upcomingData]) {
                $('#forecast').css('display', 'block');
                //todayForecast
                let todaySymb = todayData.forecast.condition;
                let cityCountry = todayData.name;
                let tempHigh = todayData.forecast.high+'&#176;';
                let tempLow = todayData.forecast.low+'&#176;';
                let divToday = $('#current');
                divToday.empty();
                let spanSymb = $('<span>').addClass('condition symbol').html(weatherSymbols[todaySymb]);
                let today = $('<span>').addClass('condition');
                let spanCity = $('<span>').addClass('forecast-data').html(cityCountry);
                let spanTemp = $('<span>').addClass('forecast-data').html(tempLow+'/'+tempHigh);
                let spanCond = $('<span>').addClass('forecast-data').html(todaySymb);
                divToday.append(spanSymb);
                today.append(spanCity).append(spanTemp).append(spanCond);
                divToday.append(today);

                //upcoming forecast
                let divUpcoming = $('#upcoming');
                divUpcoming.empty();
                function displayUpcomingForecast(symb, tempLowU, tempHighU) {
                    tempHighU += '&#176;';
                    tempLowU += '&#176;';
                    let todayU = $('<span>').addClass('upcoming');
                    let spanSymbU = $('<span>').addClass('symbol').html(weatherSymbols[symb]);
                    let spanTempU = $('<span>').addClass('forecast-data').html(tempLowU+'/'+tempHighU);
                    let spanCondU = $('<span>').addClass('forecast-data').html(symb);
                    divUpcoming.append(todayU.append(spanSymbU).append(spanTempU).append(spanCondU))
                }

                for (let day of upcomingData.forecast) {
                    displayUpcomingForecast(day.condition, day.low, day.high)
                }
            }
        }
    }
    function handleError() {
        $('#forecast')
            .css('display', 'block') // Unlock div
            .text('Error');
    }
}

