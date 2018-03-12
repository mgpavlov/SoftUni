class Textbox {
    constructor(selector, regex) {
        this.selector = selector;
        this._elements = $(selector);
        this._invalidSymbols = regex;

        $(this._elements).on('input', (e) =>{
            this._value = $(e.target).val();
            this.updateElements();
        })
    }
    updateElements(){
        for (let element of this._elements) {
            $(element).val(this._value);
        }
    }
    get elements() {
        return this._elements;
    }

    set elements(value) {
        this._elements = value;
    }

    get value() {
        return this._value;
    }
    set value(newValue) {
        this._value = newValue;
        this.updateElements();
    }
    isValid() {
        return !this._invalidSymbols.test(this._value)
    }
}

let textbox = new Textbox(".textbox",/[^a-zA-Z0-9]/);
let inputs = $('.textbox');

inputs.on('input',function(){console.log(textbox.value);});
