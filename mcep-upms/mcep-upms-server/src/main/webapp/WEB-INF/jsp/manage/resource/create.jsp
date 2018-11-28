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
		<div class="form-group">
			<label for="resourceName">资源名称</label>
			<input id="resourceName" type="text" class="form-control" name="resourceName" maxlength="20">
		</div>
		<%--<div class="form-group">--%>
			<%--<label for="businessObject">业务对象</label>--%>
			<%--<input id="businessObject" type="text" class="form-control" name="businessObject" maxlength="20">--%>
		<%--</div>--%>

		<div class="form-group">
			<div class="fg-line">
				<label for="businessObject">业务对象</label>
				<select id="businessObject" class="form-control" name="businessObject" style="float:left" >
					<option  value="">请选择</option>
				</select>
			</div>
		</div>


		<div class="form-group">
			<label for="businessId">业务Id</label>
			<input id="businessId" type="text" class="form-control" name="businessId" maxlength="300">
		</div>
		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>


    function initSelect2() {
        initUpmsCodeName();
    }
    function initUpmsCodeName() {
        $.getJSON('/manage/resource/code',{businessObject:'resource',limit: 10000}, function(json) {
            var datas = [{id: "", text: '请选择'}];
            for (var i = 0; i < json.rows.length; i ++) {
                var data = {};
                data.id = json.rows[i].cdNo;
                data.text = json.rows[i].cdName;
                datas.push(data);
            }
            $('#businessObject').empty();
            $('#businessObject').select2({
                data : datas
            });
            $('span.select2').css('margin-left','100px');
            $('span.select2').css('width','180px');
        });
    }



function createSubmit() {
	check();
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/resource/create',
        data: $('#createForm').serialize(),
        beforeSend: function() {
            if ($('#resourceName').val() == '') {
                $('#resourceName').focus();
                return false;
            }
			if ($('#businessObject').val() == '') {
				$('#businessObject').focus();
				return false;
			}
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
				createDialog.close();
				$table.bootstrapTable('refresh');
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

function check(){
    var re = /^\d+(?=\.{0,1}\d+$|$)/;


    var resourceName = $("#resourceName").val();
    if (resourceName == "" || resourceName == null) {
        $.confirm({
            title: false,
            content: '“资源名称”不能为空!',
            autoClose: 'cancel|3000',
            backgroundDismiss: true,
            buttons: {
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    //    $("#resourceName").focus();
    }

//    if (!re.test(resourceName)) {
//        $.confirm({
//            title: false,
//            content: '请输入正确的“当日日照辐射量”',
//            autoClose: 'cancel|3000',
//            backgroundDismiss: true,
//            buttons: {
//                cancel: {
//                    text: '取消',
//                    btnClass: 'waves-effect waves-button'
//                }
//            }
//        });
//        $("#resourceName").focus();
//    }




    var businessObject = $("#businessObject").val();
    if (businessObject == "" || businessObject == null) {
        $.confirm({
            title: false,
            content: '“业务对象”不能为空!',
            autoClose: 'cancel|3000',
            backgroundDismiss: true,
            buttons: {
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
 //       $("#businessObject").focus();
    }





}



</script>