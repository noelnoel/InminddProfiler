function loadUsers(){
	data = $("#userInputRPC").val();
	data = data.replace(/\t/g, "");
	data = JSON.parse(data);
	console.info(data);

	$("#usersSelect").html("");
	for (var i = 0; i < data.length; i++) {
		var option = $("<option></option>").attr("value", data[i]).html(data[i]);
		$("#usersSelect").append(option);
	};
	$("#choosePanel").attr("style", "text-align:center;margin-top:150px");
	$("#loadingPanel").css("display", "none");
}

function trigeredUserByGWT() {
	console.info("trigered!");
	loadUsers();
}

function trigeredMailingByGWT(){
	console.info("trigered for mailing");
	console.info($("#mailingInputRPC").val());
	myMail = $("#mailingInputRPC").val();
	myMail = myMail.split("|");
	for (var i = myMail.length - 1; i >= 0; i--) {
		$("#mailingPanel").append($("<p></p>").html(myMail[i]));
		myMail[i] = myMail[i].split(";");
		if(myMail[i].length > 1){
			myMail[i][1] = myMail[i][1].split(",");
		}
	};
	$("#hiddingMenu").css("display", "none");
	$("#mailingPanel").css("display", "");
	$(".bandeauUE").css("display", "none");
	$("hr").css("display", "none");
	console.info(myMail);
}

function goalClickJS(event){
	window.userIdClick($("#usersSelect").val());
	$("#choosePanel").css("display", "none");
	$("#loadingPanel").css("display", "");
}

function adminReady(){
	$("#hiddingMenu").css("display", "none");
	$('a:visible').contents().unwrap();
	window.setTimeout(function(){
      window.print();
    }, 1000);
	
}

$(document).ready(function(){
	isAdmin = true;
	$(document).on("click","#userIdButton", goalClickJS);
});