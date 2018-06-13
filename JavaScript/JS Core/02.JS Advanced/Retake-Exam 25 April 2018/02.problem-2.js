class SubscriptionCard {
    constructor(firstName, lastName, SSN) {
        this._firstName = firstName;
        this._lastName = lastName;
        this._SSN = SSN;
        this._subscriptions = [];
        this._blocked = false;
    }

    get firstName() {
        return this._firstName;
    }

    get lastName() {
        return this._lastName;
    }

    get SSN() {
        return this._SSN;
    }

    get isBlocked() {
        return this._blocked;
    }

    addSubscription(line, startDate, endDate) {
        this._subscriptions.push({
            line,
            startDate,
            endDate
        });
    }

    isValid(line, date) {
        if (this.isBlocked) return false;
        return this._subscriptions.filter(s => s.line === line || s.line === '*')
            .filter(s => {
                return s.startDate <= date &&
                    s.endDate >= date;
            }).length > 0;
    }

    block() {
        this._blocked = true;
    }

    unblock() {
        this._blocked = false;
    }
}



let expect = require('chai').expect;

describe('Test function', function () {
    let card;
    beforeEach(function () {
        card = new SubscriptionCard('Pesho', 'Petrov', '00000000');
    });
    it('should init as class', function () {
        expect(card instanceof SubscriptionCard).to.be.true;
        expect(SubscriptionCard.prototype.hasOwnProperty('firstName')).to.be.true;
        expect(SubscriptionCard.prototype.hasOwnProperty('lastName')).to.be.true;
        expect(SubscriptionCard.prototype.hasOwnProperty('SSN')).to.be.true;
        expect(SubscriptionCard.prototype.hasOwnProperty('addSubscription')).to.be.true;
        expect(SubscriptionCard.prototype.hasOwnProperty('isValid')).to.be.true;
        expect(SubscriptionCard.prototype.hasOwnProperty('block')).to.be.true;
        expect(SubscriptionCard.prototype.hasOwnProperty('unblock')).to.be.true;
    });

    it('test initial', function () {
        expect(card.firstName).equal('Pesho');
        expect(card.lastName).equal('Petrov');
        expect(card.SSN).equal('00000000');
        expect(card.isValid()).equal(false);
        expect(card.isBlocked).equal(false);
        card.addSubscription('120', new Date('2018-04-22'), new Date('2018-05-21'));
        expect(card.isValid('120', new Date('2018-04-22'))).equal(true);
        card.firstName = 'Gosho';
        expect(card.firstName).equal('Pesho');
        card.block();
        expect(card.isBlocked).equal(true);
        card.unblock()
        expect(card.isBlocked).equal(false);
        expect(card.isValid('120', new Date('2017-04-22'))).equal(false);
        expect(card.isValid('120', new Date('2019-04-22'))).equal(false);
        expect(card.isValid('120', new Date('2018-05-21'))).equal(true);
        expect(card.isValid('140', new Date('2018-05-21'))).equal(false);
        card.addSubscription('*', new Date('2018-05-25'), new Date('2018-06-24'));
        expect(card.isValid('120', new Date('2018-06-24'))).equal(true);
        expect(card.isValid('111', new Date('2018-06-24'))).equal(true);
        expect(card.isValid('333', new Date('2018-05-25'))).equal(true);
        expect(card.isValid('333', new Date('2018-04-22'))).equal(false);
        expect(card.isValid('333', new Date('2019-06-24'))).equal(false);

    });

});
