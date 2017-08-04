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
                cache: false,
                async: false,
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
                window.location.href += "userManager";
            },
            error: function (res) {
                alert("用户名或密码错误");
            }
        });//end ajax


    }//end getToken


   /* function clickForBtnRoleManager() {
        var $btnRoleManger = $("#btnRoleManager");

        $btnRoleManger.click(function () {
            window.localStorage.setItem("access_token",access_token);

            var localHref = window.location.href;
            var tempIndex = localHref.lastIndexOf("/");

            var newLocalHref = localHref.substr(0,tempIndex)+"/roleManager";
            window.location.href = newLocalHref;


            /!*alert("access_token ="+access_token+"   window.localStorage.access_token = "+window.localStorage.getItem("access_token"));

            $.post({
                dataType:"json",
                url:"users/roleManager",
                headers: {access_token: access_token},
                success:function (res) {
                    alert("success");
                },
                error:function (msg) {
                    alert("error");
                }

            });//end post
             *!/

        });
    }//end clickForBtnRoleManager

    /!*登录成功后，跳转到首页*!/
    function goHome() {
        $.ajax({
            type: "post",
            dataType: "json",
            url: "<basePath>home",
            data: {
                access_token: access_token,
                username: $("#inputUserName").val()
            },
            success: function (rep) {
                alert("success" + rep.access_token);
            },
            error: function (msg) {
                alert("error msg");
            }
        });//end ajax


    }


    // /!*获得用户列表*!/
    function getAllUserInfo() {
        $.ajax({
            type: "get",
            //dataType :"json",
            url: baseUrl + "users/getAllUserInfo",
            headers: {access_token: access_token},
            success: function (res) {

                alert("success = " + res.access_token);
            },
            error: function (res) {
                alert("error");
            }
        });


    }//end getAllUserInfo//


    // /!*根据用户平台获得用户信息及其对应的角色信息*!/
    function getUserInfoBySystemFlag(flag) {
        //清除数据
        // $("#table").bootstrapTable('removeAll');

        /!*判断平台标志*!/
        switch (flag) {
            case "zong" : {
                systemFlag = "synthetical";
                break;
            }
            case "wai" : {
                systemFlag = "outbound";
                break;
            }
            default: {
                systemFlag = "cardservice";
            }
        }

        /!*重新设置表格的参数*!/
        $("#table").bootstrapTable('refreshOptions', {

            queryParams: function (params) {
                params = {
                    systemFlag: systemFlag
                }
                return params;
            }

        });//end refresh



        $("#table").bootstrapTable({
            url: "<basePath%>users/getUserInfoBySystemFlag",
            method: "get",
            ajaxOptions: {
                headers: {access_token: access_token}
            },//设置ajax的配置参数
            queryParams: queryParamsFunction,
            responseHandler: responseHandlerFunction,
            search: true,
            trimOnSearch: false,//不允许空字符串搜索
            showHeader: true,//显示列头
            columns: [{
                field: "id",
                title: "id",
                align: "center",
                visible: false
            }, {
                field: "uuid",
                title: "uuid",
                align: "center",
                visible: false
            }, {
                field: "username",
                title: "用户名称",
                align: "center",
                visible: true,
                sortable: true,//True to allow the column can be sorted.
                order: 'desc'//The default sort order, can only be 'asc' or 'desc'.
            }, {
                field: "rolesname",
                title: "角色名称",
                align: "center",
                visible: true
            }, {
                field: "description",
                title: "角色描述",
                align: "center",
                visible: true
            }, {
                field: "operate",
                title: "操作",
                align: "center",
                visible: true,
                events: operateEvents,
                formatter: operateFormatter
            }

            ],//列配置项
            /!* onLoadSuccess: function(data){  //加载成功时执行
             alert("加载成功"+data);
             },
             onLoadError: function(){  //加载失败时执行
             //layer.msg("加载数据失败", {time : 1500, icon : 2});
             alert("加载数据失败");
             }*!/

        });//end bootstrapTable

    }//end getUserInfoBySystemFlag

    /!*处理参数*!/
    function queryParamsFunction(params) {
        params = {
            systemFlag: "synthetical"
        };
        return params;
    }//end queryParamsFunction

    function operateFormatter(value, row, index) {

        return [
            '<a class ="edit" href="javascript:void(0)" title="编辑" >',
            '<i class="glyphicon glyphicon-edit"></i>',
            '</a>  ',
            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
            '<a  class= "remove" href="javascript:void(0)" title="删除">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join('');

    }//end operation

    window.operateEvents = {
        'click .edit': function (e, value, row, index) {
            alert('You click like action, row: ' + JSON.stringify(row));
        },

        'click .remove': function (e, value, row, index) {

          $.confirm({
                icon: 'glyphicon glyphicon-question-sign',
                title: '<h3>您确定要删除此用户吗？</h3>',
                content: '按[ENTER]删除此用户，按[ESC]取消此次操作',
                animation: 'top',
                theme :'bootstrap',
                buttons: {
                    ok: {
                        text: "确定",
                        btnClass: 'btn-success',
                        keys: ['enter'],
                       action: function(){
                            if(removeUserFromDataBase()){
                                $table.bootstrapTable('remove', {
                                    field: 'id',
                                    values: [row.id]
                                });
                            }else{
                                alert("移除失败，请联系工程师...");
                           }


                        }
                    },
                    cancel:{
                        text: "取消",
                        btnClass: 'btn-warning',
                        keys: ['esc'],
                        action: function(){
                        }
                    }
                },
                confirm : function () {

                },
                cancel: function () {
                }
            });  //end confirm

        }//end remove
    };//end widows.operateEvents

    function removeUserFromDataBase() {

        return true;
    }//end removeUserFromDataBase


    /!*处理bootstrapTable的响应数据*!/
    function responseHandlerFunction(res) {

        $.each(res, function (index, row) {
            var roleName = "";
            var roleDescription = "";

            $.each(row.roleList, function (i, role) {
                roleName += role.rolesname + "<br/>";
                roleDescription += role.description + "<br/>";
            });//end each

            row.rolesname = roleName;
            row.description = roleDescription;

        });//end each

        return res;
    }//end responseHandlerFunction


    /!*
     * 替换当前url 并不导致浏览器页面刷新
     * name 参数名
     * value 参数值
     *!/
   /!* function replaceUrl(name, value) {
        var obj = new Object();
        obj[name] = value;
        obj.rand = Math.random();
        History.replaceState(obj, '', '?' + name + '=' + value);
    }*!/


    /!*根据用户名称判断用户是否存在*!/
   /!* function getUserByName() {
        $("#checkUserByUserName").click(function () {

            $.ajax({
                type: "get",
                dataType: "json",
                url: baseUrl + "users/checkUserByUserName",
                data: {userName: $("#inputUserName").val()},
                headers: {access_token: access_token},
                success: function (res) {

                    alert("success = " + res);
                },
                error: function (res) {
                    alert("error");
                }
            });

        });


    }*!/







    /!*为新增用户按钮添加点击事件*!/

    function clickForBtnAddUser() {
        $("#btnAddUser").click(function () {

            var addUserForm = '--新增用户信息的隐藏表单--'
                + ' <form class="form-horizontal" id="addUserForm" >'

                + '<div class="panel panel-info">'
                + '<div class="panel-heading">'
                + '<h3 class="panel-title">新增用户信息</h3>'
                + '</div>'
                + '<div class="panel-body">'

                + '<div class="form-group">'
                + '<label for="newUserName" class="col-sm-2 control-label">用户名</label>'
                + '<div class="col-sm-10">'
                + '<input type="text" class="form-control" id="newUserName" placeholder="用户名">'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label for="newUserPassword" class="col-sm-2 control-label">密码</label>'
                + '<div class="col-sm-10">'
                + '<input type="password" class="form-control" id="newUserPassword" placeholder="密码">'
                + '</div>'
                + '</div>'

                + '</div>'
                + '</div>'






                + '<div class="panel panel-success">'
                + '<div class="panel-heading">'
                + '<h3 class="panel-title">配置用户角色</h3>'
                + '</div>'
                + '<div class="panel-body">'

                + '<div class="form-group">'
                + '<label for="platform1" class="col-sm-2 control-label">卡客服平台</label>'
                + '<div class="col-sm-10">'
                + '<select class="form-control" id="platform1" >'
                + '<option>请选择角色</option>'
                + '<option>1</option>'
                + '<option>2</option>'
                + '<option>3</option>'
                + '<option>4</option>'
                + '<option>5</option>'
                + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label for="platform2" class="col-sm-2 control-label">综合客服平台</label>'
                + '<div class="col-sm-10">'
                + '<select class="form-control" id="platform2" >'
                + '<option>请选择角色</option>'
                + '<option>1</option>'
                + '<option>2</option>'
                + '<option>3</option>'
                + '<option>4</option>'
                + '<option>5</option>'
                + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label for="platform3" class="col-sm-2 control-label">外呼平台</label>'
                + '<div class="col-sm-10">'
                + '<select class="form-control" id="platform3" >'
                + '<option>请选择角色</option>'
                + '<option>1</option>'
                + '<option>2</option>'
                + '<option>3</option>'
                + '<option>4</option>'
                + '<option>5</option>'
                + '</select>'
                + '</div>'
                + '</div>'


                + '</div>'
                + '</div>'
                + '</form>';



            $(".modal-body").html(addUserForm);


            /!*为按钮添加模态框属性*!/
            $(this).attr({
                "data-toggle":"modal" ,
                "data-target":"#myModal"
            });







            /!*$.ajax({
                type: "post",
                dataType: "json",
                url: "<basePath>home",
                data:{token :access_token,userName:"wbliu"},
                //headers: {access_token: access_token},
                success: function (res) {
                    alert("success");
                },
                error: function (res) {
                    alert("error");
                }
            });//end ajax
*!/



        });//end click


        /!*模态框的保存按钮添加点击事件*!/
        $("#saveNewUserInfo").click(function () {

            var platform1SelectedRolesValue = $("#platform1 option:selected").val();


             var newUserInfo = {
                 "userName" :$("#newUserName").val(),
                 "userPassword" :$("#newUserPassword").val()
             }


            alert("保存成功！"+newUserInfo.userName+"_"+newUserInfo.userPassword+"_"+platform1SelectedRolesValue);
        });






    }//end clickForBtnAddUser*/


</script>


</body>
</html>