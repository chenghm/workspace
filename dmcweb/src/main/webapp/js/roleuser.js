$(function(){
	$.getUrlParam = function(name)
	{
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r!=null) return unescape(r[2]); return null;
	}
	// 配置jqGrid组件
	$("#gridTable").jqGrid({
		url: "roleuseraction_find?roleId="+$.getUrlParam('roleId')  ,
		datatype: "json",
		mtype: "GET",
		height: 380,
		//width: 800,
		colModel: [
		    // {name:"id",key:true,index:"id",label:"编码",search:true,searchtype:"integer",sorttype:"int",stype:"text",searchrules:{required:true, integer:true}},  
		      {name:"role.code",index:"code",label:"角色编码",sortable:false},
		      {name:"role.name",index:"name",label:"角色名称",sortable:false},
		      {name:"user.username",index:"username",label:"用户名",sortable:false},
		      {name:"user.chineseName",index:"chineseName",label:"中文名",sortable:false}
		     // {name:"role",index:"role",label:"角色",sortable:false}
		      //,
		      //{name:"createdTime",index:"createdTime",label:"创建时间",formatter:'date',searchtype:"datetime", formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'},sortable:false,stype:'text', searchoptions:{dataInit:dateTimePick, attr:{title:'Select Date'}},searchrules:{required:true} }
		],
		viewrecords: true,
		rowNum : 50,
		rowList : [ 50,100,200 ],
		prmNames: {search: "search"},
		jsonReader: {
			root:"gridModel",
			records: "record",
			repeatitems : false
		},
		pager: "#gridPager",
		caption: "角色用户列表",
		hidegrid: false,
		shrikToFit: true,
		multiselect: true
	});
	
	
	 // 配置对话框  
    $("#consoleDlg").dialog({  
        autoOpen: false,      
        modal: true,    // 设置对话框为模态（modal）对话框  
        resizable: true,      
        width: 650,  
        buttons: {  // 为对话框添加按钮  
            "取消": function() {$("#consoleDlg").dialog("close");},  
            "创建": addrole,  
            "保存": updaterole,  
            "删除": deleteRoleUser  
        }  ,
		close : function() {
		 	 $("#error_role_code").html("");
			 $("#error_role_name").html(""); 
		}
    });  
	 
//    $("#gridTable").jqGrid("navGrid", "#gridPager", {  
//        addfunc : openDialog4Adding,    // (1) 点击添加按钮，则调用openDialog4Adding方法  
//        editfunc : openDialog4Updating, // (2) 点击编辑按钮，则调用openDialog4Updating方法  
//        delfunc : deleteRole,  // (3) 点击删除按钮，则调用openDialog4Deleting方法  
//        alerttext : "请选择需要操作的数据行!"  // (4) 当未选中任何行而点击编辑、删除、查看按钮时，弹出的提示信息  
//    },
//    {}, // prmEdit  
//    {}, // prmAdd  
//    {}, // prmDel  
//    {       // prmSearch  
//      //  caption: "查找",  
//       // Find: "Let's go!",  
//   	    multipleSearch : true  ,
//        closeAfterSearch: true  
//    },  
//    {}  // prmView
//    
//    );
 //   $("#gridTable").jqGrid('filterToolbar',{stringResult: true, searchOnEnter: true,defaultSearch: 'cn', ignoreCase: true}); 
    
});  
 var openDialog4Adding = function() {  
    var consoleDlg = $("#consoleDlg");  
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
    consoleDlg.find("input").removeAttr("disabled").val("");  
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
    dialogButtonPanel.find("button:contains('创建')").show();  
    consoleDlg.dialog("option", "title", "创建新角色").dialog("open");  
};  
var openDialog4Updating = function() {  
    var consoleDlg = $("#consoleDlg");  
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
      
    consoleDlg.find("input").removeAttr("disabled");  
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
    dialogButtonPanel.find("button:contains('保存')").show();  
    consoleDlg.dialog("option", "title", "修改角色信息");  
      
    loadSelectedRowData();  
}  
var openDialog4Deleting = function() {  
    var consoleDlg = $("#consoleDlg");  
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
      
    consoleDlg.find("input").attr("disabled", true);  
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
    dialogButtonPanel.find("button:contains('删除')").show();  
    consoleDlg.dialog("option", "title", "删除角色");  
      
    loadSelectedRowData();  
}  
var loadSelectedRowData = function() {    
    var selectedRowId = $("#gridTable").jqGrid("getGridParam", "selarrrow");  
    if (selectedRowId.length==0 || selectedRowId.length >1) {  
        alert("请先选择一条需要编辑的行!");  
        return false;  
    } else {  
        /* var params = {  
        		
            "role.id" : selectedRowId  
        };  */ 
        var actionUrl = "roleaction_view?role.id="+selectedRowId;  
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
                var rowData = data.role;  
                var consoleDlg = $("#consoleDlg");  
                consoleDlg.find("#id").val(rowData.id);  
                consoleDlg.find("#code").val(rowData.code);  
                consoleDlg.find("#name").val(rowData.name);  
                consoleDlg.find("#descn").val(rowData.descn);  
             
                  
                // 根据新载入的数据将表格中的对应数据行一并更新一下  
                var dataRow = {  
                        id : rowData.id,  
                        code : rowData.code,  
                        name : rowData.name,  
                        descn : rowData.descn
                    };  
                  
                $("#gridTable").jqGrid("setRowData", data.role.id, dataRow);  
                      
                // 打开对话框  
                consoleDlg.dialog("open");  
            }  
        });  
          
    }  
};  
var addrole = function() {  
	 $("#error_role_code").html("");
	 $("#error_role_name").html(""); 
    var consoleDlg = $("#consoleDlg");  
          
    var pCode = $.trim(consoleDlg.find("#code").val());  
    var pName = $.trim(consoleDlg.find("#name").val());  
    var pDescn = $.trim(consoleDlg.find("#descn").val());  
      
    var params = {  
      /*   "role.code" : pcode,  
        "role.name" : pname,  
        "role.email" : pEmail,  
        "role.phone" : pPhone  */
    };  
      
    var actionUrl = "roleaction_create"  
      
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
                    id : data.role.id,   // 从Server端得到系统分配的id  
                    code : pCode,  
                    name : pName,  
                    descn : pDescn
                };  
                  
                var srcrowid = $("#gridTable").jqGrid("getGridParam", "selrow");  
                  
                if(srcrowid) {  
                    $("#gridTable").jqGrid("addRowData", data.role.id, dataRow, "before", srcrowid);  
                } else {  
                    $("#gridTable").jqGrid("addRowData", data.role.id, dataRow, "first");  
                }  
                consoleDlg.dialog("close");  
                  
                alert("角色添加操作成功!");  
                  
            } else {  
                alert("添加操作失败!");
                $.each(data.messages, function(index, obj) {
					$("#" + index).html(obj);
				});
                
                
            }  
        }  
    });  
};  
 var updaterole = function() {  
	 $("#error_role_code").html("");
	 $("#error_role_name").html(""); 
    var consoleDlg = $("#consoleDlg");  
      
    var pId = $.trim(consoleDlg.find("#selectId").val());  
    var pCode = $.trim(consoleDlg.find("#code").val());  
    var pName = $.trim(consoleDlg.find("#name").val());  
    var pDescn = $.trim(consoleDlg.find("#descn").val());  
    
    var params = {  
        "role.id" : pId,  
        "role.code" : pCode,  
        "role.name" : pName,  
        "role.descn" : pDescn 
        
    };  
    var actionUrl = "roleaction_update";  
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
                    id : data.role.id,  
                    code : pCode,  
                    name : pName,  
                    descn : pDescn
                };  
                $("#gridTable").jqGrid("setRowData", data.role.id, dataRow, {color:"#FF0000"});  
                  
                alert("角色信息更新成功!");  
                  
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

