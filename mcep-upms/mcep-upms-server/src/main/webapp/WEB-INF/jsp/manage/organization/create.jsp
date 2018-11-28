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
		<div class="row">
			<div class="col-sm-12">
				<div class="form-group">
					<label for="name">名称</label>
					<input id="name" type="text" class="form-control" name="name" maxlength="20">
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<label for="abbreviation">简称</label>
					<input id="abbreviation" type="text" class="form-control" name="abbreviation" maxlength="20">
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<label for="description">描述</label>
					<input id="description" type="text" class="form-control" name="description" maxlength="300">
				</div>
			</div>

			<div class="col-sm-12">
			<div class="form-group">
				<label for="pid">上级组织机构</label>
				<select id="pid" class="form-control" name="pid" style="float:left" >
					<option  value="">请选择</option>
				</select>
			</div>
			</div>
			<%--<div class="col-sm-12">--%>
				<%--<div class="form-group">--%>
					<%--<label for="property">属性</label>--%>
					<%--<input id="property" type="text" class="form-control" name="property" maxlength="300">--%>
				<%--</div>--%>
			<%--</div>--%>

			<div class="col-sm-12">
				<div>
					<label>属性（是否虚拟组织）</label>
				</div>

				<div class="radio">
					<div class="radio radio-inline radio-success">
						<input id="property_1" type="radio" name="property" value="1" checked>
						<label for="property_1">是 </label>
					</div>
					<div class="radio radio-inline">
						<input id="property_0" type="radio" name="property" value="0">
						<label for="property_0">不是 </label>
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="form-group">
					<label for="enact">定编</label>
					<input id="enact" type="text" class="form-control" name="enact" maxlength="300">
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<label for="manageLead">分管领导</label>
					<input id="manageLead" type="text" class="form-control" name="manageLead" maxlength="300">
				</div>
			</div>
			<%--<div class="col-sm-12">--%>
				<%--<div class="form-group">--%>
					<%--<label for="interior">是否内部组织</label>--%>
					<%--<input id="interior" type="text" class="form-control" name="interior" maxlength="300">--%>
				<%--</div>--%>
			<%--</div>--%>

			<div class="col-sm-12">
				<div>
					<label>是否内部组织</label>
				</div>

			<div class="radio">
				<div class="radio radio-inline radio-success">
					<input id="interior_1" type="radio" name="interior" value="1" checked>
					<label for="interior_1">是 </label>
				</div>
				<div class="radio radio-inline">
					<input id="interior_0" type="radio" name="interior" value="0">
					<label for="interior_0">不是 </label>
				</div>
			</div>
			</div>

		</div>
		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
		</div>


	</form>
</div>
<script>


    function initSelect2() {
        initPid();
    }

    function initPid() {
        $.getJSON('/manage/organization/list',{limit: 10000}, function(json) {
            var datas = [{id: 0, text: '请选择'}];
            for (var i = 0; i < json.rows.length; i ++) {
                var data = {};
                data.id = json.rows[i].organizationId;
                data.text = json.rows[i].name;
                datas.push(data);
            }
            $('#pid').empty();
            $('#pid').select2({
                data : datas
            });
            $('span.select2').css('margin-left','100px');
            $('span.select2').css('width','180px');
        });
    }



function createSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/organization/create',
        data: $('#createForm').serialize(),
        beforeSend: function() {
            if ($('#name').val() == '') {
                $('#name').focus();
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
</script>