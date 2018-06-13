let receiptService = (() => {
    function getActiveReceipt(userId) {
        let endpoint = `receipts?query={"_acl.creator":"${userId}","active":"true"}`;
        return requester.get('appdata', endpoint, 'kinvey');
    }
    
    function createReceipt(active, productCount, total) {
        let receiptObj = {
            active,
            productCount,
            total
        };

        return requester.post('appdata', 'receipts', 'kinvey', receiptObj);
    }

    function commitReceipts(receiptId, active, productCount, total) {
        let updatedObj = {
            active,
            productCount,
            total
        };

        return requester.update('appdata', `receipts/${receiptId}`, 'kinvey', updatedObj)
    }

    function getOwnReceipts(userId) {
        let endpoint = `receipts?query={"_acl.creator":"${userId}","active":"false"}`;

        return requester.get('appdata', endpoint, 'kinvey');
    }

    function receiptDetails(receiptId) {
        let endpoint = `receipts/${receiptId}`;

        return requester.get('appdata', endpoint, 'kinvey');
    }


    return {
        getActiveReceipt,
        createReceipt,
        commitReceipts,
        getOwnReceipts,
        receiptDetails
    }
})()