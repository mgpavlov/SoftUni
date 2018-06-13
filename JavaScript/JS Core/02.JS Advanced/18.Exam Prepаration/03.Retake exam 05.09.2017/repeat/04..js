class Dialog {
    constructor(text, callback){
        this.text = text;
        this.callback = callback;
        this.element = this.overlay();
    }
    addInput(label, name, type){

    }
    render (){
        $('body').append(this.element)
    }

    overlay (){

    }
}