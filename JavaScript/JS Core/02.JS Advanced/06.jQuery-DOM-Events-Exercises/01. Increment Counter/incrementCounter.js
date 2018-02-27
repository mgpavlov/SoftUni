function increment(selector) {
    let container = $(selector);
/*
    Adding multiple elements to the DOM can be expensive, instead of repeatedly adding to the
    DOM we can create a DocumentFragment and add the elements to it instead. When we have built our
    hierarchy we can append the DocumentFragment to the DOM, which will add all of the
    fragment’s elements to the specified selector.
*/
    let fragment = document.createDocumentFragment();
    let textArea = $('<textarea>');
    //let incrementBtn = $('<button>Increment</button>');
    let incrementBtn = $('<button>');
    let addBtn = $('<button>Add</button>');
    let ul = $('<ul>');

    textArea.addClass('counter');
    textArea.val(0);
    textArea.attr('disabled', true);

    incrementBtn.addClass('btn');
    incrementBtn.attr('id', 'incrementBtn');
    incrementBtn.text('Increment');

    addBtn.addClass('btn');
    addBtn.attr('id', 'addBtn');
    // addBtn.val('Add');

    ul.addClass('results');


    $(incrementBtn).click(increaseByOne);
    function increaseByOne() {
        //textArea.val(+textArea.val() + 1);
        textArea.val(Number(textArea.val()) + 1);
    }

    $(addBtn).click(addLi);
    function addLi() {
        //let li = $(`<li>${textArea.val()}</li>`);
        let li = $('<li>');
        li.text(textArea.val());
        ul.append(li);
        //li.appendTo(ul);
    }

    textArea.appendTo(fragment);
    incrementBtn.appendTo(fragment);
    addBtn.appendTo(fragment);
    ul.appendTo(fragment);

    container.append(fragment);
}
