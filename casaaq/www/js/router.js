define(["jquery", "underscore", "parse", "collections/AdCollection", "models/Ad", "views/AdView", "views/AdListView", "views/MapView", "views/StructureView"],
    function ($, _, Parse, AdCollection, Ad, AdView, AdListView, MapView, StructureView) {

    var AppRouter = Parse.Router.extend({

      routes: {
        "": "structure",
        "list": "list",
        "map": "map",
        "ads/:id": "adDetails"
      },

      initialize: function () {
        this.currentView = undefined;
        var ad1 = new Ad({
          title: "Camera1",
          price: "200",
          position: [42.36812, 13.350607]
        });
        var ad2 = new Ad({
          title: "Camera2",
          price: "150",
          position: [42.35259, 13.402417]
        });
        this.ads = new AdCollection([ad1, ad2]);
        this.ads.query = new Parse.Query(Ad);
      },

      structure: function () {
        if(!this.structureView) {
          this.structureView = new StructureView();
          this.structureView.render();
          this.contents = this.structureView.$el.find("#content #contents");
        }
        this.list();
      },

      list: function () {
        var page = new AdListView({
          model: this.ads
        });
        this.changePage(page);
      },

      map: function () {
        var page = new MapView({
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
           this.currentView.off();
        }
        this.currentView = page;
        page.render();
        this.contents.append($(page.el));
        this.currentView.trigger("inTheDom");
      }

    });

    return AppRouter;

  });
