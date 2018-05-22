let lookupChar = require('./char-Lookup').lookupChar;
let expect = require('chai').expect;

describe('CharLookup Test', function () {
    it('should return M for lookupChar(Misho, 0)', function () {
        expect(lookupChar('Misho', 0)).to.equal('M');
    });
    it('should return о for lookupChar(misho, 4)', function () {
        expect(lookupChar('Misho', 4)).to.equal('o');
    });
    it('should return i for lookupChar(misho, 1)', function () {
        expect(lookupChar('Misho', 1)).to.equal('i');
    });
    it('should return undefined for type of first parameter non-string ', function () {
        expect(lookupChar(6, 4)).to.equal(undefined);
    });
    it('should return undefined for type of second parameter non-number', function () {
        expect(lookupChar('misho', 'asd')).to.equal(undefined);
    });
    it('should return Incorrect index for second parameter greater then string.length-1', function () {
        expect(lookupChar('misho', 5)).to.equal('Incorrect index');
    });
    it('should return Incorrect index for second parameter < 0', function () {
        expect(lookupChar('misho', -1)).to.equal('Incorrect index');
    });
    it('should return Incorrect index for lookupChar(\'\', 0)', function () {
        expect(lookupChar('', 0)).to.equal('Incorrect index');
    });
    it('should return undefined for empty input', function () {
        expect(lookupChar()).to.equal(undefined);
    });
    it('should return undefined for one string parameter', function () {
        expect(lookupChar('asd')).to.equal(undefined);
    });
    it('should return undefined for one number parameter', function () {
        expect(lookupChar(5)).to.equal(undefined);
    });
    it('Pass a floating-point (should return undefined)', function () {
        let result = lookupChar('message' , 3.3);
        expect(result).to.equal(undefined);
    });
});