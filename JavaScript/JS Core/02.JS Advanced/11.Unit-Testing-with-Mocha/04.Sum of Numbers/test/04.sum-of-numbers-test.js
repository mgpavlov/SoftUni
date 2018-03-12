let sum = require('../04.Sum of Numbers').sum;
let expect = require('chai').expect;

describe('Test summator', function () {
    it('should return 3 for [1,2]', function () {
        expect(sum([1, 2])).to.equal(3);
    });
    it('should return 0 for []', function () {
        expect(sum([])).to.equal(0);
    });
    it('should return 1 for [1]', function () {
        expect(sum([1])).to.equal(1);
    });
    it('should return NaN', function () {
        expect(sum(['misho', 2, 3])).to.be.NaN;
    });
    it('should return 3.3', function () {
        expect(sum([1.1, 1.1, 1.1])).to.be.closeTo(3.3, 0.000001);
    });
    it('should return 2', function () {
        expect(sum([1.1, 1.9, -1])).to.equal(2);
    });
    it('should return NaN', function () {
        expect(sum('misho')).to.be.NaN;
    });
});

/*describe("sum(arr) - sum array of numbers", function() {
    it("should return 3 for [1, 2]", function() {
        expect(sum([1, 2])).to.be.equal(3);
    });
    it("should return 1 for [1]", function() {
        expect(sum([1])).to.be.equal(1);
    });
    it("should return 0 for empty array", function() { … });
    it("should return 3 for [1.5, 2.5, -1]", function() { … });
    it("should return NaN for invalid data", function() { … });
});*/
