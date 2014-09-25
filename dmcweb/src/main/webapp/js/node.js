$(function(){
	// 配置jqGrid组件
	$("#gridTable").jqGrid({
		url: "nodeaction_find",
		datatype: "json",
		mtype: "GET",
		height : 400,
		//width: 800,
		colModel: [
		    // {name:"id",key:true,index:"id",label:"编码",search:true,searchtype:"integer",sorttype:"int",stype:"text",searchrules:{required:true, integer:true}},  
		      {name:"number",index:"number",label:"节点号",sortable:false},
		      {name:"fileSize",index:"fileSize",label:"文件大小",sortable:false},
		      {name:"catchType",index:"catchType",label:"捕获类型",sortable:false},
		      {name:"remark",index:"remark",label:"备注",sortable:false}
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
		caption: "节点列表",
		hidegrid: false,
		shrikToFit: true,
		multiselect: true
	});
	
	
	 // 配置对话框  
    $("#consoleDlg").dialog({  
        autoOpen: false,      
        modal: true,    // 设置对话框为模态（modal）对话框  
        resizable: true,      
        width: 600,  
        buttons: {  // 为对话框添加按钮  
            "取消": function() {$("#consoleDlg").dialog("close");},  
            "创建": addNode,  
            "保存": updateNode,  
            "删除": deleteNode  
        }  ,
		close : function() {
		 	 $("#error_node_number").html("");
			 $("#error_node_fileSize").html(""); 
			 $("#number").val("");
			 $("#fileSize").val("");
			 $("#remark").val("");
			 $("input:checked").removeAttr("checked");
		}
    });  
	 
    $("#gridTable").jqGrid("navGrid", "#gridPager", {  
        addfunc : openDialog4Adding,    // (1) 点击添加按钮，则调用openDialog4Adding方法  
        editfunc : openDialog4Updating, // (2) 点击编辑按钮，则调用openDialog4Updating方法  
        delfunc : deleteNode,  // (3) 点击删除按钮，则调用openDialog4Deleting方法  
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
    consoleDlg.dialog("option", "title", "创建新节点").dialog("open");  
};  
var openDialog4Updating = function() {  
    var consoleDlg = $("#consoleDlg");  
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
      
    consoleDlg.find("input").removeAttr("disabled");  
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
    dialogButtonPanel.find("button:contains('保存')").show();  
    consoleDlg.dialog("option", "title", "修改节点信息");  
      
    loadSelectedRowData();  
}  ;
var openDialog4Deleting = function() {  
    var consoleDlg = $("#consoleDlg");  
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
      
    consoleDlg.find("input").attr("disabled", true);  
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
    dialogButtonPanel.find("button:contains('删除')").show();  
    consoleDlg.dialog("option", "title", "删除节点");  
      
    loadSelectedRowData();  
} ; 
var loadSelectedRowData = function() {    
    var selectedRowId = $("#gridTable").jqGrid("getGridParam", "selarrrow");  
    if (selectedRowId.length==0 || selectedRowId.length >1) {  
        alert("请先选择一条需要编辑的行!");  
        return false;  
    } else {  
        /* var params = {  
        		
            "node.id" : selectedRowId  
        };  */ 
        var actionUrl = "nodeaction_view?node.id="+selectedRowId;  
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
                var rowData = data.node;  
                var consoleDlg = $("#consoleDlg");  
                consoleDlg.find("#id").val(rowData.id);  
                consoleDlg.find("#number").val(rowData.number);  
                consoleDlg.find("#fileSize").val(rowData.fileSize);  
                consoleDlg.find("#remark").val(rowData.remark); 
                if(rowData.isHttp==1){
                	$("#isHttp").attr("checked",'true');  
                }
                if(rowData.isSearch==1){
                	$("#isSearch").attr("checked",'true');  
                }
                if(rowData.isThrough==1){
                	$("#isThrough").attr("checked",'true');  
                }
                if(rowData.isCommerce==1){
                	$("#isCommerce").attr("checked",'true');  
                }
                if(rowData.isSocial==1){
                	$("#isSocial").attr("checked",'true');  
                }
                if(rowData.isPassword==1){
                	$("#isPassword").attr("checked",'true');  
                }
                if(rowData.isFtp==1){
                	$("#isFtp").attr("checked",'true');  
                }
                if(rowData.isNetdisk==1){
                	$("#isNetdisk").attr("checked",'true');  
                }
                if(rowData.isControl==1){
                	$("#isControl").attr("checked",'true');  
                }
                if(rowData.isChat==1){
                	$("#isChat").attr("checked",'true');  
                }
                if(rowData.isMail==1){
                	$("#isMail").attr("checked",'true');  
                }
                
                
             
                  
                // 根据新载入的数据将表格中的对应数据行一并更新一下  
//                var dataRow = {  
//                        id : rowData.id,  
//                        chineseName : rowData.chineseName,  
//                        nodename : rowData.nodename,  
//                        email : rowData.email,  
//                        phone : rowData.phone,  
//                        descn : rowData.descn
//                    };  
                  
//                $("#gridTable").jqGrid("setRowData", data.node.id, dataRow);  
                      
                // 打开对话框  
                consoleDlg.dialog("open");  
            }  
        });  
          
    }  
};  
var addNode = function() {  
	 $("#error_node_number").html("");
	 $("#error_node_fileSize").html(""); 
    var consoleDlg = $("#consoleDlg");  
          
    var number = $.trim(consoleDlg.find("#number").val());  
    var fileSize = $.trim(consoleDlg.find("#fileSize").val());  
    var p1= /^[1-9]+[0-9]*]*$/;
    var p2 = /^([1-9]\d*|[0]{1,1})$/	;
    if(!p1.test(number)){
    	$("#error_node_number").html("节点号不能为空，且必须为正整数！");
    	return;
    }
    if(!p2.test(fileSize)){
    	$("#error_node_fileSize").html("文件大小不能为空,且必须为非负整数！");
    	return;
    }
    
    var data = "node.number="+number+"&node.fileSize="+fileSize;
    
    $("input:checked").each(function(index,value){
    	data+="&"+$(this).attr("name")+"=1";
    });
      
    var actionUrl = "nodeaction_create" ; 
    
      
    $.ajax( {  
    	type : 'post',
        url : actionUrl,  
        data :  data,  
        dataType : "json",  
        cache : false,  
        error : function(textStatus, errorThrown) {  
            alert("系统ajax交互错误: " + textStatus);  
        },  
        success : function(data, textStatus) {  
            if(data.ajaxResult == "success") {  
                var dataRow = {  
                    id : data.node.id,   // 从Server端得到系统分配的id  
                    number : data.node.number,  
                    fileSize : data.node.fileSize,  
                    catchType : data.node.catchType,  
                    remark : data.node.remark
                };  
                  
                var srcrowid = $("#gridTable").jqGrid("getGridParam", "selrow");  
                  
                if(srcrowid) {  
                    $("#gridTable").jqGrid("addRowData", data.node.id, dataRow, "before", srcrowid);  
                } else {  
                    $("#gridTable").jqGrid("addRowData", data.node.id, dataRow, "first");  
                }  
                consoleDlg.dialog("close");  
                  
                alert("节点添加操作成功!");  
                  
            } else {  
                alert("添加操作失败!");
                $.each(data.messages, function(index, obj) {
					$("#" + index).html(obj);
				});
                
                
            }  
        }  
    });  
};  
 var updateNode = function() {  
	 $("#error_node_chineseName").html("");
	 $("#error_node_nodename").html(""); 
    var consoleDlg = $("#consoleDlg");  
     var id =  $.trim(consoleDlg.find("#id").val());  
    var number = $.trim(consoleDlg.find("#number").val());  
    var fileSize = $.trim(consoleDlg.find("#fileSize").val());  
    var p1= /^[1-9]+[0-9]*]*$/;
    var p2 = /^([1-9]\d*|[0]{1,1})$/	;
    if(!p1.test(number)){
    	$("#error_node_number").html("节点号不能为空，且必须为正整数！");
    	return;
    }
    if(!p2.test(fileSize)){
    	$("#error_node_fileSize").html("文件大小不能为空,且必须为非负整数！");
    	return;
    }
    
    var data = "node.id="+id+"&node.number="+number+"&node.fileSize="+fileSize;
    
    $("input:checked").each(function(index,value){
    	data+="&"+$(this).attr("name")+"=1";
    });
    
    var actionUrl = "nodeaction_update";  
    $.ajax( {  
    	type : 'post',
        url : actionUrl,  
        data :  data,  
        dataType : "json",  
        cache : false,  
        error : function(textStatus, errorThrown) {  
            alert("系统ajax交互错误: " + textStatus);  
        },  
        success : function(data, textStatus) {  
            if (data.ajaxResult == "success") {  
                var dataRow = {  
                		  id : data.node.id,   // 从Server端得到系统分配的id  
                          number : data.node.number,  
                          fileSize : data.node.fileSize,  
                          catchType : data.node.catchType,  
                          remark : data.node.remark
                };  
                $("#gridTable").jqGrid("setRowData", data.node.id, dataRow, {color:"#FF0000"});  
                  
                alert("节点信息更新成功!");  
                  
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

var deleteNode = function() {  
    //var consoleDlg = $("#consoleDlg");  
      
    //var pId = $.trim(consoleDlg.find("#selectId").val());  
   // var params = {  
   //     "node.id" : pId  
    //};  
    var selectedRowId = $("#gridTable").jqGrid("getGridParam", "selarrrow");  
    if (selectedRowId.length==0 ) {  
        alert("请先选择需要删除的记录!");  
        return false;  
    } 
    var actionUrl = "nodeaction_delete?ids="+selectedRowId;  
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
                alert("节点删除成功!");  
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
}

dateTimePick = function(elem)
{
   jQuery(elem).datetimepicker({ dateFormat: 'yy-mm-dd',timeFormat: "HH:mm:ss" });
}