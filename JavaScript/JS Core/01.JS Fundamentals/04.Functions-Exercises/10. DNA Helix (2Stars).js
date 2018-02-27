function dna(num) {

    let sequences = 'ATCGTTAGGG';
    let stars = [2, 1, 0, 1];

    for (let i = 0; i < num*2; i += 2) {
        let result = '*'.repeat(stars[0]) + sequences[i%10] + '-'.repeat(6-2-2*stars[0]) + sequences[(i+1)%10] + '*'.repeat(stars[0]);
        console.log(result);

        let temp = stars[0];
        stars.shift();
        stars.push(temp);
    }
}

dna(15);