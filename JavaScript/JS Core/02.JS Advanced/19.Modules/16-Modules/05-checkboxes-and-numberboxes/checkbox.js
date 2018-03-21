class Checkbox {
    constructor(label, selector) {
        this._label = label;
        this._elements = $(selector);
        this._value = $(selector).is(':checked');

        let that = this;
        $(selector).change(function () {
            that.value = $(selector).is(':checked');
        })
    }

    get label() {
        return this._label;
    }

    get elements() {
        return this._elements;
    }

    get value() {
        return this._value;
    }

    set value(val) {
        if (typeof val !== "boolean")
            throw new Error;
        this._value = val;
        $(this._elements).attr('checked', this._value);
    }
}

module.exports = Checkbox;