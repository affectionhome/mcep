
var $table = $('#table');
$(function() {
    // bootstrap table初始化
    $table.bootstrapTable({
        url: '/manage/demo/list',
        height: getHeight()-$("#queryTable").height(),//这里减掉查询工具栏的高度
        striped: true,
        search: true,
        showRefresh: true,
        showColumns: true,
        minimumCountColumns: 2,
        clickToSelect: true,
        //detailView: true,
        detailFormatter: 'detailFormatter',
        pagination: true,
        paginationLoop: false,
        sidePagination: 'server',
        silentSort: false,
        smartDisplay: false,
        escape: true,
        searchOnEnterKey: false,
        idField: 'proprietorId',
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                offset: params.offset,
                limit: params.limit,
                sort:params.sort,
                order:params.order,
                search:params.search,
                //上面四个是固定参数，下面这个是自定义的参数
                //   categoryName: category_name
            };
            return param;
        },
        sortName: 'proprietorId',
        sortOrder: 'asc',
        maintainSelected: true,
        singleSelect:true,
        toolbar: '#toolbar',
        columns: [
            {field: 'ck', checkbox: true},
            //                     {field: 'proprietorId', title: '编号', sortable: true, align: 'center',width:'5%'},
            {field: 'proprietorCode', title: '业主编码',align: 'center',formatter: 'proprietorFormatter'},
            {field: 'proprietorName', title: '业主名称',align: 'center'}

        ],
        onClickRow:function(row,$element,field){//行点击事件

        }
    });

});

//给字段值加超链接
function proprietorFormatter(value, row, index){
    if(value != ''|| value != null){
        return "<a href='#' onclick='viewAction(\""+row.proprietorId+"\")'>"+value+"</a>";
    }

}

var category_name="";
function queryFormData(){
    category_name = $("#categoryName").val();
    $table.bootstrapTable('refresh');
}

// 新增
var createDialog;
function createAction() {
    Mcep.Page.Tab.createNewTab("/manage/demo/create","新增**信息");
}

// 编辑
var updateDialog;
function updateAction() {
    var rows = $table.bootstrapTable('getSelections');
    if (rows.length != 1) {
        $.confirm({
            title: false,
            content: '请选择一条记录！',
            autoClose: 'cancel|3000',
            backgroundDismiss: true,
            buttons: {
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    } else {
        Mcep.Page.Tab.createNewTab("/manage/demo/update/"+ rows[0].proprietorId,"修改**信息");
    }
}

// 查看
function viewAction(value) {
    Mcep.Page.Tab.createNewTab("/manage/demo/view/"+ value,"浏览**信息");
}


