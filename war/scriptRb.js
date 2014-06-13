function load(){
	specials_risk_factor = ["heart_disease","kidney_disease","diabetes"];
	data = {"blood_pressure":8.6,
		"cholesteral":0, //7.5
		"coginitive_activity":17.1,
		"diabetes":7,
		"diet":0, //9.1
		"drinking":5.3,
		"heart_disease":5.9,
		"kidney_disease":5.9,
		"mood":0, //11.2
		"obesity":0, //8.6
		"physical_exercise":0, //5.9
		"smoking":0 //8.0
	}
	/*	Alcohol	0.74	-0.30	-1.0	1	5.3
Physical inactivity	1.39	0.33	+1.1	1.1	5.9
Coron. heart dis.	1.38	0.32	+1.1	1.1	5.9
Chron. kidney disease	1.39	0.33	+1.1	1.1	5.9
Diabetes	1.47	0.39	+1.3	1.3	7.0
Cholesterol	1.54	0.43	+1.4	1.4	7.5
Smoking	1.59	0.46	+1.5	1.5	8.0
Midlife obesity	1.60	0.47	+1.6	1.6	8.6
Midlife hypertension	1.61	0.48	+1.6	1.6	8.6
Healthy diet	0.60	-0.51	-1.7	1.7	9.1
Depressed mood	1.85	0.62	+2.1	2.1	11.2
High cognitive activity	0.38	-0.97	-3.2	3.2	17.1
*/

	dataGraph = {"pie": {"manage":0, "improvement":0,"keep":0}, "bars":{}};
	sum = 0;
	$.each(data, function(index, value) {
		if(jQuery.inArray( index, specials_risk_factor) != -1){
			$(".libra-improvement .img-"+index).css("display","none");
			//$(".libra-manage .img-"+index).css("display","none");
			$(".libra-keep .img-"+index).css("display","none");
			dataGraph.pie["manage"] += value;
		} else if(value == 0){
			$(".libra-improvement .img-"+index).css("display","none");
			$(".libra-manage .img-"+index).css("display","none");
			//$(".libra-keep .img-"+index).css("display","none");
			sum += value;
		} else {
			//$(".libra-improvement .img-"+index).css("display","none");
			$(".libra-manage .img-"+index).css("display","none");
			$(".libra-keep .img-"+index).css("display","none");
			dataGraph.pie["improvement"] += value;
			sum += value;
		}
		
	}); 
	$.each(data, function(index, value) {
		if(jQuery.inArray( index, specials_risk_factor) == -1 && value != 0){
			dataGraph.bars[index] = value/sum*100;
		}
	}); 
	dataGraph.pie["keep"] = 100 - dataGraph.pie["manage"] - dataGraph.pie["improvement"];
	graph(dataGraph);

}

function clickFactors(event){
	var title = $(event.target).parents("div").first().children("h4").html();
	$("#sidebar-title").html(title);

}

var data = {};
$(document).ready(function(){
	load();
	$(document).on("click",".circle", clickFactors);
});