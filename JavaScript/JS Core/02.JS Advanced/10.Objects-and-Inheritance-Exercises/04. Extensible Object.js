function solve(extensible, template) {
    let newObj = {
        extend: function (template) {
            for (let prop in template) {
                if(typeof template[prop] === 'function'){
                    Object.getPrototypeOf(newObj)[prop] = template[prop];
                }else
                    newObj[prop] = template[prop];
            }
        }
    };
    return newObj
}