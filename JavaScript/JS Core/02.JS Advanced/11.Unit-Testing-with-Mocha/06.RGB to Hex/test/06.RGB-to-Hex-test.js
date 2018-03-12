let rgbToHexColor = require('../06.RGB-to-Hex').rgbToHexColor;
let expect = require('chai').expect;

describe('Test isRgbToHexColor', function () {

    it('should return #FF9EAA for (255, 158, 170)', function () {
        expect(rgbToHexColor(255, 158, 170)).to.equal('#FF9EAA');
    });
    it('should pad values with zeroes', function () {
        expect(rgbToHexColor(12, 13, 14)).to.equal('#0C0D0E');
    });
    it('should work at lower limit', function () {
        expect(rgbToHexColor(0, 0, 0)).to.equal('#000000');
    });
    it('should work at lower limit', function () {
        expect(rgbToHexColor(255, 255, 255)).to.equal('#FFFFFF');
    });
    it('should return undefined for negative numbers', function () {
        expect(rgbToHexColor(-2, 33, 255)).to.equal(undefined);
    });
    it('should return undefined for numbers greater then 255', function () {
        expect(rgbToHexColor(3, 33, 256)).to.equal(undefined);
    });
    it('should return undefined for fractions', function () {
        expect(rgbToHexColor(3.55, 33, 254.99)).to.equal(undefined);
    });
    it('should return undefined for incorrect input', function () {
        expect(rgbToHexColor('pesho', 'misho', [])).to.equal(undefined);
    });
});