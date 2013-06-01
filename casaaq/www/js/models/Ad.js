define(["jquery", "underscore", "parse"],
  function ($, _, Parse) {
    var Ad = Parse.Object.extend("Ad", {
      defaults: {
      	title: undefined,
      	price: 0,
      	figure: undefined,
        position: undefined
      }

      });

    return Ad;

  });