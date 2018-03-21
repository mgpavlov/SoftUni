class Repository{
    constructor(props){
        this.data = new Map();
        this.props = props;
        this._id = 0;
    }
    add(entity){
        this._validate(entity);
        this.data.set(this._id, entity);
        return this._id++;
    }
    get (id){
        if(!this.data.has(id)){
            throw new Error(`Entity with id: ${id} does not exist!`)
        }
        return this.data.get(id)
    }
    update (id, newEntity){
        if(!this.data.has(id)){
            throw new Error(`Entity with id: ${id} does not exist!`)
        }
        this._validate(newEntity);
        this.data.set(id, newEntity);
    }
    del(id){
        if(!this.data.has(id)){
            throw new Error(`Entity with id: ${id} does not exist!`)
        }
        this.data.delete(id);
    }
    get count (){
        return this.data.size;
    }

    _validate(entity) {
        let entityKeys = Object.keys(entity);
        let propsKeys = Object.keys(this.props);
        for (let key of propsKeys) {
            if(!entity.hasOwnProperty(key)){
                throw new Error(`Property ${key} is missing from the entity!`)
            }
        }
        for (let key of entityKeys) {
            if(this.props.hasOwnProperty(key)){
                if(typeof entity[key] !== this.props[key])
                    throw new TypeError(`Property ${key} is of incorrect type!`)
            }
        }
/*        for (let key in this.props) {
            if(!entity.hasOwnProperty(key)) {
                throw new Error(`Property ${key} is missing from the entity!`)
            }
            if(this.props[key] !== typeof entity[key]) {
                throw new TypeError(`Property ${entity[key]} is of incorrect type!`)
            }
        }*/
    }
}


// Initialize props object
let properties = {
    name: "string",
    age: "number",
    birthday: "object"
};
//Initialize the repository
let repository = new Repository(properties);
// Add two entities
let entity = {
    name: "Kiril",
    age: 19,
    birthday: new Date(1998, 0, 7)
};
repository.add(entity); // Returns 0
repository.add(entity); // Returns 1
console.log(repository.get(0));
// {"name":"Kiril","age":19,"birthday":"1998-01-06T22:00:00.000Z"}
console.log(repository.get(1));
// {"name":"Kiril","age":19,"birthday":"1998-01-06T22:00:00.000Z"}
//Update an entity
entity = {
    name: 'Valio',
    age: 19,
    birthday: new Date(1998, 0, 7)
};
repository.update(1, entity);
console.log(repository.get(1));
// {"name":"Valio","age":19,"birthday":"1998-01-06T22:00:00.000Z"}
// Delete an entity
repository.del(0);
console.log(repository.count); // Returns 1
let anotherEntity = {
    name1: 'Nakov',
    age: 26,
    birthday: new Date(1991, 0, 21)
};
repository.add(anotherEntity); // should throw an Error
anotherEntity = {
    name: 'Nakov',
    age: 26,
    birthday: 1991
};
repository.add(anotherEntity); // should throw a TypeError
repository.del(-1); // should throw Error for invalid id
