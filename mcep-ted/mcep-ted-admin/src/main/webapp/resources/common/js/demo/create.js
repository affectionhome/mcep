

$(function () {
    $('#proprietorCode').val(maxNo);
});

//选择联络人
function selectYanUser(){
    chooseUserDialog = $.dialog({
        animationSpeed: 300,
        title: '选择人员',
        columnClass: 'xlarge',
        content: 'url:/resources/common/page/user/selectUser.html',
        onContentReady: function () {

        },
        onDestroy: function () {
            if(Mcep.dialog.record!=null&&Mcep.dialog.record.length>0){
                $("#contactId").val(Mcep.dialog.record[0].userId);
                $("#contactName").val(Mcep.dialog.record[0].realname);
            }
            Mcep.dialog.record = null;
        }
    });

}

function commit(){
    $("#createDialog").bootstrapValidator('validate');//提交验证
    if ($("#createDialog").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
        save();
    }
}

function save() {
    var result = Mcep.Page.util.getData("main");
    $.ajax({
        type: 'post',
        url: '/manage/demo/create',
        data: result,
        beforeSend: function() {
            /*if ($('#assetTypeName').val() == '') {
             $('#assetTypeName').focus();
             return false;
             }*/
        },
        success: function(result) {
            if (result.code != 1) {
                if (result.data instanceof Array) {
                    $.each(result.data, function(index, value) {
                        $.confirm({
                            theme: 'dark',
                            animation: 'rotateX',
                            closeAnimation: 'rotateX',
                            title: false,
                            content: value.errorMsg,
                            buttons: {
                                confirm: {
                                    text: '确认',
                                    btnClass: 'waves-effect waves-button waves-light'
                                }
                            }
                        });
                    });
                } else {
                    $.confirm({
                        theme: 'dark',
                        animation: 'rotateX',
                        closeAnimation: 'rotateX',
                        title: false,
                        content: result.data.errorMsg,
                        buttons: {
                            confirm: {
                                text: '确认',
                                btnClass: 'waves-effect waves-button waves-light'
                            }
                        }
                    });
                }
            } else {
                Mcep.Page.Tab.refreshBeforePage();
                Mcep.Page.Tab.closeCurrenPage();
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            $.confirm({
                theme: 'dark',
                animation: 'rotateX',
                closeAnimation: 'rotateX',
                title: false,
                content: textStatus,
                buttons: {
                    confirm: {
                        text: '确认',
                        btnClass: 'waves-effect waves-button waves-light'
                    }
                }
            });
        }
    });
}
