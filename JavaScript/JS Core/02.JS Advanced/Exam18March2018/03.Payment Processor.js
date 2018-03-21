class PaymentProcessor {
    constructor(options){
        this.options = options;
        if(!options){
            this.options = {
                types: ["service", "product", "other"],
                precision: 2
            };
        }else {
            if(!options.hasOwnProperty('types')){
                this.options['types'] = ["service", "product", "other"];
            }else {
                this.options['types'] = options['types'];
            }
            if(!options.hasOwnProperty('precision')){
                this.options['precision'] = 2;
            }else {
                this.options['precision'] = options['precision'];
            }
        }

        this.payments = [];
    }
    registerPayment(id, name, type, value){
        let isValid = true;
        this._validationPayment(id, name, type, value, isValid);
        if(isValid){
            this.payments.push({
                id: id,
                name: name,
                type: type,
                value: Number(value).toFixed(Number(this.options.precision))
            })
        }
    }
    deletePayment(id){
       let validId = false;
        for (let i = 0; i < this.payments.length; i++) {
            if(this.payments[i].id === id){
                this.payments.splice(i, 1);
                validId = true;
            }
        }
        if(validId === false){
            throw new Error('Invalid Payment id');
        }
    }
    get(id){
        let validId = false;
        for (let i = 0; i < this.payments.length; i++) {
            if(this.payments[i].id === id){
                validId = true;
                return `Details about payment ID: ${this.payments[i].id}\n- Name: ${this.payments[i].name}\n- Type: ${this.payments[i].type}\n- Value: ${this.payments[i].value}`;
            }
        }
        if(validId === false){
            throw new Error('Invalid Payment id');
        }
    }
    setOptions(newOptions){
        if(newOptions.hasOwnProperty('types')){
            if(Array.isArray(newOptions.types))
            this.options.types = newOptions.types;
        }
        if(newOptions.hasOwnProperty('precision')){
            if(typeof newOptions.precision === 'number')
            this.options.precision = newOptions.precision;
        }
    }
    _validationPayment(id, name, type, value, isValid){
        if(id ==='') {
            isValid = false;
            throw new Error('Id must be non-empty strings')
        }
        if(name ==='') {
            isValid = false;
            throw new Error('Name must be non-empty strings');
        }
        if(typeof value !== 'number') {
            isValid = false;
            throw new Error('Value must be a number');
        }
        let isType = false;
        for (let typ of this.options.types) {
            if(type === typ){
                isType = true;
            }
        }
        if(isType === false){
            isValid = false;
            throw new TypeError('Types must contains type');
        }

        for (let payment of this.payments) {
            if(payment.id === id){
                isValid = false;
                throw new Error('Invalid Payment id');
            }
        }
    }
    toString(){
        let result = '';
        let count = this.payments.length;
        let sum = 0;
        if(this.payments!== []){
            for (let payment of this.payments) {
                sum+=Number(payment.value);
            }
            let myNamespace = {};
            result+=`Summary:\n- Payments: ${count}\n- Balance: ${sum.toFixed(Number(this.options.precision))}\n`
        }
        return result;
    }
}

/*const aaa = new PaymentProcessor({types: ['material']});
console.log(aaa.options);*/
/*generalPayments.registerPayment('0001', 'Microchips', 'material', 15000);
generalPayments.registerPayment('01A3', 'Biopolymer', 'material', 23000);*/
// Initialize processor with default options
const generalPayments = new PaymentProcessor();
generalPayments.registerPayment('0001', 'Microchips', 'product', 15000);
generalPayments.registerPayment('01A3', 'Biopolymer', 'product', 23000);
console.log(generalPayments.toString());

// Should throw an error (invalid type)
//generalPayments.registerPayment('E028', 'Rare-earth elements', 'materials', 8000);

generalPayments.setOptions({types: ['product', 'material']});
generalPayments.registerPayment('E028', 'Rare-earth elements', 'material', 8000);
console.log(generalPayments.get('E028'));
generalPayments.registerPayment('CF15', 'Enzymes', 'material', 55000);

// Should throw an error (ID not found)
//generalPayments.deletePayment('E027');
// Should throw an error (ID not found)
//generalPayments.get('E027');

generalPayments.deletePayment('E028');
console.log(generalPayments.toString());

// Initialize processor with custom types
const servicePyaments = new PaymentProcessor({types: ['service']});
servicePyaments.registerPayment('01', 'HR Consultation', 'service', 3000);
servicePyaments.registerPayment('02', 'Discount', 'service', -1500);
console.log(servicePyaments.toString());

// Initialize processor with custom precision
const transactionLog = new PaymentProcessor({precision: 5});
transactionLog.registerPayment('b5af2d02-327e-4cbf', 'Interest', 'other', 0.00153);
console.log(transactionLog.toString());

