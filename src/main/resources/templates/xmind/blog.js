$(function () {

    bindDataTables();
    bindHotDataTables();
    getUserInfo();

    function bindDataTables() {
        $("#datatable").dataTable({
            "bFilter": false,//不使用自带搜索框
            "bProcessing": true, // 是否显示取数据时的那个等待提示
            "bServerSide": true,//这个用来指明是通过服务端来取数据
            "bstatesave" :false,
            "bPaginate": true,
            "bSort": false,
            bAutoWidth: false, //自动宽度
            destroy:true,
            "sAjaxSource": "../blog/getUserXmindList",
            "fnServerData": dataTableParam.retrieveData ,// 获取数据的处理函数
            "bPaginate": true,
            "sPaginationType": "full_numbers",
            "aLengthMenu" : [20, 40, 60], //更改显示记录数选项
            "iDisplayLength" : 40, //默认显示的记录数
            "columns": [
                { "data": "id" },
                { "data": "topic" },
                { "data": "created" },
                { "data": "views" },
                { "data": "downloads" },
                { "data": "downloads" },
                /*{ "data": "thumbnailurl" },*/
                { "data": {"previewurl":"previewurl","idname":"idname","topic":"topic"}},
            ],
            "createdRow": function (row, data, index) {
                /* 设置表格中的内容居中 */
                $('td', row).attr("class", "text-center");
            },
            "fnServerParams": function (aoData) {
                var name = $('#username').val();
                //console.log("lang="+lang+",topic="+topic);
                aoData.push({
                    "name": "name",
                    "value": name
                });
            },
            "fnDrawCallback": function(){
                var api = this.api();
                var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
                api.column(0).nodes().each(function(cell, i) {
                    cell.innerHTML = startIndex + i + 1;
                });
            },
            "aoColumnDefs": [{
                "render": function(data, type, row) {
                    var da = data;
                    da = new Date(da);
                    var year = da.getFullYear();
                    var month = da.getMonth()+1;
                    var date = da.getDate();
                    var time = [year,month,date].join('-');
                    //var html = "<span class=\"time-badge\">"+ time +"</span>"
                    return time;
                },
                aTargets: [2]
            },{
                "render": function(data, type, row) {
                    return "<span class=\"label label-success\">"+ data +"</span>";
                },
                aTargets: [3]
            },{
                "render": function(data, type, row) {
                    return "<span class=\"pull-center badge bg-red\">"+ data +"</span>";
                },
                aTargets: [4]
            },{
                "render": function(data, type, row) {
                    var width = (data/1500)*100;
                    return "<div class=\"progress progress-xs\"><div class=\"progress-bar progress-bar-danger\" style=\"width: "+ width +"%\"></div></div>";
                },
                aTargets: [5]
            },/*{
                "render": function(data, type, row) {
                    return "<img alt=\"\" src=\""+ data +"\" width=\"100%\" height=\"30px;\">";
                },
                aTargets: [7]
            },*/{
                "mRender": function (data, type,row ) {
                    //
                    return "<a href='#' id='"+data+"' style='cursor: pointer;' class=\"btn btn-primary view\" onclick=\"viewModal('"+ data.previewurl + "','"+ data.idname + "','"+ data.topic +"')\">预览</a>  "+
                        "<a herf='#' id='"+data+"' style='cursor: pointer;' class=\"btn btn-danger download\" onclick=\"downModal('"+ data.idname +"')\">下载</a>";
                },
                sDefaultContent: '',
                aTargets: [6]       //列index
            }
            ],
            columnDefs: [
                { "bSortable": false, "aTargets": [ 0 ] }],
            "language": dataTableParam.lang
        });
    }

    function bindHotDataTables() {
        $("#hotdatatable").dataTable({
            "bFilter": false,//不使用自带搜索框
            "bProcessing": true, // 是否显示取数据时的那个等待提示
            "bServerSide": true,//这个用来指明是通过服务端来取数据
            "bstatesave" :false,
            "bPaginate": true,
            "bSort": false,
            bAutoWidth: false, //自动宽度
            destroy:true,
            "sAjaxSource": "../blog/getUserHotXmindList",
            "fnServerData": dataTableParam.retrieveData ,// 获取数据的处理函数
            "bPaginate": true,
            "sPaginationType": "full_numbers",
            "aLengthMenu" : [20, 40, 60], //更改显示记录数选项
            "iDisplayLength" : 40, //默认显示的记录数
            "columns": [
                { "data": "id" },
                { "data": "topic" },
                { "data": {"username":"username","id":"id"}},
                { "data": "created" },
                { "data": "views" },
                { "data": "downloads" },
                { "data": "downloads" },
                /*{ "data": "thumbnailurl" },*/
                { "data": {"previewurl":"previewurl","idname":"idname","topic":"topic"}},
            ],
            "createdRow": function (row, data, index) {
                /* 设置表格中的内容居中 */
                $('td', row).attr("class", "text-center");
            },
            "fnServerParams": function (aoData) {
                var name = $('#username').val();
                //console.log("lang="+hotlang+",topic="+hotTopic);
                aoData.push({
                    "name": "name",
                    "value": name
                });
            },
            "fnDrawCallback": function(){
                var api = this.api();
                var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
                api.column(0).nodes().each(function(cell, i) {
                    cell.innerHTML = startIndex + i + 1;
                });
            },
            "aoColumnDefs": [{
                "render": function(data, type, row) {
                    return "<a href=\"javascript:;\" title=\"查看详情\" onclick=\"javascript:author('"+ data.username +"')\">"+ data.username +"</a>";
                },
                aTargets: [2]
            },{
                "render": function(data, type, row) {
                    var da = data;
                    da = new Date(da);
                    var year = da.getFullYear();
                    var month = da.getMonth()+1;
                    var date = da.getDate();
                    var time = [year,month,date].join('-');
                    //var html = "<span class=\"time-badge\">"+ time +"</span>"
                    return time;
                },
                aTargets: [3]
            },{
                "render": function(data, type, row) {
                    return "<span class=\"label label-success\">"+ data +"</span>";
                },
                aTargets: [4]
            },{
                "render": function(data, type, row) {
                    return "<span class=\"pull-center badge bg-red\">"+ data +"</span>";
                },
                aTargets: [5]
            },{
                "render": function(data, type, row) {
                    var width = (data/1500)*100;
                    return "<div class=\"progress progress-xs\"><div class=\"progress-bar progress-bar-danger\" style=\"width: "+ width +"%\"></div></div>";
                },
                aTargets: [6]
            }/*,{
                "render": function(data, type, row) {
                    return "<img alt=\"\" src=\""+ data +"\" width=\"100%\" height=\"30px;\">";
                },
                aTargets: [7]
            }*/,{
                "mRender": function (data, type,row ) {
                    //
                    return "<a href='#' id='"+data+"' style='cursor: pointer;' class=\"btn btn-primary view\" onclick=\"viewModal('"+ data.previewurl + "','"+ data.idname + "','"+ data.topic +"')\">预览</a>  "+
                        "<a herf='#' id='"+data+"' style='cursor: pointer;' class=\"btn btn-danger download\" onclick=\"downModal('"+ data.idname +"')\">下载</a>";
                },
                sDefaultContent: '',
                aTargets: [7]       //列index
            }
            ],
            columnDefs: [
                { "bSortable": false, "aTargets": [ 0 ] }],
            "language": dataTableParam.lang
        });
    }

    // With JQuery 使用JQuery 方式调用
    $('.slider').slider({
        formatter: function (value) {
            return '更新数据量: ' + value;
        }
    }).on('slide', function (slideEvt) {
        //当滚动时触发
        //console.info(slideEvt);
        //获取当前滚动的值，可能有重复
        // console.info(slideEvt.value);
    }).on('change', function (e) {
        //当值发生改变的时候触发
        //console.info(e);
        //获取旧值和新值
        console.info(e.value.oldValue + '--' + e.value.newValue);
        $("#sliderVal").val(e.value.newValue);
    });

    $("#openmodal").on('click',function () {
        $('#modal-slider').modal({
            keyboard: false,
            backdrop: false
        });
    });

    $("#update").on('click',function () {
        var start = 0;
        var end = $("#sliderVal").val()
        $.ajax({
            type: 'post',
            url: "../api/getNewXmind",
            dataType: "json",
            async: false,
            data : {
                start : start,
                end : end
            },
            beforeSend:function(XMLHttpRequest){
                $("#slidermodal").hide();
                $(".loading").show();
                scrollTo(0,0);
                //显示文字 $("#loading").html.("<img src='/jquery/images/loading.gif' />");
            },
            success: function (result) {
                if(result.status=="1"){
                    bindDataTables();
                    alert(result.msg);
                }else{
                    alert(result.msg);
                }
            },
            complete:function(XMLHttpRequest,textStatus){
                // window.Ewin.alert('远程调用成功，状态文本值：'+textStatus);
                $(".loading").hide();
                $("#slidermodal").show();
            },
            error: function () {
                alert("下载出错");
            }
        });
    });

    $("#topicBtn").on('click',function () {
        bindDataTables();
    });

    $("#hotTopicBtn").on('click',function () {
        bindHotDataTables();
    });

    $("#downloadBtn").on('click',function () {
        $('#modal-view').modal('hide');
        var idName = $("#idName").val();
        downModal(idName);
    });
    
});

