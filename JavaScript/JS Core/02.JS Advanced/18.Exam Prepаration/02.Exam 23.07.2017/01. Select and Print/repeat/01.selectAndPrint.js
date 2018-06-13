function move(command) {
    let availableTowns = $('#available-towns');
    let selectedTowns = $('#selected-towns');
    let selectedAvailableTown = availableTowns.find(":selected");
    let selectedTown = selectedTowns.find(":selected");

    if (command === 'right') {
        selectedTowns.append(selectedAvailableTown);
    }
    if (command === 'left') {
        availableTowns.append(selectedTown);

    }
    if (command === 'print') {
        let output = $('#output');
        //let towns = $('#selected-towns option');
        let print = [];
        let towns = selectedTowns.find($('option'));
        for (let town of towns) {
            print.push($(town).text());
        }
        let result = print.join(('; '));
        output.text(print.join(('; ')))
    }
}