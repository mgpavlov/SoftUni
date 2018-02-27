function getArticleGenerator(articles) {
    let content = $('#content');
    return function () {
        if (articles.length > 0)
           content.append($('<article>').append($('<p>').text(`${articles.shift()}`)));
           /* $('content').append('$(<article>)'.append(`<p>${articles.shift()}</p>`));*/
/*            let article = $('<article>');
            article.append(`<p>${articles.shift()}</p>`);
            $('#content').append(article);*/

    }
}


/*
function getArticleGenerator(articles) {
    let copyArticles = Object.assign([], articles);
    return function () {
        if (copyArticles.length > 0) {
            let article = $('<article>');
            article.append(`<p>${copyArticles.shift()}</p>`);
            $('#content').append(article);
        }
    }
}*/
