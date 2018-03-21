class Turtle {
    constructor(name, age, gender) {
        if (new.target === Turtle)
            throw new Error;
        // private members, getters only
        this._name = name;
        this._age = age;
        this._gender = gender;
    }

    get name() {
        return this._name;
    }

    get age() {
        return this._age;
    }

    get gender() {
        return this._gender;
    }

    grow(age) {
        this._age += age;
    }

    toString() {
        return `Turtle: ${this._name}\n` +
            `Aged - ${this._age}; Gender - ${this._gender}`;
    }
}

module.exports = Turtle;