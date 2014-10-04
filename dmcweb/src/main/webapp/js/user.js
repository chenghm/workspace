$(function(){
	$('#userNode').multiselect2side({
		selectedPosition : 'right',
		moveOptions : false,
		labelsx : '可选择节点',
		labeldx : '已选择节点',
		autoSort : true,
		autoSortAvailable : true
	});
	// 配置jqGrid组件
	$("#gridTable").jqGrid({
		url: "useraction_find",
		datatype: "json",
		mtype: "GET",
		height : 400,
		//width: 800,
		colModel: [
		    // {name:"id",key:true,index:"id",label:"编码",search:true,searchtype:"integer",sorttype:"int",stype:"text",searchrules:{required:true, integer:true}},  
		      {name:"username",index:"username",label:"用户名",sortable:false},
		      {name:"chineseName",index:"chineseName",label:"姓名",sortable:false},
		      {name:"userType",index:"userType",label:"用户类型",sortable:false,formatter : 'select',
					edittype : 'select',
					editoptions : {
						value : '1:管理员;0:普通用户'
					},
					stype : 'select',
					searchoptions : {
						sopt : [ "eq", "ne" ],
						value : '1:管理员;0:普通用户'
					}},
			  {name:"node",index:"node",label:"浏览节点",sortable:false,search:false},
		      {name:"email",index:"email",label:"电子邮箱",sortable:false},
		      {name:"phone",index:"phone",label:"电话",sortable:false},
		      {name:"descn",index:"descn",label:"备注",sortable:false}
		     // {name:"role",index:"role",label:"角色",sortable:false}
		      //,
		      //{name:"createdTime",index:"createdTime",label:"创建时间",formatter:'date',searchtype:"datetime", formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'},sortable:false,stype:'text', searchoptions:{dataInit:dateTimePick, attr:{title:'Select Date'}},searchrules:{required:true} }
		],
		viewrecords: true,
		rowNum: 50,
		rowList: [50,100,200],
		prmNames: {search: "search"},
		jsonReader: {
			root:"gridModel",
			records: "record",
			repeatitems : false
		},
		pager: "#gridPager",
		caption: "用户列表",
		hidegrid: false,
		shrikToFit: true,
		multiselect: true
	});
	
	
	 // 配置对话框  
    $("#consoleDlg").dialog({  
        autoOpen: false,      
        modal: true,    // 设置对话框为模态（modal）对话框  
        resizable: true,      
        width: 540,  
        buttons: {  // 为对话框添加按钮  
            "取消": function() {$("#consoleDlg").dialog("close");},  
            "创建": addUser,  
            "保存": updateUser,  
            "删除": deleteUser  
        }  ,
		close : function() {
		 	 $("#error_user_chineseName").html("");
			 $("#error_user_username").html(""); 
			 $("#error_user_password").html("");
			 $("#error_user_confirmPassword").html(""); 
			 
			 $("#nodeIdsms2side__sx").empty();
			 $("#nodeIdsms2side__dx").empty();
		}
    });  
	 
    $("#gridTable").jqGrid("navGrid", "#gridPager", {  
        addfunc : openDialog4Adding,    // (1) 点击添加按钮，则调用openDialog4Adding方法  
        editfunc : openDialog4Updating, // (2) 点击编辑按钮，则调用openDialog4Updating方法  
        delfunc : deleteUser,  // (3) 点击删除按钮，则调用openDialog4Deleting方法  
        alerttext : "请选择需要操作的数据行!"  // (4) 当未选中任何行而点击编辑、删除、查看按钮时，弹出的提示信息  
    },
    {}, // prmEdit  
    {}, // prmAdd  
    {}, // prmDel  
    {       // prmSearch  
      //  caption: "查找",  
       // Find: "Let's go!",  
   	    multipleSearch : true  ,
        closeAfterSearch: true  
    },  
    {}  // prmView
    
    );
 //   $("#gridTable").jqGrid('filterToolbar',{stringResult: true, searchOnEnter: true,defaultSearch: 'cn', ignoreCase: true}); 
    
});  
 var openDialog4Adding = function() {  
    var consoleDlg = $("#consoleDlg");  
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
    consoleDlg.find("input").removeAttr("disabled").val("");  
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
    dialogButtonPanel.find("button:contains('创建')").show();  
    consoleDlg.dialog("option", "title", "创建新用户").dialog("open");  
    
    
    
    loadaddselect();
};  
var openDialog4Updating = function() {  
    var consoleDlg = $("#consoleDlg");  
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
      
    consoleDlg.find("input").removeAttr("disabled");  
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
    dialogButtonPanel.find("button:contains('保存')").show();  
    consoleDlg.dialog("option", "title", "修改用户信息");  
      
    loadSelectedRowData();  
    loadeditselect( $("#gridTable").jqGrid("getGridParam", "selarrrow"));
}  ;
var openDialog4Deleting = function() {  
    var consoleDlg = $("#consoleDlg");  
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
      
    consoleDlg.find("input").attr("disabled", true);  
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
    dialogButtonPanel.find("button:contains('删除')").show();  
    consoleDlg.dialog("option", "title", "删除用户");  
      
    loadSelectedRowData();  
} ; 
var loadSelectedRowData = function() {    
    var selectedRowId = $("#gridTable").jqGrid("getGridParam", "selarrrow");  
    if (selectedRowId.length==0 || selectedRowId.length >1) {  
        alert("请先选择一条需要编辑的行!");  
        return false;  
    } else {  
        /* var params = {  
        		
            "user.id" : selectedRowId  
        };  */ 
        var actionUrl = "useraction_view?user.id="+selectedRowId;  
        // 从Server读取对应ID的JSON数据  
        $.ajax( {  
        	type : 'post',
            url : actionUrl,  
           // data : params,  
            dataType : "json",  
            cache : false,  
            error : function(textStatus, errorThrown) {  
                alert("系统ajax交互错误: " + textStatus);  
            },  
            success : function(data, textStatus) {  
                // 如果读取结果成功，则将信息载入到对话框中                   
                var rowData = data.user;  
                var consoleDlg = $("#consoleDlg");  
                consoleDlg.find("#id").val(rowData.id);  
                consoleDlg.find("#chineseName").val(rowData.chineseName);  
                consoleDlg.find("#userType").val(rowData.userType);  
                consoleDlg.find("#password").val(rowData.password);  
                consoleDlg.find("#confirmPassword").val(rowData.password);
                consoleDlg.find("#username").val(rowData.username);  
                consoleDlg.find("#email").val(rowData.email);  
                consoleDlg.find("#phone").val(rowData.phone);  
                consoleDlg.find("#descn").val(rowData.descn);  
             
                  
                // 根据新载入的数据将表格中的对应数据行一并更新一下  
                var dataRow = {  
                        id : rowData.id,  
                        chineseName : rowData.chineseName,  
                        username : rowData.username,  
                        email : rowData.email,  
                        phone : rowData.phone,  
                        descn : rowData.descn
                    };  
                  
                $("#gridTable").jqGrid("setRowData", data.user.id, dataRow);  
                      
                // 打开对话框  
                consoleDlg.dialog("open");  
            }  
        });  
          
    }  
};  
var addUser = function() {  
	 $("#error_user_chineseName").html("");
	 $("#error_user_username").html(""); 
	 $("#error_user_password").html("");
	 $("#error_user_confirmPassword").html("");
    var consoleDlg = $("#consoleDlg");  
          
    var pChineseName = $.trim(consoleDlg.find("#chineseName").val());  
    var pUsername = $.trim(consoleDlg.find("#username").val());  
    var pEmail = $.trim(consoleDlg.find("#email").val());  
    var pPhone = $.trim(consoleDlg.find("#phone").val());  
    var pDescn = $.trim(consoleDlg.find("#descn").val());  
    var pUserType = $.trim(consoleDlg.find("#userType").val());    
    var selected = $("#nodeIdsms2side__dx").find("option");
    var pNode="";
    $.each(selected,function(){
    	pNode+=","+$(this).text();
    });
    if(pNode!=""){
    	pNode = pNode.substring(1);
    }
    var params = {  
      /*   "user.chineseName" : pChineseName,  
        "user.username" : pUsername,  
        "user.email" : pEmail,  
        "user.phone" : pPhone  */
    };  
      
    var actionUrl = "useraction_create";  
      
    $.ajax( {  
    	type : 'post',
        url : actionUrl,  
        data :  $("#consoleForm").serialize(),  
        dataType : "json",  
        cache : false,  
        error : function(textStatus, errorThrown) {  
            alert("系统ajax交互错误: " + textStatus);  
        },  
        success : function(data, textStatus) {  
            if(data.ajaxResult == "success") {  
                var dataRow = {  
                    id : data.user.id,   // 从Server端得到系统分配的id  
                    chineseName : pChineseName,  
                    username : pUsername,
                    userType:pUserType,
                    email : pEmail,  
                    phone : pPhone,
                    descn : pDescn,
                    node  : pNode
                    };  
                  
                var srcrowid = $("#gridTable").jqGrid("getGridParam", "selrow");  
                  
                if(srcrowid) {  
                    $("#gridTable").jqGrid("addRowData", data.user.id, dataRow, "before", srcrowid);  
                } else {  
                    $("#gridTable").jqGrid("addRowData", data.user.id, dataRow, "first");  
                }  
                consoleDlg.dialog("close");  
                  
                alert("用户添加操作成功!");  
                  
            } else {  
                alert("添加操作失败!");
                $.each(data.messages, function(index, obj) {
					$("#" + index).html(obj);
				});
                
                
            }  
        }  
    });  
};  
 var updateUser = function() {  
	 $("#error_user_chineseName").html("");
	 $("#error_user_username").html(""); 
	 $("#error_user_password").html("");
	 $("#error_user_confirmPassword").html("");
    var consoleDlg = $("#consoleDlg");  
      
    var pId = $.trim(consoleDlg.find("#selectId").val());  
    var pChineseName = $.trim(consoleDlg.find("#chineseName").val());  
    var pUsername = $.trim(consoleDlg.find("#username").val());  
    var pEmail = $.trim(consoleDlg.find("#email").val());  
    var pPhone = $.trim(consoleDlg.find("#phone").val());  
    var pDescn = $.trim(consoleDlg.find("#descn").val());  
    var pUserType = $.trim(consoleDlg.find("#userType").val());  
    var selected = $("#nodeIdsms2side__dx").find("option");
    var pNode="";
    $.each(selected,function(){
    	pNode+=","+$(this).text();
    });
    if(pNode!=""){
    	pNode = pNode.substring(1);
    }
    var params = {  
        "user.id" : pId,  
        "user.chineseName" : pChineseName,  
        "user.username" : pUsername,  
        "user.email" : pEmail,  
        "user.phone" : pPhone,
        "user.descn" : pDescn 
        
    };  
    var actionUrl = "useraction_update";  
    $.ajax( {  
    	type : 'post',
        url : actionUrl,  
        data :  $("#consoleForm").serialize(),  
        dataType : "json",  
        cache : false,  
        error : function(textStatus, errorThrown) {  
            alert("系统ajax交互错误: " + textStatus);  
        },  
        success : function(data, textStatus) {  
            if (data.ajaxResult == "success") {  
                var dataRow = {  
                    id : data.user.id,  
                    chineseName : pChineseName,  
                    username : pUsername,  
                    userType: pUserType,  
                    email : pEmail,  
                    phone : pPhone,
                    descn : pDescn,
                    node  : pNode
                };  
                $("#gridTable").jqGrid("setRowData", data.user.id, dataRow, {color:"#FF0000"});  
                  
                alert("用户信息更新成功!");  
                  
                consoleDlg.dialog("close");  
                  
            } else {  
                alert("修改操作失败!");  
                $.each(data.messages, function(index, obj) {
					$("#" + index).html(obj);
				});
            }  
        }  
    });  
};  

