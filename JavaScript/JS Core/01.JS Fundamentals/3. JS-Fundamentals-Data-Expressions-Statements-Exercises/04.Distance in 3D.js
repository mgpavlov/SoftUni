function distXYZ(arr) {
    let x1 = Number(arr[0]);
    let y1 = Number(arr[1]);
    let z1 = Number(arr[2]);

    let x2 = Number(arr[3]);
    let y2 = Number(arr[4]);
    let z2 = Number(arr[5]);

    let x = x1-x2;
    let y = y1-y2;
    let z = z1-z2;

    let dist3D =  Math.sqrt(x*x + y*y + z*z);

    console.log(dist3D);
}
distXYZ([3.5, 0, 1, 0, 2, -1])