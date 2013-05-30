define(["jquery", "underscore", "parse", "models/Ad"],
    function ($, _, Parse, Ad) {

    var AdCollection = Parse.Collection.extend({
        model: Ad
      });

    return AdCollection;

  });