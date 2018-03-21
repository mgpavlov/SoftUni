let Turtle = require('./turtle');

class EvkodianTurtle extends Turtle {
    constructor(name, age, gender, evkodiumValue) {
        super(name, age, gender);
        // getter only
        this._evkodium = evkodiumValue;
    }

    get evkodium() {
        this._density = this._age * 2;
        if (this._gender == 'male')
            this._density = this._age * 3;

        return {
            value: this._evkodium,
            density: this._density
        };
    }

    toString() {
        return super.toString() +
            `\nEvkodium: ${this.evkodium.value * this.evkodium.density}`;
    }
}

module.exports = EvkodianTurtle;