function orderRectangles(input) {
    function createRect(width, height){
        let rect =  {
            width: width,
            height: height,
            area: function () {
                return rect.width * rect.height;
            },
            compareTo: function(other) {
                return other.area() - rect.area() || other.width - rect.width;
            }
        };

        return rect;
    }

    let rects = [];
    for(let [width, height] of input){
        rects.push(createRect(width, height));
    }

    let sortedRects = rects.sort((a, b) => a.compareTo(b));
    return sortedRects;
}

console.log(orderRectangles([[4, 3], [5, 3], [3, 4], [3, 5], [12, 1]
]));

/*
function solve(array) {
    let sortArr = array.sort((a,b) => (b[0]*b[1])-(a[0]*a[1])|| b[0] - a[0]);
    console.log(sortArr);
}*/

/*
function compareRect(array) {
    let print = [];
    for (let [width, high] of array) {
        print.push(rectObj(width, high));
    }
    return print.sort((a, b) => b.compareTo(a));

    function rectObj(width, high) {
        return {
            width: width,
            high: high,
            area: function () {
                return width*high;
            },
            compareTo: function (other) {
                let result =  this.width*this.high-other.width*other.high;
                return result || this.width-other.width;
            }
        };
    }
}*/
