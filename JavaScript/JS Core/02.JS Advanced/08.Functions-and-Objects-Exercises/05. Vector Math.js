(function () {
    function add(v1, v2) {
        return [v1[0]+v2[0], v1[1]+v2[1]]
    }
    function multiply(v1, scalar) {
        return [v1[0]*scalar, v1[1]*scalar];
    }
    function length(v1) {
        return Math.sqrt(Math.pow(v1[0],2)+ Math.pow(v1[1],2));
    }
    function dot(v1, v2) {
        return v1[0]*v2[0] + v1[1]*v2[1];
    }
    function cross(v1, v2) {
        return v1[0]*v2[1] - v1[1]*v2[0];
    }

    return {
        add: add,
        multiply: multiply,
        length: length,
        dot: dot,
        cross: cross
    }
})();



/*
(() => {
    let add = (vec1, vec2) => [vec1[0]+vec2[0], vec1[1]+vec2[1]];
    let multiply = (vec1, scalar) => [vec1[0] * scalar, vec1[1] * scalar];
    let length = (vec1) => Math.sqrt(Math.pow(vec1[0], 2) + Math.pow(vec1[1], 2));
    let dot = (vec1, vec2) => vec1[0] * vec2[0] + vec1[1] * vec2[1];
    let cross = (vec1, vec2) => vec1[0] * vec2[1] - vec1[1] * vec2[0];

    return {add, multiply, length, dot, cross};
})();*/
