let Turtle = require('./turtle');

class NinjaTurtle extends Turtle {
    constructor(name, age, gender, maskColor, weapon) {
        super(name, age, gender);
        // no indication public or private => private
        this._maskColor = maskColor;
        this._weapon = weapon;
        this._level = 0;
    }

    grow(age) {
        super.grow(age);
        this._level += age;
    }

    toString() {
        let output = super.toString() +
            `\n${this._name.slice(0, 3)} wears a ${this._maskColor} mask, and is `;

        if (this._level >= 100)     output += `BEYOND GODLIKE`;
        else if (this._level < 25)  output += `an apprentice`;
        else                        output += `smokin strong`;

        output += ` with the ${this._weapon}.`;

        return output;
    }
}

module.exports = NinjaTurtle;