let isOddOrEven = require('./even-or-odd').isOddOrEven;
let expect = require('chai').expect;

describe('Test is odd or even', function () {
    it('should return even for koko', function () {
        expect(isOddOrEven('koko')).to.equal('even')
    });
    it('should return odd for gosho', function () {
        expect(isOddOrEven('gosho')).to.equal('odd')
    });
    it('should return even for empty string', function () {
        expect(isOddOrEven('')).to.equal('even')
    });
    it('should return undefind for numbers', function () {
        expect(isOddOrEven(3)).to.equal(undefined);
    });
    it('should return undefind for []', function () {
        expect(isOddOrEven([2, 3, 4, 5])).to.equal(undefined);
    });
});