function employeeData(arrStr) {
    let regex = /^([A-Z][a-zA-Z]*) - ([1-9][0-9]*) - ([a-zA-Z0-9 -]+)$/;
    for (let str of arrStr) {
        let match = str.match(regex);
        if (regex.test(str)){
            console.log(`Name: ${match[1]}`);
            console.log(`Position: ${match[3]}`);
            console.log(`Salary: ${match[2]}`);
        }

    }
}

employeeData(['Isacc - 1000 - CEO', 'asdasd', 'Ivan - 500 - Employee', 'Peter - 500 - Employee']);
