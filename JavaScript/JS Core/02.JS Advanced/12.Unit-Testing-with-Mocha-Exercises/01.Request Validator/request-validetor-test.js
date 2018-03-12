function validateRequest(obj) {
    let methodRegex = /^GET$|^POST$|^DELETE$|^CONNECT$/g;
    let uriRegex = /^[A-Za-z\d.*]+$/g;
    let versionRegex = /^HTTP\/0.9$|^HTTP\/1.0$|^HTTP\/1.1$|^HTTP\/2.0$/g;
    let messageRegex = /^[^<>&'"\\]*$/g;

    if (!obj.method||!methodRegex.test(obj.method)) {
        throw new Error('Invalid request header: Invalid Method')
    }
    if (!obj.uri || !uriRegex.test(obj.uri)) {
        throw new Error('Invalid request header: Invalid URI');
    }
    if (!obj.version||!versionRegex.test(obj.version)) {
        throw new Error('Invalid request header: Invalid Version');
    }
    if (!obj.message || !messageRegex.test(obj.message)) {
        if(obj.message === '') return obj;
        throw new Error('Invalid request header: Invalid Message');
    }
    return obj
}

console.log(validateRequest({
    method: 'POST',
    version: 'HTTP/2.0',
    message: 'rm -rf /*'
}));
/*console.log(validateRequest({
    method: 'GET',
    uri: '...aaa666',
    version: 'HTTP/1.1',
    message: ''
}));*/

/*console.log(validateRequest({
    method: 'GET',
    uri: 'svn.public.catalog',
    version: 'HTTP/1.1',
    message: ''
}));*/

/*console.log(validateRequest({
    method: 'GET ',
    uri: 'svn.public.catalog',
    version: 'HTTP/1.1',
    message: ''
}));*/
