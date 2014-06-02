$(function() {
	$("#startTime").datepicker({
		dateFormat : 'yy-mm-dd'
	});
	$("#endTime").datepicker({
		dateFormat : 'yy-mm-dd'
	});
	// 配置jqGrid组件
	$("#gridTable").jqGrid({
		url : "wechataction_find",
		datatype : "json",
		mtype : "GET",
		 height: 370,
		// width: 800,
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
		}, 
		{
			name : "message",
			index : "message",
			label : "聊天信息",
			sortable : false
		},

		{
			name : "sourceAccount",
			index : "sourceAccount",
			label : "源账号",
			sortable : false
		},

		{
			name : "sourcePassword",
			index : "sourcePassword",
			label : "源密码",
			sortable : false
		}, {
			name : "destAccount",
			index : "destAccount",
			label : "目标账号",
			sortable : false
		},

		{
			name : "destPassword",
			index : "destPassword",
			label : "目标密码",
			sortable : false
		},

		{
			name : "sourceIp",
			index : "sourceIp",
			label : "源IP地址",
			sortable : false
		}, {
			name : "destIp",
			index : "destIp",
			label : "目标IP地址",
			sortable : false
		}, {
			name : "sourceMac",
			index : "sourceMac",
			label : "源MAC",
			sortable : false
		}, {
			name : "destMac",
			index : "destMac",
			label : "目标Mac",
			sortable : false
		},
		{
			name : "isBlack",
			index : "isBlack",
			label : "黑名单",
			sortable : false,
			width:50,
			formatter : 'select',
			edittype : 'select',
			editoptions : {
				value : '1:是;0:否'
			},
			stype : 'select',
			searchoptions : {
				sopt : [ "eq", "ne" ],
				//dataUrl : "js/yesno.html"
				value : '1:是;0:否'
			}
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
		caption : "微信记录",
		hidegrid : false,
		shrikToFit : true,
		multiselect : true
	});

	$("#gridTable").jqGrid("navGrid", "#gridPager", {
		view : true,
		search : false,
		edit : false,
		add : false,
		del : true,
		refresh : true
	}, {}, // prmEdit
	{}, // prmAdd
	{
		 mtype: 'GET',
         onclickSubmit: function(rp_ge, postdata) {
        	 rp_ge.url = 'wechataction_delete?productId='+ postdata;
         }
	,
    serializeDelData: function (postdata) { return ""; },
    reloadAfterSubmit: true,
    closeOnEscape: true}, // prmDel
	{ 
	}, {

	} // prmView

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

var mysearch = function() { 
	var rules="";
	var searchField,searchOper,searchString;
	var time = $('input[name=time]:checked').val();
	var date = new Date();
	var dd = date.getDate();
	var mm = date.getMonth()+1; 
	var yyyy = date.getFullYear();
	var startTime="";
	var endTime=yyyy+"-"+mm+"-"+dd+" 23:59:59";
	
	if(time==1){
		//当天
		startTime=yyyy+"-"+mm+"-"+dd+" 00:00:00";
		
	}else if(time==2){
		//昨天
		date.setDate(date.getDate()-1);
		dd = date.getDate();
		mm = date.getMonth()+1; 
		yyyy = date.getFullYear();
		startTime=yyyy+"-"+mm+"-"+dd+" 00:00:00";
		endTime=yyyy+"-"+mm+"-"+dd+" 23:59:59";
		
	}else if(time==3){
		//三天
		date.setDate(date.getDate()-2);
	    dd = date.getDate();
		mm = date.getMonth()+1; 
		yyyy = date.getFullYear();
		startTime=yyyy+"-"+mm+"-"+dd+" 00:00:00";
		
	}else if(time==4){
		//一周
		date.setDate(date.getDate()-6);
	    dd = date.getDate();
		mm = date.getMonth()+1; 
		yyyy = date.getFullYear();
		startTime=yyyy+"-"+mm+"-"+dd+" 00:00:00";
	}else if(time==5){
		//一月
		date.setDate(date.getDate()-29);
	    dd = date.getDate();
		mm = date.getMonth()+1; 
		yyyy = date.getFullYear();
		startTime=yyyy+"-"+mm+"-"+dd+" 00:00:00";
	}else if(time==6){
		//选择时间段
		startTime = $("#startTime").val();
		endTime = $("#endTime").val();
		if(startTime=="" || endTime==""){
			alert("请选择时间！");
			return;
		}
		startTime+=" 00:00:00";
		endTime+=" 23:59:59";
	}
	
	
	
	if(startTime!=""){
	searchField = "time";
	searchOper="ge";
	searchString=startTime;
	rules += ',{"field":"' + searchField + '","op":"' + searchOper + '","data":"' + searchString + '"}';
	searchOper="le";
	searchString=endTime;
	rules += ',{"field":"' + searchField + '","op":"' + searchOper + '","data":"' + searchString + '"}';
	}
	
	
var black=$("#black").val();
	
	if(black=='1'){
		searchField = "isBlack";
		searchOper="eq";
		searchString='1';
		rules += ',{"field":"' + searchField + '","op":"' + searchOper + '","data":"' + searchString + '"}';
	}else if(black=='0'){
		searchField = "isBlack";
		searchOper="eq";
		searchString='0';
		rules += ',{"field":"' + searchField + '","op":"' + searchOper + '","data":"' + searchString + '"}';
	}
	
    if(rules)  {
    rules = rules.substring(1);
    }
	var filtersStr = '{"groupOp":"AND","rules":[' + rules + ']}';
    // (4)获得当前postData选项的值  
    var postData = $("#gridTable").jqGrid("getGridParam", "postData");  
      
    // (5)将查询参数融入postData选项对象  
    $.extend(postData, {filters: filtersStr});  
   
    $("#gridTable").jqGrid("setGridParam", {  
        search: true    // (6)将jqGrid的search选项设为true  
    }).trigger("reloadGrid", [{page:1}]);   // (7)重新载入Grid表格，以使上述设置生效  
    
};  