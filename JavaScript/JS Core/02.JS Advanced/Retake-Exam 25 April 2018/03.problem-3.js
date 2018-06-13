class LineManager {
    constructor(stops) {
        this.stops = stops;
        if (this.stops === []) {
            throw Error
        }
        for (let i = 0; i < this.stops.length; i++) {
            this.stop = this.stops[i];
            if (!this.stop.hasOwnProperty('name')) throw new Error(`Property name should be a non-empty string`);
            if (!this.stop.hasOwnProperty('timeToNext')) throw new Error(`Property timeToNext should be a positive number `);
            if (typeof this.stop['name'] !== 'string' && this.stop['name'] === '') {
                throw new Error(`Property name should be a non-empty string`)
            }
            if (typeof this.stop['timeToNext'] !== 'number' && this.stop.timeToNext < 0) {
                throw new Error(`Property timeToNext should be a positive number `)
            }
            if (Object.keys(this.stop).length !== 2) {
                throw new Error(`Only 2 property`)
            }

            if (i < this.stops.length - 1) {
                this.nextStop = this.stops[i+1]
            }
            this.coveredStops = i;
        }
        this.lastStop = this.stops[this.stops.length-1];
        this.duration = 0;
        this.delay = 0;
    }

    get atDepot() {
        if(this.stop === this.stops[this.stops.length-1]){
            return true
        }
        return false
    }

    get nextStopName() {
        if(this.stop === this.lastStop){
            return "At depot.";
        }
        return this.nextStop['name'];
    }

    get currentDelay() {
        return this.delay
    }

    arriveAtStop(minutes) {
        if (minutes < 0 || this.atDepot) {
            throw Error ("minutes cannot be negative")
        }
        else {
            this.stop = this.nextStop;
            this.duration += minutes;
            this.delay = minutes - this.stop['timeToNext'];

            if (this.stop === this.stops[this.stops.length - 1]) {
                return false
            }
            return true;
        }
    }

    _validate(stop) {
        /*if (!stop.hasOwnProperty('name')) {
            throw new Error(`Property name is missing from the entity!`)
        }
        if (!stop.hasOwnProperty('timeToNext')) {
            throw new Error(`Property timeToNext is missing from the entity!`)
        }*/
        if (typeof stop.name !== 'string' && stop.name === '') {
            throw new Error(`Property name should be a non-empty string`)
        }
        if (typeof stop.timeToNext !== 'number' && stop.timeToNext < 0) {
            throw new Error(`Property timeToNext should be a positive number `)
        }
        if (Object.keys(stop).length !== 2) {
            throw new Error(`Only 2 property`)
        }
    }

    toString() {
        return `Line summary\n- Next stop: ${this.nextStop['name']}\n- Stops covered: ${this.coveredStops}\n- Time on course: ${this.duration} minutes\n- Delay: ${this.delay} minutes`
    }

}