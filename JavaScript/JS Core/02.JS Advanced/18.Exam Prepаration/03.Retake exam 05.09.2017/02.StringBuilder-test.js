class StringBuilder {
    constructor(string) {
        if (string !== undefined) {
            StringBuilder._vrfyParam(string);
            this._stringArray = Array.from(string);
        } else {
            this._stringArray = [];
        }
    }

    append(string) {
        StringBuilder._vrfyParam(string);
        for(let i = 0; i < string.length; i++) {
            this._stringArray.push(string[i]);
        }
    }

    prepend(string) {
        StringBuilder._vrfyParam(string);
        for(let i = string.length - 1; i >= 0; i--) {
            this._stringArray.unshift(string[i]);
        }
    }

    insertAt(string, startIndex) {
        StringBuilder._vrfyParam(string);
        this._stringArray.splice(startIndex, 0, ...string);
    }

    remove(startIndex, length) {
        this._stringArray.splice(startIndex, length);
    }

    static _vrfyParam(param) {
        if (typeof param !== 'string') throw new TypeError('Argument must be string');
    }

    toString() {
        return this._stringArray.join('');
    }
}

let expect = require('chai').expect;

describe("StringBuilder tests", function() {
    let builder;
    beforeEach(function () {
        builder = new StringBuilder('test')
    });

    it("It should have initialized all methods", function() {
        expect(Object.getPrototypeOf(builder).hasOwnProperty('append')).to.be.equal(true)
        expect(Object.getPrototypeOf(builder).hasOwnProperty('prepend')).to.be.equal(true)
        expect(Object.getPrototypeOf(builder).hasOwnProperty('insertAt')).to.be.equal(true)
        expect(Object.getPrototypeOf(builder).hasOwnProperty('remove')).to.be.equal(true)
        expect(Object.getPrototypeOf(builder).hasOwnProperty('toString')).to.be.equal(true)
    })

    it("It should return same string", function() {
        expect(builder.toString()).to.be.equal('test')
    })

    it("It should return same string", function() {
        builder = new StringBuilder();
        expect(builder.toString()).to.be.equal('')
    })

    it("It should throw Error", function() {
        expect(() => {builder = new StringBuilder(5)}).to.throw(TypeError)
    })

    it("append", function() {
        builder.append(' function');
        expect(builder._stringArray.length).to.be.equal(13);
        expect(builder.toString()).to.be.equal('test function');
    })

    it("append Error", function() {
        expect(() => {builder.append({})}).to.throw(TypeError)
    })

    it("prepend", function() {
        builder.prepend('function ')
        expect(builder._stringArray.length).to.be.equal(13)
        expect(builder.toString()).to.be.equal('function test')
    })

    it("prepend Error", function() {
        expect(() => {builder.prepend(10)}).to.throw(TypeError)
    })

    it("insertAt", function() {
        builder.insertAt('ss', 2)
        expect(builder._stringArray.length).to.be.equal(6)
        expect(builder.toString()).to.be.equal('tessst')
    })

    it("insertAt Error", function() {
        expect(() => {builder.insertAt([], 2)}).to.throw(TypeError)
    })

    it("remove", function() {
        builder.remove(1, 2)
        expect(builder._stringArray.length).to.be.equal(2)
        expect(builder.toString()).to.be.equal('tt')
    })
})







