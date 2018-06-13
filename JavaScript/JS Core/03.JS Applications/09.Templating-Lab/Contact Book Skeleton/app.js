$(() => {
    const listContent = $('#list').find('.content');
    const detailsContent = $('#details').find('.content');


    const templates = {};
    let context = {
        contacts: [],
    };

    loadData();
    loadTemplates();
    
    async function loadData() {
        context.contacts = await $.get('data.json');
    }
    
    async function loadTemplates() {
        const [contactSource, listSource, detailsSource] =
            await Promise.all([
                $.get('./templates/contact.html'),
                $.get('./templates/contactsList.html'),
                $.get('./templates/details.html')
            ]);
        Handlebars.registerPartial('contact', contactSource);
        templates.list = Handlebars.compile(listSource);
        templates.details = Handlebars.compile(detailsSource);

       renderList();
    }

    function renderList() {
        listContent.html(templates.list(context));
        attachHandlers();
    }
    function renderDetails(index) {
        detailsContent.html(templates.details(context.contacts[index]));
    }

    function attachHandlers() {
        $('.contact').click((e)=>{

            let index = Number($(e.target).closest('.contact').attr('data-index'));
            renderDetails(index);
        })
    }
});