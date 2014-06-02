//(function($) {
//	$("#roleId").val("");
//	$("#error_role_right").html("");
//	$("#save_role_right").bind('click',function(e){
function saveRoleRight(){
//		e.preventDefault();
		if($("#roleId").val()==null){
			alert("角色不能为空");
			return;
		}
		
		var moduleIds ="";
		$("input[name='moduleIds']:checked").each(function(){
			moduleIds+=","+$(this).val();
			
		});
		$.ajax( {
			url: "rolerightaction_save",
			type: "POST",
			data: {"moduleIds" : moduleIds.substring(1),"roleId":$("#roleId").val()},
			dataType: "json",
			traditional: true,
			success:function(data){
				if(data.actionStatus=="success"){
					alert("保存授权成功！");
				}else if(data.actionStatus=="error"){
					alert(data.messages.error_role_right);
				}
				
				
			},
			error:function(data){
				
			}
		
		});
//	});
}

//	$("#query_role_right").bind('click',function(e){
//		e.preventDefault();
function queryRoleRight(){
		if($("#roleId").val()==null){
			alert("角色不能为空");
			return;
		}
//		Window.location.href="http://www.baidu.com";
//		window.location.href="http://www.baidu.com";
		$("body").load("rolerightaction_query?roleId="+$("#roleId").val(),function(){});
//	});
}
		
//	
//	$("#roleId").dblclick(function(e){
//		e.preventDefault();
//		
//		$("body").load("rolerightaction_query?roleId="+$(this).val(),function(){});
//		
//	});
	
	
//})(jQuery);