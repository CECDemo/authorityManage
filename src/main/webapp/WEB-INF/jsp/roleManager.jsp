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
    <title>角色管理</title>

    <!-- Bootstrap -->
    <link href="js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="js/bootstrapTable/dist/bootstrap-table.min.css" rel="stylesheet">

    <link href="js/jquery/jquery-confirm-v3.1.1/jquery-confirm-master/dist/jquery-confirm.min.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <div class="row  alert alert-info" role="alert" align="center" id="errormsg" hidden></div>
    <%--页头--%>
    <div class="row page-header" id="pageName">
        <div class="col-md-4"></div>
        <div class="col-md-4" align="center"><h2>角色信息浏览页面</h2></div>
        <div class="col-md-4" align="right" id="navCol" style="bottom :-24px;" hidden>

            <%--<p class="navbar-text">Signed in as <a href="#" class="navbar-link">Mark Otto</a></p>--%>
            <ul class="nav navbar-nav navbar-right">
                <li class="navbar-left navbar-brand">
                    <button type="button" class="btn btn-success " id="btnAddRole">
                        <i class="glyphicon glyphicon-plus">新增角色</i>
                    </button>
                </li>
                <li class="navbar-left navbar-brand">
                    <button type="button" class="btn btn-primary  " id="btnUserManager">
                        <i class="glyphicon glyphicon-user">用户管理</i>
                    </button>
                </li>
                <li class="dropdown" style="bottom:44px; left:251px">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"> <i class="glyphicon glyphicon-user" id="userName"></i><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a class="changePassword" href="javascript:void(0)">修改密码</a></li>
                        <li><a class="logout" href="javascript:void(0)">退 出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

    <%--标签页--%>
    <div class="row" id="tabRow" hidden>
        <%-- <ul class="nav nav-tabs">
             <li role="presentation" class="active"><a id="ka">卡客服角色列表</a></li>
             <li role="presentation"><a id="zong">综合客服角色列表</a></li>
             <li role="presentation"><a id="wai">外呼角色列表</a></li>
         </ul>--%>
    </div>

    <div class="row" id="tableRow">
        <table id="table"></table>
    </div>
</div> <!-- /container -->

<%--新增角色模态框--%>
<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增角色</h4>
            </div>

            <div class="modal-body" id="addRoleModal">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="saveNewRoleInfo">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<%--修改角色模态框--%>
<div class="modal fade bs-example-modal-lg" id="modifyRoleModal" tabindex="-1" role="dialog"
     aria-labelledby="modifyRoleModalModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modifyRoleModalModalLabel">修改角色</h4>
            </div>

            <div class="modal-body" id="modifyRole">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="saveRoleInfo">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<%--修改密码modal--%>
<div class="modal fade bs-example-modal-lg" id="changePasswordModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabe2">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabe2">修改密码</h4>
            </div>

            <div class="modal-body" id="changePasswordModalBody">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="savePassword">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<script src="js/jquery/jquery-3.1.1.min.js"></script>
<script src="js/jquery/jQuery.md5.js"></script>
<script src="js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/bootstrapTable/dist/bootstrap-table.min.js"></script>
<script src="js/bootstrapTable/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="js/jquery/jqueryHistroy/jquery.histroy.js"></script>
<script src="js/jquery/jquery-confirm-v3.1.1/jquery-confirm-master/dist/jquery-confirm.min.js"></script>

