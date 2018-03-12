function makeCard(face, suit) {
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

        toString: ()=>{
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

console.log('' + makeCard('A', 'S'));
console.log('' + makeCard('10', 'H'));
console.log('' + makeCard('1', 'C'));