// submit in Judge
let Checkbox = require('./checkbox');
let Numberbox = require('./numberbox');
result.Checkbox = Checkbox;
result.Numberbox = Numberbox;

// // local testing
// class Checkbox {
//     constructor(label, selector) {
//         this._label = label;
//         this._elements = $(selector);
//         this._value = $(selector).is(':checked');
//
//         let that = this;
//         $(selector).change(function () {
//             that.value = $(selector).is(':checked');
//         })
//     }
//
//     get label() {
//         return this._label;
//     }
//
//     get elements() {
//         return this._elements;
//     }
//
//     get value() {
//         return this._value;
//     }
//
//     set value(val) {
//         if (typeof val !== "boolean")
//             throw new Error;
//         this._value = val;
//         $(this._elements).attr('checked', this._value);
//     }
// }
// class Numberbox {
//     constructor(label, selector, minValue, maxValue) {
//         this._label = label;
//         this._elements = $(selector);
//         this.minValue = minValue;
//         this.maxValue = maxValue;
//         this._value = minValue;
//
//         let that = this;
//         $(selector).change(function () {
//             that.value = $(this).val();
//         });
//     }
//
//     get label() {
//         return this._label;
//     }
//
//     get elements() {
//         return this._elements;
//     }
//
//     get value() {
//         return this._value;
//     }
//
//     set value(val) {
//         if (Number(val) < this.minValue || Number(val) > this.maxValue)
//             throw new Error;
//         this._value = val;
//         $(this._elements).val(val);
//     }
// }
//
// let checkbox = new Checkbox("Is Married:", "#married");
// let numberbox = new Numberbox("Age:", "#age", 1, 100);
// let checkboxSelector = $('#married');
// let numberboxSelector = $('#age');
// checkboxSelector.on('change', () => console.log(checkbox.value));
// numberboxSelector.on('change', () => console.log(numberbox.value));