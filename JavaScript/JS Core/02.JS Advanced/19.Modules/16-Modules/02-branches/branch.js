class Branch {
    constructor(id, branchName, companyName) {
        // private members, no getters or setters
        this._id = id;
        this._branchName = branchName;
        this._companyName = companyName;

        this._employees = [];
    }

    // getter only
    get employees() {
        return this._employees;
    }

    hire(employee) {
        this.employees.push(employee);
    }

    toString() {
        let output = `@ ${this._companyName}, ${this._branchName}, ${this._id}\n` +
            `Employed:`;
        if (this.employees.length > 0)
            for (let employee of this.employees) {
                output += `\n** ${employee}`;
            }
        else
            output += '\nNone...';

        return output;
    }
}

module.exports = Branch;