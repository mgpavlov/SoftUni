function censor(text, arr) {
    for (let i = 0; i < arr.length; i++) {
        let word = new RegExp(`${arr[i]}`, 'g');
        text = text.replace(word, '-'.repeat(arr[i].length));
        }

    console.log(text);
}

