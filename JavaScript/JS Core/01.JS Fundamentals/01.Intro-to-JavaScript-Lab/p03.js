function counter(str1, str2) {

    let counter = 0;
    for (let i = 0; i < str1.length; i++) {
        if (str1[i]===str2)
        counter++;
    }
    console.log(counter);
}