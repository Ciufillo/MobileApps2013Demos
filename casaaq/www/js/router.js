define(["jquery", "underscore", "parse", "collections/AdCollection", "models/Ad", "views/AdView", "views/AdListView", "views/StructureView"],
    function ($, _, Parse, AdCollection, Ad, AdView, AdListView, StructureView) {

    var AppRouter = Parse.Router.extend({

      routes: {
        "": "structure",
        "list": "list",
        "ads/:id": "adDetails"
      },

      initialize: function () {
        this.currentView = undefined;
        var ad1 = new Ad({
          title: "Camera1",
          price: "200"
        });
        var ad2 = new Ad({
          title: "Camera2",
          price: "150"
        });
        this.ads = new AdCollection([ad1, ad2]);
        this.ads.query = new Parse.Query(Ad);
      },

      structure: function () {
        if(!this.structureView) {
          this.structureView = new StructureView();
          this.structureView.render();
        }
        this.list();
      },

      list: function () {
        var page = new AdListView({
          model: this.ads
        });
        this.changePage(page);
      },

      adDetails: function (id) {
        var ad = this.ads.getByCid(id);
        this.changePage(new AdView({
          model: ad
        }));
      },

      changePage: function (page) {
        if(this.currentView) {
           this.currentView.remove();
        }
        this.currentView = page;
        page.render();
        console.log(this.structureView.$el);
        this.structureView.$el.find("#content #contents").append($(page.el));
      }

    });

    return AppRouter;

  });
