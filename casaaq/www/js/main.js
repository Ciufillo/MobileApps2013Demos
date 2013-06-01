require.config({
  paths: {
    jquery: '../lib/jquery/zepto',
    underscore: '../lib/underscore/underscore-min',
    parse: "../lib/parse/parse-1.2.7.min",
    text: '../lib/require/text-1.0.6',
    async: '../lib/require/async',
    handlebars: '../lib/handlebars/handlebars',
    templates: '../templates',
    leaflet: '../lib/leaflet/leaflet'
  },
  shim: {
    'jquery': {
      exports: '$'
    },
    'underscore': {
      exports: '_'
    },
    'handlebars': {
      exports: 'Handlebars'
    },
    'parse': {
      deps: ['jquery', 'underscore'],
      exports: 'Parse'
    },
    'leaflet': {
      exports: 'L'
    }
  }
});

// We launch the App
require(['underscore', 'parse', 'router'],
    function (_, Parse, AppRouter) {

      document.addEventListener("deviceready", run, false);
      function run() {
        Parse.initialize("WcxDz5mVqJzgHVYM3655WJoVDTphUpAcrhOAzS9C", "26MoNXeBtKABSPAaFAGpGpqRbPZUtodLbvqoxXwF");
        new AppRouter();
        Parse.history.start();
      }
  });
