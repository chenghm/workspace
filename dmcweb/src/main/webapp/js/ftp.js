$(function() {
	$("#startTime").datepicker({
		dateFormat : 'yy-mm-dd'
	});
	$("#endTime").datepicker({
		dateFormat : 'yy-mm-dd'
	});
	// 配置jqGrid组件
	$("#gridTable").jqGrid({
		url : "ftpaction_find",
		datatype : "json",
		mtype : "GET",
		 height: 370,
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
		},/* {
			name : "fileOldName",
			index : "fileOldName",
			label : "文件原名",
			sortable : false
		}, */{
			name : "fileName",
			index : "fileName",
			label : "文件名",
			sortable : false
		}, {
			name : "fileSize",
			index : "fileSize",
			label : "文件大小",
			sortable : false,
			searchtype : "integer",
			sorttype : "int",
			stype : "text",
			searchrules : {
				required : true,
				integer : true
			}
		}, 
		
		{
			name : "account",
			index : "account",
			label : "账号",
			sortable : false
		},

		{
			name : "password",
			index : "password",
			label : "密码",
			sortable : false
		}, {
			name : "rootDirectory",
			index : "rootDirectory",
			label : "FTP根目录",
			sortable : false
		},
		{
			name : "fileClassify",
			index : "fileClassify",
			label : "分类",
			sortable : false,
			formatter : 'select',
			edittype : 'select',
			editoptions : {
				value : '1:上传;2:下载'
			},
			stype : 'select',
			searchoptions : {
				sopt : [ "eq", "ne" ],
				//dataUrl : "js/fileclassify.html"
				value : '1:上传;2:下载'
			}
		}, /*{
			name : "protocol",
			index : "protocol",
			label : "协议",
			sortable : false,
			formatter : 'select',
			edittype : 'select',
			editoptions : {
				value : '1:FTP;2:FTPS;3:SFTP'
			},
			stype : 'select',
			searchoptions : {
				sopt : [ "eq", "ne" ],
				//dataUrl : "js/ftpprotocol.html"
				value : '1:FTP;2:FTPS;3:SFTP'
			}
		}, */{
			name : "host",
			index : "host",
			label : "主机地址",
			sortable : false
		}, {
			name : "port",
			index : "port",
			label : "端口",
			sortable : false,
			searchtype : "integer",
			sorttype : "int",
			stype : "text",
			searchrules : {
				required : true,
				integer : true
			}
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
		},/* {
			name : "sourceBelong",
			index : "sourceBelong",
			label : "源归属地",
			sortable : false
		}, {
			name : "destBelong",
			index : "destBelong",
			label : "目标归属地",
			sortable : false
		},*/

		{
			name : "isBlack",
			index : "isBlack",
			label : "黑名单",
			sortable : false,
			width : 50,
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
		caption : "FTP记录",
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
        	 rp_ge.url = 'ftpaction_delete?productId='+ postdata;
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