$(function() {
	// 配置jqGrid组件
	$("#gridTable").jqGrid({
		url : "blacklistaction_find",
		datatype : "json",
		mtype : "GET",
		 height: 400,
		// width: 800,
		colModel : [
		// {name:"id",key:true,index:"id",label:"编码",search:true,searchtype:"integer",sorttype:"int",stype:"text",searchrules:{required:true,
		// integer:true}},
		{
			name : "name",
			index : "name",
			label : "黑名单",
			sortable : false
		}, {
			name : "classify",
			index : "classify",
			label : "类型",
			sortable : false,
			formatter : 'select',
			edittype : 'select',
			editoptions : {
				value : '1:关键字;2:IP地址;3:邮箱;4:HTTP'
			},
			stype : 'select',
			searchoptions : {
				sopt : [ "eq", "ne" ],
				value : {
					'1' : '关键字',
					'2' : 'IP地址',
					'3' : '邮箱',
					'4' : 'HTTP'
				}
			}
		}, {
			name : "status",
			index : "status",
			label : "状态",
			sortable : false,
			formatter : 'select',
			edittype : 'select',
			editoptions : {
				value : '1:启用;0:禁用'
			},
			stype : 'select',
			searchoptions : {
				sopt : [ "eq", "ne" ],
				// dataUrl : "js/status.html"
				value : "1:启用;0:禁用"
			}
		}, {
			name : "descn",
			index : "descn",
			label : "备注",
			sortable : false
		}, {
			name : "createdTime",
			index : "createdTime",
			label : "创建时间",
			formatter : 'date',
			searchtype : "datetime",
			formatoptions : {
				srcformat : 'Y-m-d H:i:s',
				newformat : 'Y-m-d H:i:s'
			},
			sortable : false,
			stype : 'text',
			searchoptions : {
				dataInit : dateTimePick,
				attr : {
					title : 'Select Date'
				}
			},
			searchrules : {
				required : true
			}
		} ],
		viewrecords : true,
		rowNum : 50,
		rowList : [ 50,100,200 ],
		prmNames : {
			search : "search"
		},
		jsonReader : {
			root : "gridModel",
			records : "record",
			repeatitems : false
		},
		pager : "#gridPager",
		caption : "黑名单列表",
		hidegrid : false,
		shrikToFit : true,
		multiselect : true
	});

	// 配置对话框
	$("#consoleDlg").dialog({
		autoOpen : false,
		modal : true, // 设置对话框为模态（modal）对话框
		resizable : true,
		width : 540,
		buttons : { // 为对话框添加按钮
			"取消" : function() {
				$("#consoleDlg").dialog("close");
			},
			"创建" : addBlacklist,
			"保存" : updateBlacklist,
			"删除" : deleteBlacklist
		},
		close : function() {
			$("#error_blacklist_chineseName").html("");
			$("#error_blacklist_blacklistname").html("");
		}
	});

	$("#gridTable").jqGrid("navGrid", "#gridPager", {
		addfunc : openDialog4Adding, // (1) 点击添加按钮，则调用openDialog4Adding方法
		editfunc : openDialog4Updating, // (2) 点击编辑按钮，则调用openDialog4Updating方法
		delfunc : deleteBlacklist, // (3) 点击删除按钮，则调用openDialog4Deleting方法
		alerttext : "请选择需要操作的数据行!" // (4) 当未选中任何行而点击编辑、删除、查看按钮时，弹出的提示信息
	}, {}, // prmEdit
	{}, // prmAdd
	{}, // prmDel
	{ // prmSearch
		// caption: "查找",
		// Find: "Let's go!",
		multipleSearch : true,
		closeAfterSearch : true
	}, {} // prmView

	);
	// $("#gridTable").jqGrid('filterToolbar',{stringResult: true,
	// searchOnEnter: true,defaultSearch: 'cn', ignoreCase: true});

});
var openDialog4Adding = function() {
	var consoleDlg = $("#consoleDlg");
	var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");
	consoleDlg.find("input").removeAttr("disabled").val("");
	dialogButtonPanel.find("button:not(:contains('取消'))").hide();
	dialogButtonPanel.find("button:contains('创建')").show();
	consoleDlg.dialog("option", "title", "创建黑名单").dialog("open");
};
var openDialog4Updating = function() {
	var consoleDlg = $("#consoleDlg");
	var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");

	consoleDlg.find("input").removeAttr("disabled");
	dialogButtonPanel.find("button:not(:contains('取消'))").hide();
	dialogButtonPanel.find("button:contains('保存')").show();
	consoleDlg.dialog("option", "title", "修改黑名单信息");

	loadSelectedRowData();
};
var openDialog4Deleting = function() {
	var consoleDlg = $("#consoleDlg");
	var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");

	consoleDlg.find("input").attr("disabled", true);
	dialogButtonPanel.find("button:not(:contains('取消'))").hide();
	dialogButtonPanel.find("button:contains('删除')").show();
	consoleDlg.dialog("option", "title", "删除黑名单");

	loadSelectedRowData();
};
var loadSelectedRowData = function() {
	var selectedRowId = $("#gridTable").jqGrid("getGridParam", "selarrrow");
	if (selectedRowId.length == 0 || selectedRowId.length > 1) {
		alert("请先选择一条需要编辑的行!");
		return false;
	} else {
		/*
		 * var params = {
		 * 
		 * "blacklist.id" : selectedRowId };
		 */
		var actionUrl = "blacklistaction_view?blacklist.id=" + selectedRowId;
		// 从Server读取对应ID的JSON数据
		$.ajax({
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
				var rowData = data.blacklist;
				var consoleDlg = $("#consoleDlg");
				consoleDlg.find("#id").val(rowData.id);
				consoleDlg.find("#classify").val(rowData.classify);
				consoleDlg.find("#name").val(rowData.name);
				consoleDlg.find("#status").val(rowData.status);
				consoleDlg.find("#descn").val(rowData.descn);

				// 根据新载入的数据将表格中的对应数据行一并更新一下
				var dataRow = {
					id : rowData.id,
					classify : rowData.classify,
					name : rowData.name,
					status : rowData.status,
					descn : rowData.descn
				};

				$("#gridTable")
						.jqGrid("setRowData", data.blacklist.id, dataRow);

				// 打开对话框
				consoleDlg.dialog("open");
			}
		});

	}
};
var addBlacklist = function() {
	// $("#classify").val("");
	// $("#status").val("1");
	$("#error_blacklist_classify").html("");
	$("#error_blacklist_name").html("");
	var consoleDlg = $("#consoleDlg");

	/*
	 * var classify = $.trim(consoleDlg.find("#classify").val()); var name =
	 * $.trim(consoleDlg.find("#name").val()); var status =
	 * $.trim(consoleDlg.find("#email").val()); var pPhone =
	 * $.trim(consoleDlg.find("#phone").val()); var descn =
	 * $.trim(consoleDlg.find("#descn").val());
	 * 
	 * var params = { "blacklist.chineseName" : pChineseName,
	 * "blacklist.blacklistname" : pBlacklistname, "blacklist.email" : pEmail,
	 * "blacklist.phone" : pPhone };
	 */

	var actionUrl = "blacklistaction_create";

	$
			.ajax({
				type : 'post',
				url : actionUrl,
				data : $("#consoleForm").serialize(),
				dataType : "json",
				cache : false,
				error : function(textStatus, errorThrown) {
					alert("系统ajax交互错误: " + textStatus);
				},
				success : function(data, textStatus) {
					if (data.ajaxResult == "success") {
						var dataRow = {
							id : data.blacklist.id, // 从Server端得到系统分配的id
							name : data.blacklist.name,
							createdTime : data.blacklist.createdTime,
							classify : data.blacklist.classify,
							status : data.blacklist.status,
							descn : data.blacklist.descn
						};

						var srcrowid = $("#gridTable").jqGrid("getGridParam",
								"selrow");

						if (srcrowid) {
							$("#gridTable").jqGrid("addRowData",
									data.blacklist.id, dataRow, "before",
									srcrowid);
						} else {
							$("#gridTable").jqGrid("addRowData",
									data.blacklist.id, dataRow, "first");
						}
						consoleDlg.dialog("close");

						alert("黑名单添加操作成功!");

					} else {
						alert("添加操作失败!");
						$.each(data.messages, function(index, obj) {
							$("#" + index).html(obj);
						});

					}
				}
			});
};
var updateBlacklist = function() {
	$("#error_blacklist_classify").html("");
	$("#error_blacklist_name").html("");
	var consoleDlg = $("#consoleDlg");

	var actionUrl = "blacklistaction_update";
	$.ajax({
		type : 'post',
		url : actionUrl,
		data : $("#consoleForm").serialize(),
		dataType : "json",
		cache : false,
		error : function(textStatus, errorThrown) {
			alert("系统ajax交互错误: " + textStatus);
		},
		success : function(data, textStatus) {
			if (data.ajaxResult == "success") {
				var dataRow = {
					id : data.blacklist.id, // 从Server端得到系统分配的id
					name : data.blacklist.name,
					createdTime : data.blacklist.createdTime,
					classify : data.blacklist.classify,
					status : data.blacklist.status,
					descn : data.blacklist.descn
				};
				$("#gridTable").jqGrid("setRowData", data.blacklist.id,
						dataRow, {
							color : "#FF0000"
						});

				alert("黑名单信息更新成功!");

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

var deleteBlacklist = function() {
	// var consoleDlg = $("#consoleDlg");

	// var pId = $.trim(consoleDlg.find("#selectId").val());
	// var params = {
	// "blacklist.id" : pId
	// };
	var selectedRowId = $("#gridTable").jqGrid("getGridParam", "selarrrow");
	if (selectedRowId.length == 0) {
		alert("请先选择需要删除的记录!");
		return false;
	}
	var actionUrl = "blacklistaction_delete?ids=" + selectedRowId;
	$.ajax({
		type : "post",
		url : actionUrl,
		// data : params,
		dataType : "json",
		cache : false,
		error : function(textStatus, errorThrown) {
			alert("系统ajax交互错误: " + textStatus);
		},
		success : function(data, textStatus) {
			if (data.ajaxResult == "success") {
				var len = selectedRowId.length;
				for (var i = 0; i < len; i++) {
					$("#gridTable").jqGrid("delRowData", selectedRowId[0]);
				}

				// consoleDlg.dialog("close");
				alert("黑名单删除成功!");
			} else {
				alert(data.ajaxResult);
			}
		}
	});
};
// });
datePick = function(elem) {
	jQuery(elem).datepicker({
		dateFormat : 'yy-mm-dd HH24:mm:ss'
	});
};

dateTimePick = function(elem) {
	jQuery(elem).datetimepicker({
		dateFormat : 'yy-mm-dd',
		timeFormat : "HH:mm:ss"
	});
};