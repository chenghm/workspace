$(function() {
	// 配置jqGrid组件
	$("#gridTable").jqGrid({
		url : "logaction_find",
		datatype : "json",
		mtype : "GET",
		 height: 400,
		// width: 800,
		colModel : [ {
			name : "logTime",
			index : "logTime",
			label : "日志时间",
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
		}, {
			name : "logUser",
			index : "logUser",
			label : "操作用户",
			sortable : false
		}, {
			name : "logIp",
			index : "logIp",
			label : "用户IP",
			sortable : false
		}, {
			name : "logContent",
			index : "logContent",
			label : "日志内容",
			sortable : false
		}

		],
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
		caption : "日志列表",
		hidegrid : false,
		shrikToFit : true,
		multiselect : true
	});

	$("#gridTable").jqGrid("navGrid", "#gridPager", {edit:false,add:false,del:false}, {}, // prmEdit
	{}, // prmAdd
	{}, // prmDel
	{ // prmSearch
		// caption: "查找",
		// Find: "Let's go!",
		multipleSearch : true,
		closeAfterSearch : true
	}, {} // prmView

	);
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