/*describe('Test StringBuilder', function () {
    let myStringBuilder = new StringBuilder();
    beforeEach(function(){myStringBuilder = new StringBuilder();});
    describe('InitialTests', function () {
        it('append exist', function () {
            expect(StringBuilder.prototype.hasOwnProperty('append')).equal(true)
        });
        it('prepend exist', function () {
            expect(StringBuilder.prototype.hasOwnProperty('prepend')).equal(true)
        });
        it('insertAt exist', function () {
            expect(StringBuilder.prototype.hasOwnProperty('insertAt')).equal(true)
        });
        it('remove exist', function () {
            expect(StringBuilder.prototype.hasOwnProperty('remove')).equal(true)
        });
        it(' _vrfyParam exist', function () {
            expect(StringBuilder.hasOwnProperty('_vrfyParam')).equal(true)
        });
        it('toString exist', function () {
            expect(StringBuilder.prototype.hasOwnProperty('toString')).equal(true)
        });
    });
    describe('append Tests', function () {
        it('append to empty []', function () {
            myStringBuilder.append('misho');
            expect(myStringBuilder.toString()).equal('misho');
        });
        it('append', function () {
            myStringBuilder.append('misho');
            myStringBuilder.append('misho');
            expect(myStringBuilder._stringArray.length).to.equal(10);
        });

        it('append ("")', function () {
            myStringBuilder.append('misho');
            myStringBuilder.append('');
            expect(myStringBuilder.toString()).equal('misho');
        });
        it('append empty', function () {
            expect(() => myStringBuilder.append()).throw(TypeError,'Argument must be string')
        });
        it('append number', function () {
            expect(() => myStringBuilder.append(3)).throw(TypeError,'Argument must be string')
        });
        it('append {}', function () {
            expect(() => myStringBuilder.append({})).throw(TypeError,'Argument must be string')
        });
        it('append ("")', function () {
            myStringBuilder.append('misho');
            myStringBuilder.append('gosho');
            myStringBuilder.append('tosho');
            expect(myStringBuilder.toString()).equal('mishogoshotosho');
        });
    });
    describe('prepend Tests', function () {
        it('prepend to empty []', function () {
            myStringBuilder.prepend('misho');
            expect(myStringBuilder.toString()).equal('misho');
        });
        it('prepend ("")', function () {
            myStringBuilder.prepend('misho');
            myStringBuilder.prepend('');
            expect(myStringBuilder.toString()).equal('misho');
        });
        it('prepend empty', function () {
            expect(() => myStringBuilder.prepend()).throw(TypeError, 'Argument must be string')
        });
        it('prepend number', function () {
            expect(() => myStringBuilder.prepend(3)).throw(TypeError, 'Argument must be string')
        });
        it('prepend {}', function () {
            expect(() => myStringBuilder.prepend({})).throw(TypeError, 'Argument must be string')
        });
        it('prepend more', function () {
            myStringBuilder.prepend('misho');
            myStringBuilder.prepend('gosho');
            myStringBuilder.prepend('tosho');
            expect(myStringBuilder.toString()).equal('toshogoshomisho');
        });
    });
    describe('insertAt Tests', function () {
        it('index 0', function () {
            myStringBuilder.append('misho');
            myStringBuilder.insertAt('GGG', 0);
            expect(myStringBuilder.toString()).equal('GGGmisho');
        });
        it('index at the end', function () {
            myStringBuilder.append('misho');
            myStringBuilder.insertAt('GGG', 5);
            expect(myStringBuilder.toString()).equal('mishoGGG');
        });
        it('index in the middle', function () {
            myStringBuilder.append('misho');
            myStringBuilder.insertAt('GGG', 3);
            expect(myStringBuilder.toString()).equal('misGGGho');
        });
        it('index after the end', function () {
            myStringBuilder.append('misho');
            myStringBuilder.insertAt('GGG', 8);
            expect(myStringBuilder.toString()).equal('mishoGGG');
        });
        it('index negative', function () {
            myStringBuilder.append('misho');
            myStringBuilder.insertAt('GGG', -1);
            expect(myStringBuilder.toString()).equal('mishGGGo');
        });
        it('index negative', function () {
            myStringBuilder.append('misho');
            myStringBuilder.insertAt('GGG', -8);
            expect(myStringBuilder.toString()).equal('GGGmisho');
        });
        it('insert empty string', function () {
            myStringBuilder.append('misho');
            myStringBuilder.insertAt('', -8);
            expect(myStringBuilder.toString()).equal('misho');
        });
        it('insert number', function () {
            myStringBuilder.append('misho');
            expect(()=>myStringBuilder.insertAt(8, -8)).throw(TypeError, 'Argument must be string')
        });
        it('insert {}', function () {
            myStringBuilder.append('misho');
            expect(()=>myStringBuilder.insertAt({}, -8)).throw(TypeError, 'Argument must be string')
        });
    });

    describe('remove Tests', function () {
        it('index 0', function () {
            myStringBuilder.append('misho');
            myStringBuilder.remove(0, 3);
            expect(myStringBuilder.toString()).equal('ho');
        });
        it('index after the end', function () {
            myStringBuilder.append('misho');
            myStringBuilder.remove(5, 5);
            expect(myStringBuilder.toString()).equal('misho');
        });
        it('index at the end', function () {
            myStringBuilder.append('misho');
            myStringBuilder.remove(4, 3);
            expect(myStringBuilder.toString()).equal('mish');
        });
        it('index negative', function () {
            myStringBuilder.append('misho');
            myStringBuilder.remove(-1, 2);
            expect(myStringBuilder.toString()).equal('mish');
        });
        it('length negative', function () {
            myStringBuilder.append('misho');
            myStringBuilder.remove(3, -5);
            expect(myStringBuilder.toString()).equal('misho');
        });
        it('length > string.length', function () {
            myStringBuilder.append('misho');
            myStringBuilder.remove(0, 10);
            expect(myStringBuilder.toString()).equal('');
        });
        it('length =0', function () {
            myStringBuilder.append('misho');
            myStringBuilder.remove(2, 0);
            expect(myStringBuilder.toString()).equal('misho');
        });
        it('in the middle', function () {
            myStringBuilder.append('misho');
            myStringBuilder.remove(2, 2);
            expect(myStringBuilder.toString()).equal('mio');
        });
    });
    describe('toString Tests', function () {
        it('empty', function () {
            expect(myStringBuilder.toString()).equal('');
        });

    });
});*/
