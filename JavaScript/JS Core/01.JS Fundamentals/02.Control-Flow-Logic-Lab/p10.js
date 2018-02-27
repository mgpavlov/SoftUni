function chessBoard(n) {

    console.log('<div class="chessboard">');
    for (var i = 0; i < n; i++) {
        console.log('<div>');
        for (var j = 0; j < n; j++) {
            if((i+j)%2==0){
                console.log('<span class="black"></span>');
            }else{
                console.log('<span class="white"></span>');
            }
        }
        console.log('</div>');
    }
    console.log('</div>');
}

chessBoard(8);