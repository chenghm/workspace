$(function() {
	$("#startTime").datepicker({
		dateFormat : 'yy-mm-dd'
	});
	$("#endTime").datepicker({
		dateFormat : 'yy-mm-dd'
	});
	// 配置jqGrid组件
	$("#gridTable").jqGrid(
			{
				height : 380,
				url : "mailsmtpaction_find",
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
					name : "attachName",
					index : "attachName",
					label : "附件",
					sortable : false,
					width : 30,
					formatter : 'select',
					edittype : 'select',
					editoptions : {
						value : '1:<font color=\'red\'>★</font>'
					},
					stype : 'select',
					searchoptions : {
						sopt : [ "eq"],
						value : '1:<font color=\'red\' >★</font>'
					},
				},

				{
					name : "subject",
					index : "subject",
					label : "主题",
					sortable : false
				}, {
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
					name : "from",
					index : "from",
					label : "发送者",
					sortable : false
				}, {
					name : "to",
					index : "to",
					label : "接收者",
					sortable : false
				},

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
						value : '1:是;0:否'
					}
				},
				{
					name : "isRead",
					index : "isRead",
					label : "已读",
					hidden:true,
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
						value : '1:是;0:否'
					}
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
				/*
				 * onSelectRow: function(id) { if (id && id !== lastSel) {
				 * myGrid.jqGrid('restoreRow',lastSel); lastSel = id; } },
				 */

				ondblClickRow : function(rowid) {
					var actionUrl = "mailsmtpaction_view?mailId=" + rowid;
					// 从Server读取对应ID的JSON数据
					$.ajax({
						type : 'GET',
						url : actionUrl,
						dataType : "json",
						cache : false,
						error : function(textStatus, errorThrown) {
							alert("系统ajax交互错误: " + textStatus);
						},
						success : function(data, textStatus) {
							// 如果读取结果成功，则将信息载入到对话框中
							var rowData = data.vo;
							var consoleDlg = $("#consoleDlg");
							consoleDlg.find("#sourceIp").text(rowData.sourceIp);
							consoleDlg.find("#destIp").text(rowData.destIp);
							consoleDlg.find("#sourceBelong").text(rowData.sourceBelong);
							consoleDlg.find("#destBelong").text(rowData.destBelong);
							consoleDlg.find("#sourceMac").text(rowData.sourceMac);
							consoleDlg.find("#destMac").text(rowData.destMac);
							consoleDlg.find("#account").text(rowData.account);
							consoleDlg.find("#password").text(rowData.password);
							consoleDlg.find("#from").text(rowData.from);
							consoleDlg.find("#to").text(rowData.to);
							consoleDlg.find("#cc").text(rowData.cc);
							consoleDlg.find("#bcc").text(rowData.bcc);
							consoleDlg.find("#subject").text(rowData.subject);
							/*consoleDlg.find("#attach").html(
									"<a href='mailsmtpaction_download?attach="
											+ rowData.attachPath + "' >"
											+ rowData.attachName + "</a>");*/
							consoleDlg.find("#content").html(
									"<a href='mailsmtpaction_download?attach="
											+ rowData.contentPath + "' >"
											+ rowData.content + "</a>");
							consoleDlg.find("#body").html(rowData.body);
							// document.getElementById("content").innerHTML=rowData.content;
							// 打开对话框
							consoleDlg.dialog("option", "title", "查看邮件")
									.dialog("open");
						}
					});

					// jQuery(this).jqGrid('viewGridRow', rowid,{ width:
					// "500"});
				},
				pager : "#gridPager",
				caption : "SMTP记录",
				hidegrid : false,
				shrikToFit : true,
				multiselect : true,
				loadComplete: function() { 
					var n = $('#gridTable').getGridParam('reccount'); 
			        for(i=0;i<n;i++)                                     
			        {
			          var data =arguments[0].gridModel[i];
			          if(data.isRead != "1")                                   
			         {	
			              $("#gridTable tr:eq("+(i+1)+") td").css("font-weight", "bold");   
			          }
			        }
				}
			});

	// 配置对话框
	$("#consoleDlg").dialog({
		autoOpen : false,
		modal : false, // 设置对话框为模态（modal）对话框
		resizable : true,
		width : 600,
		buttons : { // 为对话框添加按钮
			"关闭" : function() {
				$("#consoleDlg").dialog("close");
			}
		},
		close : function() {
		}
	});

	$("#gridTable").jqGrid("navGrid", "#gridPager", {
		view : true,
		search : false,
		// viewtext:'My View Text',viewtitle:'My View Title',
		edit : false,
		add : false,
		del : true,
		refresh : true
	}, {}, // prmEdit
	{}, // prmAdd
	{
		 mtype: 'GET',
         onclickSubmit: function(rp_ge, postdata) {
        	 rp_ge.url = 'mailsmtpaction_delete?productId='+ postdata;
         }
	,
    serializeDelData: function (postdata) { return ""; },
    reloadAfterSubmit: true,
    closeOnEscape: true}, // prmDel
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
	
	var attach=$("#attach").val();
	if(attach=='1'){
		searchField = "attachName";
		searchOper="ne";
		searchString="";
		rules += ',{"field":"' + searchField + '","op":"' + searchOper + '","data":"' + searchString + '"}';
	}else if(attach=='0'){
		searchField = "attachName";
		searchOper="eq";
		searchString="";
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