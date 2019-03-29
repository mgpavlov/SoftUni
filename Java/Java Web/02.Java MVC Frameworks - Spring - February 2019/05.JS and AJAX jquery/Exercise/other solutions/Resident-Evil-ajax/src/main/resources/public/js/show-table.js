$(document).ready(function (){
    $("#capitals").click(function () {
          let radioAnswer = $("#capitals").val();
          $.ajax({
          type: "POST",
          url: "http://localhost:8080/show-capitals",
          data: radioAnswer,
          dataType: "json",
          contentType:"text/plain"
      }).then(function (data, status) {
        $('#main-text').replaceWith('<h1 id="main-text">All Capitals</h1>');
        $("#ajax-table").replaceWith('<div id="ajax-table">'
                                       +'<table id="capital-table" class="table table-hover">'
                                         +' <thead class="font-weight-normal">'
                                         +'<tr>'
                                         +' <th scope="col">#</th>'
                                         +' <th scope="col">Name</th>'
                                         +' <th scope="col">Latitude</th>'
                                         +'<th scope="col">Longitude</th>'
                                         +'  </tr>'
                                         +' </thead>'
                                         +' <tbody id="capital-data">'
                                         +' </tbody>'
                                       +'</table>'
                                      +'</div>')

         for(i=0;i<data.length;i++){
         $('#capital-data').append('<tr><td>'+i+
         '</td>'+'<td>'+data[i].name+'</td>'+
         '</td>'+'<td>'+data[i].latitude+'</td>'
         +'<td>'+data[i].longitude+'</td></tr>')
         }
        $("#select-msg").remove();
       });
    });

    $('#viruses').click(function () {
          let radioAnswer = $('#viruses').val();
          $.ajax({
          type: "POST",
          url: "http://localhost:8080/show-viruse-table",
          data: radioAnswer,
          dataType: "json",
          contentType:"text/plain"
      }).then(function (data, status) {
        $('#main-text').replaceWith('<h1 id="main-text">All Viruses</h1>');
        $("#ajax-table").replaceWith('<div id="ajax-table">'
                                        +'<table id="virus-table" class="table table-hover">'
                                          +' <thead class="font-weight-normal">'
                                          +'<tr>'
                                          +' <th scope="col">#</th>'
                                          +' <th scope="col">Name</th>'
                                          +' <th scope="col">Magnitude</th>'
                                          +' <th scope="col">Released On</th>'
                                          +' <th scope="col">Actions</th>'
                                          +' <th scope="col"></th>'
                                          +'</tr>'
                                          +' </thead>'
                                          +' <tbody id="virus-data">'
                                          +' </tbody>'
                                        +'</table>'
                                       +'</div>');

         for(i=0;i<data.length;i++){
         $('#virus-data').append('<tr><td>'+i+
         '</td>'+'<td>'+data[i].name+'</td>'+
         '</td>'+'<td>'+data[i].magnitude+'</td>'
         +'<td>'+data[i].releasedOn+'</td>'
         +'<td>'
              +'<a class="btn btn-secondary" href="/viruses/edit/'+ data[i].id +'">Edit</a>'
          +'</td>'
          +'<td>'
              +'<form method="post" action="/viruses/delete/'+ data[i].id +'">'
                  +'<button class="btn btn-secondary">Delete</button>'
              +'</form>'
          +'</td></tr>')
         }
         $("#select-msg").remove();
        });
    });
});
