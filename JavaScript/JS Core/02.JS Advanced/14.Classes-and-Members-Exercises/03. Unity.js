class Rat {
    constructor(name){
        this.name = name;
        this.arr = [];
    };
    getRats(){
        return this.arr
    }
    unite(data){
        if(data instanceof Rat)
        this.arr.push(data);
    }
/*    toString() {
        let result = this.name + '\n';
        for(let rat of this.arr) {
            result += "##" +  rat.name + '\n';
        }

        return result.trim();
    }*/
    toString(){
        let r1 = '##'+this.arr.filter(e=>e!=='').join('\n##');
        let result = this.name+'\n'+r1;
        return result.trim();
    }
}
let test = new Rat("Pesho");
console.log(test.toString()); //Pesho

console.log(test.getRats()); //[]

test.unite(new Rat("Gosho"));
test.unite(new Rat("Sasho"));
test.unite(new Rat("Sasho"));
test.unite(new Rat("Sasho"));
console.log(test.getRats());
//[ Rat { name: 'Gosho', unitedRats: [] },
//  Rat { name: 'Sasho', unitedRats: [] } ]

console.log(test.toString());
// Pesho
// ##Gosho
// ##Sasho