<script>
    var access_token = window.localStorage.getItem("access_token");
    if (access_token === null || access_token === "") {
        redirectLoginPage();
    } else {
        //alert("access_token123  ="+access_token);
    }
    var platformMark = "";
    var $table = $("table");

    var localPlatformInfoList;
    var localPlatformName = "";
    var localPlatformName2 = "";

    var LocalUserMenuDtoList = [];
    var UserMenuStr = "";
    var commonUserMenuStr = "";
    var originalUsermentStr = "";


    $(function () {
        /*登录成功后，组装标签页*/
        AssembleTabPage();

        /*登录成功后，显示导航栏*/
        $("#navCol").show();
        var userName = window.localStorage.getItem("userName");
        $("#userName").text(userName);

        /*登录成功后，默认加载一个页面的数据*/
        setTimeout(function () {
            getRoleInfoByPlatformMark();
        }, 1000);

        /*为标签添加点击事件*/
        setTimeout(function () {
            clickForTabBtn();
        }, 2000);

        setTimeout(function () {
            clickForBtnAddRole();
        }, 2100);


        //selectBtnOnChange();

        clickForBtnUserManager();

        changePassword();
        logout();

        btnSaveRoleInfo();

    });//end jquery

    function clickForTabBtn() {

        $("li[role ='presentation']").each(function (index, element) {

            $(this).click(function () {
                $("li[class ='active'] ").removeClass("active");
                $(this).addClass("active");

                getRoleInfoByPlatformMark();
            });
        });
    }//end clickForTabBtn

    function AssembleTabPage() {
        $.ajax({
            type: "get",
            url: "users/getAllPlatformMarkInfo",
            dataType: "json",
            headers: {access_token: access_token},
            success: function (res) {
                localPlatformInfoList = res;
                var tabStr = '<ul class="nav nav-tabs">'

                $.each(localPlatformInfoList, function (index, platformInfo) {

                    if (index === 0) {
                        tabStr += '<li role="presentation" class="active"><a id="' + platformInfo.shortName + '">' + platformInfo.describe + '</a></li>';
                    } else {
                        tabStr += '<li role="presentation" ><a id="' + platformInfo.shortName + '">' + platformInfo.describe + '</a></li>';
                    }

                })//end each


                tabStr += '</ul>';

                $("#tabRow").html(tabStr);

                $("#tabRow").show();
            },
            error: function (msg) {
                $("#errormsg").html("加载平台信息失败...").show(300).delay(3000).hide(300);
            }
        });//end ajax
    }//end AssembleTabPage

    function redirectLoginPage() {

        window.localStorage.setItem("access_token", "");

        var localHref = window.location.href;
        var tempIndex = localHref.lastIndexOf("/");

        var newLocalHref = localHref.substr(0, tempIndex) + "/login";

        //alert("newLocalHref = "+ newLocalHref);
        window.location.href = newLocalHref;
    }

    function changePassword() {
        var $changePassword = $("a.changePassword");
        var $btnSavePassword = $("#savePassword");

        $changePassword.click(function () {
            var changePasswordForm = '<%--修改密码--%>'
                + ' <form class="form-horizontal" id="modifyPasswordForm" >'
                + '<div class="panel panel-info">'
                + '<div class="panel-heading">'
                + '<h3 class="panel-title">修改密码</h3>'
                + '</div>'

                + '<div class="panel-body">'

                + '<div class="form-group">'
                + '<label for="oldPassword" class="col-sm-2 control-label">原始密码</label>'
                + '<div class="col-sm-10">'
                + '<input type="password" class="form-control" id="oldPassword" placeholder="原始密码">'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label for="newPassword1" class="col-sm-2 control-label">新密码</label>'
                + '<div class="col-sm-10">'
                + '<input type="password" class="form-control" id="newPassword1" placeholder="新密码">'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label for="newPassword2" class="col-sm-2 control-label">确认密码</label>'
                + '<div class="col-sm-10">'
                + '<input type="password" class="form-control" id="newPassword2" placeholder="确认密码">'
                + '</div>'
                + '</div>'

                + '</div>'
                + '</div>'
                + '</form>';


            $("#changePasswordModalBody").html(changePasswordForm);


            /*为按钮添加模态框属性*/
            $(this).attr({
                "data-toggle": "modal",
                "data-target": "#changePasswordModal"
            });


        });//end click

        $btnSavePassword.click(function () {
            var userName = $("#userName").text();
            var oldPassword = $.md5($("#oldPassword").val());
            var newPassword1 = $.md5($("#newPassword1").val());
            var newPassword2 = $.md5($("#newPassword2").val());

            var userDto = {
                "username": userName,
                "password": oldPassword,
                "newPassword1": newPassword1,
                "newPassword2": newPassword2
            }


            $.ajax({
                type: "post",
                dataType: "json",
                data: JSON.stringify(userDto),
                url: "users/modifyPassword",
                headers: {access_token: access_token},
                contentType: "application/json; charset=utf-8",
                success: function (msg) {

                    if (msg.message === "修改成功，请重新登录") {
                        alert(msg.message);
                        //$("#errormsg").html(msg.message).show(300).delay(3000).hide(300);
                        redirectLoginPage();
                    } else {
                        alert(msg.message);
                        //$("#errormsg").html(msg.message).show(300).delay(3000).hide(300);
                    }
                },
                error: function (msg) {
                    alert(msg.message);
                    //$("#errormsg").html(msg.message).show(300).delay(3000).hide(300);
                }
            });//end ajax

            //alert("userName = "+userName+"  oldPasswor = "+oldPassword+" newPassword1 ="+newPassword1+"  newPassword2  ="+newPassword2);
        });//end click

    }//end changePassword

    function logout() {
        var $logout = $("a.logout");
        $logout.click(function () {
            $.confirm({
                icon: 'glyphicon glyphicon-question-sign',
                title: '<h3>您确定要退出吗？</h3>',
                content: '按[ENTER]确认退出，按[ESC]取消此次操作',
                animation: 'top',
                theme: 'bootstrap',
                buttons: {
                    ok: {
                        text: "确定",
                        btnClass: 'btn-success',
                        keys: ['enter'],
                        action: function () {
                            redirectLoginPage();
                        }
                    },
                    cancel: {
                        text: "取消",
                        btnClass: 'btn-warning',
                        keys: ['esc'],
                        action: function () {
                        }
                    }
                },
                confirm: function () {

                },
                cancel: function () {
                }
            });  //end confirm


        });//end click
    }//end logout


    function getRoleInfoByPlatformMark() {

        platformMark = $("li[class ='active'] a").attr("id");

        $.each(localPlatformInfoList, function (index, platformInfo) {
            if (platformMark === platformInfo.shortName) {
                localPlatformName = platformInfo.describe;

                $.ajax({
                    type: "get",
                    url: "users/getUserMenuByPlatformMark",
                    dataType: "json",
                    headers: {access_token: access_token},
                    data: {platformMark: platformMark},

                    success: function (res) {
                        LocalUserMenuDtoList = res;
                    },
                    error: function (msg) {
                        $("#errormsg").html(msg).show(300).delay(3000).hide(300);
                    }

                });//end ajax

            } else {
                return;
            }
        });
        //清除数据
        // $("#table").bootstrapTable('removeAll');
        /*重新设置表格的参数*/
        $table.bootstrapTable('refreshOptions', {

            queryParams: function (params) {
                params = {
                    platformMark: platformMark
                }
                return params;
            }

        });//end refresh

        var columnArray = [{
            field: "id",
            title: "id",
            align: "center",
            visible: false
        }, {
            field: "rolesname",
            title: "角色名称0",
            align: "center",
            visible: false
        }, {
            field: "description",
            title: "角色名称",
            align: "center",
            visible: true,
            sortable: true,
            order: 'desc'
        }, {
            field: "roleComment",
            title: "角色描述",
            align: "center",
            visible: true,
            sortable: true,
            order: 'desc'
        },
            {
                field: "userMenu",
                title: "功能权限",
                align: "center",
                visible: true
            },
            {
                field: "operate",
                title: "操作",
                align: "center",
                visible: true,
                events: operateEvents,
                formatter: operateFormatter
            }

        ];

        $table.bootstrapTable({
            url: "<%=basePath%>users/getRoleInfoByPlatformMark",
            method: "get",
            ajaxOptions: {
                headers: {access_token: access_token}
            },//设置ajax的配置参数
            queryParams: queryParamsFunction,
            responseHandler: responseHandlerFunction,
            search: true,
            trimOnSearch: false,//不允许空字符串搜索
            showHeader: true,//显示列头
            columns: columnArray//列配置项
            /* onLoadSuccess: function(data){  //加载成功时执行
             alert("加载成功"+data);
             },
             onLoadError: function(){  //加载失败时执行
             //layer.msg("加载数据失败", {time : 1500, icon : 2});
             alert("加载数据失败");
             }*/

        });//end bootstrapTable

    }//end getRoleInfoByPlatformMark


    function selectChange() {


        var platformMarkSelect = $("#platformSelect option:selected").val();
        var platformShortName = "";
        var userMenuDtoList = [];
        var userMenuStr = "";
        var commonUserMenuStr2 = "";

        $.each(localPlatformInfoList, function (index, platformInfo) {
            if (platformInfo.describe === platformMarkSelect) {
                platformShortName = platformInfo.shortName;
                localPlatformName2 = platformShortName;
            }

        })//end each

        $.ajax({
            cache: false,
            async: false,
            type: "get",
            url: "users/getUserMenuByPlatformMark",
            dataType: "json",
            headers: {access_token: access_token},
            data: {platformMark: platformShortName},
            success: function (res) {
                userMenuDtoList = res;
            },
            error: function (msg) {
                $("#errormsg").html(msg).show(300).delay(3000).hide(300);
            }

        });//end ajax


        $.each(userMenuDtoList, function (index, usermenu2) {
            userMenuStr += '<label class="checkbox-inline">'
                + '<input type="checkbox" value="' + usermenu2.menuname + '"> ' + usermenu2.menudescription
                + '</label>';
        })//end each


        commonUserMenuStr2 += '<label class="checkbox-inline">'
            + '<input type="checkbox"  value="下载" > 下载'
            + '</label>'.trim();


        $("#userMenuStrAdd").html(userMenuStr);
        $("#userMenuStrAddCommon").html(commonUserMenuStr2);


        var comentStr = getSensitiveSetFieldComentStr(localPlatformName2);
        if (comentStr.length <= 0) {
            comentStr = '<ul class="list-unstyled"><label class="col-sm-4 control-label" id = "noInfo">暂无敏感信息</label></ul>';
        }

        $("#sensitiveSetField").html(comentStr);
    }//end selectChange


    function selectChangeModifyRole() {
        var platformMarkSelect = $("#platformSelectModifyRole option:selected").val();
        var platformShortName = "";
        var userMenuDtoList = [];
        var userMenuStr = "";
        var commonUserMenuStr2 = "";

        $.each(localPlatformInfoList, function (index, platformInfo) {
            if (platformInfo.describe === platformMarkSelect) {
                platformShortName = platformInfo.shortName;
                localPlatformName2 = platformShortName;
            }

        })//end each

        $.ajax({
            cache: false,
            async: false,
            type: "get",
            url: "users/getUserMenuByPlatformMark",
            dataType: "json",
            headers: {access_token: access_token},
            data: {platformMark: platformShortName},
            success: function (res) {
                userMenuDtoList = res;
            },
            error: function (msg) {
                $("#errormsg").html(msg).show(300).delay(3000).hide(300);
            }

        });//end ajax


        $.each(userMenuDtoList, function (index, usermenu2) {
            userMenuStr += '<label class="checkbox-inline">'
                + '<input type="checkbox"  name="modifyRoleCheckbox"    value="' + usermenu2.menuname + '"> ' + usermenu2.menudescription
                + '</label>';
        })//end each


        commonUserMenuStr2 += '<label class="checkbox-inline">'
            + '<input type="checkbox" name="modifyRoleCheckbox"  value="下载" > 下载'
            + '</label>'.trim();

        $("#userMenuStr123").html(userMenuStr);
        $("#userMenuStr1234").html(commonUserMenuStr2);


        var comentStr = getSensitiveSetFieldComentStr(localPlatformName2);
        if (comentStr.length <= 0) {
            comentStr = '<ul class="list-unstyled"><label class="col-sm-4 control-label" id = "noInfo">暂无敏感信息</label></ul>';
        }

        $("#sensitiveSetFieldModifyRole").html(comentStr);
    }//end selectChangeModifyRole


    function clickForBtnAddRole() {

        $("#btnAddRole").click(function () {


            UserMenuStr = "";
            commonUserMenuStr = "";

            $.each(LocalUserMenuDtoList, function (index, usermenu2) {

                UserMenuStr += '<label class="checkbox-inline">'
                    + '<input type="checkbox"   value="' + usermenu2.menuname + '"> ' + usermenu2.menudescription
                    + '</label>';

            })//end each


            commonUserMenuStr += '<label class="checkbox-inline">'
                + '<input type="checkbox"   value="下载" > 下载'
                + '</label>';


            var addRoleForm = '<%--新增角色信息的隐藏表单--%>'
                + ' <form class="form-horizontal" id="addRoleForm" >'
                + '<div class="panel panel-info">'
                + '<div class="panel-heading">'
                + '<h3 class="panel-title">新建角色信息</h3>'
                + '</div>'
                + '<div class="panel-body">'

                + '<div class="form-group">'
                + '<label for="newRoleName" class="col-sm-2 control-label">角色名称</label>'
                + '<div class="col-sm-10">'
                + '<input type="text" class="form-control" id="newRoleName" maxlength ="50" placeholder="角色名称(50个字符以内，必输项)">'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label for="roleComment" class="col-sm-2 control-label">角色描述</label>'
                + '<div class="col-sm-10">'
                + '<input type="text" class="form-control" id="roleComment" maxlength ="100" placeholder="角色描述（100个字符以内）">'
                    /*+ '<select class="form-control" id="roleComment" >'
                     + '<option>请选择角色类别</option>'
                     + '<option>管理员</option>'
                     + '<option>普通员工</option>'
                     + '</select>'*/
                + '</div>'
                + '</div>'

                + '</div>'
                + '</div>'

                + '<div class="panel panel-success">'
                + '<div class="panel-heading">'
                + '<h3 class="panel-title">配置功能信息</h3>'
                + '</div>'
                + '<div class="panel-body">';


            addRoleForm += '<div class="panel">'
                + '<div class="panel-body  bg-danger">'
                + '<div class="form-group">'
                + '<label for="platform1" class="col-sm-2 control-label">平台功能</label>'
                + '<div class="col-xs-3">'
                + '<select class="form-control" id="platformSelect"  onchange="selectChange();">';

            $.each(localPlatformInfoList, function (index, platformInfo) {

                if (platformInfo.describe === localPlatformName) {
                    addRoleForm += '<option selected>' + platformInfo.describe + '</option>';
                    localPlatformName2 = platformInfo.shortName;
                } else {
                    addRoleForm += '<option>' + platformInfo.describe + '</option>';
                }
            })//end each

            addRoleForm += '</select>'
                + '</div>'/*end col-xs-3*/

                + '<div  id = "userMenuStrAdd" >' + UserMenuStr + '</div>'
                + '</div>'/*end form-group*/

                + '</div>'/*end panel-body*/
                + '</div>';
            /*end panel*/

            addRoleForm += getPanelForSensitiveSetFieldStr(localPlatformName2);


            addRoleForm += '<div class="panel">'
                + '<div class="panel-body  bg-success">'
                + '<div class="form-group">'
                + '<label for="platform1" class="col-sm-2 control-label">通用功能</label>'
                + '<div class="col-xs-3">'

                + '</div>'/*end col-xs-3*/
                + '<div  id = "userMenuStrAddCommon" >' + commonUserMenuStr + '</div>'
                + '</div>'/*end form-group*/
                + '</div>'/*end panel-body*/
                + '</div>'/*end panel*/


                + '</div>'/*end panel-body*/
                + '</div>'/*end panel*/
                + '</form>';

            $("#addRoleModal").html(addRoleForm);


            /*为按钮添加模态框属性*/
            $(this).attr({
                "data-toggle": "modal",
                "data-target": "#myModal"
            });

        });//end click


        /*模态框的保存按钮添加点击事件*/
        $("#saveNewRoleInfo").click(function () {
            var menuStr = "";

            $("input[type ='checkbox']:checked").each(function (index, checkedElement) {
                menuStr += $(this).val() + ",";
            });//end each

            var roleName = $("#newRoleName").val();
            var roleComment = $("#roleComment").val();


            var dataSetCodeAndFlagList2 = [];

            if ($("#noInfo").length <= 0) {


                $('input[type="radio"]:checked').each(function (index, checkedElement) {
                    dataSetCodeAndFlagList2.push($(this).attr("id").substr(1) + "-" + $(this).val());
                });
            }

            var roleDto = {
                "description": roleName,
                "roleComment": roleComment,
                "platformMark": localPlatformName2,
                "usermentStr": menuStr,
                "dataSetCodeAndFlagList": dataSetCodeAndFlagList2
            }

            $.ajax({
                type: "post",
                dataType: "json",
                data: JSON.stringify(roleDto),
                url: "users/addRole",
                headers: {access_token: access_token},
                contentType: "application/json; charset=utf-8",
                success: function (msg) {
                    $("#errormsg").html(msg.message).show(300).delay(3000).hide(300);
                    getRoleInfoByPlatformMark();

                },
                error: function (msg) {
                    $("#errormsg").html(msg.message).show(300).delay(3000).hide(300);
                }
            });//end ajax


            $("#addRoleModal").html("");
        });//end click



    }//end clickForBtnAddRole


    function getPanelForSensitiveSetFieldStr(platformMark) {

        var panelForSensitiveSet = '<div class="panel" id = "panel_sensitiveSet">'
            + '<div class="panel-body  bg-info">'
            + '<div class="form-group">'
            + '<label for="sensitiveSetField" class="col-sm-2 control-label">管理敏感信息</label>'
            + '<div class="col-sm-10">'

            + '<div  id = "sensitiveSetField">';
        panelForSensitiveSet += getSensitiveSetFieldComentStr(platformMark);

        panelForSensitiveSet += '</div>'

            + '</div>'/*end col-sm-10*/
            + '</div>'/*end form-group*/
            + '</div>'/*end panel-body*/
            + '</div>'/*end panel*/;


        return panelForSensitiveSet;
    }//end getPanelForSensitiveSetFieldStr


    function getSensitiveSetFieldComentModifyRoleStr(platformMark, rolesname) {

        var panelForSensitiveSet = '<div class="panel" id = "panel_sensitiveSet">'
            + '<div class="panel-body  bg-info">'
            + '<div class="form-group">'
            + '<label for="sensitiveSetFieldModifyRole" class="col-sm-2 control-label">管理敏感信息</label>'
            + '<div class="col-sm-10">'

            + '<div  id = "sensitiveSetFieldModifyRole">';
        panelForSensitiveSet += getSensitiveSetFieldComentStr(platformMark, rolesname);

        panelForSensitiveSet += '</div>'

            + '</div>'/*end col-sm-10*/
            + '</div>'/*end form-group*/
            + '</div>'/*end panel-body*/
            + '</div>'/*end panel*/;


        return panelForSensitiveSet;
    }//end getSensitiveSetFieldComentModifyRoleStr


    function getSensitiveSetFieldComentStr(platformMark, rolesname) {

        var sensitiveSetFieldComentStr = '';

        $.ajax({
            type: "get",
            cache: false,
            async: false,
            dataType: "json",
            url: "users/getSensitiveSetField?platformMark=" + platformMark + "&&roleName=" + rolesname,
            headers: {access_token: access_token},
            contentType: "application/json; charset=utf-8",
            success: function (res) {

                if (res.length >= 1) {

                    sensitiveSetFieldComentStr = '<ul class="list-unstyled">';

                    $.each(res, function (index, dataSetBean) {
                        sensitiveSetFieldComentStr += '<li><label for="' + dataSetBean.dataSetCode + '" class="col-sm-4 control-label">' + dataSetBean.dataSetName + '</label>'
                            + '<div class="col-sm-8">';

                        if (dataSetBean.display) {
                            sensitiveSetFieldComentStr += '<span id ="' + dataSetBean.dataSetCode + '">'
                                + '<label class="radio-inline">'
                                + '<input type="radio" name="' + dataSetBean.dataSetCode + '"    id="1' + dataSetBean.dataSetCode + '"  value="可见" checked >可见 '
                                + '</label>'
                                + '<label class="radio-inline">'
                                + '<input type="radio"  name="' + dataSetBean.dataSetCode + '"   id="2' + dataSetBean.dataSetCode + '"  value="不可见" >不可见'
                                + '</label>'
                                + '</span>'
                                + '</div></li>';
                        } else {

                            sensitiveSetFieldComentStr += '<span id ="' + dataSetBean.dataSetCode + '">'
                                + '<label class="radio-inline">'
                                + '<input type="radio" name="' + dataSetBean.dataSetCode + '"    id="1' + dataSetBean.dataSetCode + '"  value="可见"  >可见 '
                                + '</label>'
                                + '<label class="radio-inline">'
                                + '<input type="radio"  name="' + dataSetBean.dataSetCode + '"   id="2' + dataSetBean.dataSetCode + '"  value="不可见"  checked>不可见'
                                + '</label>'
                                + '</span>'
                                + '</div></li>';
                        }
                    });

                    sensitiveSetFieldComentStr = sensitiveSetFieldComentStr + '</ul>';

                } else {
                    sensitiveSetFieldComentStr = '<ul class="list-unstyled"><label class="col-sm-4 control-label" id = "noInfo">暂无敏感信息</label></ul>';
                }

            },
            error: function (res) {
                sensitiveSetFieldComentStr = '<ul class="list-unstyled"><label class="col-sm-4 control-label" id = "noInfo">暂无敏感信息</label></ul>';
            }
        });//end ajax

        return sensitiveSetFieldComentStr;
    }// end getSensitiveSetFieldComentStr


    function clickForBtnUserManager() {
        var $btnUserManger = $("#btnUserManager");

        $btnUserManger.click(function () {
            //window.localStorage.setItem("access_token",access_token);

            var localHref = window.location.href;
            var tempIndex = localHref.lastIndexOf("/");

            var newLocalHref = localHref.substr(0, tempIndex) + "/userManager";

            window.location.href = newLocalHref;

        });
    }//end clickForBtnUserManager

    /*处理参数*/
    function queryParamsFunction(params) {
        params = {
            platformMark: platformMark
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

    function btnSaveRoleInfo() {

        $("#saveRoleInfo").click(function () {
            $.ajax({
                type: "post",
                dataType: "json",
                data: JSON.stringify(getRoleDtoForModifyRole()),
                url: "users/modifyRole",
                headers: {access_token: access_token},
                contentType: "application/json; charset=utf-8",
                success: function (msg) {
                    $("#errormsg").html(msg.message).show(300).delay(3000).hide(300);
                    //getRoleInfoByPlatformMark();
                    $table.bootstrapTable('refresh');
                },
                error: function (msg) {
                    $("#errormsg").html(msg.message).show(300).delay(3000).hide(300);
                }
            });//end ajax


            $("#modifyRole").html("");
        });

    }//end  btnSaveRoleInfo();

    function getRoleDtoForModifyRole(){
        var menuStr = "";
        $("input[name='modifyRoleCheckbox']:checked").each(function () {
            menuStr += $(this).attr('value') + ",";
        });

        var roleId = $("#roleId").val();
        var roleName = $("#roleName").val();
        var roleComment = $("#roleComment1").val();

        var dataSetCodeAndFlagList = [];
        if ($("#noInfo").length <= 0) {
            $('input[type="radio"]:checked').each(function (index, checkedElement) {
                dataSetCodeAndFlagList.push($(this).attr("id").substr(1) + "-" + $(this).val());
            });
        }

        var roleDto = {
            "id": roleId,
            "description": roleName,
            "roleComment": roleComment,
            "platformMark": localPlatformName2,
            "usermentStr": menuStr,
            "originalPlatformMark": platformMark,
            "originalUsermentStr": originalUsermentStr,
            "dataSetCodeAndFlagList": dataSetCodeAndFlagList
        }
        menuStr ="";

     return roleDto;
    }

    window.operateEvents = {
        'click .edit': function (e, value, row, index) {
            //alert('You click like action, row: ' + JSON.stringify(row));
            var UserMenuStr = "";
            var originalUsermentStr = "";
            var commonUserMenuStr = "";


            $.each(LocalUserMenuDtoList, function (index, usermenu2) {
                if (row.userMenu.indexOf(usermenu2.menudescription) >= 0) {
                    UserMenuStr += '<label class="checkbox-inline"> '
                        + '<input type="checkbox" name="modifyRoleCheckbox"  value="' + usermenu2.menuname + '" checked> ' + usermenu2.menudescription
                        + '</label>';

                    originalUsermentStr += usermenu2.menuname + ",";
                } else {
                    UserMenuStr += '<label class="checkbox-inline">'
                        + '<input type="checkbox"  name="modifyRoleCheckbox" value="' + usermenu2.menuname + '"> ' + usermenu2.menudescription
                        + '</label>';
                }

            })//end each

            if (row.userMenu.indexOf("下载") >= 0) {
                commonUserMenuStr += '<label class="checkbox-inline">'
                    + '<input type="checkbox"  name="modifyRoleCheckbox" value="下载" checked> 下载'
                    + '</label>';

                originalUsermentStr += "下载,";
            } else {
                commonUserMenuStr += '<label class="checkbox-inline">'
                    + '<input type="checkbox"  name="modifyRoleCheckbox"  value="下载" > 下载'
                    + '</label>';
            }

            var modifyRoleForm = '<%--修改角色信息的隐藏表单--%>'
                + ' <form class="form-horizontal" id="modifyRoleForm" >'
                + '<div class="panel panel-info">'
                + '<div class="panel-heading">'
                + '<h3 class="panel-title">修改角色信息</h3>'
                + '</div>'
                + '<div class="panel-body">'

                + '<div class="form-group" hidden>'
                + '<label for="roleId" class="col-sm-2 control-label">角色id</label>'
                + '<div class="col-sm-10">'
                + '<input type="text" class="form-control" id="roleId"   placeholder="角色id" value="' + row.id + '">'
                + '</div>'
                + '</div>'


                + '<div class="form-group">'
                + '<label for="roleName" class="col-sm-2 control-label">角色名称</label>'
                + '<div class="col-sm-10">'
                + '<input type="text" class="form-control" id="roleName" maxlength ="50"  placeholder="角色名称(50个字符以内，必输项)" value="' + row.description + '">'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label for="roleComment1" class="col-sm-2 control-label">角色描述</label>'
                + '<div class="col-sm-10">'
                + '<input type="text" class="form-control" id="roleComment1" maxlength ="100"  placeholder="角色描述（100个字以内）" value="' + row.roleComment + '">'
                    /*+ '<select class="form-control" id="roleComment" >'
                     + '<option>请选择角色类别</option>'
                     + '<option>管理员</option>'
                     + '<option>普通员工</option>'
                     + '</select>'*/
                + '</div>'
                + '</div>'

                + '</div>'
                + '</div>'

                + '<div class="panel panel-success">'
                + '<div class="panel-heading">'
                + '<h3 class="panel-title">配置功能信息</h3>'
                + '</div>'
                + '<div class="panel-body">'


            /* + '<div class="form-group">'
             + '<label for="platform1" class="col-sm-2 control-label">'+localPlatformName+'</label>'
             + '<div class="col-sm-10">' + UserMenuStr + '</div>'
             + '</div>'/!*end form-group*!/*/


            modifyRoleForm += '<div class="panel">'
                + '<div class="panel-body  bg-danger">'
                + '<div class="form-group">'
                + '<label for="platform1" class="col-sm-2 control-label">平台功能</label>'
                + '<div class="col-xs-3">'
                + '<select class="form-control" id="platformSelectModifyRole"  onchange="selectChangeModifyRole();">';

            $.each(localPlatformInfoList, function (index, platformInfo) {

                if (platformInfo.describe === localPlatformName) {
                    modifyRoleForm += '<option selected>' + platformInfo.describe + '</option>';

                    localPlatformName2 = platformMark;
                } else {
                    modifyRoleForm += '<option>' + platformInfo.describe + '</option>';
                }
            })//end each


            modifyRoleForm += '</select>'

                + '</div>'/*end col-xs-3*/
                + '<div  id = "userMenuStr123" >' + UserMenuStr + '</div>'
                + '</div>'/*end form-group*/

                + '</div>'/*end panel-body*/
                + '</div>';
            /*end panel*/

            modifyRoleForm += getSensitiveSetFieldComentModifyRoleStr(localPlatformName2, row.rolesname);

            modifyRoleForm += '<div class="panel">'
                + '<div class="panel-body  bg-success">'
                + '<div class="form-group">'
                + '<label for="platform1" class="col-sm-2 control-label">通用功能</label>'
                + '<div class="col-xs-3">'

                + '</div>'/*end col-xs-3*/
                + '<div  id = "userMenuStr1234" >' + commonUserMenuStr + '</div>'
                + '</div>'/*end form-group*/
                + '</div>'/*end panel-body*/
                + '</div>'/*end panel*/


                + '</div>'/*end panel-body*/
                + '</div>'/*end panel*/
                + '</form>';


            $("#modifyRole").html(modifyRoleForm);

            /*为按钮添加模态框属性*/
            $(this).attr({
                "data-toggle": "modal",
                "data-target": "#modifyRoleModal"
            });
        },
        'click .remove': function (e, value, row, index) {

            $.confirm({
                icon: 'glyphicon glyphicon-question-sign',
                title: '<h3>您确定要删除此角色吗？</h3>',
                content: '按[ENTER]删除此角色，按[ESC]取消此次操作',
                animation: 'top',
                theme: 'bootstrap',
                buttons: {
                    ok: {
                        text: "确定",
                        btnClass: 'btn-success',
                        keys: ['enter'],
                        action: function () {
                            removeRoleFromDataBaseAndRemoveRow(row);
                        }
                    },
                    cancel: {
                        text: "取消",
                        btnClass: 'btn-warning',
                        keys: ['esc'],
                        action: function () {
                        }
                    }
                },
                confirm: function () {

                },
                cancel: function () {
                }
            });  //end confirm

        }//end remove
    };//end widows.operateEvents

    function removeRoleFromDataBaseAndRemoveRow(row) {

        $.ajax({
            type: "get",
            dataType: "json",
            data: {
                roleName: row.rolesname,
                platformMark: platformMark
            },
            url: "users/deleteRoleByRoleName",
            headers: {access_token: access_token},
            //contentType: "application/json; charset=utf-8",
            success: function (msg) {
                $("#errormsg").html(msg.message).show(300).delay(3000).hide(300);
                $table.bootstrapTable('remove', {
                    field: 'id',
                    values: [row.id]
                });
            },
            error: function (msg) {
                $("#errormsg").html(msg.message).show(300).delay(3000).hide(300);
            }
        });//end ajax
    }//end removeRoleFromDataBaseAndRemoveRow

    /*处理bootstrapTable的响应数据*/
    function responseHandlerFunction(res) {

        $.each(res, function (index, row) {
            var userMenuDescription = "";

            $.each(row.userMenuList, function (i, userMenu) {
                userMenuDescription += userMenu.menudescription + "<br/>";
            });//end each

            row.userMenu = userMenuDescription;

        });//end each

        return res;
    }//end responseHandlerFunction

</script>
</body>
</html>