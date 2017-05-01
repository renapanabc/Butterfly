//重写Window的alter方法,使用jquery-confirm
window.alert = function (msg) {
	if(!msg){
		msg=" ";
	}
	$.alert({
	    title: '提示',
	    type: 'green',
	    content: msg
	});
}

