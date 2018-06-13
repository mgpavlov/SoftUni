class PaymentPackage {
    constructor(name, value) {
            this.name = name;
            this.value = value;
            this.VAT = 20;      // Default value
            this.active = true; // Default value
        }

        get name() {
            return this._name;
        }

        set name(newValue) {
            if (typeof newValue !== 'string') {
                throw new Error('Name must be a non-empty string');
            }
            if (newValue.length === 0) {
                throw new Error('Name must be a non-empty string');
            }
            this._name = newValue;
        }

        get value() {
            return this._value;
        }

        set value(newValue) {
            if (typeof newValue !== 'number') {
                throw new Error('Value must be a non-negative number');
            }
        if (newValue < 0) {
            throw new Error('Value must be a non-negative number');
        }
        this._value = newValue;
    }

    get VAT() {
        return this._VAT;
    }

    set VAT(newValue) {
        if (typeof newValue !== 'number') {
            throw new Error('VAT must be a non-negative number');
        }
        if (newValue < 0) {
            throw new Error('VAT must be a non-negative number');
        }
        this._VAT = newValue;
    }

    get active() {
        return this._active;
    }

    set active(newValue) {
        if (typeof newValue !== 'boolean') {
            throw new Error('Active status must be a boolean');
        }
        this._active = newValue;
    }

    toString() {
        const output = [
            `Package: ${this.name}` + (this.active === false ? ' (inactive)' : ''),
            `- Value (excl. VAT): ${this.value}`,
            `- Value (VAT ${this.VAT}%): ${this.value * (1 + this.VAT / 100)}`
        ];
        return output.join('\n');
    }
}

let expect = require('chai').expect;

describe('Test PaymantPackage', function () {
    let myPackage = new PaymentPackage('bill', 1);
    //initial
    it('should init as class', function () {
        let pack = new PaymentPackage('pesho', 5.00);
        expect(pack instanceof PaymentPackage).to.be.true;
        expect(PaymentPackage.prototype.hasOwnProperty('name')).to.be.true;
        expect(PaymentPackage.prototype.hasOwnProperty('value')).to.be.true;
        expect(PaymentPackage.prototype.hasOwnProperty('VAT')).to.be.true;
        expect(PaymentPackage.prototype.hasOwnProperty('active')).to.be.true;
        expect(PaymentPackage.prototype.hasOwnProperty('toString')).to.be.true;
    });
    describe('Test initial parameters', function () {
        it('get VAT is exist', function () {
            expect(myPackage.VAT).to.equal(20)
        });
        it('active is exist', function () {
            expect(myPackage.active).to.equal(true)
        });
        it('name is exist', function () {
            expect(myPackage.name).to.equal('bill')
        });
        it('value is exist', function () {
            expect(myPackage.value).to.equal(1)
        });
        it('one param number', function () {
            expect(()=>myPackage = new PaymentPackage(2)).to.throw(Error, 'Name must be a non-empty string')
        });
        it('one param string', function () {
            expect(()=>myPackage = new PaymentPackage('asd')).to.throw(Error, 'Value must be a non-negative number')
        });
    });
    //name
    describe('Test name', function () {
        it('name === number', function () {
            expect(()=>myPackage = new PaymentPackage(0, 1)).to.throw(Error, 'Name must be a non-empty string')
        });
        it('name =""', function () {
            expect(()=>myPackage = new PaymentPackage('', 2)).to.throw(Error, 'Name must be a non-empty string')
        });

        it('name === {}', function () {
            expect(()=>myPackage = new PaymentPackage({}, 2)).to.throw(Error, 'Name must be a non-empty string')
        });
        it('name', function () {
            expect(()=>myPackage = new PaymentPackage()).to.throw(Error, 'Name must be a non-empty string')
        });



/*        it('name === number', function () {
            expect(()=>myPackage.name = 2).to.throw(Error, 'Name must be a non-empty string')
        });
        it('name =""', function () {
            expect(()=>myPackage.name = '').to.throw(Error, 'Name must be a non-empty string')
        });

        it('name === {}', function () {
            expect(()=> myPackage.name = {}).to.throw(Error, 'Name must be a non-empty string')
        });*/

        it('name === "bill2"', function () {
            myPackage.name = 'bill2';
            expect(myPackage.name).to.equal("bill2")
        });
    });

    //value
    describe('Test value', function () {
        it('value === string', function () {
            expect(()=>myPackage = new PaymentPackage('as', 'as')).to.throw(Error, 'Value must be a non-negative number')
        });
        it('value = -2', function () {
            expect(()=>myPackage = new PaymentPackage('asd', -2)).to.throw(Error, 'Value must be a non-negative number')
        });
        it('value = {}', function () {
            expect(()=>myPackage = new PaymentPackage('asd', {})).to.throw(Error, 'Value must be a non-negative number')
        });


      /*  it('value === string', function () {
            expect(()=> myPackage.value = 'as').to.throw(Error, 'Value must be a non-negative number')
        });
        it('value = -2', function () {
            expect(()=>myPackage.value = -2).to.throw(Error, 'Value must be a non-negative number')
        });
        it('value = {}', function () {
            expect(()=>myPackage.value = {}).to.throw(Error, 'Value must be a non-negative number')
        });*/


        it('value = 2', function () {
            myPackage.value = 2;
            expect(myPackage.value).to.equal(2)
        });

    });

    //VAT
    describe('Test VAT', function () {
        it('VAT === string', function () {
            expect(()=>myPackage.VAT = 'string').to.throw(Error, 'VAT must be a non-negative number')
        });
        it('VAT = -2', function () {
            expect(()=>myPackage.VAT = -2).to.throw(Error, 'VAT must be a non-negative number')
        });
        it('VAT = {}', function () {
            expect(()=>myPackage.VAT = {}).to.throw(Error, 'VAT must be a non-negative number')
        });

        it('value = 2', function () {
            myPackage.value = 2;
            expect(myPackage.value).to.equal(2)
        });
    });

    //active
    describe('Test Active', function () {
        it('Active === string', function () {
            expect(()=>myPackage.active = 'string').to.throw(Error, 'Active status must be a boolean')
        });
        it('active = -2', function () {
            expect(()=>myPackage.active = 2).to.throw(Error, 'Active status must be a boolean')
        });
        it('VAT = {}', function () {
            expect(()=>myPackage.active = {}).to.throw(Error, 'Active status must be a boolean')
        });

        it('active = false', function () {
            myPackage.active = false;
            expect(myPackage.active).to.equal(false)
        });
        it('active = false', function () {
            myPackage.active = true;
            expect(myPackage.active).to.equal(true)
        });
    });

    //toString
    describe('Test toString', function () {
        it('Test toString', function () {
            let pay = new PaymentPackage('test', 2);

            pay.active = true;
            expect(pay.toString()).to.be.equal('Package: test\n- Value (excl. VAT): 2\n- Value (VAT 20%): 2.4');

            pay.active = false;
            expect(pay.toString()).to.be.equal('Package: test (inactive)\n- Value (excl. VAT): 2\n- Value (VAT 20%): 2.4');
        });
    });
});