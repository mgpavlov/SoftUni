let expect = require('chai').expect;
let list = (function(){
    let data = [];
    return {
        add: function(item) {
            data.push(item);
        },
        delete: function(index) {
            if (Number.isInteger(index) && index >= 0 && index < data.length) {
                return data.splice(index, 1)[0];
            } else {
                return undefined;
            }
        },
        toString: function() {
            return data.join(", ");
        }
    };
})();

describe('List Tests', function () {
    let myList;
    beforeEach(function () {
        myList = list;
    });
    afterEach(function () {
        myList ={}
    });

    it('Test empty list', function () {
        expect(myList.toString()).to.equal('', 'List was not empty!')
    });


    describe('Add Tests', function () {
        it('Add one item (should add item)', function () {
            myList.add(4);
            expect(myList.toString()).to.equal('4', 'List did not add item!');
        });
        it('Add 3 items (should add items)', function () {
            myList.add('pesho');
            myList.add('pesho');
            myList.add('pesho');
            expect(myList.toString()).to.equal('pesho, pesho, pesho', 'List did not add items!');
        });
    });

    describe('Delete', function () {
        it('with floating-point should return undefined', function () {
            let result = myList.delete(3.12);
            expect(result).to.be.undefined;
        });
        it('with empty list should return undefined', function () {
            expect(myList.delete(0)).to.be.undefined;
        });
        it('with index as much as list length should return undefined', function () {
            myList.add(3);
            myList.add(4);
            expect(myList.delete(2)).to.be.undefined;
        });
        it('with negative index should return undefined', function () {
            myList.add(3);
            myList.add(4);
            expect(myList.delete(-2)).to.be.undefined;
        });

        describe('Correct delete', function () {
            it('with index 0 should return correct item', function () {
                myList.add(5);
                myList.add(6);
                myList.add(7);
                expect(myList.delete(0)).to.be.equal(5, 'List delete did not return correct value');
            });
            it('with index 0 should delete from list', function () {
                myList.add(5);
                myList.add(6);
                myList.add(7);
                myList.delete(0);
                expect(myList.toString()).to.be.equal('6, 7', 'List is not empty!');
            });
        });
    });
});
