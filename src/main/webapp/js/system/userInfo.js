function refresh(){
	$("#table").bootstrapTable('refresh');
}

function queryParams(params) {
	var params = {
		limit:params.limit,   //页面大小
		offset:params.offset,  //页码
		keyWords:$("#keyWords").val(),
    };
    return params;
}
          

function addUser(){
	 $.dialog({
         title: '添加用户',
         content: 'url:views/system/userInfoDetail.jsp?operaType=add',
         animation: 'zoom',
         columnClass: 'medium',
         closeAnimation: 'scale',
         backgroundDismiss: true,
     });
}

function enableUser(){
	
}

function disableUser(){
	
}

function deleteUser(){
	
}
 