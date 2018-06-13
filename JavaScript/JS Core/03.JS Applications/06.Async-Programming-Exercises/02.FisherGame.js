function attachEvents(){
    let catches = $('#catches');

    let baseUrl = "https://baas.kinvey.com/appdata/kid_rkSZuM59G/biggestCatches";
    const kinveyUsername = "pesho";
    const kinveyPassword = "p";
    const base64auth = btoa(kinveyUsername + ":" + kinveyPassword);
    const authHeaders = {"Authorization": "Basic " + base64auth,
        'Content-type': 'application/json'};

    function request(method, endpoint, obj) {
        return $.ajax({
            method: method,
            url: baseUrl + endpoint,
            headers: authHeaders,
            data: JSON.stringify(obj)
        })
    }

    let asideDiv = $('#aside');
    let addForm = $('#addForm');

    let addBtn = addForm.find('.add');
    let loadBtn = asideDiv.find('.load');

    addBtn.click(add);
    loadBtn.click(load);

    function add() {
         let anglerA = addForm.find('.angler').val();
         let weightA = Number(addForm.find('.weight').val());
         let speciesA = addForm.find('.species').val();
         let locationA = addForm.find('.location').val();
         let baitA = addForm.find('.bait').val();
         let captureTimeA = Number(addForm.find('.captureTime').val());
         let obj = {"angler":anglerA,
             "weight":weightA,
             "species":speciesA,
             "location":locationA,
             "bait":baitA,
             "captureTime":captureTimeA};
         request('POST', '', obj);
         load();
     }

    function load() {
         catches.empty();
        request('GET','').then(displayCatches).catch(handleError);
        function displayCatches(data) {
            for (let obj of data) {
                let data_id = obj._id;
                let anglerC = obj.angler;
                let weightC = Number(obj.weight);
                let speciesC = obj.species;
                let locationC = obj.location;
                let baitC = obj.bait;
                let captureTimeC = Number(obj.captureTime);


                let divCatch = $(`<div class="catch" data-id=${data_id}>`);
                let labelAngler = $(`<label>Angler</label>`);
                let inputAngler = $(`<input type="text" class="angler" value="${anglerC}">`);
                let labelWeight = $(`<label>Weight</label>`);
                let inputWeight = $(`<input type="number" class="weight" value="${weightC}">`);
                let labelSpecies = $(`<label>Species</label>`);
                let inputSpecies = $(`<input type="text" class="species" value="${speciesC}">`);
                let labelLocation = $(`<label>Location</label>`);
                let inputLocation = $(`<input type="text" class="location" value="${locationC}">`);
                let labelBait = $(`<label>Bait</label>`);
                let inputBait = $(`<input type="text" class="bait" value="${baitC}">`);
                let labelCaptureTime = $(`<label>Capture Time</label>`);
                let inputCaptureTime = $(`<input type="number" class="captureTime" value="${captureTimeC}">`);
                let updateBtn = $(`<button class="update">Update</button>`);
                let deleteBtn = $(`<button class="delete">Delete</button>`);

                updateBtn.click(update);
                deleteBtn.click(del);

                divCatch
                    .append(labelAngler).append(inputAngler)
                    .append(labelWeight).append(inputWeight)
                    .append(labelSpecies).append(inputSpecies)
                    .append(labelLocation).append(inputLocation)
                    .append(labelBait).append(inputBait)
                    .append(labelCaptureTime).append(inputCaptureTime)
                    .append(updateBtn).append(deleteBtn);
                catches.append(divCatch);

                function update() {
                    let anglerU = divCatch.find('.angler').val();
                    let weightU = Number(divCatch.find('.weight').val());
                    let speciesU = divCatch.find('.species').val();
                    let locationU = divCatch.find('.location').val();
                    let baitU = divCatch.find('.bait').val();
                    let captureTimeU = Number(divCatch.find('.captureTime').val());

                    let obj = {"angler":anglerU,
                        "weight":weightU,
                        "species":speciesU,
                        "location":locationU,
                        "bait":baitU,
                        "captureTime":captureTimeU};

                    request('PUT', `/${data_id}`, obj).then(load).catch(handleError);
                }

                function del() {
                    request('DELETE', `/${data_id}`).then(load).catch(handleError);
                }
            }
        }
    }
    function handleError(err) {
        alert(`Error happened: ${err.statusText}`);
    }
}
