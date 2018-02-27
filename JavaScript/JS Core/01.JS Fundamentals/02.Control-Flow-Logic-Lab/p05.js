function area(s1, s2, s3) {
    let p = (s1+s2+s3)/2;

    let area = Math.sqrt(p*(p-s1)*(p-s2)*(p-s3));
    console.log(area);
}

area(2, 3.5, 4)