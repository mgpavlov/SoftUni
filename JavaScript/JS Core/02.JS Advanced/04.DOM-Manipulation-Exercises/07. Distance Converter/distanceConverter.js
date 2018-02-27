function attachEventsListeners() {
    let data = {
        'km': 1000,
        'm': 1,
        'cm': 0.01,
        'mm': 0.001,
        'mi': 1609.34,
        'yrd': 0.9144,
        'ft': 0.3048,
        'in': 0.0254
    };
     document.getElementById('convert').addEventListener("click", convert);
     function convert() {
         let input = document.getElementById('inputDistance');
         let output = document.getElementById('outputDistance');
         output.value = Number(input.value)*data[document.getElementById('inputUnits').value]/data[document.getElementById('outputUnits').value]
     }
}
