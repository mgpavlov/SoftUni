function printDeckOfCards(cardsArr) {
    function testCard(face, suit) {
        let validCardsFaces = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'];
        let validCardsSuits = ['S', 'H', 'D', 'C'];

        if (!validCardsFaces.includes(face)) {
            throw new ReferenceError('Error');
        }
        if (!validCardsSuits.includes(suit)) {
            throw new ReferenceError('Error')
        }
        return {
            face: face,
            suit: suit,

            toString: () => {
                let unicodeSymbols = {
                    'S': '\u2660',
                    'H': '\u2665',
                    'D': '\u2666',
                    'C': '\u2663'
                }
                return `${face}${unicodeSymbols[suit]}`
            }
        }
    }

    let deck = [];

    for (let card of cardsArr) {
        let face = card.substring(0, card.length - 1);
        let suit = card[card.length - 1];
        try {
            deck.push(testCard(face, suit));
        } catch (err){
            console.log(`Invalid card: ${card}`)
            return;
        }
    }
    console.log(deck.join(` `));
}

printDeckOfCards(['AS', '10D', 'KH', '2C']);
printDeckOfCards(['5S', '3D', 'QD', '1C']);
