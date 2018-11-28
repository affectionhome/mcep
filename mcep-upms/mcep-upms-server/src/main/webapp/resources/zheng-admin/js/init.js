

$(function() {
    //防止用户点击浏览器后退，重新进入管理页面
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });
    // 点击登录按钮
    $('#login-bt').click(function() {
        init();
    });
    // 回车事件
    $('#username, #password').keypress(function (event) {
        if (13 == event.keyCode) {
            init();
        }
    });
});
// 登录
function init() {

        $.ajax({
            type: 'post',
            url: '/manage/user/init',
            data: {
                userId: $('#userId').val(),
                password: $('#newPassword').val()
            },
            beforeSend: function() {
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
                if (result.code != 1) {

                } else {
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
                        },
                        onDestroy: function () {
                            location.href = result.data;
                        }
                    });
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });



}