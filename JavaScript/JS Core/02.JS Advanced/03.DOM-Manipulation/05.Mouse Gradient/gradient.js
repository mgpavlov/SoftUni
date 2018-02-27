function attachGradientEvents() {
    let gradient = document.getElementById('gradient');
    let result = document.getElementById('result');
    gradient.addEventListener("mousemove", gradientMove);
    gradient.addEventListener("mouseout", gradientOut);

    function gradientMove(event) {
        let x = event.offsetX;
        let percent =x*100/(event.target.clientWidth-1); //взимаме ширината на обекта, може с this.clientWidth
        result.textContent =Math.trunc(percent) + '%';
    }
    function gradientOut() {
        result.textContent = '';
    }
}