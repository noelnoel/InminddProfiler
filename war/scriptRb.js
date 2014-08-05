function trigeredByGWT() {
	$("#scoreInputRPC").trigger("change");
}

function loadCharts(){
	specials_risk_factor = ["heart_disease","kidney_disease","diabetes"];
	data = $("#scoreInputRPC").val();
	data = JSON.parse(data);

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
		if(jQuery.inArray( index, specials_risk_factor) != -1 && value.score != 0){
			$(".libra-improvement .img-"+index).css("display","none");
			$(".libra-keep .img-"+index).css("display","none");
			$(".libra-manage .img-"+index).attr("href","infos-riskfactor.html?riskfactor="+value.id);
			$(".libra-manage .img-"+index+" img.img-picto").attr("src", value.imageUrl.replace("amber", "orange"));
			dataGraph.pie["manage"] += value.score;
		} else if(value.score == 0){
			$(".libra-improvement .img-"+index).css("display","none");
			$(".libra-manage .img-"+index).css("display","none");
			$(".libra-keep .img-"+index).attr("href","infos-riskfactor.html?riskfactor="+value.id);
			$(".libra-keep .img-"+index+" img.img-picto").attr("src", value.imageUrl.replace("amber", "blue"));
			if(jQuery.inArray( index, specials_risk_factor) != -1){
				sum += value.score;
			}
		} else {
			$(".libra-manage .img-"+index).css("display","none");
			$(".libra-keep .img-"+index).css("display","none");
			$(".libra-improvement .img-"+index).attr("href","infos-riskfactor.html?riskfactor="+value.id);
			$(".libra-improvement .img-"+index+" img.img-picto").attr("src", value.imageUrl);
			dataGraph.pie["improvement"] += value.score;
			sum += value.score;
		}
		
	}); 
	$.each(data, function(index, value) {
		if(jQuery.inArray( index, specials_risk_factor) == -1 && value.score != 0){
			dataGraph.bars[index] = value.score/sum*100;
		}
	}); 
	dataGraph.pie["keep"] = 100 - dataGraph.pie["manage"] - dataGraph.pie["improvement"];
	text.rmw = $("#rmw").html();
	text.rfi = $("#rfi").html();
	text.keepthisup = $("#keepthisup").html();
	text.rfCognitive = $("#rf-cognitive").html();
	text.rfMood = $("#rf-mood").html();
	text.rfDiet = $("#rf-diet").html();
	text.rfPressure = $("#rf-pressure").html();
	text.rfObesity = $("#rf-obesity").html();
	text.rfSmoking = $("#rf-smoking").html();
	text.rfCholesterol = $("#rf-cholesterol").html();
	text.rfDiabetes = $("#rf-diabetes").html();
	text.rfKidney = $("#rf-kidney").html();
	text.rfHeart = $("#rf-heart").html();
	text.rfActivity = $("#rf-activity").html();
	text.rfAlchool = $("#rf-alchool").html();
	graph(dataGraph);
	
	var scoreEchoKeep = dataGraph.pie["keep"].toFixed(2);
	var scoreEchoImpr = dataGraph.pie["improvement"].toFixed(2);
	var scoreEchoMana = dataGraph.pie["manage"].toFixed(2);
	if(scoreEchoKeep.split(".")[1] == "00"){
		scoreEchoKeep = scoreEchoKeep.split(".")[0];
	}
	if(scoreEchoImpr.split(".")[1] == "00"){
		scoreEchoImpr = scoreEchoImpr.split(".")[0];
	}
	if(scoreEchoMana.split(".")[1] == "00"){
		scoreEchoMana = scoreEchoMana.split(".")[0];
	}
	
	$("#score-ktu-Num").html("" + scoreEchoKeep + "%");
	$("#score-rfi-Num").html("" + scoreEchoImpr + "%");
	$("#score-rmw-Num").html("" + scoreEchoMana + "%");

	$("#loadingPanel").css("display","none");
	$("#scorePanel").attr("style", "");
	if(typeof(adminReady) == "function"){
		adminReady();
	}
}

function clickFactors(event){
	var title = $(event.target).parents("div").first().children("h4").html();
	$("#sidebar-title").html(title);

}

var data = {};
var text = {};
$(document).ready(function(){
	$("#scoreInputRPC").change(loadCharts);
	$(document).on("click",".circle", clickFactors);
});