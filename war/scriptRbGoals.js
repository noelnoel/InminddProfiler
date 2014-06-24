function load(){
	data = $("#goalsInputRPC").val();
	data = JSON.parse(data);
	data.marginCol = 0;
	if(data.length == 2){
		data.colSpan = 6;
	} else if(data.length == 3){
		data.colSpan = 4;
	} else if(data.length == 4){
		data.colSpan = 3;
	} else if(data.length == 5){
		data.colSpan = 2;
		data.marginCol = 1;
	} else if(data.length == 6){
		data.colSpan = 2;
	}
	console.info(data);

	$("#imagesGoals").html("");
	$("#textGoals").html("");
	for (var i = 0; i < data.length; i++) {
		var h4 = $("<h4></h4>").html("Goal #"+data[i].goalNb);
		var img = $("<img />").attr("src", data[i].image_url).addClass("img-circle").attr("x-image", data[i].goalNb);
		var a = $("<a></a>").attr("href","#").attr("id", "image"+ data[i].goalNb).append(img);
		var div = $("<div class=\"col-md-"+data.colSpan+" col-md-offset-"+data.marginCol+" text-center\"></div>").append(h4).append(a);
		$("#imagesGoals").append(div);


		var h4 = $("<h4></h4>").html(data[i].name);
		var p = $("<p></p>").attr("id", "tranlateText"+ data[i].goalNb).html(data[i].text);
		var button = $("<a></a>").attr("href", "#").addClass("btn btn-success btn-lg btn-block goalsButtons").html("Choose this program").attr("id", "buttonGoal"+data[i].goalNb);
		var div = $("<div class=\"col-md-"+data.colSpan+" col-md-offset-"+data.colSpan*i+" text\"></div>").attr("x-goal", data[i].goalNb).attr("id","text"+data[i].goalNb).append(h4).append(p).append($("<br />")).append(button);
		$("#textGoals").append(div);
	};
	$("#imagesGoals").attr("style", "");
	$("#loadingPanel").css("display", "none");
}

function trigeredByGWT() {
	console.info("trigered!");
	load();
}

function clickFactors(event){
	$(".text").css("display", "");
	$("#text"+ $(event.target).attr("x-image")).css("display", "block");
}

function goalClickJS(event){
	var parent = $(event.target).parent();
	var goalNb = parent.attr("x-goal");
	var textarea = parent.find("textarea").val();
	window.goalClick(goalNb, textarea);
}

$(document).ready(function(){
	$(document).on("mouseenter",".img-circle", clickFactors);
	$(document).on("click",".goalsButtons", goalClickJS);
});