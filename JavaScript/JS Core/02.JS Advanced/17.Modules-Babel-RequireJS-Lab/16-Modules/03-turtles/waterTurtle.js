let Turtle = require('./turtle');

class WaterTurtle extends Turtle {
    constructor(name, age, gender, waterPool) {
        super(name, age, gender);
        // private member, getter only
        this._waterPool = waterPool;
    }

    get currentWaterPool() {
        return this._waterPool;
    }

    travel(waterPool) {
        this._waterPool = waterPool;
        this._age += 5;
    }

    toString() {
        return super.toString() +
            `\nCurrently inhabiting ${this.currentWaterPool}`;
        // `\nCurrently inhabiting ${this._waterPool}`;
    }
}

module.exports = WaterTurtle;