function load(){
	data = $("#expertsInputRPC").val();
	data = JSON.parse(data);
	console.info(data);

	$("#expertsDiv tbody").html("");
	var country = "";
	for (var i = 0; i < data.length; i++) {
		if(country != data[i].country){
			var tr = $("<tr></tr>");
			var td = $("<td></td>").attr("colspan", "2").html("<h3>"+ data[i].country + "</h3>");
			tr.append(td);
			$("#expertsDiv tbody").append(tr);
			country = data[i].country;
		}

		var tr = $("<tr></tr>");
		var td = $("<td></td>").addClass("staffPic");
		if(data[i].image_url != ""){
			var img = $("<img />").attr("src", data[i].image_url);
			td.append(img);
		}
		tr.append(td);
		td = $("<td></td>").html(data[i].description);
		tr.append(td);
		$("#expertsDiv tbody").append(tr);
	};
	$("#expertsDiv").attr("style", "");
	$("#askExpertsDiv").attr("style", "");
	$("#loadingPanel").css("display", "none");
}

function trigeredByGWT() {
	console.info("trigered!");
	load();
}

$(document).ready(function(){
	//$(document).on("mouseenter",".img-circle", clickFactors);
	//$(document).on("click",".goalsButtons", goalClickJS);
});