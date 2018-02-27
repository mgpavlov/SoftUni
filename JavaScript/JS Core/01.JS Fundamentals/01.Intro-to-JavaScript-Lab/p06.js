function areaCalc(w, h, W, H) {
     let ar1 = w*h;
     let ar2 = W*H;

     let minW = Math.min(w, W);
     let minH = Math.min(h, H);
     let areaReduction = minH*minW;
     let area = ar1+ar2-areaReduction;

     return area;
}