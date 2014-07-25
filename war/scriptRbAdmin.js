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