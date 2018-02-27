function sortArray(arr) {

    arr.sort((a,b) => {if (a.length < b.length) return -1;
                           if (a.length > b.length) return 1;
                           if (a < b) return 1;
                           if (a > b) return -1;
                           return 0;
        });


    console.log(arr.join('\n'));
}

sortArray(['alpha', 'beta', 'gamma']);