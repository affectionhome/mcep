<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<link href="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
<link href="${basePath}/resources/zheng-admin/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
<link href="${basePath}/resources/zheng-admin/plugins/bootstrap-table-1.11.0/bootstrap-table.min.css" rel="stylesheet"/>
<link href="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
<link href="${basePath}/resources/zheng-admin/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet"/>
<link href="${basePath}/resources/zheng-admin/plugins/jquery-confirm/jquery-confirm.min.css" rel="stylesheet"/>
<link href="${basePath}/resources/zheng-admin/plugins/select2/css/select2.min.css" rel="stylesheet"/>
<link href="${basePath}/resources/zheng-admin/plugins/select2/theme/select2-bootstrap.min.css" rel="stylesheet"/>
<link href="${basePath}/resources/zheng-admin/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"/>
<link href="${basePath}/resources/zheng-admin/plugins/webuploader-0.1.5/webuploader.css" rel="stylesheet"/>
<link href="${basePath}/resources/zheng-admin/css/common.css" rel="stylesheet"/>
<link href="${basePath}/resources/mcep-admin/bootstrapvalidator-0.4.5/bootstrapValidator.css" rel="stylesheet"/>

<script src="${basePath}/resources/zheng-admin/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/bootstrap-table-1.11.0/bootstrap-table.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/jquery-confirm/jquery-confirm.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/select2/js/select2.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/webuploader-0.1.5/webuploader.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/zTree_v3/js/jquery.ztree.all.min.js"></script>
<script src="${basePath}/resources/zheng-admin/js/common.js"></script>
<script src="${basePath}/resources/zheng-admin/js/Mcep.js"></script>
<script src="${basePath}/resources/mcep-admin/bootstrapvalidator-0.4.5/bootstrapValidator.js"></script>

<div id="createDialog" class="crudDialog">

<div>
        <table id="main">
            <thead>
            <tr>
                <th style="width: 13%;"></th>
                <th style="width: 18%;"></th>
                <th style="width: 13%;"></th>
                <th style="width: 18%;"></th>
                <th style="width: 13%;"></th>
                <th style="width: 18%;"></th>
            </tr>
            </thead>
            <tbody>
            <tr style="display: none"></tr>

            <div class="tab-pane fade" id="check">
                <table id="codeClass" class="timeTable" style="margin-bottom: 20px;margin-top: 20px;">
                    <thead>
                    <tr>
                        <th style="width: 13%;"></th>
                        <th style="width: 18%;"></th>
                        <th style="width: 13%;"></th>
                        <th style="width: 18%;"></th>
                        <th style="width: 13%;"></th>
                        <th style="width: 18%;"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td style="width: 17%;"></td>
                        <td style="width: 18%;"></td>
                        <td style="width: 17%;padding-right: 10px;" align="right"><label>分类编码</label></td>
                        <td style="width: 18%;">
                            <%--<div class="form-group">--%>
                            <input id="catNo" type="text" class="form-control" name="catNo"
                                <%--data-bv-message="分类编码是无效的"--%>

                                <%--required--%>
                                <%--data-bv-notempty-message="分类编码不能为空"--%>

                            >
                            <%--</div>--%>
                        </td>
                        <td style="width: 13%;padding-right: 10px;" align="right"><label >分类名称</label></td>
                        <td style="width: 18%;">
                            <%--<div class="form-group">--%>
                            <input id='catName' class='form-control catName' style='width: 100%' name='catName' type='text' >
                            <%--th:value="${pomsFault.checkPerson}"--%>
                            <%--</div>--%>
                        </td>

                    </tr>
                    </tbody>
                </table>

                <table id="code" class="table table-bordered" border="1" cellspacing="1" cellpadding="1" style="width: 80%;margin-left: 150px;">
                    <thead>
                    <tr style="width: 80%"  bgcolor="#CCCCCC">
                        <th style='width: 25%;text-align: center;' ><label>分类编码</label></th>
                        <th style='width: 25%;text-align: center;'><label>取值编码</label></th>
                        <th style='width: 25%;text-align: center;'><label>取值名称</label></th>
                        <th style='width: 25%;text-align: center;'><label>状态</label></th>
                    </tr>
                    </thead>
                    <tbody>


                    </tbody>

                    <tfoot><tr><td colspan="4">
                        <div>
                            <button type="button" class="btn btn-default btnCode">添加记录</button>
                        </div>
                    </td> </tr></tfoot>
                </table>

            </div>

            </tbody>


     </table>
