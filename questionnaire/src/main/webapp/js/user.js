$(function() {
	// 配置jqGrid组件
	$("#gridTable")
			.jqGrid(
					{
						url : "useraction_find.action",
						datatype : "json",
						mtype : "GET",
						height : 400,
						colModel : [
								// {name:"id",key:true,index:"id",label:"编码",search:true,searchtype:"integer",sorttype:"int",stype:"text",searchrules:{required:true,
								// integer:true}},
								{
									name : "username",
									index : "username",
									label : "用户名",
									sortable : false
								},
								{
									name : "age",
									index : "age",
									label : "年龄段",
									sortable : false,
									formatter : 'select',
									edittype : 'select',
									editoptions : {
										value : '10岁以下:10岁以下;10-19岁:10-19岁;20-24岁:20-24岁;25-29岁:25-29岁;30-39岁:30-39岁;40-49岁:40-49岁;50-59岁:50-59岁;60岁以上:60岁以上'
									},
									stype : 'select',
									searchoptions : {
										sopt : [ "eq", "ne" ],
										value : '10岁以下:10岁以下;10-19岁:10-19岁;20-24岁:20-24岁;25-29岁:25-29岁;30-39岁:30-39岁;40-49岁:40-49岁;50-59岁:50-59岁;60岁以上:60岁以上'
									}
								},

								{
									name : "gender",
									index : "gender",
									label : "性别",
									sortable : false,
									formatter : 'select',
									edittype : 'select',
									editoptions : {
										value : '男:男;女:女'
									},
									stype : 'select',
									searchoptions : {
										sopt : [ "eq", "ne" ],
										value : '男:男;女:女'
									}
								},
								{
									name : "occupation",
									index : "occupation",
									label : "职业",
									sortable : false,
									formatter : 'select',
									edittype : 'select',
									editoptions : {
										value : '白领:白领;技术人员:技术人员;销售:销售;行政:行政;前台:前台;后勤:后勤;市场:市场;财务:财务;会计:会计'
									},
									stype : 'select',
									searchoptions : {
										sopt : [ "eq", "ne" ],
										value : '白领:白领;技术人员:技术人员;销售:销售;行政:行政;前台:前台;后勤:后勤;市场:市场;财务:财务;会计:会计'
									}
								},
								{
									name : "education",
									index : "education",
									label : "学历",
									sortable : false,
									formatter : 'select',
									edittype : 'select',
									editoptions : {
										value : '博士:博士;硕士:硕士;本科:本科;专科:专科;高中:高中;初中:初中'
									},
									stype : 'select',
									searchoptions : {
										sopt : [ "eq", "ne" ],
										value : '博士:博士;硕士:硕士;本科:本科;专科:专科;高中:高中;初中:初中'
									}
								},
								{
									name : "income",
									index : "income",
									label : "收入",
									sortable : false,
									formatter : 'select',
									edittype : 'select',
									editoptions : {
										value : '1000以下:1000以下;1000-1999:1000-1999;2000-2999:2000-2999;3000-3999:3000-3999;4000-4999:4000-4999;5000-7999:5000-7999;8000-11999:8000-11999;12000及以上:12000及以上'
									},
									stype : 'select',
									searchoptions : {
										sopt : [ "eq", "ne" ],
										value : '1000以下:1000以下;1000-1999:1000-1999;2000-2999:2000-2999;3000-3999:3000-3999;4000-4999:4000-4999;5000-7999:5000-7999;8000-11999:8000-11999;12000及以上:12000及以上'
									}
								},

								{
									name : "email",
									index : "email",
									label : "邮箱",
									sortable : false
								},
								{
									name : "imei",
									index : "imei",
									label : "设备号",
									sortable : false
								},
								{
									name : "score",
									index : "score",
									label : "积分",
									searchtype:"integer",
									sorttype:"int",
									stype:"text",
									searchrules:{ integer:true},
									sortable : false
								}

						],
						viewrecords : true,
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
						caption : "用户列表",
						hidegrid : false,
						shrikToFit : true,
						multiselect : true
					});

	$("#gridTable").jqGrid("navGrid", "#gridPager", {
		view : true,
		search : true,
		edit : false,
		add : false,
		del : false,
		refresh : true
	}, {}, // prmEdit
	{}, // prmAdd
	{}, // prmDel
	{
		multipleSearch : true,
		closeAfterSearch : true
	}, {} // prmView
	);

});