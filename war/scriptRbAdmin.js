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
	var stringOutputGlobal = "";
	for (var i = myMail.length - 1; i >= 0; i--) {
		
		myMail[i] = myMail[i].split(";");
		var stringOutput = "";
		for (var j = 0; j < myMail[i].length; j++) {
			if(j != 0){
				myMail[i][j] = myMail[i][j].split(",");
				stringOutput += "\"";
				for (var k = myMail[i][j].length - 1; k >= 0; k--) {
					stringOutput += myMail[i][j][k];
					if(1 == k){
						stringOutput += " "+ $("#andtranslationRPC").val() +" ";
					} else if(0 != k) {
						stringOutput += ", ";
					}
				};
				stringOutput += "\"";
				if(j != myMail[i].length-1){
					stringOutput += ",";
				}
			} else {
				stringOutput += "\"" + myMail[i][j] + "\",";
			}
		};
		stringOutputGlobal += stringOutput + "\n";
		$("#mailingPanel").append($("<span></span>").html(stringOutput)).append($("<br />"));
	};
	$("#hiddingMenu").css("display", "none");
	$("#mailingPanel").css("display", "");
	$(".bandeauUE").css("display", "none");
	$("hr").css("display", "none");
	console.info(myMail);
	newFile(stringOutputGlobal);
}

function newFile(data) {
    var blob = new Blob([data], {type: 'text/csv'});
    var url  = window.URL.createObjectURL(blob);
    //window.location.assign(url);

    window.URL = window.webkitURL || window.URL;
	var a = document.createElement('a');
	a.download = 'mailing.csv';
	a.href = url;
	a.textContent = 'Download CSV';

	a.dataset.downloadurl = ['text/csv', a.download, a.href].join(':');

	document.body.appendChild(a);

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