</div>

    <div style="height:60px;">
        <div class="form-group text-left dialog-buttons toolbar">
            <button type="submit" id="save" class="btn btn-default" onclick="save();">&nbsp;&nbsp;保存&nbsp;&nbsp;</button>

        </div>
    </div>

</div>

<script>


//    function initSelect2() {
//        initPermission();
//        initResource();
//    }
//    function initPermission() {
//        $.getJSON('/manage/permission/list',{limit: 10000}, function(json) {
//            var datas = [{id: 0, text: '请选择'}];
//            for (var i = 0; i < json.rows.length; i ++) {
//                var data = {};
//                data.id = json.rows[i].permissionId;
//                data.text = json.rows[i].name;
//                datas.push(data);
//            }
//            $('#permissionId').empty();
//            $('#permissionId').select2({
//                data : datas
//            });
//            $('span.select2').css('margin-left','100px');
//            $('span.select2').css('width','180px');
//        });
//    }
//
//    function initResource() {
//        $.getJSON('/manage/resource/list',{limit: 10000}, function(json) {
//            var datas = [{id: 0, text: '请选择'}];
//            for (var i = 0; i < json.rows.length; i ++) {
//                var data = {};
//                data.id = json.rows[i].resourceId;
//                data.text = json.rows[i].resourceName;
//                datas.push(data);
//            }
//            $('#resourceId').empty();
//            $('#resourceId').select2({
//                data : datas
//            });
//            $('span.select2').css('margin-left','100px');
//            $('span.select2').css('width','180px');
//        });
//    }



    //保存
    function save(){
        debugger;
        var result = Mcep.Page.util.getData("codeClass");
        var code = Mcep.Page.util.getDetailTableData("code");
        result.upmsCodeList = code;

//        $("#createForm").bootstrapValidator('validate');//提交验证
//        if ($("#createForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
        $.ajax({
            type: 'post',
            url: '/manage/codeClass/create/',
            //data: result,
            data: JSON.stringify(result),
            dataType:"json",
            contentType:"application/json",
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
                    Mcep.Page.Tab.refreshBeforePage();//刷新跳转过来的那个页面
                    Mcep.Page.Tab.closeCurrenPage();//关闭当前页面
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


        <%--}--%>
    }

    //模版行
    var $trs;
    $(".btnCode").click(function(){
        var $_tr = $trs.clone();
        $("#code tbody").append($_tr);
        var catNo =  $('#catNo').val();
        $('.catNo').val(catNo);

    });

    function initCc() {
        var text = " <tr class='lists' style='width: 100%; '>" +
            "<td style='align-content: center;width: 25%'>" +
            "<input id='catNo' class='form-control catNo' readonly style='width: 100%;padding-top: 0px;padding-bottom: 0px;' name='catNo' type='text'>" +
            "</td>" +     //分类编码

            "<td style='align-content: center;width: 25%'>" +
            "<input id='cdNo' class='form-control cdNo' style='width: 100%;padding-top: 0px;padding-bottom: 0px;' name='cdNo' type='text'>" +
            "</td>" +         //取值编码

            "<td style='align-content: center;width: 8%'>" +
            "<input id='cdName' class='form-control cdName' style='width: 100%;padding-top: 0px;padding-bottom: 0px;' name='cdName' type='text'>" +
            "</td>" +         //取值名称

            "<td style='align-content: center;width: 8%;'>" +
         //   "<input id='ison' class='form-control ison' style='width: 100%;padding-top: 0px;padding-bottom: 0px;' name='ison' type='text' >" +
                " <select id='ison' name='ison' class='form-control faultCause' style='width:100%;height: 32px;' >"+
                "<option  value=''>请选择</option>"+
                "<option  value='启用'>启用</option>"+
                "<option  value='禁用'>禁用</option>"+
                "</select>"+
//            "<div class='radio' style='margin-bottom: 0px;margin-top: 5px;padding-left: 35%;'>" +
//            "<div class='radio radio-inline radio-success'>" +
//            "<input id='state_1' type='radio' name='state' value='1'>" +
//            "<label for='state_1'>启用</label>" +
//            "</div>" +
//            "<div class='radio radio-inline'>" +
//            "<input id='state_0' type='radio' name='state' value='0'>" +
//            "<label for='state_0'>禁用</label>" +
//            "</div>" +
//            "</div>" +
            "</td>" +        //状态
            "<td style='align-content: center;width: 8%; display: none;'>" +
            "<input id='old' class='form-control old' style='width: 100%;padding-top: 0px;padding-bottom: 0px;' name='old' type='text' value='0'>" +
            "</td>" +        //是否旧数据
            "</tr>";
        $trs = $(text);
    }

    $(function(){
        initCc();
    });

</script>