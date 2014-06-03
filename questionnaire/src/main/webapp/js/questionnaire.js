$(function () {
    // 配置jqGrid组件
    $("#gridTable")
        .jqGrid(
        {
            url: "questionnaireaction_find.action",
            datatype: "json",
            mtype: "GET",
            height: 400,
            colModel: [
                // {name:"id",key:true,index:"id",label:"编码",search:true,searchtype:"integer",sorttype:"int",stype:"text",searchrules:{required:true,
                // integer:true}},
                {
                    name: "name",
                    index: "name",
                    label: "问卷名",
                    sortable: false,
                    editable: true,
                    editrules: {
                        required: true
                    },
                },
                {
                    name: "point",
                    index: "point",
                    label: "分值",
                    searchtype: "integer",
                    sorttype: "int",
                    stype: "text",
                    searchrules: {
                        integer: true
                    },
                    sortable: false,
                    editable: true,
                    editrules: {
                        required: true,
                        integer: true
                    },

                },

                {
                    name: "endtime",
                    index: "endtime",
                    label: "结束日期",
                    formatter: 'date',
                    searchtype: "datetime",
                    formatoptions: {
                        srcformat: 'Y-m-d',
                        newformat: 'Y-m-d'
                    },
                    sortable: false,
                    stype: 'text',
                    searchoptions: {
                        dataInit: datePick,
                        attr: {
                            title: 'Select Date'
                        }
                    },
                    editoptions: {
                        dataInit: datePick,
                        attr: {
                            title: 'Select Date'
                        }
                    },
                    searchrules: {
                        // required : true
                    },
                    editable: true,
                    editrules: {
                        // required : true,
                        readonly: true
                    }
                },
                {
                    name: "gender",
                    index: "gender",
                    label: "性别",
                    sortable: false,
                    formatter: 'select',
                    edittype: 'select',
                    editoptions: {
                        value: ':不限;男:男;女:女'
                    },
                    stype: 'select',
                    searchoptions: {
                        sopt: [ "eq", "ne" ],
                        value: ':不限;男:男;女:女'
                    },
                    editable: true,
                    width: 50
                },
                {
                    name: "age",
                    index: "age",
                    label: "年龄段",
                    sortable: false,
                    formatter: 'select',
                    edittype: 'select',
                    editoptions: {
                        value: ':不限;10岁以下:10岁以下;10-19岁:10-19岁;20-24岁:20-24岁;25-29岁:25-29岁;30-39岁:30-39岁;40-49岁:40-49岁;50-59岁:50-59岁;60岁以上:60岁以上'
                    },
                    stype: 'select',
                    searchoptions: {
                        sopt: [ "eq", "ne" ],
                        value: ':不限;10岁以下:10岁以下;10-19岁:10-19岁;20-24岁:20-24岁;25-29岁:25-29岁;30-39岁:30-39岁;40-49岁:40-49岁;50-59岁:50-59岁;60岁以上:60岁以上'
                    },
                    editable: true,
                    width: 100
                },

                {
                    name: "occupation",
                    index: "occupation",
                    label: "职业",
                    sortable: false,
                    formatter: 'select',
                    edittype: 'select',
                    editoptions: {
                        value: ':不限;白领:白领;技术人员:技术人员;销售:销售;行政:行政;前台:前台;后勤:后勤;市场:市场;财务:财务;会计:会计'
                    },
                    stype: 'select',
                    searchoptions: {
                        sopt: [ "eq", "ne" ],
                        value: ':不限;白领:白领;技术人员:技术人员;销售:销售;行政:行政;前台:前台;后勤:后勤;市场:市场;财务:财务;会计:会计'
                    },
                    editable: true,
                    width: 100
                },
                {
                    name: "education",
                    index: "education",
                    label: "学历",
                    sortable: false,
                    formatter: 'select',
                    edittype: 'select',
                    editoptions: {
                        value: ':不限;博士:博士;硕士:硕士;本科:本科;专科:专科;高中:高中;初中:初中'
                    },
                    stype: 'select',
                    searchoptions: {
                        sopt: [ "eq", "ne" ],
                        value: ':不限;博士:博士;硕士:硕士;本科:本科;专科:专科;高中:高中;初中:初中'
                    },
                    editable: true,
                    width: 100
                },
                {
                    name: "income",
                    index: "income",
                    label: "收入",
                    sortable: false,
                    formatter: 'select',
                    edittype: 'select',
                    editoptions: {
                        value: ':不限;1000以下:1000以下;1000-1999:1000-1999;2000-2999:2000-2999;3000-3999:3000-3999;4000-4999:4000-4999;5000-7999:5000-7999;8000-11999:8000-11999;12000及以上:12000及以上'
                    },
                    stype: 'select',
                    searchoptions: {
                        sopt: [ "eq", "ne" ],
                        value: ':不限;1000以下:1000以下;1000-1999:1000-1999;2000-2999:2000-2999;3000-3999:3000-3999;4000-4999:4000-4999;5000-7999:5000-7999;8000-11999:8000-11999;12000及以上:12000及以上'
                    },
                    editable: true,
                    width: 100
                }

            ],
            viewrecords: true,
            rowNum: 50,
            rowList: [ 50, 100, 200 ],
            prmNames: {
                search: "search"
            },
            jsonReader: {
                root: "gridModel",
                records: "record",
                repeatitems: false
            },
            pager: "#gridPager",
            caption: "问卷列表",
            hidegrid: false,
            shrikToFit: true,
            multiselect: true
        });

    $("#gridTable").jqGrid("navGrid", "#gridPager", {
            view: true,
            search: true,
            edit: true,
            add: true,
            del: true,
            refresh: true
        }, {
            mtype: 'POST',
            onclickSubmit: function (rp_ge, postdata) {
                rp_ge.url = 'questionnaireaction_edit.action';
            },
            serializeEditData: function (postdata) {
                return postdata;
            },
            reloadAfterSubmit: true,
            closeOnEscape: true,
            closeAfterEdit: true,
            afterComplete: function (response, postdata, formid) {
                if (response.status == 200) {
                    alert("操作成功！");
                } else {
                    alert("操作失败，请重试！");
                }
            }
        }, // prmEdit
        {
            mtype: 'POST',
            onclickSubmit: function (rp_ge, postdata) {
                rp_ge.url = 'questionnaireaction_edit.action';
            },
            serializeEditData: function (postdata) {
                return postdata;
            },
            reloadAfterSubmit: true,
            closeOnEscape: true,
            closeAfterAdd: true,
            afterComplete: function (response, postdata, formid) {
                if (response.status == 200) {
                    alert("操作成功！");
                } else {
                    alert("操作失败，请重试！");
                }
            }
        }, // prmAdd
        {

            mtype: 'GET',
            onclickSubmit: function (rp_ge, postdata) {
                rp_ge.url = 'questionnaireaction_delete.action?ids=' + postdata;
            },
            serializeDelData: function (postdata) {
                return "";
            },
            reloadAfterSubmit: true,
            closeOnEscape: true,
            afterComplete: function (response, postdata, formid) {
                if (response.status == 200) {
                    alert("操作成功！");
                } else {
                    alert("操作失败，请重试！");
                }
            }

        }, // prmDel
        {
            multipleSearch: true,
            closeAfterSearch: true
        }, {} // prmView
    );

});
datePick = function (elem) {
    jQuery(elem).datepicker({
        dateFormat: 'yy-mm-dd'
    });
}