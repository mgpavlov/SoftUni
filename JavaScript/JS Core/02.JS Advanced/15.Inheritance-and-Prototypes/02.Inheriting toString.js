function toStringExtensions() {
    class Person {
        constructor(name, email) {
            this.name = name;
            this.email = email;
        }
        talk(){
            return `${this.name} saying ${this.saying}`
        }

        toString() {
            let className = this.constructor.name;
            return `${className} (name: ${this.name}, email: ${this.email})`;
        }
    }

    class Teacher extends Person {
        constructor(name, email, subject) {
            super(name, email);
            this.subject = subject;
        }

        toString(){
            return super.toString().slice(0,-1) + `, subject: ${this.subject})`
        }
    }

    class Student extends Person {
        constructor(name, email, course) {
            super(name, email);
            this.course = course;
        }
        toString(){
            return super.toString().slice(0,-1) + ', course: ' + this.course +')';
        }
    }
    return{
        Person,
        Teacher,
        Student
    }
}

//testing
/*
class Person {
    constructor(name, email, saying) {
        this.name = name;
        this.email = email;
        this.saying = saying;
    }
    talk(){
        return `${this.name} saying ${this.saying}`
    }
    toString() {
        let className = this.constructor.name;
        return `${className} (name: ${this.name}, email: ${this.email})`;
    }
}

class Teacher extends Person {
    constructor(name, email, saying, subject) {
        super(name, email, saying);
        this.subject = subject;
    }
    exclaim(){
       return super.talk().toUpperCase() + `!!!!!!!`;
    }
    toString(){
        return super.toString().slice(0,-1) + `, subject: ${this.subject})`
    }
}

class Student extends Person {
    constructor(name, email, saying, course) {
        super(name, email, saying);
        this.course = course;
    }
    toString(){
        return super.toString().slice(0,-1) + ', course: ' + this.course +')';
    }
}

let person = new Person('Maria', 'maria@abv.bg', 'hi');


let teacher = new Teacher('Ivan', 'ivan@email.bg', 'Solo Levski', 'History');


let student = new Student('Misho', 'pesho@gmail.com', 'I love Liverpool', 'Mathematics');


console.log(person.toString());
console.log(teacher.toString());
console.log(student.toString());

console.log(person.talk());
console.log(student.talk());
console.log(teacher.talk());
console.log(teacher.exclaim());*/
