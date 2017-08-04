<%@ page pageEncoding="UTF-8" %><%-- jsp页面编码: jsp文件本身的编码--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><%--web页面显示编码: jsp的输出流在浏览器中显示的编码--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>超级管理员登录页面</title>

    <!-- Bootstrap -->
    <link href="js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="js/bootstrapTable/dist/bootstrap-table.min.css" rel="stylesheet">

    <link href="js/jquery/jquery-confirm-v3.1.1/jquery-confirm-master/dist/jquery-confirm.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <%--页头--%>
    <div class="row page-header" id="pageName">
        <div class="col-md-4"></div>
        <div class="col-md-4" align="center"><h2>超级管理员登录页面</h2></div>
        <div class="col-md-4" align="right" id="navCol" style="bottom :-24px;" hidden>

            <%--  &lt;%&ndash;<p class="navbar-text">Signed in as <a href="#" class="navbar-link">Mark Otto</a></p>&ndash;%&gt;
              <ul class="nav navbar-nav navbar-right">
                  <li class="navbar-left navbar-brand">
                      <button type="button" class="btn btn-success "  id="btnAddUser">
                          <i class="glyphicon glyphicon-plus">新增用户</i>
                      </button>
                  </li>
                  <li class="navbar-left navbar-brand">
                      <button type="button" class="btn btn-primary  "  id ="btnRoleManager">
                          <i class="glyphicon glyphicon-knight">角色管理</i>
                      </button>
                  </li>
                  <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                         aria-expanded="false"> <i class="glyphicon glyphicon-user" id="userName"></i><span
                              class="caret"></span></a>
                      <ul class="dropdown-menu" >
                          <li><a href="#">修改密码</a></li>
                          <li><a href="#">退 出</a></li>
                      </ul>
                  </li>
              </ul>--%>
        </div>
    </div>

    <%--登录表单--%>
    <div class="row " id="formRow">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form class="form-signin">
                <label for="inputUserName" class="sr-only">用户名</label>
                <input type="text" id="inputUserName" name="inputUserName" class="form-control" placeholder="用户名"
                       required autofocus>
                <label for="inputPassword" class="sr-only">密 码</label>
                <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="密 码"
                       required>
                <div><br></div>
                <%--  <div class="checkbox">
                      <label>
                          <input type="checkbox" value="remember-me"> 记住用名密码
                      </label>
                  </div>--%>
            </form>
            <button class="btn btn-lg btn-primary btn-block" type="submit" id="submit">登 录</button>
        </div>
        <div class="col-md-4"></div>
    </div>

    <%--标签页--%>
    <%-- <div class="row" id="tabRow" hidden>

         <ul class="nav nav-tabs">
             <li role="presentation" class="active"><a id="ka">卡客服用户列表</a></li>
             <li role="presentation"><a id="zong">综合客服用户列表</a></li>
             <li role="presentation"><a id="wai">外呼用户列表</a></li>
         </ul>

     </div>
 --%>

    <%--<div class="row" id="tableRow">
        <table id="table"></table>
    </div>--%>


</div> <!-- /container -->

<%--新增用户模态框--%>
<%--
<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增用户</h4>
            </div>

            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" id ="saveNewUserInfo">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
--%>

<script src="js/jquery/jquery-3.1.1.min.js"></script>
<script src="js/jquery/jQuery.md5.js"></script>
<script src="js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/bootstrapTable/dist/bootstrap-table.min.js"></script>
<script src="js/bootstrapTable/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="js/jquery/jqueryHistroy/jquery.histroy.js"></script>
<script src="js/jquery/jquery-confirm-v3.1.1/jquery-confirm-master/dist/jquery-confirm.min.js"></script>


<script>
    var baseUrl = "<%=basePath%>";
    var access_token = "";
    /* var systemFlag = 1;//（0：用户权限管理平台；1：卡客服平台；2：综合客服平台；3：外呼平台 ；）
     var $table = $("table");*/

    $(function () {
        /*处理登录按钮*/
        $("#submit").click(function () {
            var userDto ={
                username: $("#inputUserName").val(),
                password: $.md5($("#inputPassword").val())
            }

            $.ajax({
                type: "post",
                dataType: "json",
                url: baseUrl + "users/login",
                data: JSON.stringify(userDto),
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    if(res.message === "登录成功"){
                        getToken();
                    }else{
                        alert(res.message);
                        return;
                    }
                },
                error: function (res) {
                    alert("登录异常...");
                }
            });//end ajax

        });

    });//end jquery


    function getToken() {

            $.ajax({
                cache: false,
                async: false,
                type: "get",
                dataType: "json",
                url: baseUrl + "oauth/token",
                data: {
                    username: $("#inputUserName").val(),
                    password: $.md5($("#inputPassword").val()),
                    client_id: "boc_frontend",
                    client_secret: "b0c_5eCr3t",
                    grant_type: "password",
                    is_encode: true
                },
                success: function (res) {
                    access_token = res.access_token;
                    window.localStorage.setItem("access_token",access_token);
                    window.localStorage.setItem("userName",$("#inputUserName").val());

                    var localHref = window.location.href;
                    var tempIndex = localHref.lastIndexOf("/");

                    var newLocalHref = localHref.substr(0,tempIndex)+"/userManager";
                    window.location.href = newLocalHref;

                },
                error: function (res) {
                    alert("用户名或密码错误");
                }
            });//end ajax
        }//end getToken
</script>
</body>
</html>