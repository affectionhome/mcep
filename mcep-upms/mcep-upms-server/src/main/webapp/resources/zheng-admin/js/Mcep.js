/**
 * Created by admin on 2018/1/18.
 */
Mcep = window['Mcep'] || {};

Mcep.Page = function(){

}

Mcep.Page.Tab = {

    /**
     * 打开当前域下面的一个url的页面
     * @param url   相对路径， 如：/manage/leave/add
     * @param name  tab页名称
     * @param parameter 参数对象
     */
    createNewTab : function(url,name,parameter){
        var protocol = window.location.protocol;
        var host = window.location.host;
        var _url = protocol+'//'+host + url;
        var parms = "";
        parameter = parameter ||{}
        var currentUrl = window.location.href;
        var pid = currentUrl.replace(/\./g, '_').replace(/\//g, '_').replace(/:/g, '_').replace(/\?/g, '_').replace(/,/g, '_').replace(/=/g, '_').replace(/&/g, '_');
        parameter.pid = pid;
        for(var key in parameter){
            parms = parms + key + '=' + parameter[key]+'&';
        }
        if(parms.length>0){
            _url = _url + "?" + parms.substring(0,parms.length-1);
        }

        parent.window.postMessage(['Tab.addTab',name,_url],'*');

    },
    /**
     * 打开其他域下面的一个url链接
     * @param url   绝对url路径
     * @param name  tab页名称
     * @param parameter 参数对象
     */
    crossDomainCreateNewTab : function (url,name,parameter) {
        var parms = "";
        parameter = parameter ||{};
        for(var key in parameter){
            parms = parms + key + '=' + parameter[key];
        }
        if(parms.length>0){
            _url = _url + "?" + parms.substring(0,parms.length-1);
        }
        parent.window.postMessage(['Tab.addTab',name,_url],'*');
    },
    /**
     * 刷新跳转过来的那个页面
     */
    refreshBeforePage : function(){
        var obj = Mcep.URL.util.getURLparams();
        if(obj['pid']!=null && obj['pid']!=""){
            parent.window.postMessage(['Page.utils.refresh',obj['pid']],'*');
        }
    },
    /**
     * 关闭当前页面
     */
    closeCurrenPage : function () {
        var currentUrl = window.location.href;
        var pageId = currentUrl.replace(/\./g, '_').replace(/\//g, '_').replace(/:/g, '_').replace(/\?/g, '_').replace(/,/g, '_').replace(/=/g, '_').replace(/&/g, '_');
        var urlParams = Mcep.URL.util.getURLparams();
        if(urlParams["taskId"]!=null && urlParams["taskId"]!=""){
            pageId = "http___flow_mcep_net_cn_8888_manage_process_exection_"+urlParams["taskId"]+"_pageId_http___flow_mcep_net_cn_8888_manage_process_index";
        }
        parent.window.postMessage(['Page.utils.close','tab_'+pageId],'*');
    }





}

Mcep.Page.util = {

    _getData : function($obj) {
        var data = {};
        var objs = $obj.find("input:text");//文本框
        for(var i=0;i<objs.length;i++){
            var name = objs.eq(i).attr("name");
            var val = objs.eq(i).val();
            if(!Mcep.Data.util.isNull(name)){
                data[name] = val;
            }

        }
        objs = $obj.find("input:hidden");//隐藏文本框
        for(var i=0;i<objs.length;i++){
            var name = objs.eq(i).attr("name");
            var val = objs.eq(i).val();
            if(!Mcep.Data.util.isNull(name)){
                data[name] = val;
            }

        }
        objs = $obj.find("textarea");//文本域
        for(var i=0;i<objs.length;i++){
            var name = objs.eq(i).attr("name");
            var val = objs.eq(i).val();
            if(!Mcep.Data.util.isNull(name)){
                data[name] = val;
            }
        }
        objs = $obj.find("select");     //下拉框
        for(var i=0;i<objs.length;i++){
            var name = objs.eq(i).attr("name");
            var val = objs.eq(i).val();
            if(!Mcep.Data.util.isNull(name)){
                data[name] = val;
            }
        }
        objs = $obj.find("input:radio:checked");//单选按钮
        for(var i=0;i<objs.length;i++){
            var name = objs.eq(i).attr("name");
            var val = objs.eq(i).val();
            if(!Mcep.Data.util.isNull(name)){
                data[name] = val;
            }
        }
        objs = $obj.find("input:checkbox:checked");//多选框
        for(var i=0;i<objs.length;i++){
            var name = objs.eq(i).attr("name");
            var val = objs.eq(i).val();
            if(!Mcep.Data.util.isNull(name)){
                if(data[name]){
                    data[name] += ","+val;
                }else{
                    data[name] = val;
                }

            }
        }

        return data;
    },
    getData : function(id) {
        id = id.trim();
        if(id == "") return;
        var $obj = $("#" + id);
        var data = Mcep.Page.util._getData($obj);
        return data;
    },

    /**
     * 获取明细表中的数据
     * @param tableId  明细表的id
     * @returns {Array} 返回数据中的每个对象就是每行数据的json对象
     */
    getDetailTableData : function(tableId){

        tableId = tableId.trim();
        if(tableId == "") return;
        var $obj = $("#" + tableId+">tbody");
        var rows = $obj.get(0).rows;//获得表格所有行

        //var cells = tableObj.get(0).rows[0].cells;//获得表格第一行所有列

        var result = [];
        for(var i = 0; i < rows.length; i++) {
            var trData = {};
            var cells = rows[i].cells;//获得行的所有列
            for(var j = 0; j < cells.length; j++) {
                var tdData = Mcep.Page.util._getData($(cells[j]));
                trData = $.extend(trData, tdData)
            }
            if(Object.keys(trData).length>0)
                result.push(trData);

        }
        return result;
    }




}

Mcep.Data = {
    util: {
        isNull:function(val){
            if(val == null || val =="") return true;
        }
    }
}

Mcep.URL = {

    util :{
        /**
         * //获取url中"?"符后的所有参数值
         * @returns {Object}
         */
        getURLparams: function () {
            var url = location.search;
            var theRequest = new Object();
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                strs = str.split("&");
                for(var i = 0; i < strs.length; i ++) {
                    theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
                }
            }
            return theRequest;
        }
    }


}

Mcep.Date = {

    /**
     * 增加日期
     * @param dateParameter 日期字符串
     * @param num 步长
     * @returns {String}
     */
    addByTransDate: function(dateParameter, num) {
        var translateDate = "", dateString = "", monthString = "", dayString = "";
        translateDate = dateParameter.replace("-", "/").replace("-", "/");
        var newDate = new Date(translateDate);
        newDate = newDate.valueOf();
        newDate = newDate + num * 24 * 60 * 60 * 1000;
        newDate = new Date(newDate);
        //如果月份长度少于2，则前加 0 补位
        if ((newDate.getMonth() + 1).toString().length == 1) {
            monthString = 0 + "" + (newDate.getMonth() + 1).toString();
        } else {
            monthString = (newDate.getMonth() + 1).toString();
        }
        //如果天数长度少于2，则前加 0 补位
        if (newDate.getDate().toString().length == 1) {
            dayString = 0 + "" + newDate.getDate().toString();
        } else {
            dayString = newDate.getDate().toString();
        }
        dateString = newDate.getFullYear() + "-" + monthString + "-" + dayString;
        return dateString;
    },
}

Mcep.WebUploader = {

    /**
     * 创建百度上传控件
     * @param eleId     按钮的id
     * @param fileListDivId 文件列表的id
     * @param fileTokenId   放fileToken的文本框id
     * @param maxNum    最大上传数量,默认5个
     * @param auto      是否自动上传（true,false）,默认true
     * @param multiple  是否多个上传（true,false）,默认false
     * @returns {uploaderObj}
     */
    initUploader : function(eleId,fileListDivId,fileTokenId,maxNum,auto,multiple){

        eleId = eleId || 'picker';
        fileListDivId = fileListDivId || 'fileList';
        fileTokenId = fileTokenId || 'fileToken';
        maxNum = maxNum || '5';
        auto = auto || true;
        multiple = multiple || false;

        var uploaderParams = {
            // swf文件路径
            swf: '/resources/zheng-admin/plugins/webuploader-0.1.5/Uploader.swf',
            // 文件接收服务端
            server:'/manage/file/upload',
            method: 'POST',
            fileNumLimit: maxNum, //限制上传个数
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: {
                "id": '#'+eleId,
                "multiple": multiple
            },
//            chunked: true,//开始分片上传
//            chunkSize: 2048000,//每一片的大小
            // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
            resize: false,
            // 选完文件后，是否自动上传。
            auto: auto/*,
             // 只允许选择图片文件
             accept: {
             title: '图片文件',
             extensions: 'gif,jpg,jpeg,bmp,png',
             mimeTypes: 'image/!*'
             }*/
        };
        //百度上传按钮
        var uploader = WebUploader.create(uploaderParams);
        uploader.on( 'beforeFileQueued', function(file) {
            //如果上传文件的大小不得大于20M
            if(file.size>20*1024*1024){
                $.confirm({
                    title: false,
                    content: '附件的大小不大于20M！',
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
            //TODO 这里还可以继续对可允许上传文件数进行一个控制

        });
        uploader.on( 'fileQueued', function(file) {

            var $list = $("#"+fileListDivId),
                $li = $(
                    '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<img>' +
                    '<div class="info">' + file.name + '</div>' +
                    '</div>'
                ),
                $img = $li.find('img');

            // $list为容器jQuery实例
            $list.append( $li );

            // 创建缩略图
            /*uploader.makeThumb( file, function( error, src ) {
             if ( error ) {
             $img.replaceWith('<span>不能预览</span>');
             return;
             }

             $img.attr( 'src', src );
             }, 100, 100 ); //100x100为缩略图尺寸
             */
        });
        // 文件上传过程中创建进度条实时显示。
        uploader.on( 'uploadProgress', function( file, percentage ) {

            var $li = $( '#'+file.id ),
                $percent = $li.find('.progress p');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress"><p style="background-color: #00B83F;height:100%;"></p></div>')
                    .appendTo( $li )
                    .find('p');
            }

            $percent.css( 'width', percentage * 100 + '%' );
        });
        //文件上传之前
        uploader.on('uploadBeforeSend', function(obj,data, headers) {
            var fileToke = $("#"+fileTokenId).val();
            data.fileToken = fileToke;

        });
        //文件上传成功
        uploader.on( 'uploadSuccess', function(file, response) {
            //console.log("fileToken:"+response._raw);//文件上传成功后返回回来的fileToken
            $("#"+fileTokenId).val(response.fileToken);
            file.recoredId = response.recoredId;
            $( '#'+file.id ).addClass('upload-state-done');
        });
        //文件上传出错
        uploader.on( 'uploadError', function(file) {
            var $li = $( '#'+file.id ),
                $error = $li.find('div.error');

            // 避免重复创建
            if ( !$error.length ) {
                $error = $('<div class="error"></div>').appendTo( $li );
            }

            $error.text('上传失败');
        });
        // 完成上传完了，成功或者失败，先删除进度条。
        uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').remove();
            $('<button type="button" class="btn btn-warning btn-xs" onclick="Mcep.WebUploader.deleteFile(\''+file.id+'\','+file.recoredId+')">删除</button>')
                .appendTo( $( '#'+file.id ) )
        });

        return uploader;
    },
    /**
     * 删除文件
     * @param id
     * @param recoredId
     */
    deleteFile : function(id,recoredId){
        $( '#'+id ).remove();
        if(recoredId!=null && recoredId!=""){
            $.ajax({
                type: 'get',
                url: '/manage/file/delete/' + recoredId,
                success: function(result) {
                    if (result.code != 1) {

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
    },
    showFileList :function(fileToken,fileListId){
        if(fileToken == null || fileToken == "") {
            var $list = $("#"+fileListId);
                $li = $(
                    '<div>' +
                    '    无'+
                    '</div>'
                );
                $list.append( $li );
            return;
        };
        $.ajax({
            type: 'get',
            url: '/manage/file/list',
            data: {
                "file_token": fileToken
            },
            success: function(result) {
                if (result != null && result.length>0) {
                    var $list = $("#"+fileListId);
                    for(var i=0;i<result.length;i++){
                        $li = $(
                            '<div id="' + result[i].id + '" >' +
                            /* '<div class="info">' + result[i].fileName + '</div>' +*/
                            '    <a href="/manage/file/download/'+result[i].id+'" >' + result[i].fileName + '</a>'+
                            '</div>'
                        );
                        $list.append( $li );
                    }

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


}





