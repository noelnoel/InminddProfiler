function load(){
	data = $("#faqInputRPC").val();
	data = JSON.parse(data);
	console.info(data);

	$("#faqDivP").html("");
	for (var i = 0; i < data.length; i++) {
		var h3 = $("<h3></h3>").html(data[i].question).addClass("faqTitle");
		var p = $("<p></p>").html(data[i].answer).css("display", "none").addClass("faqAnswer");
		var div = $("<div></div>").attr("x-id", data[i].id).append(h3).append(p);
		
		$("#faqDivP").append(div);
	};
	$("#faqDiv").attr("style", "");
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
	$(".faqAnswer").css("display", "none");
	var parent = $(event.target).parent().find(".faqAnswer").attr("style", "");
}

function trigeredUserIDByGWT(userID){
	console.info("trigered userID: "+userID);
	ga('create', 'UA-53537839-1', 'auto');
	ga('set', '&uid', userID); // Définir l'ID utilisateur à partir du paramètre user_id de l'utilisateur connecté.
	ga('send', 'pageview');
}

$(document).ready(function(){
	$(document).on("mouseenter",".img-circle", clickFactors);
	$(document).on("click",".faqTitle", goalClickJS);
});