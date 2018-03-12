class Circle {
    constructor(radius) {
        this.radius = radius;
    }

    get diameter() {
        return this.radius * 2;
    }

    set diameter(diameter) {
        this.radius = diameter / 2;
    }

    get area() {
        return this.radius * this.radius * Math.PI;
    }
}
let c = new Circle(4);
console.log(c);
console.log(c.area);
console.log(c.radius);
console.log(c.diameter);
c.diameter = 4;
console.log(c.radius);
