function converter(inches) {
    let foots = Math.floor(Number(inches/12));
    inches = inches%12;

    console.log(`${foots}'-${inches}"`);
}

converter(36);