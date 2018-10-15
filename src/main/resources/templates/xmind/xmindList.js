$(function () {

    $("#datatable").dataTable({
        "bFilter": false,//不使用自带搜索框
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true,//这个用来指明是通过服务端来取数据
        "bPaginate": true,
        "bSort": false,
        bAutoWidth: false, //自动宽度
        destroy:true,
        "sAjaxSource": "../xmind/getXmindList",
        "fnServerData": dataTableParam.retrieveData ,// 获取数据的处理函数
        "bPaginate": true,
        "sPaginationType": "full_numbers",
        "columns": [
            { "data": "id" },
            { "data": "topic" },
            { "data": {"username":"username","id":"id"}},
            { "data": "created" },
            { "data": "views" },
            { "data": "downloads" },
            { "data": "downloads" },
            { "data": "thumbnailurl" },
            { "data": "id" },
        ],
        "createdRow": function (row, data, index) {
            /* 设置表格中的内容居中 */
            $('td', row).attr("class", "text-center");
        },
        "fnServerParams": function (aoData) {
            var wzlb =  $(".input-group .input-title").val(); //你要传递的参数
            aoData.push({
                "name": "wzlb",
                "value": wzlb
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
        },{
            "render": function(data, type, row) {
                return "<img alt=\"\" src=\""+ data +"\" width=\"100%\" height=\"30px;\">";
            },
            aTargets: [7]
        },{
            "mRender": function (data, type,row ) {
                //
                return "<a href='#' id='"+data+"' style='cursor: pointer;' class=\"btn btn-primary disableUser\">预览</a>  "+
                    "<a herf='#' id='"+data+"' style='cursor: pointer;' class=\"btn btn-danger deleteArticle\">下载</a>";
            },
            sDefaultContent: '',
            aTargets: [8]       //列index
        }
        ],
        columnDefs: [
            { "bSortable": false, "aTargets": [ 0 ] }],
        "language": dataTableParam.lang

    });

});