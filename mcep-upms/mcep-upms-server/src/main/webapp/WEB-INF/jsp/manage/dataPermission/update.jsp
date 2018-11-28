<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="updateDialog" class="crudDialog">
	<form id="updateForm" method="post">
		<%--<div class="form-group">--%>
			<%--<label for="permissionId">权限</label>--%>
			<%--<input id="permissionId" type="text" class="form-control" name="permissionId" maxlength="20" value="${dataPermission.permissionId}">--%>
		<%--</div>--%>
			<div >
				<label >权限</label>
			</div>

		<div class="form-group">
			<div class="fg-line">
				<label for="permissionId">权限</label>
				<select id="permissionId" class="form-control permissionId" name="permissionId" >
					<option  value="">请选择</option>
				</select>
			</div>
		</div>

		<%--<div class="form-group">--%>
			<%--<label for="resourceId">资源</label>--%>
			<%--<input id="resourceId" type="text" class="form-control" name="resourceId" maxlength="20" value="${dataPermission.resourceId}">--%>
		<%--</div>--%>
			<div >
				<label >资源</label>
			</div>
		<div class="form-group">
				<label for="resourceId">资源</label>
				<select id="resourceId" class="form-control resourceId" name="resourceId">
					<option  value="">请选择</option>
				</select>
		</div>

			<div>
					<%--<input id="authorizationTypes" type="hidden" name="authorizationType" value="${dataPermission.authorizationType}">--%>
					<label>授权类型</label>
			</div>

			<div class="form-group">
				<%--<label for="authorizationType">授权类型</label>--%>
				<%--<input id="authorizationType" type="text" class="form-control" name="authorizationType" maxlength="300">--%>
					<td>
						<select id="authorizationType" name="authorizationType" style="width:100%;height: 27px;">
							<option <c:if test='${dataPermission.authorizationType== ""}'>  selected  </c:if> value="">请选择</option>
							<option <c:if test='${dataPermission.authorizationType== "人员"}'>  selected  </c:if> value="人员">人员</option>
							<option <c:if test='${dataPermission.authorizationType== "岗位"}'>  selected  </c:if> value="岗位">岗位</option>
							<option <c:if test='${dataPermission.authorizationType== "角色"}'>  selected  </c:if> value="角色">角色</option>
						</select>
					</td>
					<td colspan="4">
					</td>

			</div>
		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="updateDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>

    function initSelect2(){
        initPermission(${dataPermission.permissionId});
        initResource(${dataPermission.resourceId});
    }
    function initPermission(val) {

        $.getJSON('/manage/permission/list', {limit: 10000}, function (json) {
            var datas = [{id: 0, text: '请选择'}];
            for (var i = 0; i < json.rows.length; i++) {
                var data = {};
                data.id = json.rows[i].permissionId;
                data.text = json.rows[i].name;
                datas.push(data);
            }
            $('.permissionId').empty();
            $('.permissionId').select2({
                data: datas
            });

            if (!!val) {
                $('.permissionId').select2().val(val).trigger('change');
            }

        });

    }

    function initResource(val) {

        $.getJSON('/manage/resource/list', {limit: 10000}, function (json) {
            var datas = [{id: 0, text: '请选择'}];
            for (var i = 0; i < json.rows.length; i++) {
                var data = {};
                data.id = json.rows[i].resourceId;
                data.text = json.rows[i].resourceName;
                datas.push(data);
            }
            $('.resourceId').empty();
            $('.resourceId').select2({
                data: datas
            });

            if (!!val) {
                $('.resourceId').select2().val(val).trigger('change');
            }

        });

    }



function createSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/dataPermission/update/${dataPermission.id}',
        data: $('#updateForm').serialize(),
        beforeSend: function() {
			if ($('#permissionId').val() == '') {
				$('#permissionId').focus();
				return false;
			}
			if ($('#resourceId').val() == '') {
				$('#resourceId').focus();
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
				updateDialog.close();
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

    $(function(){
        debugger;

       var authorizationType =  $("#authorizationType").val;

    });



</script>