//点击菜单，匹配事件，填充frame
function mainMenusClick(action,url){
	$("#main_frame").attr('src','');
	if (action=="page") {
		if(url||url==""){
			$("#main_frame").attr('src',url);
		}
	}
}