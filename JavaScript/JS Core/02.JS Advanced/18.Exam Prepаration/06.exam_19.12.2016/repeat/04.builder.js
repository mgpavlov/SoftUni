function listBuilder(selector) {
    let container =$(selector);

    function createNewList(){
        container.empty();
        container.append($('<ul>').attr('id', 'ul'))
    }

    function addItem(text) {
        let ul = $(selector+' ul');
        let li = $('<li>');
        li.text(text).append($('<button>').click(moveUp).text('Up'));
        li.append($('<button>').click(moveDown).text('Down'));
        ul.append(li);
        function moveUp(ev) {
            let li = $(ev.target).parent();
            li.insertBefore(li.prev());
        }
        function moveDown(ev) {
            let li = $(ev.target).parent();
            li.insertAfter(li.next())
        }
    }
    return{
        createNewList,
        addItem
    }
}