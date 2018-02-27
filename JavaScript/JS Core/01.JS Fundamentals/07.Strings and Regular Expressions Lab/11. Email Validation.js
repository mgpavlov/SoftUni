function solve(email) {
    let regex = /^[a-zA-Z0-9]+@[a-z]+\.[a-z]+$/;
    console.log(regex.test(email)? 'Valid': 'Invalid');
}