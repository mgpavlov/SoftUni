function attachEvents() {
    let root = $('#root');

    let btnLoadTowns = $('#btnLoadTowns').click(renderTowns);
    function renderTowns(e) {
        let towns = $('#towns').val();
        let listOfTowns = towns.split(',').filter(e=>e!=='').map(e=>({town: e.trim()}));
        loadTowns(listOfTowns);
    }

    async function loadTowns(towns) {
        //let townsTemplate = $('#towns-template').html();
        let townsTemplate = await $.get("./templates/template.hbs");
        let compiled = Handlebars.compile(townsTemplate);
        let template = compiled({towns});
        //root.empty();
        //root.append(template);
        root.html(template);
    }
}