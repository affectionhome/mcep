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
			<label for="username">帐号</label>
			<input id="username" type="text" class="form-control" name="username" maxlength="20">
		</div>
		<div class="form-group">
			<label for="password">密码</label>
			<input id="password" type="text" class="form-control" name="password" maxlength="32">
		</div>
		<div class="form-group">
			<label for="realname">姓名</label>
			<input id="realname" type="text" class="form-control" name="realname" maxlength="20">
		</div>

		<%--<div class="form-group">--%>
			<%--<div class="fg-line">--%>
				<%--<label for="organization">部门</label>--%>
				<%--<select id="organization" class="form-control" name="organization" style="float:left" >--%>
					<%--<option  value="">请选择</option>--%>
				<%--</select>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div>
			<label for="organization">部门</label>
		</div>
		<div class="form-group">
			<div class="fg-line">
				<select id="organization" class="form-control" name="organization" style="width: 330px;">
					<option value="0">请选择部门</option>
					<c:forEach var="upmsOrganization" items="${upmsOrganizations}">
						<option value="${upmsOrganization.organizationId}">${upmsOrganization.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div>
			<label for="jobs">主岗位</label>
		</div>
		<div class="form-group">
			<div class="fg-line">

				<select id="jobs" class="form-control" name="jobs" style="float:left;width: 330px;" >
					<option  value="">请选择岗位</option>
				</select>
			</div>
		</div>


		<%--<div class="row">--%>
			<%--<div class="col-lg-8 form-group">--%>
				<%--<label for="avatar">头像</label>--%>
				<%--<input id="avatar" type="text" class="form-control" name="avatar" maxlength="150">--%>
			<%--</div>--%>
			<%--<div class="col-lg-4">--%>
				<%--<div id="picker">上传头像</div>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-group">
			<label for="phone">手机</label>
			<input id="phone" type="text" class="form-control" name="phone" maxlength="20">
		</div>
		<div class="form-group">
			<label for="fixPhone">固话</label>
			<input id="fixPhone" type="text" class="form-control" name="fixPhone" maxlength="20">
		</div>
		<div class="form-group">
			<label for="email">邮箱</label>
			<input id="email" type="text" class="form-control" name="email" maxlength="50">
		</div>
		<%--<div class="form-group">--%>
			<%--<label for="jobs">岗位</label>--%>
			<%--<input id="jobs" type="text" class="form-control" name="jobs" maxlength="50">--%>
		<%--</div>--%>


            <%--<div class="form-group">--%>
                <%--<div class="fg-line">--%>
                    <%--<label for="jobs">岗位</label>--%>
                    <%--<select id="jobs" class="jobs" name="jobs" style="float:left" >--%>
                        <%--<option  value="">请选择</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
            <%--</div>--%>



		<div class="radio">
			<div class="radio radio-inline radio-info">
				<input id="sex_1" type="radio" name="sex" value="1" checked>
				<label for="sex_1">男 </label>
			</div>
			<div class="radio radio-inline radio-danger">
				<input id="sex_0" type="radio" name="sex" value="0">
				<label for="sex_0">女 </label>
			</div>
			<div class="radio radio-inline radio-success">
				<input id="locked_0" type="radio" name="locked" value="0" checked>
				<label for="locked_0">正常 </label>
			</div>
			<div class="radio radio-inline">
				<input id="locked_1" type="radio" name="locked" value="1">
				<label for="locked_1">锁定 </label>
			</div>
		</div>
		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>
    var organization = 0;

    $(function() {
        // 选择部门
        $('#organization').change(function() {
            organization = $(this).val();
            $('select').select2();
            initOrganizationJobs();
        });
    });



function initUploader() {
    //百度上传按钮
    var uploader = WebUploader.create({
        // swf文件路径
        swf: '${basePath}/resources/zheng-admin/plugins/webuploader-0.1.5/Uploader.swf',
        // 文件接收服务端
        server:'${basePath}/manage/user/upload',
        method: 'POST',
        fileNumLimit: 8, //限制上传个数
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: {
            "id": '#picker',
            "multiple": false
        },
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,
        // 选完文件后，是否自动上传。
        auto: true,
        // 只允许选择图片文件
        accept: {
            title: '图片文件',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    uploader.on( 'fileQueued', function(file) {

    });
    uploader.on( 'uploadSuccess', function(file, response) {
        $('#avatar').val(response.data.filename).focus();
    });
    uploader.on( 'uploadError', function(file) {
        console.log('uploadError', file);
    });





	//百度上传按钮
	/*var uploader = WebUploader.create({
		// swf文件路径
		swf: '${basePath}/resources/zheng-admin/plugins/webuploader-0.1.5/Uploader.swf',
		// 文件接收服务端
		method: 'POST',
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick: {
			"id": '#picker',
			"multiple": false
		},
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		resize: false,
		// 选完文件后，是否自动上传。
		auto: false,
		// 只允许选择图片文件
		accept: {
			title: '图片文件',
			extensions: 'gif,jpg,jpeg,bmp,png',
			mimeTypes: 'image/!*'
		}
	});
	uploader.on( 'fileQueued', function(file) {
		$.ajax({
			url: '${ZHENG_OSS_ALIYUN_OSS_POLICY}',
			type: 'GET',
			dataType: 'jsonp',
			jsonp: 'callback',
			success: function(result) {
				var suffix = get_suffix(file.name);
				var random_name = random_string();
				uploader.options.formData.key = result.dir + '/' + random_name + suffix;
				uploader.options.formData.policy = result.policy;
				uploader.options.formData.OSSAccessKeyId = result.OSSAccessKeyId;
				uploader.options.formData.success_action_status = 200;
				uploader.options.formData.callback = result.callback;
				uploader.options.formData.signature = result.signature;
				uploader.options.server = result.action;
				uploader.upload();
			},
			error: function(msg) {
				console.log(msg);
			}
		});
	});
	uploader.on( 'uploadSuccess', function(file, response) {
		$('#avatar').val(response.data.filename).focus();
	});
	uploader.on( 'uploadError', function(file) {
		console.log('uploadError', file);
	});*/
}

//
//function initJobs() {
//    $.getJSON('/manage/jobs/list',{limit: 10000}, function(json) {
//        var datas = [{id: 0, text: '请选择'}];
//        for (var i = 0; i < json.rows.length; i ++) {
//            var data = {};
//            data.id = json.rows[i].jobsId;
//            data.text = json.rows[i].name;
//            datas.push(data);
//        }
//        $('#jobs').empty();
//        $('#jobs').select2({
//            data : datas
//        });
//        $('span.select2').css('margin-left','100px');
//        $('span.select2').css('width','180px');
//    });
//}

function initOrganizationJobs() {
    if (organization != 0) {
        $.getJSON('/manage/jobs/list', {organization: organization,limit: 10000}, function (json) {
            var datas = [{id: 0, text: '请选择'}];
            for (var i = 0; i < json.rows.length; i++) {
                var data = {};
                data.id = json.rows[i].jobsId;
                data.text = json.rows[i].name;
                datas.push(data);
            }
            $('#jobs').empty();
            $('#jobs').select2({
                data: datas
            });
      //      $('span.select2').css('margin-left','100px');
            $('span.select2').css('width','330px');
        });
    }else {
        $('#jobs').empty();
        $('#jobs').select2({
            data : [{id: 0, text: '请选择'}]
        });
     //   $('span.select2').css('margin-left','100px');
        $('span.select2').css('width','330px');
    }
}


// 根据路径获取后缀
function get_suffix(filename) {
	var pos = filename.lastIndexOf('.');
	var suffix = '';
	if (pos != -1) {
		suffix = filename.substring(pos);
	}
	return suffix;
}
// 随机字符串
function random_string(len) {
	len = len || 32;
	var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
	var maxPos = chars.length;
	var pwd = '';
	for (i = 0; i < len; i++) {
		pwd += chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
}
function createSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/user/create',
        data: $('#createForm').serialize(),
        beforeSend: function() {
            if ($('#username').val() == '') {
                $('#username').focus();
                return false;
            }
            if ($('#password').val() == '' || $('#password').val().length < 5) {
                $('#password').focus();
                return false;
            }


            if ($('#organization').val() == 0) {
                $.confirm({
                    title: false,
                    content: '请选择部门！',
                    autoClose: 'cancel|3000',
                    backgroundDismiss: true,
                    buttons: {
                        cancel: {
                            text: '取消',
                            btnClass: 'waves-effect waves-button'
                        }
                    }
                });
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