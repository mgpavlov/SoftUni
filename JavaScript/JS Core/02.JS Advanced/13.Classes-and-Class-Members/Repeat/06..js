let result = (function(){
    let suits = {
        SPADES: '♠',
        HEARTS: '♥',
        DIAMONDS: '♦',
        CLUBS: '♣'
    };
    let faces = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'];

    class Card {
        
        constructor(face, suit) {
            this._face = face;
            this._suit = suit;
        }
        get face() {
            return this._face;
        }

        set face(value) {
            if (!faces.includes(value)) {
                throw new Error('Face does not exist!');
            }
            this._face = value;
        }

        get suit() {
            return this._suit;
        }

        set suit(value) {
            if (!Object.values(suits).includes(value)) {
                throw new Error('Suit does not exist!')
            }
            this._suit = value;
        }
    }

    return {
        Suits:suits,
        Card:Card
    }
}())

let Card = result.Card;
let Suits = result.Suits;

let card = new Card('Q', Suits.CLUBS);
console.log(card);
card.face = "A";
card.suit = Suits.DIAMONDS;
/*let card2 = new Card("1", Suits.DIAMONDS);*/
