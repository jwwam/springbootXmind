$(function () {

    //初始化验证框
    formValidator();

    function login_popup() {
        $("#loginModal").modal("show");
    }

    $(".globalLoginBtn").on("click", login_popup),function() {
        console.log("执行login");
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

    function formValidator(){
        var againSubmit = false;
        $('#login_form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                username: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '账号不能为空！'
                        },
                        emailAddress: {
                            message: '邮箱地址格式有误'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空！'
                        }
                    }
                }
            }
        }).on('success.form.bv', function(e) {
                // Prevent form submission
                e.preventDefault();
                if(againSubmit){
                    return ;
                }
                againSubmit = true;
                // Get the form instance
                var $form = $(e.target);
                // Get the BootstrapValidator instance
                var bv = $form.data('bootstrapValidator');
                // Use Ajax to submit form data
                var id = "";
                $.post($form.attr('action'), $form.serialize(), function(result) {
                    if(result.status=="0"){
                        //Ewin.alert(result.msg);
                        againSubmit = true;
                        formValidator();
                        $("#login-form-tips").text(result.msg);
                        $("#login-form-tips").show();
                        var countdown = 8;
                        var flag = setInterval(setTimeoutFunc,1000);
                        function setTimeoutFunc(){
                            if( countdown == 0 ){
                                clearInterval(flag);
                                resetForm();
                            }
                            countdown--;
                        }
                        return false;
                    }
                    if( result.status == "1" ){
                        Ewin.alert(result.msg);
                        changeLogin(result.data.username, "");
                        $('#login_form').bootstrapValidator('validate');
                        return false;
                    }
                    Ewin.alert(result.msg);
                }, 'json');
            });
    }

    $("#btnRegister").on("click",function () {
        //点击立即注册
        changeRegist();
    });

    function changeRegist(){
        $("input[name='username']").val("");
        $("input[name='password']").val("");
        $("#login_btn").hide();
        $("#btnRegister").hide();
        $("#regist_btn").show();
        $("#btnLogin").show();
        $("#loginModalLabel").text("注册");
        $("#noaccount").text("已有账号？");
        $("#login_form").attr("action","../userInfo/regist");
    }

    $("#btnLogin").on("click",function () {
        //点击立即登录
        changeLogin("", "");
    });

    function changeLogin(username, password){
        $("input[name='username']").val(username);
        $("input[name='password']").val(password);
        $("#login_btn").show();
        $("#btnRegister").show();
        $("#regist_btn").hide();
        $("#btnLogin").hide();
        $("#loginModalLabel").text("登录");
        $("#noaccount").text("还没有账号？");
        $("#login_form").attr("action","../userInfo/loginUser");
    }

    function resetForm() {
        $("#login-form-tips").val("");
        $("#login-form-tips").hide();
    }
});


function changeValidateCode() {
    var timestamp = new Date().getTime();
    $("#randImg").attr('src','../login/getRand?flag='+timestamp);
}