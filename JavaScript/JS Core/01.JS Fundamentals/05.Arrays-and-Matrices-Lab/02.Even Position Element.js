function evenPosition(arr) {

    console.log(arr.filter((e, i) => i%2 === 0).join(' '));
}
evenPosition(['20', '30', '40'])