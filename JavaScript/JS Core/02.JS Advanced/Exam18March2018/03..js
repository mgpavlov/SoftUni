class PaymentProcessor {
    constructor(options){
        this.payments = [];
        this.options = options;
        if (!options){
            this.options = {
                types: ["service", "product", "other"],
                precision: 2
            }
        } else {
            if (!options.hasOwnProperty('types')) {
                this.options['types'] = ["service", "product", "other"];
            }else{
                this.options['types'] = options['types'];
            }
            if (options.hasOwnProperty('precision')) {
                this.options['precision'] = options['precision'];
            }else{
                this.options['precision'] = 2;
            }
        }
    }

    registerPayment(id, name, type, value){
        this._validation(id, name, type, value);
        value = value.toFixed(this.options.precision);
        this.payments.push({
            id,
            name,
            type,
            value
        })
    }
    deletePayment(id){
        let hasId = false;
        let index = null;
        for (let i = 0; i < this.payments.length; i++) {
            if (this.payments[i].id === id){
                hasId = true;
                index = i;
            }
        }
        if (hasId){
            this.payments.splice(index, 1)
        }else {
            throw new Error('Invalid Payment id')
        }
    }
    get(id){
        let hasId = false;
        let index = null;
        for (let i = 0; i < this.payments.length; i++) {
            if (this.payments[i].id === id){
                hasId = true;
                index = i;
            }
        }
        if (hasId){
            let payment = this.payments[index];
            return `Details about payment ID: ${payment.id}\n- Name: ${payment.name}\n- Type: ${payment.type}\n-Value: ${payment.value}`
        }else {
            throw new Error('Invalid Payment id')
        }
    }
    setOptions(options){
        if (options.hasOwnProperty('types')) {
            this.options.types = options.types;
        }
        if (options.hasOwnProperty('precision') && (typeof options.precision === 'number')) {
            this.options.precision = options.precision;
        }
    }
    _validation(id, name, type, value){
        if (this.payments.map(a=>a.id).includes(id)){
            throw new Error('Invalid Payment id')
        }
        if (id === '' || name === ''){
            throw new Error('Id and Name must be non-empty strings')
        }
        if (typeof value !== 'number'){
            throw new Error('Value must be a number')
        }
        if (!this.options.types.includes(type)){
            throw new Error('Types must contains type')
        }
    }
    toString(){
        return `Summary:\n- Payments: ${this.payments.length}\n- Balance: ${this.payments.map(a=>a.value).reduce((a,b)=>Number(a)+Number(b), 0)}`
        /*let result = '';
        let count = this.payments.length;
        let sum = 0;
        if(this.payments!== []){
            for (let payment of this.payments) {
                sum+=Number(payment.value);
            }
            let myNamespace = {};
            result+=`Summary:\n- Payments: ${count}\n- Balance: ${sum.toFixed(Number(this.options.precision))}\n`
        }
        return result;*/
    }
}