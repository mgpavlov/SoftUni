function solve(arrStr, sortProp) {
    let property = sortProp;
    let tickets = [];
    class Ticket {
        constructor(destination, price, status){
            this.destination = destination;
            this.price = price;
            this.status = status;
        }
    }
    for (let data of arrStr) {
        let ticket = new Ticket();
        let tokens = data.split('|').filter(e=>e!=='');
        let destination = tokens[0];
        let price = Number(tokens[1]);
        let status = tokens[2];
        ticket = {destination, price, status};
        tickets.push(ticket);
    }

    return tickets.sort((a,b)=> a[sortProp] > b[sortProp]);
}
solve(['Philadelphia|94.20|available',
        'New York City|95.99|available',
        'New York City|95.99|sold',
        'Boston|126.20|departed'],
    'destination'
)