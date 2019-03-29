$(document).ready(function () {

    let content = $('#content');
    let headerTitle = $('#headerTitle');
    $('#all_capitals').change(() => {
        headerTitle.text('All Capitals');
        content.empty()
        let table = $('<table  class="table mt-3 mb-5 center-block w-75 mx-auto">');
        let tableHead = '<thead>\n' +
            '        <tr>\n' +
            '            <th scope="col">#</th>\n' +
            '            <th scope="col" >Name</th>\n' +
            '            <th scope="col">Latitude</th>\n' +
            '            <th scope="col">Longitude</th>\n' +
            '        </tr>\n' +
            '        </thead>';
        let tableBody = $('<tbody>');
        fetch('http://localhost:8080/all_capitals')
            .then((response) => response.json())
            .then((json) => {

                json.forEach((x, y) => {
                    $(tableBody).append('<tr>\n' +
                        '                <td scope="row">' + y + '</td>\n' +
                        '                <td>' + x.name +'</td>\n' +
                        '                <td>' + x.latitude +'</td>\n' +
                        '                <td>' + x.longitude +'</td>\n' +
                        '            </tr>')
                });
            })
        content.append(table.append(tableHead).append(tableBody));
    });

    $('#all_viruses').change(() => {
        headerTitle.text('All Viruses');
        content.empty();
        let table = $('<table  class="table mt-3 mb-5 center-block w-75 mx-auto">');
        let tableHead = '<thead>\n' +
            '        <tr>\n' +
            '            <th scope="col">#</th>\n' +
            '            <th scope="col" >Name</th>\n' +
            '            <th scope="col">Magnitude</th>\n' +
            '            <th scope="col">Released On</th>\n' +
            '        </tr>\n' +
            '        </thead>';
        let tableBody = $('<tbody>');
        fetch('http://localhost:8080/viruses/all_viruses')
            .then((response) => response.json())
            .then((json) => {
                json.forEach((x, y) => {
                    $(tableBody).append('<tr>\n' +
                        '                <td scope="row">' + (Number(y) + 1) +  '</td>\n' +
                        '                <td>' + x.name +'</td>\n' +
                        '                <td>' + x.magnitude +'</td>\n' +
                        '                <td>' + x.releasedOn +'</td>\n' +
                        '<td>\n' +
                        '                    <form method="get" action="/viruses/edit/' + x.id + '">\n' +
                        '                        <button type="submit" class="btn btn-light btn-sm border-dark"\n' +
                        '                               >Edit</button>\n' +
                        '                    </form>\n' +
                        '                </td>\n' +
                        '                <td>\n' +
                        '                    <form method="get" action="/viruses/delete/' + x.id + '">\n' +
                        '                        <button type="submit" class="btn btn-light btn-sm border-dark"\n' +
                        '                                >Delete</button>\n' +
                        '                    </form>\n' +
                        '                </td>' +
                        '            </tr>')
                });
                content.append(table.append(tableHead).append(tableBody));
            })
    });
});