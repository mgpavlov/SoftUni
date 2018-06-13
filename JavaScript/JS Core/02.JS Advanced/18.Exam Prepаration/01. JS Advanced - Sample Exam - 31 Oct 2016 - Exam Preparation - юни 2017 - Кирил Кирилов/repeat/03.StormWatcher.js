class Record {
    constructor(temperature, humidity, pressure, windSpeed) {
        this.id = Record.getId();
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
    }
    static getId() {
        if(!this.id)
            this.id = 0;
        return this.id++;
    }
    weatherStatus() {
        if(this.temperature < 20 &&
            this.windSpeed > 25 &&
            (this.pressure < 700 || this.pressure > 900)) {
            return 'Stormy';
        }
        return 'Not stormy';
    }

    toString() {
        return `Reading ID: ${this.id}\nTemperature: ${this.temperature}*C\nRelative Humidity: ${this.humidity}%\nPressure: ${this.pressure}hpa\nWind Speed: ${this.windSpeed}m/s\nWeather: ${this.weatherStatus()}`;
    }
}

let record1 = new Record(32, 66, 760, 12);
console.log(record1.toString());
