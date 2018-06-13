

/*function attachEvents() {
    $('#btnDelete').click(function() {
        let townName = $('#townName').val();
        $('#townName').val('');
        let found = false;
        for (let option of $('#towns option'))
            if (option.textContent == townName) {
                found = true;
                option.remove();
            }
        if (found)
            $('#result').text(townName + " deleted.");
        else
            $('#result').text(townName + " not found.");
    });
}*/
/*
function attachEvents() {
    let resultDiv = $('#result');
    $('#btnDelete').on('click', function () {
        let hasDeleted = false;
        let townVal = $('#townName').val();
        let allTowns = $('#towns option')
            .filter((index, el) => $(el).text() === townVal)
            .each(function (index, el) {
                hasDeleted = true;
                $(el).remove();
                $('#townName').val('');
            });

        resultDiv.text(hasDeleted
            ? `${townVal} deleted.`
            : `${townVal} not found.`);
    });
}*/
