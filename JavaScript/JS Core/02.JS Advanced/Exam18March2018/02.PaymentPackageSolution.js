let expect = require('chai').expect;

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


describe('payment package', function () {

    describe('init cases', function () {
        it('should init as class', function () {
            let pack = new PaymentPackage('pesho', 5.00);
            expect(pack instanceof PaymentPackage).to.be.true;
            expect(PaymentPackage.prototype.hasOwnProperty('name')).to.be.true;
            expect(PaymentPackage.prototype.hasOwnProperty('value')).to.be.true;
            expect(PaymentPackage.prototype.hasOwnProperty('VAT')).to.be.true;
            expect(PaymentPackage.prototype.hasOwnProperty('active')).to.be.true;
            expect(PaymentPackage.prototype.hasOwnProperty('toString')).to.be.true;
        });
        it('should have valid default params', function () {
            let pack = new PaymentPackage('pesho', 5.00);
            expect(pack.name).to.equal('pesho');
            expect(pack.value).to.equal(5);
            expect(pack.VAT).to.equal(20);
            expect(pack.active).to.be.true;
        });
    })
    describe('set name tests', function () {
        it('should throw if name is not string', function () {
            expect(() => {new PaymentPackage({}, 20)}).to.throw('Name must be a non-empty string')
        });
        it('should throw if name is empty string', function () {
            expect(() => {new PaymentPackage('', 20)}).to.throw('Name must be a non-empty string')
        });
    })
    describe('set value tests', function () {
        it('should throw if value is not int', function () {
            expect(() => {new PaymentPackage('name', '20')}).to.throw('Value must be a non-negative number')
        });
        it('should throw if value is negative int', function () {
            expect(() => {new PaymentPackage('payment', -5)}).to.throw('Value must be a non-negative number')
        });
        it('should work if both valid', function () {
            let pack = new PaymentPackage('Sum', 0);
            expect(pack.name).to.equal('Sum');
            expect(pack.value).to.equal(0);
        });
    })
    describe('vat tests', function () {
        it('should throw if vat is not int', function () {
            let pack = new PaymentPackage('Shipping', 20.35);
            expect(() => {pack.VAT = '40'}).to.throw('VAT must be a non-negative number');
            expect(pack.VAT).to.equal(20);
        });
        it('should throw if vat is negative int', function () {
            let pack = new PaymentPackage('Shipping', 20.35);
            expect(() => {pack.VAT = -20}).to.throw('VAT must be a non-negative number');
            expect(pack.VAT).to.equal(20);
        });
        it('should work if vat is positive int', function () {
            let pack = new PaymentPackage('Shipping', 20.35);
            expect(() => {pack.VAT = 30}).to.not.throw();
            expect(pack.VAT).to.equal(30);
        });
    })
    describe('active tests', function () {
        it('should get active value', function () {
            let pack = new PaymentPackage('Shipping', 20.35);
            expect(pack.active).to.be.true;
        });
        it('should throw if not boolean', function () {
            let pack = new PaymentPackage('Shipping', 20.35);
            expect(() => {pack.active = 'boolean'}).to.throw('Active status must be a boolean')
        });
        it('should work if param is boolean', function () {
            let pack = new PaymentPackage('Shipping', 20.35);
            pack.active = false;
            expect(pack.active).to.be.false;
        });
    });
    describe('toString tests', function () {
        it('should print active status', function () {
            let pack = new PaymentPackage('billing', 25.35);
            expect(pack.toString()).to.equal('Package: billing\n- Value (excl. VAT): 25.35\n- Value (VAT 20%): 30.42')
        });
        it('should print inactive status', function () {
            let pack = new PaymentPackage('billing', 25.35);
            pack.active = false;
            expect(pack.toString()).to.equal('Package: billing (inactive)\n- Value (excl. VAT): 25.35\n- Value (VAT 20%): 30.42')
        });
    });
});