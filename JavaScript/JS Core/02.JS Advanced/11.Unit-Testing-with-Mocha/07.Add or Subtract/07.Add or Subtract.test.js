let createCalculator = require('./07.Add or Subtract').createCalculator;
let expect = require('chai').expect;

describe('Calculator maker', function () {
    let calc;
    beforeEach(()=>{
        calc = createCalculator();
    });
    it('should return an object', function () {
        expect(typeof  calc).to.equal('object')
    });
    it('should hav 0 value when created', function () {
        expect(calc.get()).to.equal(0)
    });
    it('should add', function () {
        calc.add(3);
        calc.add(5);
        expect(calc.get()).to.equal(8)
    });
    it('should subtract', function () {
        calc.subtract(3);
        calc.subtract(5);
        expect(calc.get()).to.equal(-8)
    });
    it('should work with fraction', function () {
        calc.add(3.265);
        calc.add(5.135);
        expect(calc.get()).to.be.closeTo(8.4, 0.00001)
    });
    it('should work with negative number', function () {
        calc.add(-3.265);
        calc.add(-5.135);
        expect(calc.get()).to.be.closeTo(-8.4, 0.00001)
    });
    it('should not add NaNs', function () {
        calc.add('pesho');
        expect(calc.get()).to.be.NaN;
    });
    it('should not subtract NaNs', function () {
        calc.subtract('pesho');
        expect(calc.get()).to.be.NaN;
    });
    it('should work with numbers as strings', function () {
        calc.subtract('7');
        expect(calc.get()).to.equal(-7);
    });
    it('should work with numbers as strings', function () {
        calc.add('7');
        calc.add(1);
        expect(calc.get()).to.equal(8);
    });
});

