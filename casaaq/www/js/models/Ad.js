define(["jquery", "underscore", "backbone"],
  function ($, _, Backbone) {
    var Ad = Backbone.Model.extend({
      defaults: {
      	title: undefined,
      	price: 0,
      	figure: undefined
      }

      });

    return Ad;

  });