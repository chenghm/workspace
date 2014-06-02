$(function(){
	// 配置jqGrid组件
	$("#gridTable").jqGrid({
		url: "jqGrid01.action",
		datatype: "json",
		mtype: "GET",
		height: 350,
		width: 600,
		colModel: [
		      {name:"id",index:"id",label:"编码",width:40},  
		      {name:"lastName",index:"lastName",label:"姓",width:80,sortable:false},
		      {name:"firstName",index:"firstName",label:"名",width:80,sortable:false},
		      {name:"email",index:"email",label:"电子邮箱",width:160,sortable:false},
		      {name:"telNo",index:"telNo",label:"电话",width:105,sortable:false}
		],
		viewrecords: true,
		rowNum: 15,
		rowList: [15,50,100],
		prmNames: {search: "search"},
		jsonReader: {
			root:"gridModel",
			records: "record",
			repeatitems : false
		},
		pager: "#gridPager",
		caption: "联系人列表",
		hidegrid: false,
		shrikToFit: true
	});
});
var echoSelRow = function() {
	var id = $("#gridTable").jqGrid("getGridParam", "selrow");
	
	alert("当前选中行ID：" + id);
};
var getContact = function() {
	var selectedId = $("#gridTable").jqGrid("getGridParam", "selrow");
	
	var rowData = $("#gridTable").jqGrid("getRowData", selectedId);
	
	alert("First Name: " + rowData.firstName);
};
var addContact = function() {
	var selectedId = $("#gridTable").jqGrid("getGridParam", "selrow");
	
	var dataRow = {	
		id : 99,
		lastName : "Zhang",
		firstName : "San",
		email : "zhang_san@126.com",
		telNo : "0086-12345678"
	};
	
	if (selectedId) {
		$("#gridTable").jqGrid("addRowData", 99, dataRow, "before", selectedId);
		
	} else {
		$("#gridTable").jqGrid("addRowData", 99, dataRow, "first");
		
	}
};
var updateContact = function() {
	var selectedId = $("#gridTable").jqGrid("getGridParam", "selrow");
	
	var dataRow = {
		lastName : "Li",
		firstName : "Si",
		email : "li_si@126.com" 
	};
	
	var cssprop = {
		color : "#FF0000"
	};
	
	$("#gridTable").jqGrid('setRowData', selectedId, dataRow, cssprop);
};
var deleteContact = function() {
	var selectedId = $("#gridTable").jqGrid("getGridParam", "selrow");
	
	$("#gridTable").jqGrid('delRowData', selectedId);
};
var changeGridOptions = function() {
	$("#gridTable").jqGrid("setGridParam", {
		rowNum: 50,
		page: 16
	}).trigger('reloadGrid');
	
	$("#gridTable").jqGrid("setCaption", "Contact List").trigger('reloadGrid');
	
	alert($("#gridTable").jqGrid("getGridParam", "caption"));
	alert($("#gridTable").jqGrid("getGridParam", "rowNum"));
};
var resetWidth = function() {
	$("#gridTable").jqGrid("setGridWidth", 300, false);
};