var currentMovie;

$('#moviePage').live('pageshow', function(event) {
    var id = getUrlVars()["id"];
    currentMovie = getMovie(id);
    $('#moviePoster').attr('src', currentMovie.posters.detailed);                 
    $('#title').append(currentMovie.title);
    $('#synopsis').append(currentMovie.synopsis);
    $('#externalLink').attr('href', currentMovie.links.alternate);
});
                       
function getUrlVars() {
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

function getMovie(id) {
    var found = false;
    var movie;
    var i = 0;
    while(i<=movies.length && !found) {
        if(movies[i].id == id) {
            found = true;
            movie = movies[i];
        }
        i++;
    }
    return movie;
}
