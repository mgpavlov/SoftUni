let Turtle = require('./turtle');

class GalapagosTurtle extends Turtle {
    constructor(name, age, gender) {
        super(name, age, gender);
        // getter only
        this._thingsEaten = [];
    }

    get thingsEaten() {
        return this._thingsEaten;
    }

    eat(food) {
        this._thingsEaten.push(food);
    }

    grow(age) {
        super.grow(age);
        this._thingsEaten = [];
    }

    toString() {
        return super.toString() +
            `\nThings, eaten this year: ` + this._thingsEaten.join(', ');
    }
}

module.exports = GalapagosTurtle;