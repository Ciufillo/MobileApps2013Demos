define(["jquery", "underscore", "parse", "handlebars", "leaflet", "text!templates/structure.html"],
    function ($, _, Parse, Handlebars, L, template) {

    var StructureView = Parse.View.extend({

        id: "mainContainer", 

        events: {
          "touchend #menuButton": "toggleMenu",
          "touchend #otherButton": "showMap"
        },

        showMap: function () {
          Parse.history.navigate("map", {trigger: true});
        },

        goBack: function () {
          window.history.back();
        },

        template: Handlebars.compile(template),

        render: function (eventName) {
          this.title = "Test";
          $(this.el).html(this.template({"title": this.title}));
          $('body').append($(this.el));
          if(!this.otherButton) {
            this.otherButton = document.getElementById("otherButton");
          }
          return this;
        },

        toggleMenu: function () {
          this.el.classList.toggle("is-menu-visible");
        }
      });

    return StructureView;

  }); 