var deleteUser = function() {  
    //var consoleDlg = $("#consoleDlg");  
      
    //var pId = $.trim(consoleDlg.find("#selectId").val());  
   // var params = {  
   //     "user.id" : pId  
    //};  
    var selectedRowId = $("#gridTable").jqGrid("getGridParam", "selarrrow");  
    if (selectedRowId.length==0 ) {  
        alert("请先选择需要删除的记录!");  
        return false;  
    } 
    var actionUrl = "useraction_delete?ids="+selectedRowId;  
    $.ajax({  
    	type:"post",
        url : actionUrl,  
      //  data : params,  
        dataType : "json",  
        cache : false,  
        error : function(textStatus, errorThrown) {  
            alert("系统ajax交互错误: " + textStatus);  
        },  
        success : function(data, textStatus) {  
            if (data.ajaxResult == "success") {  
            	var len = selectedRowId.length;
            	for(var i=0;i<len;i++){
               	 $("#gridTable").jqGrid("delRowData", selectedRowId[0]);  
                }
                  
               // consoleDlg.dialog("close");  
                alert("用户删除成功!");  
            } else {  
                alert(data.ajaxResult);  
            }  
        }  
    });  
};  
//});  
datePick = function(elem)
{
   jQuery(elem).datepicker({ dateFormat: 'yy-mm-dd HH24:mm:ss' });
};

