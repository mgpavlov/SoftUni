$(() => {
    renderCatTemplate(); //1
   async function renderCatTemplate() {//2
       let cats = window.cats;
       let allCats = $('#allCats');
       let source = await $.get('./cat-template.hbs');

       let compiled = Handlebars.compile(source);
       let template = compiled({cats});
       allCats.html(template);

       let btnShowStatus = $('.btn');//3
       btnShowStatus.click(displayStatus);//4
   }
   function displayStatus(ev) {//5
       let targetBtn = $(ev.target);
       let divInfo = targetBtn.next('div');
       if(targetBtn.text()=== "Show status code"){
           targetBtn.text("Hide status code");
       }else {
           targetBtn.text("Show status code");
       }
       divInfo.toggle();
   }
});
