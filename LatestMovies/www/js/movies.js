// this is the command to launch the app in Chrome: open -a Google\ Chrome.app --args "--allow-file-access-from-files"

var movies;

page = 1,
listType = "box_office",
apiKey = "tkwju4um7d3283vp4kh9ra8m";
baseUrl = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/";

$('#home').bind('pageinit', function(event) {
    getMovies();
});


function getMovies() {
    $.getJSON(baseUrl + listType + ".json?apikey=" + apiKey + "&page=" + page + "&callback=?", searchCallback);
}

function searchCallback(data) {
    $('#busy').hide();
    movies = data.movies;
    $.each(movies, function(index, movie) {
        $('#moviesList').append('<li><a href="moviedetails.html?id=' + movie.id + '">' +
        '<img src="' + movie.posters.thumbnail + '" class="list-icon">' +                       
        '<h4>' + movie.title + '</h4>' +
        '<p>' + movie.critics_consensus + '</p>' +
        '</li></li>');
    });
    $('#moviesList').listview('refresh');
}
