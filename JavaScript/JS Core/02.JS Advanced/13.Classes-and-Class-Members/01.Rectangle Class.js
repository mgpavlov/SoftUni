class Rectangle {
    constructor(width, height, color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }
    calcArea(){
        return this.width*this.height
    }
}
let redRect = new Rectangle(4, 5, 'red');
let blueRect = new Rectangle(8, 3, 'blue');
let rect = new Rectangle(undefined, undefined, 'pink');
console.log(redRect, blueRect);//Rectangle { width: 4, height: 5, color: 'red' } Rectangle { width: 8, height: 3, color: 'blue' }
console.log(rect); //Rectangle { width: undefined, height: undefined, color: 'pink' }
console.log(redRect.calcArea()); //20
console.log(Rectangle);//[Function: Rectangle]
