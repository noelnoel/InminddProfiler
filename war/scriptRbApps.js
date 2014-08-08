function load(){
	data = $("#appsInputRPC").val();
	data = JSON.parse(data);
	console.info(data);

	$("#appsDiv tbody").html("");
	var category = "";
	for (var i = 0; i < data.length; i++) {
		if(category != data[i].category){
			var tr = $("<tr></tr>");
			var td = $("<td></td>").attr("colspan", "2").html("<h3>"+ data[i].category + "</h3>");
			tr.append(td);
			$("#appsDiv tbody").append(tr);
			category = data[i].category;
		}

		var tr = $("<tr></tr>");
		var td = $("<td></td>").addClass("appsPic");
		if(data[i].logo_url != ""){
			var img = $("<img />").attr("src", data[i].logo_url);
			td.append(img);
		}
		tr.append(td);
		var h4 = $("<h4></h4>").html(data[i].name);
		var p = $("<p></p>").html(data[i].description);
		td = $("<td></td>").append(h4).append(p);
		tr.append(td);
		$("#appsDiv tbody").append(tr);
	};
	$("#appsDiv").attr("style", "");
	$("#appsIntroDiv").attr("style", "");
	$("#loadingPanel").css("display", "none");
}

function trigeredByGWT() {
	console.info("trigered!");
	load();
}

function trigeredUserIDByGWT(userID){
	console.info("trigered userID: "+userID);
	ga('create', 'UA-53537839-1', 'auto');
	ga('set', '&uid', userID); // Définir l'ID utilisateur à partir du paramètre user_id de l'utilisateur connecté.
	ga('send', 'pageview');
}

$(document).ready(function(){
	//$(document).on("click","#askExperts8", mailClickJS);
});