function viewModal(viewUrl, idName, topic) {
    $("#viewImg").attr("src", viewUrl);
    $("#modal-view-label").text(topic);
    $("#idName").val(idName);
    /*$('#modal-view').modal('show');*/
    $('#modal-view').modal({
        keyboard: false,
        backdrop: false
    });
}

function downModal(idName){
    $.ajax({
        type: 'post',
        url: "../xmind/getDownloadurl",
        dataType: "json",
        async: false,
        data : {
            idname : idName
        },
        beforeSend:function(XMLHttpRequest){
            $(".loading").show();
            $("#tab-content").hide();
            scrollTo(0,0);
            //显示文字 $("#loading").html.("<img src='/jquery/images/loading.gif' />");
        },
        success: function (result) {
            if(result.status=="1"){
                //$("#downloadBtn").attr("href", downUrl);
                window.location.href = result.data;
            }else{
                alert(result.msg);
            }
        },
        complete:function(XMLHttpRequest,textStatus){
            // window.Ewin.alert('远程调用成功，状态文本值：'+textStatus);
            $(".loading").hide();
            $("#tab-content").show();
        },
        error: function () {
            alert("下载出错");
        }
    });
}

function getUserInfo(){
    var name = $('#username').val();
    $.ajax({
        type: 'post',
        url: "../blog/getUserInfo",
        dataType: "json",
        async: false,
        data : {
            name : name
        },
        beforeSend:function(XMLHttpRequest){
            //$(".loading").show();
            //$("#tab-content").hide();
            //scrollTo(0,0);
            //显示文字 $("#loading").html.("<img src='/jquery/images/loading.gif' />");
        },
        success: function (result) {
            if(result.status=="1"){
                $("#userpic").attr("src", result.data.xmind.gravatar);
                $("#name").text(result.data.xmind.username);
                $("#firstname").text(result.data.xmind.firstname);
                $("#lastname").text(result.data.xmind.lastname);
                $("#count").text(result.data.count);
                $("#downs").text(result.data.downs);
                $("#views").text(result.data.views);
            }else{
                alert(result.msg);
            }
        },
        complete:function(XMLHttpRequest,textStatus){
            // window.Ewin.alert('远程调用成功，状态文本值：'+textStatus);
            //$(".loading").hide();
            //$("#tab-content").show();
        },
        error: function () {
            alert("加载发布者信息失败");
        }
    });
}
