let suits = {
    SPADES: '♠',
    HEARTS: '♥',
    DIAMONDS: '♦',
    CLUBS: '♣'
};

console.log(Object.keys(suits).map(k => suits[k]));