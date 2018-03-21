class Sumator {
    constructor() {
        this.data = [];
    }
    add(item) {
        this.data.push(item);
    }
    sumNums() {
        let sum = 0;
        for (let item of this.data)
            if (typeof (item) === 'number')
                sum += item;
        return sum;
    }
    removeByFilter(filterFunc) {
        this.data = this.data.filter(x => !filterFunc(x));
    }
    toString() {
        if (this.data.length > 0)
            return this.data.join(", ");
        else
            return '(empty)';
    }
}

let expect = require('chai').expect;

describe('', function () {
    let mySumator = new Sumator();
    beforeEach(function () {
        mySumator = new Sumator;
    });
    describe('Test initial parameters', function () {
        it('this.data is an array', function () {
            expect(Array.isArray(mySumator.data)).to.equal(true);
        });
        it('add is exist', function () {
            expect(Sumator.prototype.hasOwnProperty('add')).to.equal(true)
        });
        it('sumNums is exist', function () {
            expect(Sumator.prototype.hasOwnProperty('sumNums')).to.equal(true)
        });
        it('add is exist', function () {
            expect(Sumator.prototype.hasOwnProperty('removeByFilter')).to.equal(true)
        });
        it('add is exist', function () {
            expect(Sumator.prototype.hasOwnProperty('toString')).to.equal(true)
        });
    });
    describe('Test add', function () {
        it('add empty', function () {
            expect(mySumator.add()).to.equal(undefined)
        });
        it('add empty', function () {
            mySumator.add();
            expect(mySumator.data.toString()).equal('')
        });
        it('add one number', function () {
            mySumator.add(3);
            expect(mySumator.data.toString()).equal('3')
        });
        it('add string', function () {
            mySumator.add('misho');
            expect(mySumator.data.toString()).equal('misho')
        });
        it('add object', function () {
            mySumator.add({});
            expect(mySumator.data.toString()).equal('[object Object]')
        });
        it('add 3 elements', function () {
            mySumator.add(1);
            mySumator.add('misho');
            mySumator.add(3);
            expect(mySumator.data.join(', ')).equal("1, misho, 3")
        });
    });
    describe('Test sumNums', function () {
        it('sum strings', function () {
            mySumator.add('misho')
            expect(mySumator.sumNums()).equal(0)
        });
        it('sum array', function () {
            mySumator.add({});
            expect(mySumator.sumNums()).equal(0)
        });
        it('sum num', function () {
            mySumator.add(1);
            expect(mySumator.sumNums()).equal(1)
        });
        it('sum nums', function () {
            mySumator.add(1);
            mySumator.add(6);
            mySumator.add(2);
            expect(mySumator.sumNums()).equal(9)
        });
        it('sum negative nums', function () {
            mySumator.add(-1);
            mySumator.add(6);
            mySumator.add(2);
            expect(mySumator.sumNums()).equal(7)
        });
        it('sum nums', function () {
            mySumator.add(-1);
            mySumator.add(-6);
            mySumator.add(-2);
            expect(mySumator.sumNums()).equal(-9)
        });
        it('sum nums', function () {
            mySumator.add(0);
            expect(mySumator.sumNums()).equal(0)
        });
    });
    describe('removeByFilter', function () {
        it('removeByFilter', function () {
            mySumator.add(3);
            mySumator.add('misho');
            mySumator.add('gosho');
            mySumator.removeByFilter(function (el) {
                if(typeof el === 'number'){
                    return false
                }
            });
            expect(mySumator.toString()).equal("3, misho, gosho")
        });
    });
    describe('toString', function () {
        it('toString', function () {
            mySumator.add(3);
            mySumator.add('misho');
            mySumator.add('gosho');
            expect(mySumator.toString()).equal("3, misho, gosho");
        });
        it('toString', function () {
            expect(mySumator.toString()).equal('(empty)');
        });
    });
});