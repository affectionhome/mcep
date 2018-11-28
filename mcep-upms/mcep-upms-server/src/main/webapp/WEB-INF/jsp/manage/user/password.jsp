<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="createDialog" class="crudDialog">
    <form id="createForm" method="post">

            <input id="userId" type="hidden" class="form-control" name="userId" value="${userid}">


        <div class="form-group">
            <label for="oldPassword">旧密码</label>
            <input id="oldPassword" type="password" class="form-control" name="oldPassword" maxlength="20">
        </div>
        <div class="form-group">
            <label for="newPassword">新密码（5-20位）</label>
            <input id="newPassword" type="password" class="form-control" name="newPassword" maxlength="20">
        </div>
        <div class="form-group">
            <label for="check">确认新密码</label>
            <input id="check" type="password" class="form-control" name="check" maxlength="20">
        </div>


        <div class="form-group text-right dialog-buttons">
            <a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">提交</a>
            <a class="waves-effect waves-button" href="javascript:;" onclick="passwordDialog.close();">取消</a>
        </div>
    </form>
</div>
<script>



    function createSubmit() {
        $.ajax({
            type: 'post',
            url: '${basePath}/manage/user/password',
            data: $('#createForm').serialize(),
            beforeSend: function() {
                if ($('#oldPassword').val() == '') {
                    $('#oldPassword').focus();
                    return false;
                }
                if ($('#newPassword').val() == '' || $('#newPassword').val().length < 5) {
                    $('#newPassword').focus();
                    return false;
                }
                if ($('#check').val() == '' || $('#check').val()!=$('#newPassword').val()) {
                    $('#check').focus();
                    return false;
                }

            },
            success: function(result) {
                debugger;
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
                            content: result.data.errorMsg || result.data,
                            buttons: {
                                confirm: {
                                    text: '确认',
                                    btnClass: 'waves-effect waves-button waves-light'
                                }
                            }
                        });
                    }
                } else {
                    passwordDialog.close();
                    $.confirm({
                        title: false,
                        content: '密码修改成功！',
                        autoClose: 'cancel|3000',
                        backgroundDismiss: true,
                        buttons: {
                            cancel: {
                                text: '关闭',
                                btnClass: 'waves-effect waves-button'
                            }
                        }
                    });
                    //$table.bootstrapTable('refresh');
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
</script>