class Task {
    constructor(title, deadline) {
        this.title = title;
        this.status = 'Open';
        this.deadline = deadline;
    }

    get deadline() {
        return this._deadline;
    }

    set deadline(value) {
        if (value < Date.now()) {
            throw new Error('Deadline cannot be in the past!');
        }
        this._deadline = value;
    }

    get isOverdue() {
        //return this.deadline < Date.now() && this.status !== 'Complete';
        if (this.deadline < Date.now() && this.status !== 'Complete') {
            return true;
        }
        return false
    }
    get rank() {
        if(this.isOverdue){
            return 0;
        } else if(this.status === 'In Progress'){
            return 1;
        } else if(this.status === 'Open'){
            return 2;
        } else if(this.status === 'Complete'){
            return 3;
        }
    }

    static comparator(a, b) {
        let criteria = a.rank - b.rank;

        if(criteria !== 0){
            return criteria;
        } else {
            return a.deadline - b.deadline;
        }
    }

    get icon() {
        if (this.isOverdue) {
            return "\u26A0";
        } else if (this.status === 'In Progress') {
            return "\u219D";
        } else if (this.status === 'Complete') {
            return "\u2714";
        } else if (this.status === 'Open') {
            return "\u2731";
        }
    }

    toString() {
        let deadlineOverDue = this.isOverdue ? 'overdue' : 'deadline: ' + this.deadline;
        return `[${this.icon}] ${this.title} (${deadlineOverDue})`;
    }
}

//let overdueTask = new Task('Overdue Task', new Date(2005, '4', '20'));
let date1 = new Date();
date1.setDate(date1.getDate() + 7);
let task1 = new Task('JS Homework', date1);
task1.status = 'In Progress';
console.log(task1.toString());
