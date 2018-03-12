let isSymmetric = require('../05.Check-for-Symmetry').isSymmetric;
let expect = require('chai').expect;

describe('Test symmetry', function () {
    describe('Value tests', function () {
        it('should return true for [1,2,3,2,1]', function () {
            expect(isSymmetric([1,2,3,2,1])).to.equal(true);
        });
        it('should return false for [1,2,3,2,1]', function () {
            expect(isSymmetric([1,4,3,2,1])).to.equal(false);
        });
        it('should return true for [-1,-1]', function () {
            expect(isSymmetric([-1, -1])).to.equal(true);
        });
        it('should return true for [1]', function () {
            expect(isSymmetric([1])).to.equal(true);
        });
        it('should return true for []', function () {
            expect(isSymmetric([])).to.equal(true);
        });
        it('should return true for [1,2,2,1]', function () {
            expect(isSymmetric([-1, 2, 2, -1])).to.equal(true);
        });
        it('should return false for [1,3,2,1]', function () {
            expect(isSymmetric([1, 2, 2, -1])).to.equal(false);
        });
        it('should return true for [5,hi,{a:5},new Date(),{a:5},hi,5]', function () {
            expect(isSymmetric([5,'hi',{a:5},new Date(),{a:5},'hi',5] )).to.equal(true);
        });
        it('should return false for [-5,aa,{a:5},new Date(),{a:5},hi,5]', function () {
            expect(isSymmetric([-5,'aa',{a:5},new Date(),{a:5},'hi',5] )).to.equal(false);
        });
    })
    describe('General tests', function () {
        it('should be a function', function () {
            expect(typeof isSymmetric).to.equal('function');
        });
        it('should return false for 1,2,3', function () {
            expect(isSymmetric(1, 2, 3)).to.equal(false);
        });
    })
})

