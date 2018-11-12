$(function () {

    bindDataTables();
    bindHotDataTables();
    getDownList();

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
            "sAjaxSource": "../xmind/getXmindList",
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
                var lang = $('#lang option:selected').val();
                var topic = $('#topicVal').val();
                //console.log("lang="+lang+",topic="+topic);
                aoData.push({
                    "name": "topic",
                    "value": topic
                },{
                    "name": "language",
                    "value": lang
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
                    return "<a href=\"../blog/index?name=" + data.username + "\" title=\"查看详情\" target=\"_blank\" onclick=\"javascript:author('"+ data.username +"')\">"+ data.username +"</a>";
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
                aTargets: [7]       //列index
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
            "sAjaxSource": "../xmind/getXmindHotList",
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
                var hotlang = $('#hotlang option:selected').val();
                var hotTopic = $('#hotTopicVal').val();
                //console.log("lang="+hotlang+",topic="+hotTopic);
                aoData.push({
                    "name": "topic",
                    "value": hotTopic
                },{
                    "name": "language",
                    "value": hotlang
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
                    return "<a href=\"../blog/index?name=" + data.username + "\" title=\"查看详情\" target=\"_blank\" onclick=\"javascript:author('"+ data.username +"')\">"+ data.username +"</a>";
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

function getDownList(){
    $.ajax({
        type: 'post',
        url: "../xmind/getDownList",
        dataType: "json",
        beforeSend:function(XMLHttpRequest){
            //$(".loading").show();
            //$("#tab-content").hide();
            //scrollTo(0,0);
            //显示文字 $("#loading").html.("<img src='/jquery/images/loading.gif' />");
        },
        success: function (result) {
            if(result.status=="1"){
                var li = "";
                for(var i=0;i<result.data.length;i++){
                    if(i==0){
                        $("#downTop1").val(result.data[i].username);
                        $("#downTopImg").attr("src", result.data[i].gravatar);
                        $("#downTopName").text("用户名："+result.data[i].username);
                        $("#downTopNum").text("下载数："+result.data[i].downloads+"条");
                    }else if(i>9){
                        li = li+"<li style='display: none;' class='displayLi'><a href=\"../blog/index?name="+ result.data[i].username +"\" target='_blank'>"+ "<span style='color: orange;'>"+ (i+1) +".&nbsp;&nbsp;&nbsp;</span>" + result.data[i].username +"<span class=\"pull-right badge bg-blue\">" + result.data[i].downloads + "条</span></a></li>"
                    }else{
                        li = li+"<li><a href=\"../blog/index?name="+ result.data[i].username +"\" target='_blank'>"+ "<span style='color: orange;'>"+ (i+1) +".&nbsp;&nbsp;&nbsp;</span>" + result.data[i].username +"<span class=\"pull-right badge bg-blue\">" + result.data[i].downloads + "条</span></a></li>"
                    }
                }
                $("#downTopList").append(li);
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
            alert("下载出错");
        }
    });

    $("#downTop1Click").on("click",function () {
        window.open("../blog/index?name="+$("#downTop1").val());
    });

    $("#moreTop").on("click",function () {
        $("#moreTop").hide();
        $("#closeMoreTop").show();
        $(".displayLi").show();
    });

    $("#closeMoreTop").on("click",function () {
        $("#moreTop").show();
        $("#closeMoreTop").hide();
        $(".displayLi").hide();
        scrollTo(0,0);
    });

    //登录操作
/*
    function login_popup() {
        $("#loginModal").modal("show")
    }

    $(".globalLoginBtn").on("click", login_popup),function() {
        var e = [];
        $(".modal").on("show.bs.modal",
            function() {
                for (var s = 0; e.length > s; s++) e[s] && (e[s].modal("hide"), e[s] = null);
                e.push($(this));
                var o = $(this),
                    a = o.find(".modal-dialog"),
                    t = $('<div style="display:table; width:100%; height:100%;"></div>');
                t.html('<div style="vertical-align:middle; display:table-cell;"></div>'),
                    t.children("div").html(a),
                    o.html(t)
            })
    } ();
*/

}
