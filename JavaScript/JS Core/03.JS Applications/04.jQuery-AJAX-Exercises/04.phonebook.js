function attachEvents() {
    let btnLoad = $('#btnLoad');
    let btnCreate = $('#btnCreate');
    let phonebook = $('#phonebook');
    let person = $('#person');
    let phone = $('#phone');

    btnLoad.click(load);
    btnCreate.click(create);

    function load() {
        $.ajax({
            method: 'GET',
            url: 'https://phonebook-nakov.firebaseio.com/phonebook.json',
            success: loadSuccess
        });
        function loadSuccess(res) {
            phonebook.empty();
            for (let key in res) {
                let li = $(`<li>${res[key]['person']}: ${res[key]['phone']} </li>`);
                let delBtn = $("<button id=\"btnDelete\">[Delete]</button>").click(()=>{
                    li.remove();
                    $.ajax({
                        method: 'DELETE',
                        url: `https://phonebook-nakov.firebaseio.com/phonebook/${key}.json`
                    })
                });
                phonebook.append(li.append(delBtn));
            }
        }
    }

    function create() {
        let jsonData = {
            "person": person.val(),
            "phone": phone.val()
        };
        $.ajax({
            method: 'POST',
            url: 'https://phonebook-nakov.firebaseio.com/phonebook.json',
            data: JSON.stringify(jsonData),
            success: load,
            error: ()=>{
                phonebook.empty();
                phonebook.append($('<li>Error</li>'))
            }
        });
        person.val('');
        phone.val('');
    }
}