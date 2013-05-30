require.config({
  paths: {
    jquery: '../lib/jquery/zepto',
    underscore: '../lib/underscore/underscore-min',
    parse: "../lib/parse/parse-1.2.7.min",
    text: '../lib/require/text-1.0.6',
    async: '../lib/require/async',
    handlebars: '../lib/handlebars/handlebars',
    templates: '../templates'
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
    }
  }
});

// We launch the App
require(['underscore', 'parse', 'router'],
    function (_, Parse, AppRouter) {

      document.addEventListener("deviceready", run, false);
      function run() {
        Parse.initialize("applicationId", "javascriptKey");
        new AppRouter();
        Parse.history.start();
      }
  });
