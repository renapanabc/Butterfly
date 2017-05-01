$("#form-username").keydown(function(){
	$('#upwd').focus();
});

$("#form-password").keydown(function(){
	if (window.event.keyCode == 13) {
		login();
	}
});

$("#login_btn").click(function(){
	login();
});

var time=1;//不要搞事儿
function login() {
	var uname = $('#form-username').val();
	var upwd = $('#form-password').val();
	if (!uname) {
		alert("请输入用户名");
		return;
	}if (!upwd) {
		alert("请输入密码");
		return;
	}
	
	$.ajax({
		type : 'post', 
		dataType:"json",  
		async: false, 
		url : encodeURI('userLogin/loginCheck'), //请求地址
		data:{"uname":uname,"upwd":upwd},
		success : function(data) {
			if(data.success==true){
				location.href="views/system/main.jsp";
			}else{
				alert(data.msg);
				if(time<=2){
					time=time+1;
				}else{
					alert("<img src='img/login/biegaoshier.jpg' />");
					time=1;
				}
			}
		}
	});
}


function loginKeyDown(name) {
	if (window.event.keyCode == 13) {
		if("uname"==name){
			$('#upwd').focus();
		}else{
			login();
		}
	}
}