dateTimePick = function(elem)
{
   jQuery(elem).datetimepicker({ dateFormat: 'yy-mm-dd',timeFormat: "HH:mm:ss" });
};

//load select on add popup
function loadaddselect() {
		/*$('#userNode').multiselect2side({
			selectedPosition : 'right',
			moveOptions : false,
			labelsx : '可选择节点',
			labeldx : '已选择节点',
			autoSort : true,
			autoSortAvailable : true
		});*/

		$.ajax({
			url : "nodeaction_findAll",
			type : 'GET',
			dataType : 'json',
			success : function(data) {
				$.each(data.nodes, function(i, node) {
					$('#userNode').multiselect2side('addOption', {
						name : node.number,
						value : node.id,
						selected : false
					});
				});
			}
		});
	};
	


// load select on edit popup
function loadeditselect(userId) {
	/*$('#userNode').multiselect2side({
		selectedPosition : 'right',
		moveOptions : false,
		labelsx : '可选择节点',
		labeldx : '已选择节点',
		autoSort : true,
		autoSortAvailable : true
	});*/

	$.ajax({
		url : "nodeaction_findByUser?userId=" + userId,
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			$.each(data.nodes, function(i, node) {
				$('#userNode').multiselect2side('addOption', {
					name : node.number,
					value : node.id,
					selected : true
				});
			});
		}
	});

	$.ajax({
		url : "nodeaction_findNoUser?userId=" + userId,
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			$.each(data.nodes, function(i, node) {
				$('#userNode').multiselect2side('addOption', {
					name : node.number,
					value : node.id,
					selected : false
				});
			});
		}
	});
}