var deleteRoleUser = function() {  
    var selectedRowId = $("#gridTable").jqGrid("getGridParam", "selarrrow");  
    if (selectedRowId.length==0 ) {  
        alert("请先选择需要删除的记录!");  
        return false;  
    } 
    var actionUrl = "roleuseraction_delete?ids="+selectedRowId;  
    $.ajax({  
    	type:"post",
        url : actionUrl,  
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
                alert("删除角色用户成功!");  
            } else {  
                alert(data.ajaxResult);  
            }  
        }  
    });  
};  

var loadUser=function(){
	var selectedRowId = $("#gridTable").jqGrid("getGridParam", "selarrrow");  
    if (selectedRowId.length==0 || selectedRowId.length >1) {  
        alert("请先选择一条记录!");  
        return false;  
    } else {  
        /* var params = {  
        		
            "role.id" : selectedRowId  
        };  */ 
        var actionUrl = "roleuseraction_find?roleId="+selectedRowId;  
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
                var rowData = data.role;  
                var consoleDlg = $("#consoleDlg");  
                consoleDlg.find("#id").val(rowData.id);  
                consoleDlg.find("#code").val(rowData.code);  
                consoleDlg.find("#name").val(rowData.name);  
                consoleDlg.find("#descn").val(rowData.descn);  
             
                  
                // 根据新载入的数据将表格中的对应数据行一并更新一下  
                var dataRow = {  
                        id : rowData.id,  
                        code : rowData.code,  
                        name : rowData.name,  
                        descn : rowData.descn
                    };  
                  
                $("#gridTable").jqGrid("setRowData", data.role.id, dataRow);  
                      
                // 打开对话框  
                consoleDlg.dialog("open");  
            }  
        });  
          
    }  
	
}

//});  
datePick = function(elem)
{
   jQuery(elem).datepicker({ dateFormat: 'yy-mm-dd HH24:mm:ss' });
}

dateTimePick = function(elem)
{
   jQuery(elem).datetimepicker({ dateFormat: 'yy-mm-dd',timeFormat: "HH:mm:ss" });
}

