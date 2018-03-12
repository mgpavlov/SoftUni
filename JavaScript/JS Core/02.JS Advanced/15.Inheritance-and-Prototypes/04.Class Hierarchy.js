function solve() {
    class Figure {
        constructor() {
            if (new.target === Figure) {
                throw new TypeError('Cannot instantiate abstract classes directly');
            }
        }
        get area() {
            switch (this.constructor) {
                case Circle:
                    return this.radius * this.radius * Math.PI;
                case Rectangle:
                    return this.width * this.height;
            }
        }
        toString(){
            let type = this.constructor.name;
            let props = Object.getOwnPropertyNames(this);
            return `${type} - `+
                props.map(prop => `${prop}: ${this[prop]}`).join(', ');
        }
    }

    class Circle extends Figure{
        constructor(radius){
            super();
            this.radius = radius;
        }
/*        toString(){
            return `${this.constructor.name} - radius: ${this.radius}`
        }*/
    }
    class Rectangle extends Figure{
        constructor(width, height){
            super();
            this.width = width;
            this.height = height;
        }
/*        toString(){
            return `${this.constructor.name} - width: ${this.width}, height: ${this.height}`;
        }*/
    }
    return {
        Figure,
        Circle,
        Rectangle
    }
}

//let f = new Figure();       //Error
let c = new Circle(5);
console.log(c.area);        //78.53981633974483
console.log(c.toString());  //Circle - radius: 5
let r = new Rectangle(3,4);
console.log(r.area);        //12
console.log(r.toString());  //Rectangle - width: 3, height: 4