function createList() {
    let data = [];
    return {
        add: function (item) {
            data.push(item)
        },
        shiftLeft: function () {
            if (data.length > 1) {
                let first = data.shift();
                data.push(first);
            }
        },
        shiftRight: function () {
            if (data.length > 1) {
                let last = data.pop();
                data.unshift(last);
            }
        },
        swap: function (index1, index2) {
            if (!Number.isInteger(index1) || index1 < 0 || index1 >= data.length ||
                !Number.isInteger(index2) || index2 < 0 || index2 >= data.length ||
                index1 === index2) {
                return false;
            }
            let temp = data[index1];
            data[index1] = data[index2];
            data[index2] = temp;
            return true;
        },
        toString: function () {
            return data.join(", ");
        }
    };
}

let expect = require('chai').expect;
//let createList = require('./list-add-swap-shift-left-right.js');

describe('List Unit Tests', function () {
    let list;
    beforeEach(function () {
        list = createList();
    });


    // Test Add
    describe('Test add', function () {
        it('with one number', function () {
            list.add(4);
            expect(list.toString()).to.equal('4')
        });
        it('with two strings', function () {
            list.add('4');
            list.add('5');
            expect(list.toString()).to.equal('4, 5')
        });
        it('with everything', function () {
            list.add(4);
            list.add('5');
            list.add({name: 'Kiro'});
            expect(list.toString()).to.equal('4, 5, [object Object]')
        });
    });

    // Test ShiftLeft
    describe('Test shiftLeft', function () {
        it('with one element', function () {
            list.add(4);
            list.shiftLeft();
            expect(list.toString()).to.equal('4')
        });
        it('with many elements', function () {
            list.add(4);
            list.add(5);
            list.add(6);
            list.add(7);
            list.shiftLeft();
            expect(list.toString()).to.equal('5, 6, 7, 4')
        });
    });

    // Test ShiftRight
    describe('Test shiftRight', function () {
        it('with one element', function () {
            list.add(4);
            list.shiftRight();
            expect(list.toString()).to.equal('4')
        });
        it('with many elements', function () {
            list.add(4);
            list.add(5);
            list.add(6);
            list.add(7);
            list.shiftRight();
            expect(list.toString()).to.equal('7, 4, 5, 6')
        });
    });

    // Test Swap
    describe('Test swap', function () {
        it('index1 as string', function () {
            list.add(1);
            list.add(2);
            list.add(3);
            let result = list.swap('0', 2);
            expect(result).to.equal(false, 'Elements should not swap!');
            expect(list.toString()).to.equal('1, 2, 3', 'Elements should not swap!')
        });
        it('index2 as string', function () {
            list.add(1);
            list.add(2);
            list.add(3);
            let result = list.swap(0, '2');
            expect(result).to.equal(false, 'Elements should not swap!');
            expect(list.toString()).to.equal('1, 2, 3', 'Elements should not swap!')
        });
        it('negative index1', function () {
            list.add(1);
            list.add(2);
            list.add(3);
            let result = list.swap(-2 , 2);
            expect(result).to.equal(false, 'Elements should not swap!');
            expect(list.toString()).to.equal('1, 2, 3', 'Elements should not swap!')
        });
        it('negative index2', function () {
            list.add(1);
            list.add(2);
            list.add(3);
            let result = list.swap(2 , -2);
            expect(result).to.equal(false, 'Elements should not swap!');
            expect(list.toString()).to.equal('1, 2, 3', 'Elements should not swap!')
        });
        it('index1 as much as data length', function () {
            list.add(1);
            list.add(2);
            list.add(3);
            let result = list.swap(3 , 2);
            expect(result).to.equal(false, 'Elements should not swap!');
            expect(list.toString()).to.equal('1, 2, 3', 'Elements should not swap!')
        });
        it('index2 as much as data length', function () {
            list.add(1);
            list.add(2);
            list.add(3);
            let result = list.swap(2 , 3);
            expect(result).to.equal(false, 'Elements should not swap!');
            expect(list.toString()).to.equal('1, 2, 3', 'Elements should not swap!')
        });
        it('index1 larger than data length', function () {
            list.add(1);
            list.add(2);
            list.add(3);
            let result = list.swap(4 , 2);
            expect(result).to.equal(false, 'Elements should not swap!');
            expect(list.toString()).to.equal('1, 2, 3', 'Elements should not swap!')
        });
        it('index2 larger than data length', function () {
            list.add(1);
            list.add(2);
            list.add(3);
            let result = list.swap(2 , 4);
            expect(result).to.equal(false, 'Elements should not swap!');
            expect(list.toString()).to.equal('1, 2, 3', 'Elements should not swap!')
        });
        it('with equal indexes (in range)', function () {
            list.add(1);
            list.add(2);
            list.add(3);
            let result = list.swap(1, 1);
            expect(result).to.equal(false, 'Elements should not swap!');
            expect(list.toString()).to.equal('1, 2, 3', 'Elements should not swap!')
        });
        it('with correct indexes (should swap)', function () {
            list.add(1);
            list.add(2);
            list.add(3);
            let result = list.swap(0, 2);
            expect(result).to.equal(true, 'Elements should not swap!');
            expect(list.toString()).to.equal('3, 2, 1', 'Elements should not swap!')
        });
        it('with correct indexes (should swap)', function () {
            list.add(1);
            list.add(2);
            list.add(3);
            let result = list.swap(2, 0);
            expect(result).to.equal(true, 'Elements should not swap!');
            expect(list.toString()).to.equal('3, 2, 1', 'Elements should not swap!')
        });
    });

    //Test ToString
    describe('Test toString', function () {
        it('without elements', function () {
            expect(list.toString()).to.equal('');
        });
        it('with one element', function () {
            list.add(5);
            expect(list.toString()).to.equal('5');
        });
        it('with many elements', function () {
            list.add(5);
            list.add(6);
            list.add(7);
            expect(list.toString()).to.equal('5, 6, 7');
        });
    });
});