let myObj = {
    name: 'Pesho',
    age: 21,
    sayHello: function () {
        console.log(this.name + ' say hello!'); // this сочи към обекта;
    }
};
myObj.sayHello(); // Pesho say hello!
let g = myObj.sayHello();//Pesho say hello! , когато функцията е със скобки (),
console.log(g); // undefined
let f = myObj.sayHello; // тук разкачаме функцията от обекта и this e undefined!
f(); //undefined say hello!
let z = myObj.sayHello.bind(myObj);// ако слжим .bind(sayHello); така закачаме обекта към новата променлива и this сочи към обекта;
z();//Pesho say hello!
console.log(f === myObj.sayHello); // true


let maria = {
    name: 'Maria',
    age: 20
};

/*maria.sayHello(); //TypeError: maria.sayHello is not a function*/
myObj.sayHello.call(maria); //Maria say hello!
myObj.sayHello.apply(maria); //Maria say hello!
