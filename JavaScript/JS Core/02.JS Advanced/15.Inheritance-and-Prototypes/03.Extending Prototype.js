function extendClass(classToExtend) {
    classToExtend.prototype.species = 'Human';
    classToExtend.prototype.toSpeciesString = function () {
        return `I am a ${this.species}.${this.toString()}`
    };
/*    let person = new classToExtend('Maria', 'maria@abv.bg', 'hi');
    console.log(person.toSpeciesString());*/
}

extendClass( class Person {
    constructor(name, email) {
        this.name = name;
        this.email = email;
    }
    talk(){
        return `${this.name} saying ${this.saying}`
    }

    toString() {
        let className = this.constructor.name;
        return `${className} (name: ${this.name}, email: ${this.email})`;
    }
});