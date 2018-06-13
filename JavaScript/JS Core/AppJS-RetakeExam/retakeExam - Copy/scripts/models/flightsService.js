let flightsService = (() => {
    function getPublishedFlights() {
        return requester.get('appdata', 'flights?query={"isPublished":true}', 'kinvey');
    }
    
    function createFlight(destination, origin, departureDate, departureTime, seats, cost, image, isPublished) {
        let flightObj = {
            "destination":destination,
            "origin":origin,
            "departureDate":departureDate,
            "departureTime": departureTime,
            "seats":seats,
            "cost":cost,
            "image":image,
            "isPublished": isPublished
        };

        return requester.post('appdata', 'flights', 'kinvey', flightObj);
    }

    function editFlight(flightId, destination, origin, departureDate, departureTime, seats, cost, image, isPublished) {
        let updatedFlightObj = {
            "destination":destination,
            "origin":origin,
            "departureDate":departureDate,
            "departureTime": departureTime,
            "seats":seats,
            "cost":cost,
            "image":image,
            "isPublished": isPublished
        };

        return requester.update('appdata', `flights/${flightId}`, 'kinvey', updatedFlightObj)
    }

    function deleteFlight(flightId) {
        return requester.remove('appdata', `flights/${flightId}`, 'kinvey');
    }

    function loadMyFlights(userId) {
        let endpoint = `flights?query={"_acl.creator":"${userId}"}`;

        return requester.get('appdata', endpoint, 'kinvey');
    }

    function flightDetails(flightId) {
        let endpoint = `flights/${flightId}`;

        return requester.get('appdata', endpoint, 'kinvey');
    }

    return {
        getPublishedFlights,
        createFlight,
        editFlight,
        deleteFlight,
        loadMyFlights,
        flightDetails
    }
})()