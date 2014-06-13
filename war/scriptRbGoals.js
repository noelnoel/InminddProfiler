function load(){

}

function clickFactors(event){
	$(".text").css("display", "");
	$("#text"+ $(event.target).attr("x-image")).css("display", "block");
}

$(document).ready(function(){
	load();
	$(document).on("mouseenter",".img-circle", clickFactors);
});