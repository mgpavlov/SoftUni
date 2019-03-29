(() => {
    const renderViruses = () => {
        const $table =  $('.table').empty();
        $table
            .append($('<thead class="thead-dark font-weight-bold">')
                .append($('<tr>')
                    .append($('<th scope="col">').text("#"))
                    .append($('<th scope="col">').text("Name"))
                    .append($('<th scope="col">').text("Magnitude"))
                    .append($('<th scope="col">').text("Released On"))
                    .append($('<th scope="col">'))
                    .append($('<th scope="col">'))
                ))
            .append($('<tbody>')
            );

        fetch('http://localhost:8080/viruses/all')
            .then(res => res.json())
            .then(res => res.forEach((virus) => {
                $table.find('tbody')
                    .append($('<tr>')
                        .append($('<th scope="row">').text(virus.id))
                        .append($('<td>').text(virus.name))
                        .append($('<td>').text(virus.magnitude))
                        .append($('<td>').text(virus.releasedOn))
                        .append($('<td>')
                            .append($('<a class="btn bg-re">')
                                .attr("href", "/viruses/edit/" + virus.id)
                                .text("Edit")))
                        .append($('<td>')
                            .append($('<form method="post">').attr("action", "/viruses/delete/" + virus.id)
                                .append($('<button class="btn bg-re">').text("Delete"))))
                    );
            })).catch(err => console.log(err));
    };

    const renderCapitals = () => {
        const $table =  $('.table').empty();

        $table
            .append($('<thead class="thead-dark font-weight-bold">')
                .append($('<tr>')
                    .append($('<th scope="col">').text("#"))
                    .append($('<th scope="col">').text("Name"))
                    .append($('<th scope="col">').text("Latitude"))
                    .append($('<th scope="col">').text("Longitude"))
                ))
            .append($('<tbody>')
            );

        fetch('http://localhost:8080/capitals/all')
            .then(res => res.json())
            .then(res => res.forEach((capital) => {
                $table.find('tbody')
                    .append($('<tr>')
                        .append($('<th scope="row">').text(capital.id))
                        .append($('<td>').text(capital.name))
                        .append($('<td>').text(capital.latitude))
                        .append($('<td>').text(capital.longitude))
                    );
            })).catch(err => console.log(err));
    };

    $('input[type="radio"]').on('change', function(e) {
        $('.mc-select-header').remove();

        const value = $('input[name="radio-stacked"]:checked').val();
        switch (value) {
            case "viruses":
                renderViruses();
                $('h1.mc-choice').text("All Viruses");
                break;
            case "capitals":
                renderCapitals();
                $('h1.mc-choice').text("All Capitals");
                break;
        }
    });
})();