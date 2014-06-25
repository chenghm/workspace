$(function() {
	$("#startTime").datepicker({
		dateFormat : 'yy-mm-dd'
	});
	$("#endTime").datepicker({
		dateFormat : 'yy-mm-dd'
	});
	// 配置jqGrid组件
	$("#gridTable").jqGrid({
		height : 380,
		url : "generalaction_find",
		datatype : "json",
		mtype : "GET",
		colModel : [ {
			name : "time",
			index : "time",
			label : "时间",
			formatter : 'date',
			searchtype : "datetime",
			width : 120,
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
		}, {
			name : "type",
			index : "type",
			label : "类型",
			sortable : false,
		},

		{
			name : "sourceIp",
			index : "sourceIp",
			label : "源IP",
			sortable : false
		}, {
			name : "destIp",
			index : "destIp",
			label : "目标IP",
			sortable : false
		},

		{
			name : "sourceMac",
			index : "sourceMac",
			label : "源MAC址",
			sortable : false
		}, {
			name : "destMac",
			index : "destMac",
			label : "目标MAC",
			sortable : false
		}, {
			name : "from",
			index : "from",
			label : "发送者",
			sortable : false
		}, {
			name : "to",
			index : "to",
			label : "接收者",
			sortable : false
		}, {
			name : "url",
			index : "url",
			label : "链接",
			sortable : false
		}, {
			name : "keyword",
			index : "keyword",
			label : "关键字",
			sortable : false
		}

		],
		viewrecords : true,
		gridview : true,
		rowNum : 50,
		rowList : [ 50, 100, 200 ],
		prmNames : {
			search : "search"
		},
		jsonReader : {
			root : "gridModel",
			records : "record",
			repeatitems : false
		},
		pager : "#gridPager",
		caption : "綜合查詢记录",
		hidegrid : false,
		shrikToFit : true,
		multiselect : true
	});

	$("#gridTable").jqGrid("navGrid", "#gridPager", {
		view : true,
		search : false,
		// viewtext:'My View Text',viewtitle:'My View Title',
		edit : false,
		add : false,
		del : false,
		refresh : true
	}, {}, // prmEdit
	{}, // prmAdd
	{}, // prmDel
	{ // prmSearch
	// caption: "查找",
	// Find: "Let's go!",
	// multipleSearch : false,
	// closeAfterSearch : true
	}, {

	} // prmView

	);

	// view : {
	// caption: "View Record",
	// bClose: "Close"
	// },

});

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

var mysearch = function() {
	var rules = "";
	var searchField, searchOper, searchString;
	var time = $('input[name=time]:checked').val();
	var date = new Date();
	var dd = date.getDate();
	var mm = date.getMonth() + 1;
	var yyyy = date.getFullYear();
	var startTime = "";
	var endTime = yyyy + "-" + mm + "-" + dd + " 23:59:59";

	if (time == 1) {
		// 当天
		startTime = yyyy + "-" + mm + "-" + dd + " 00:00:00";

	} else if (time == 2) {
		// 昨天
		date.setDate(date.getDate() - 1);
		dd = date.getDate();
		mm = date.getMonth() + 1;
		yyyy = date.getFullYear();
		startTime = yyyy + "-" + mm + "-" + dd + " 00:00:00";
		endTime = yyyy + "-" + mm + "-" + dd + " 23:59:59";

	} else if (time == 3) {
		// 三天
		date.setDate(date.getDate() - 2);
		dd = date.getDate();
		mm = date.getMonth() + 1;
		yyyy = date.getFullYear();
		startTime = yyyy + "-" + mm + "-" + dd + " 00:00:00";

	} else if (time == 4) {
		// 一周
		date.setDate(date.getDate() - 6);
		dd = date.getDate();
		mm = date.getMonth() + 1;
		yyyy = date.getFullYear();
		startTime = yyyy + "-" + mm + "-" + dd + " 00:00:00";
	} else if (time == 5) {
		// 一月
		date.setDate(date.getDate() - 29);
		dd = date.getDate();
		mm = date.getMonth() + 1;
		yyyy = date.getFullYear();
		startTime = yyyy + "-" + mm + "-" + dd + " 00:00:00";
	} else if (time == 6) {
		// 选择时间段
		startTime = $("#startTime").val();
		endTime = $("#endTime").val();
		if (startTime == "" || endTime == "") {
			alert("请选择时间！");
			return;
		}
		startTime += " 00:00:00";
		endTime += " 23:59:59";
	}

	if (startTime != "") {
		searchField = "time";
		searchOper = "ge";
		searchString = startTime;
		rules += ',{"field":"' + searchField + '","op":"' + searchOper
				+ '","data":"' + searchString + '"}';
		searchOper = "le";
		searchString = endTime;
		rules += ',{"field":"' + searchField + '","op":"' + searchOper
				+ '","data":"' + searchString + '"}';
	}

	var type = $("#type").val();
	if (type != "") {
		searchField = "type";
		searchOper = "bw";
		searchString = type;
		rules += ',{"field":"' + searchField + '","op":"' + searchOper
				+ '","data":"' + searchString + '"}';
	}

	var ip = $("#ip").val();
	if (ip != "") {
		searchField = "ip";
		searchOper = "bw";
		searchString = ip;
		rules += ',{"field":"' + searchField + '","op":"' + searchOper
				+ '","data":"' + searchString + '"}';

		var mac = $("#mac").val();

		searchField = "mac";
		searchOper = "bw";
		searchString = mac;
		rules += ',{"field":"' + searchField + '","op":"' + searchOper
				+ '","data":"' + searchString + '"}';
	}
	var email = $("#email").val();
	if (email != "") {
		searchField = "email";
		searchOper = "bw";
		searchString = email;
		rules += ',{"field":"' + searchField + '","op":"' + searchOper
				+ '","data":"' + searchString + '"}';
	}

	var url = $("#url").val();
	if (url != "") {
		searchField = "url";
		searchOper = "bw";
		searchString = url;
		rules += ',{"field":"' + searchField + '","op":"' + searchOper
				+ '","data":"' + searchString + '"}';
	}

	var keyword = $("#keyword").val();
	if (keyword != "") {
		searchField = "keyword";
		searchOper = "bw";
		searchString = keyword;
		rules += ',{"field":"' + searchField + '","op":"' + searchOper
				+ '","data":"' + searchString + '"}';
	}
	if (rules) {
		rules = rules.substring(1);
	}
	var filtersStr = '{"groupOp":"AND","rules":[' + rules + ']}';
	// (4)获得当前postData选项的值
	var postData = $("#gridTable").jqGrid("getGridParam", "postData");

	// (5)将查询参数融入postData选项对象
	$.extend(postData, {
		filters : filtersStr
	});

	$("#gridTable").jqGrid("setGridParam", {
		search : true
	// (6)将jqGrid的search选项设为true
	}).trigger("reloadGrid", [ {
		page : 1
	} ]); // (7)重新载入Grid表格，以使上述设置生效

};