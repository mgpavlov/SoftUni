function cardDeckBuilder(selector) {
    let suits = {
        S: '\u2660 ',
        H: '\u2665 ',
        D: '\u2666 ',
        C: '\u2663 '
    };

    let faces = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'];


    function addCard(face, suit) {
        if (faces.includes(face) && suits.hasOwnProperty(suit)) {
            let card = $(`<div class="card">${face} ${suits[suit]}</div>`)
            $(selector).append(card.click(reverseCards));
        }
        function reverseCards() {
            let allCards = $(selector).find('.card')
                .toArray()
                .reverse()
                .forEach(c=>$(selector).append(c))
        }
    }

    return {
        addCard
    }
}