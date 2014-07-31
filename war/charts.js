function graph(graphdata){
console.log(graphdata);
 

require([
    "dojo/_base/declare",

     // Require the basic chart class
    "dojox/charting/Chart",
 
    // Require the theme of our choosing
    "dojox/charting/themes/Claro",
 
    // Charting plugins:
 
    //  We want to plot a Pie chart
    "dojox/charting/plot2d/Pie",
 
    // Retrieve the Legend, Tooltip, and MoveSlice classes
    "dojox/charting/action2d/Tooltip",
    "dojox/charting/action2d/MoveSlice",
 
    //  We want to use Markers
    "dojox/charting/plot2d/Markers",
 
    //  We'll use default x/y axes
    "dojox/charting/axis2d/Default",
 
    // Wait until the DOM is ready
    "dojo/domReady!"
], function(declare, Chart, theme, Pie, Tooltip, MoveSlice) {

  var Donut = declare(Pie, {
        render: function (dim, offsets) {
            // Call the Pie's render method
            this.inherited(arguments);

            // Draw a white circle in the middle
            var rx = (dim.width - offsets.l - offsets.r) / 2,
                ry = (dim.height - offsets.t - offsets.b) / 2,
                r = Math.min(rx, ry) / 2;
            var circle = {
                cx: offsets.l + rx,
                cy: offsets.t + ry,
                r: r
            };
            var s = this.group;

            s.createCircle(circle).setFill("#fff").setStroke("#fff");
        }
    });
 
    // Define the data
    var chartData = [{y:graphdata.pie.keep, tooltip:text.keepthisup, color:"#62C0EA"}, {y:graphdata.pie.manage, tooltip:text.rmw, color:"#E1630C"}, {y:graphdata.pie.improvement, tooltip:text.rfi, color:"#F9B233"}]; 
 
    // Create the chart within it's "holding" node
    var chart = new Chart("chartNode");
 
    // Set the theme
    chart.setTheme(theme);
 
    // Add the only/default plot
    chart.addPlot("default", {
        type: Donut,
        markers: false,
        radius:155
    });
 
    // Add axes
    chart.addAxis("x");
    chart.addAxis("y", { min: 5000, max: 30000, vertical: true, fixLower: "major", fixUpper: "major" });
 
    // Add the series of data
    chart.addSeries("Pie",chartData);
 
    // Create the tooltip
    var tip = new Tooltip(chart,"default");
 
    // Create the slice mover
    var mag = new MoveSlice(chart,"default");
 
    // Render the chart!
    chart.render();
    window.setTimeout(function(){
      $("#chartNode").css("margin", "0 auto");
      texts = $("#chartNode").find("div div");
      $(texts[0]).html("");
      $(texts[1]).html("");
      $(texts[2]).html("");
    }, 200);
});
}