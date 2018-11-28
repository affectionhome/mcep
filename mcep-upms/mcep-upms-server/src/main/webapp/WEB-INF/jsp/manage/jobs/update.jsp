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
		<div class="form-group">
			<label for="name">岗位名称</label>
			<input id="name" type="text" class="form-control" name="name" maxlength="20" value="${jobs.name}">
		</div>
		<div class="form-group">
			<label for="title">岗位标题</label>
			<input id="title" type="text" class="form-control" name="title" maxlength="20" value="${jobs.title}">
		</div>
		<div class="form-group">
			<label for="description">岗位描述</label>
			<input id="description" type="text" class="form-control" name="description" maxlength="300" value="${jobs.description}">
		</div>

		<div >
			<label >部门</label>
		</div>

		<div class="form-group">
			<div class="fg-line">
				<label for="organizationId">部门</label>
				<select id="organizationId" class="form-control organizationId" name="organizationId" >
					<option  value="">请选择</option>
				</select>
			</div>
		</div>
		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="updateDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>


    function initSelect2(){

        initOrganization(${jobs.organizationId});
    }
    function initOrganization(val) {

        $.getJSON('/manage/organization/list', {limit: 10000}, function (json) {
            var datas = [{id: 0, text: '请选择'}];
            for (var i = 0; i < json.rows.length; i++) {
                var data = {};
                data.id = json.rows[i].organizationId;
                data.text = json.rows[i].name;
                datas.push(data);
            }
            $('.organizationId').empty();
            $('.organizationId').select2({
                data: datas
            });

            if (!!val) {
                $('.organizationId').select2().val(val).trigger('change');
            }

        });

    }



function createSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/jobs/update/${jobs.jobsId}',
        data: $('#updateForm').serialize(),
        beforeSend: function() {
			if ($('#name').val() == '') {
				$('#name').focus();
				return false;
			}
			if ($('#title').val() == '') {
				$('#title').focus();
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
</script>