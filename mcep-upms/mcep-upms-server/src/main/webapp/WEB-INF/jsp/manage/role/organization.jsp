<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>角色管理</title>
	<jsp:include page="/resources/inc/head.jsp" flush="true"/>
    <style>
        .buttonItem {
            margin: 10px 0
        }

        .tableItem {
            margin-top: 10px 0
        }

        .inner {
            overflow-y: auto;
            overflow-x: hidden;
            height: 100%;
        }
        .form-control{
            ;
        }
    </style>
</head>
<div class="col-sm-9 col-md-9 col-lg-9 sp" style="width: 100%">
    <div class="inner">
    <div class="form-group">
        <font style="float: left;font-size: 17px;line-height: 200%">角色名称:&nbsp;</font><input type="text" readonly="true" style="width: 10%;margin-top: 10px" value="${upmsRole.name}" class="form-control">
    </div>
    <input id="roleId" type="hidden" class="form-control" maxlength="20" value="${upmsRole.roleId}">
    <div class="buttonItem">
        <button onclick="selectUser()" type="button" class="btn btn-default" style="float:left;">选择用户</button>
        <button id="shanchu" type="button" class="btn btn-default"
                style="float:left;margin-bottom: 10px;margin-left: 10px">删除
        </button>
    </div>
    <div id="allotDialog" class="crudDialog">
        <table id="userRoles" class="table table-bordered detail" border="1" cellspacing="1" cellpadding="1" >
            <thead>
            <tr>
                <th style='width: 1%;text-align:center;vertical-align: middle;'><input type="checkbox" name="all"/> </th>
                <th style='width: 5%;text-align:center;'>账号</th>
                <th style='width: 5%;text-align:center;'>姓名</th>
            </tr>

            </thead>
            <tbody>
            <c:forEach var="item" items="${upmsUserRoles}">
                <tr>
                    <td style="text-align:center;vertical-align: middle; ">
                        <input type="checkbox" name="item"/>
                    </td>
                    <td>
                        <div class="form-group" style="margin-bottom: -2px">
                            <input name="roleId" type="text" readonly="true" style="display: none"  class="form-control" value="${item.roleId}">
                            <input name="userId" type="text" readonly="true" style="display: none"  class="form-control" value="${item.userId}">
                            <input name="username" type="text" readonly="true" style="background-color: #FFFFFF;border-bottom: 0px"  class="form-control" value="${item.username}">
                        </div>
                    </td>
                    <td>
                        <div class="form-group" style="margin-bottom: -2px">
                            <input name="realname" type="text" readonly="true" style="background-color: #FFFFFF;border-bottom: 0px" class="form-control" value="${item.realname}">
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td style="text-align:center;vertical-align: middle; ">
                    <input type="checkbox" name="item"/>
                </td>
                <td>
                    <div class="form-group" style="margin-bottom: -2px">
                        <input name="roleId" type="text" readonly="true" style="display: none"  class="form-control">
                        <input name="userId" type="text" readonly="true" style="display: none" class="form-control">
                        <input name="username" type="text" readonly="true"  class="form-control">
                    </div>
                </td>
                <td>
                    <div class="form-group" style="margin-bottom: -2px">
                        <input name="realname" type="text" readonly="true" class="form-control">
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="float: left;" class="form-group text-right dialog-buttons">
        <a class="btn btn-success" href="javascript:;" onclick="allotSubmit();"><i class="zmdi "></i>保存</a>
        <a class="btn btn-default" href="javascript:;" onclick="allotDialog.close();"><i class="zmdi "></i>取消</a>
    </div>
</div>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    var $tr;
    $(function () {
        $tr = $(".detail>tbody>tr:last");
        $(".detail>tbody>tr:last").remove();
        //删除用户
        $("#shanchu").click(function(){
            var all_tr = $(".detail>tbody>tr");
            for(var i=0;i<all_tr.length;i++){
                if($(all_tr[i]).find("[name=item]:checked").length>0){
                    $(all_tr[i]).remove();
                }
            }
            $("[name=all]").removeAttr("checked");
        });
    })

    $("[name=all]").change(function(){
        if($(this).prop("checked")){
            $(".detail>tbody>tr").find("[name=item]").prop("checked",true);
        }else{
            $(".detail>tbody>tr").find("[name=item]:checked").removeAttr("checked");
        }
    });

    //选择用户
    var chooseUserDialog;
    function selectUser(){

        chooseUserDialog = $.dialog({
            animationSpeed: 300,
            title: '选择人员',
            columnClass: 'xlarge',
            content: 'url:/resources/zheng-admin/page/selectUser.html',
            onContentReady: function () {

            },
            onClose: function () {

            },
            onDestroy: function () {
                // when the modal is removed from DOM
                var userArray=[];
                $("input[name=userId]").each(function(index,item){
                    userArray.push(item.value);
                });
                if(userList!=null&&userList.length>0){
                    for (var i = 0; i < userList.length; i++) {
                        if(!IsInArray(userArray,userList[i].userId)){
                            var _obj = {};
                            _obj.userId = userList[i].userId;
                            _obj.username = userList[i].username;
                            _obj.realname = userList[i].realname;
                            appendToTable(_obj);
                        }
                    }
                }
                userList = null;
            }
        });
    }

    function IsInArray(arr,val){
        var testStr=','+arr.join(",")+",";
        return testStr.indexOf(","+val+",")!=-1;
    }

    function appendToTable(obj){
        var _$tr = $tr.clone();
        _$tr.find("[name=userId]").val(obj.userId);
        _$tr.find("[name=username]").val(obj.username);
        _$tr.find("[name=realname]").val(obj.realname);
        $(".detail>tbody").append(_$tr);
    };


    function allotSubmit() {
        debugger;
        var roleId = $("#roleId").val();
        var obj = {};
        var userRoles = Mcep.Page.util.getDetailTableData("userRoles");
        obj.userRoles = userRoles;
        $.ajax({
            type: 'post',
            url: '${basePath}/manage/role/allot/updateAndCommit/' + roleId,
            data: JSON.stringify(obj),
            dataType: "json",
            contentType: "application/json",
            beforeSend: function() {

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
</script>
</body>
</html>