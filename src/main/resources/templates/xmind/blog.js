$(function () {

    bindDataTables();
    getUserInfo();

    $('#content').infinitescroll({
            /*loading: {
                msgText: "",
                img: "/images/loading.gif",
                finishedMsg: '没有新数据了...',
                selector: '.loading' //loading选择器
            },*/
            button: '.view-more-button',
            scrollThreshold: false,
            status: '.page-load-status',
            history: false,
            hideNav: '#pages',
            navSelector: "#pages",//导航的选择器，会被隐藏
            nextSelector: "#next",//包含下一页链接的选择器
            itemSelector: "li",//你将要取回的选项(内容块)
            debug: false, //启用调试信息，若启用必须引入debug.js
            dataType: 'json',//格式要和itemSelector保持一致
            template: function(data) {
                //data表示服务端返回的json格式数据，这里需要把data转换成瀑布流块的html格式，然后返回给回到函数
                //console.log(data.list);
                //return '<li><label>测试数据1</label><p>测试数据1</p></li><li><label>测试数据2</label><p>测试数据2</p></li>';
                return data.list;
            },
            maxPage: 5,//最大加载的页数
            animate: true, //当有新数据加载进来的时候，页面是否有动画效果，默认没有
            extraScrollPx: 150, //滚动条距离底部多少像素的时候开始加载，默认150
            //                bufferPx: 40, //载入信息的显示时间，时间越大，载入信息显示时间越短
            errorCallback: function() { //加载完数据后的回调函数

            },
            path: function(index) { //获取下一页方法
                var name = $('#username').val();
                return "../blog/getUserContent?page=" + index+"&name=" + name;
            },
        },
        function(newElements, data, url) { //回调函数
            //console.log(data);
            //alert(url);
        });


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

    $("#openmodal").on('click',function () {
        $('#modal-slider').modal({
            keyboard: false,
            backdrop: false
        });
    });


    $("#topicBtn").on('click',function () {
        bindDataTables();
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
                $("#count").text(result.data.count+"条");
                $("#downs").text(result.data.downs+"次");
                $("#views").text(result.data.views+"次");
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

