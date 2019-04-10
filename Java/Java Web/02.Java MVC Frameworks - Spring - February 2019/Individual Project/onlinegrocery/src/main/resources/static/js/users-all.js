(() => {
    $(document).ready(() => {
        const $table = $('.table').empty();
        $table
            .append($('<thead class="font-weight-bold">')
                .append($('<tr>')
                    .append($('<th scope="col">').text("#"))
                    .append($('<th scope="col">').text("Username"))
                    .append($('<th scope="col">').text("Email"))
                    .append($('<th scope="col">').text("Authorities"))
                    .append($('<th scope="col">'))
                ))
            .append($('<tbody>')
            );

        fetch('/api/users')
            .then(res => res.json())
            .then(res => res.forEach((user) => {
                $table.find('tbody')
                    .append($('<tr>')
                        .append($('<th scope="row">').text(user.id))
                        .append($('<td>').text(user.username))
                        .append($('<td>').text(user.email))
                        .append($('<td>').text(parseRoles(user.authorities)))
                        .append($('<td>')
                            .append($('<a class="btn bg-re"><i class="fas fa-user-edit"></i> Profile</a>')
                                .attr("href", "/user/profile/" + user.id)
                        ))
                    );
            })).catch(err => console.log(err));
    });

    function parseRoles(roles) {
        return roles
            .map(r => {
                return r.authority.startsWith("ROOT") ? r.authority
                    : r.authority.split("_")[1];
            })
            .sort()
            .join(", ");
    }

})();