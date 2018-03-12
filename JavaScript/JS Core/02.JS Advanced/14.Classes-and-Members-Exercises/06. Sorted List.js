class SortedList {
    constructor(collection){
        this.collection = [];
        this.size = 0;
    }
    add(element){
        this.collection.push(element);
        this.size++;
        this.collection.sort((a,b)=>a-b);
    }
    remove (index){
        if(index >= 0 && index < this.collection.length){
            this.collection.splice(index,1);
            this.size--;
        } else {
            throw new RangeError('Index is outside the bounds of the list!');
        }

    }
    get(index) {
        if(index >= 0 && index < this.collection.length){

            return this.collection[index];
        }else {
            throw new RangeError('Index is outside the bounds of the list!');
        }
    }
}
