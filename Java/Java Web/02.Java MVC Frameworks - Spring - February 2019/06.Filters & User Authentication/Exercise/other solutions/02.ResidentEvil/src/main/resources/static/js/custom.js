function showViruses() {

    fetch('/viruses/all-viruses')
        .then((response) => response.json())
        .then((json) => {
            $('#customText').text('All Viruses');
            $('.container #content-container').remove();
            $('.container').append('<div id ="content-container" class="mt-4 bg-white"></div>');
            let table =
                ` <table class="table mt-3 bg-white table-hover" >
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Magnitude</th>
            <th scope="col">Released On</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>`;

            json.forEach((virus, index) => {
                table +=
                    `<tr><th scope="row">${index + 1}</th> 
                <td>${virus.name}</td>
                <td>${virus.magnitude}</td>
                <td>${virus.releasedOn}</td>
                <td>
                <a href="/viruses/edit?id=${virus.id}" class="btn btn-outline-dark text-info">Edit</a>
                </td>
                <td>
                <a href="/viruses/delete?id=${virus.id}" class="btn btn-outline-dark text-danger">Delete</a>
                </td>
                </tr>`;
            });
            table +=
                `<tbody></table>`;

            $('#content-container').append(table);
        });
}

function showCapitals() {

    fetch('/viruses/all-capitals')
        .then((response) => response.json())
        .then((json) => {
            $('#customText').text('All Capitals');
            $('.container #content-container').remove();
            $('.container').append('<div id="content-container" class="mt-4 bg-white"></div>');

            let table =
                ` <table class="table mt-3 bg-white table-hover" >
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Latitude</th>
            <th scope="col">Longitude</th>
        </tr>
        </thead>
        <tbody>`;

            json.forEach((capital, index) => {
                table +=
                    `<tr>
                   <th scope="row">${index + 1}</th>
                   <td>${capital.name}</td>
                   <td>${capital.latitude}</td>
                   <td>${capital.longitude}</td>
                   </tr>`;
            });
            table +=
                '<tbody>' +
                '</table>';

            $('#content-container').append(table);
        });
}



