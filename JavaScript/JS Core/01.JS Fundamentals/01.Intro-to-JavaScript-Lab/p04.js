function filterByAge(minAge, name1, age1, name2, age2) {
    let obj1 = {name: name1, age: age1};
    let obj2 = {name: name2, age: age2};
    if(age1>=minAge){
        console.log(obj1);
    }
    if(age2>=minAge){
        console.log(obj2);
    }
}

filterByAge(12, 'Ivan', 15, 'Asen', 9)