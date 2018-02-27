function solve(name, age, weight, height) {
    let bmi = weight/Math.pow((height/100), 2);
    bmi = Math.round(bmi);
    function getStatus() {
        if(bmi < 18.5){
            return 'underweight';
        } else if(bmi < 25){
            return 'normal';
        } else if(bmi < 30){
            return 'overweight';
        } else {
            return 'obese';
        }
    }
    let result = {
        name: name,
        personalInfo: {
            age: age,
            weight: weight,
            height: height
        },
        BMI: bmi,
        status: getStatus(bmi)
    };
    if(result.status === "obese"){
        result.recommendation = "admission required";
    }
   return result;

}

console.log(solve('Misho', 37, 106, 186));