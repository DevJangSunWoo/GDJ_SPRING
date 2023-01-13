function addMsgSystem(msg){
	const $h3=$("<h3>").css("textAlign","center").text("======="+msg.msg+"======");
	
	$("#cattingcontainer").append($h3);
}

function printMsg(myId,msg){
	const $p=$("<p>").css("textAlign",(myId==msg.sender?"left":"right"))
	.text(`${msg.sender} : ${msg.msg}`);
		
	$("#cattingcontainer").append($p);
}