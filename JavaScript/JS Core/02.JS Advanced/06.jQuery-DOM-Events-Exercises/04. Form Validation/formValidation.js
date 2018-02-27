function validate() {
    let usernamePattern = /^[a-zA-Z0-9]{3,20}$/g;
    let passwordPattern = /^[\w]{5,15}$/g;
    let emailPattern = /^.*?@.*?\..*?$/g;
    let companyNumberPattern = /^[1-9][0-9]{3}$/g;

    let username = $('#username');
    let email = $('#email');
    let password = $('#password');
    let confirmPassword = $('#confirm-password');
    let companyCheckbox = $('#company');
    let companyInfo = $('#companyInfo');
    let companyNumber = $('#companyNumber');
    let submitBtn = $('#submit');
    let validationDiv = $('#valid');
    let isValid = true;
    companyCheckbox.on('change', checked);
    function checked() {
        if(companyCheckbox.is(':checked')){
            companyInfo.css('display', 'block');
        }else{
            companyInfo.css('display', 'none');
        }
    }

    submitBtn.on('click', function (event) {
        event.preventDefault(); //така не се рефрешва страницата при натискане на submit;
        //може и с this.preventDefault();
        validateForm();
        validationDiv.css("display", isValid
            ? "block" : "none");
        isValid = true;

    });
    function validation(pattern, cell) {
        if(pattern.test(cell.val())){
            cell.css('border', 'none');
        }else{
            cell.css('border', 'solid red');
            isValid = false;
        }
    }
    function validateForm() {
        if(companyCheckbox.is(':checked')){
            validation(companyNumberPattern, companyNumber);
        }
        validation(usernamePattern, username);
        validation(emailPattern, email);

        if (password.val() === confirmPassword.val()){
            if(passwordPattern.test(password.val())){
                password.css('border', 'none');
                confirmPassword.css('border', 'none');
            }else {
                password.css('border', 'solid red');
                confirmPassword.css('border', 'solid red');
                isValid=false;
            }
        }else {
            password.css('border', 'solid red');
            confirmPassword.css('border', 'solid red');
            isValid=false;
        }
    }
}


/*
function validate() {
    let username = $('#username');
    let email = $('#email');
    let password = $('#password');
    let confirmPassword = $('#confirm-password');
    let companyCheckbox = $('#company');
    let companyNumber = $('#companyNumber');
    let companyInfo = $('#companyInfo');
    let submitBtn = $('#submit');
    let validationDiv = $('#valid');
    let allIsValid = true;
    companyCheckbox.on("change", function () {
        if (companyCheckbox.is(":checked")) {
            companyInfo.css("display", "block");
        } else {
            companyInfo.css("display", "none");
        }
    });

    submitBtn.on("click", function (ev) {
        ev.preventDefault();
        validateForm();
        validationDiv.css("display", allIsValid
            ? "block" : "none");
        allIsValid = true;
    });

    function validateForm() {
        validateInputWithRegex(username, /^[A-Za-z\d]{3,20}$/g);
        validateInputWithRegex(email, /^.*?@.*?\..*$/g);
        if (confirmPassword.val() === password.val()) {
            validateInputWithRegex(password, /^\w{5,15}$/g);
            validateInputWithRegex(confirmPassword, /^\w{5,15}$/g);
        } else {
            confirmPassword.css("border", "solid red");
            password.css("border", "solid red");
            allIsValid = false;
        }

        if (companyCheckbox.is(":checked")) {
            validateCompanyInfo();
        }
    }

    function validateInputWithRegex(input, pattern) {
        if(pattern.test(input.val())){
            input.css("border", "none");
        } else {
            input.css("border", "solid red");
            allIsValid = false;
        }

    }

    function validateCompanyInfo() {
        let numValue = +companyNumber.val();
        if (numValue >= 1000 && numValue <= 9999) {
            companyNumber.css("border", "none");
        } else {
            companyNumber.css("border", "solid red");
            allIsValid = false;
        }
    }
}
*/
