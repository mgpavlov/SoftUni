function attachEvents() {
    let delBtn = $('#btnDelete').click(delTown);
    let addBtn = $('#btnAdd').click(addTown);
    let towns = $('#towns');
    let newItem = $('#newItem');

    function delTown() {
        towns.find(':selected').remove();
    }
    function addTown() {
        if (newItem.val()!== '') {
            towns.append($(`<option>`).text(newItem.val()))
        }
        newItem.val('')
    }
}