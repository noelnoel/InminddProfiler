function graph(graphdata){
console.log(graphdata);
require(["dojox/charting/Chart", "dojox/charting/axis2d/Default",  "dojox/charting/plot2d/StackedBars", "dojox/charting/action2d/Tooltip", "dojox/charting/action2d/Highlight", "dojox/charting/action2d/Magnify", "dojox/charting/widget/SelectableLegend", "dojo/ready","dojox/charting/themes/Claro",],
  function(Chart, Default, StackedColumns, Tooltip, Highlight, Magnify, SelectableLegend, ready, theme){
    ready(function(){
      var chart1 = new dojox.charting.Chart("chart1");
        
      chart1.setTheme(theme);
      chart1.addPlot("default",{type: "StackedBars"});
      chart1.addAxis("x", {includeZero: true});


      if(graphdata.bars.hasOwnProperty("drinking")){
        chart1.addSeries("alcool", [graphdata.bars.drinking], {plot: "default", fill: "blue", stroke: {color: "blue"}});
      }
      if(graphdata.bars.hasOwnProperty("obesity")){
        chart1.addSeries("midlife obesity", [graphdata.bars.obesity], {plot: "default", fill: "green", stroke: {color: "green"}});
      }
      if(graphdata.bars.hasOwnProperty("diet")){
        chart1.addSeries("healthy diet", [graphdata.bars.diet], {plot: "default", fill: "orange", stroke: {color: "orange"}});
      }
      if(graphdata.bars.hasOwnProperty("mood")){
        chart1.addSeries("depressed mood", [graphdata.bars.mood], {plot: "default", fill: "purple", stroke: {color: "purple"}});
      }
      if(graphdata.bars.hasOwnProperty("blood_pressure")){
        chart1.addSeries("blood pressure", [graphdata.bars.blood_pressure], {plot: "default", fill: "yellow", stroke: {color: "yellow"}});
      }
      /*if(graphdata.bars.hasOwnProperty("diabetes")){
        chart1.addSeries("diabetes", [graphdata.bars.diabetes], {plot: "default", fill: "red", stroke: {color: "red"}});
      }*/
      if(graphdata.bars.hasOwnProperty("smoking")){
        chart1.addSeries("smoking", [graphdata.bars.smoking], {plot: "default", fill: "blue", stroke: {color: "blue"}});
      }
      if(graphdata.bars.hasOwnProperty("cholesteral")){
        chart1.addSeries("cholesterol", [graphdata.bars.cholesteral], {plot: "default", fill: "green", stroke: {color: "green"}});
      }
      if(graphdata.bars.hasOwnProperty("coginitive_activity")){        
        chart1.addSeries("coginitive activity", [graphdata.bars.coginitive_activity], {plot: "default", fill: "orange", stroke: {color: "orange"}});
      }
      /*if(graphdata.bars.hasOwnProperty("heart_disease")){
        chart1.addSeries("heart disease", [graphdata.bars.heart_disease], {plot: "default", fill: "purple", stroke: {color: "purple"}});
      }
      if(graphdata.bars.hasOwnProperty("kidney_disease")){        
        chart1.addSeries("kidney disease", [graphdata.bars.kidney_disease], {plot: "default", fill: "yellow", stroke: {color: "yellow"}});
      }*/
      if(graphdata.bars.hasOwnProperty("physical_exercise")){
        chart1.addSeries("physical exercise", [graphdata.bars.physical_exercise], {plot: "default", fill: "red", stroke: {color: "red"}});
      }
      //chart1.addPlot("linesPlot",{type:"Lines", markers: true});
      //chart1.addSeries("B",[2,4,6,8,3,5,7], {plot: "linesPlot", stroke: {color: "red", width: 4}});
      //chart1.movePlotToBack("default");
 
      var tooltip = new Tooltip( chart1, "default", {
        text : function(point) {
          console.debug(point);
          return  point.run.name;        
        }              
      }); 

      chart1.render();
      //var clusteredColumnsLegend = new SelectableLegend({chart: chart1}, "chart1Legend");   
      window.setTimeout(function(){
        $("#chart1").css("margin", "0 auto");
      }, 200);
    });
  });      

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