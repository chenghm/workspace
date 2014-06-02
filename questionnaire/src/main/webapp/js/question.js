$(function() {
	// 配置jqGrid组件
	$("#gridTable").jqGrid(
			{
				url : "questionaction_find.action",
				datatype : "json",
				mtype : "GET",
				height : 400,
				colModel : [
						// {name:"id",key:true,index:"id",label:"编码",search:true,searchtype:"integer",sorttype:"int",stype:"text",searchrules:{required:true,
						// integer:true}},
						{
							name : "name",
							index : "name",
							label : "问题",
							sortable : false,
							editable : true,
							editrules : {
								required : true
							},
						},
						{
							name : "type",
							index : "type",
							label : "类型",
							sortable : false,
							formatter : 'select',
							edittype : 'select',
							editoptions : {
								value : '单选:单选;多选:多选;文本:文本;滑动:滑动;日期:日期'
							},
							stype : 'select',
							searchoptions : {
								sopt : [ "eq", "ne" ],
								value : '单选:单选;多选:多选;文本:文本'
							},
							editable : true,
							width : 50
						},
						{
							name : "questionnaire.name",
							index : "questionnaire.name",
							label : "所属问卷",
							sortable : false,
							// formatter : 'select',
							edittype : 'select',
							editoptions : {
								dataUrl : 'questionnaireaction_findAll.action',
								buildSelect : function(data) {
									var list = $.parseJSON(data).list;
									var select = "<select>";
									$.each(list, function(index, value) {
										select += "<option value='" + value.id
												+ "'>" + value.name
												+ "</option>"
									});
									select += "</select>"
									return select;
								}
							},
							stype : 'select',
							searchoptions : {
								sopt : [ "eq", "ne" ],
								dataUrl : 'questionnaireaction_findAll.action',
								buildSelect : function(data) {
									var list = $.parseJSON(data).list;
									var select = "<select>";
									$.each(list, function(index, value) {
										select += "<option value='" + value.id
												+ "'>" + value.name
												+ "</option>"
									});
									select += "</select>"
									return select;
								}
							},
							editable : true,
							editrules : {
								required : true
							},
							width : 200
						}, {
							name : "option1",
							index : "option1",
							label : "选项1",
							sortable : false,
							editable : true
						}, {
							name : "option2",
							index : "option2",
							label : "选项2",
							sortable : false,
							editable : true
						}, {
							name : "option3",
							index : "option3",
							label : "选项3",
							sortable : false,
							editable : true
						}, {
							name : "option4",
							index : "option4",
							label : "选项4",
							sortable : false,
							editable : true
						}, {
							name : "option5",
							index : "option5",
							label : "选项5",
							sortable : false,
							editable : true
						}, {
							name : "option6",
							index : "option6",
							label : "选项6",
							sortable : false,
							editable : true
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
				caption : "问题列表",
				hidegrid : false,
				shrikToFit : true,
				multiselect : true
			});

	$("#gridTable").jqGrid("navGrid", "#gridPager", {
		view : true,
		search : true,
		edit : true,
		add : true,
		del : true,
		refresh : true
	}, {
		mtype : 'POST',
		onclickSubmit : function(rp_ge, postdata) {
			rp_ge.url = 'questionaction_edit.action';
		},
		serializeEditData : function(postdata) {
			return postdata;
		},
		reloadAfterSubmit : true,
		closeOnEscape : true,
		closeAfterEdit : true,
		afterComplete : function(response, postdata, formid) {
			if (response.status == 200) {
				alert("操作成功！");
			} else {
				alert("操作失败，请重试！");
			}
		}

	}, // prmEdit
	{
		mtype : 'POST',
		onclickSubmit : function(rp_ge, postdata) {
			rp_ge.url = 'questionaction_edit.action';
		},
		serializeEditData : function(postdata) {
			return postdata;
		},
		reloadAfterSubmit : true,
		closeOnEscape : true,
		closeAfterAdd : true,
		afterComplete : function(response, postdata, formid) {
			if (response.status == 200) {
				alert("操作成功！");
			} else {
				alert("操作失败，请重试！");
			}
		}

	}, // prmAdd
	{

		mtype : 'GET',
		onclickSubmit : function(rp_ge, postdata) {
			rp_ge.url = 'questionaction_delete.action?ids=' + postdata;
		},
		serializeDelData : function(postdata) {
			return "";
		},
		reloadAfterSubmit : true,
		closeOnEscape : true,
		afterComplete : function(response, postdata, formid) {
			if (response.status == 200) {
				alert("操作成功！");
			} else {
				alert("操作失败，请重试！");
			}
		}

	}, // prmDel
	{
		multipleSearch : true,
		closeAfterSearch : true
	}, {} // prmView
	);

});
datePick = function(elem) {
	jQuery(elem).datepicker({
		dateFormat : 'yy-mm-dd'
	});
}