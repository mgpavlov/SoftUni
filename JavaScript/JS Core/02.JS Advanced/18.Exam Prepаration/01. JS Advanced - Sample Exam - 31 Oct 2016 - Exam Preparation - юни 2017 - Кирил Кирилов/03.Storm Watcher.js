class Reading{
    constructor(temperature, humidity, pressure, windSpeed){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.id = Reading.getId();
    }
    static getId(){
        if(!this.id)
            this.id = 0;
        return this.id++;
    }
    toString(){
        let weather = 'Not stormy';
        if(this.temperature < 20 &&
            this.windSpeed > 25 &&
            (this.pressure < 700 || this.pressure > 900)){
            weather = 'Stormy';
        }
        return `Reading ID: ${this.id}\nTemperature: ${this.temperature}*C\nRelative Humidity: ${this.humidity}%\nPressure: ${this.pressure}hpa\nWind Speed: ${this.windSpeed}m/s\nWeather: ${weather}`
    }
}
let reading1 = new Reading(28, 45, 800, 15);
console.log(reading1.id);
let reading2 = new Reading(28, 45, 800, 15);
console.log(reading2.id);

let Result = (function () {
    let closureId = 0;
    return class Reading{
        constructor(temperature, humidity, pressure, windSpeed){
            this.temperature = temperature;
            this.humidity = humidity;
            this.pressure = pressure;
            this.windSpeed = windSpeed;
            this.id = closureId++;
        }
        toString(){
            let weather = 'Not stormy';
            if(this.temperature < 20 &&
                this.windSpeed > 25 &&
                (this.pressure < 700 || this.pressure > 900)){
                weather = 'Stormy';
            }
            return `Reading ID: ${this.id}\nTemperature: ${this.temperature}*C\nRelative Humidity: ${this.humidity}%\nPressure: ${this.pressure}hpa\nWind Speed: ${this.windSpeed}m/s\nWeather: ${weather}`
        }
    }
})();