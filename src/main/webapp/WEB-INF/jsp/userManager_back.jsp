<%@ page pageEncoding="UTF-8"%><%-- jsp页面编码: jsp文件本身的编码--%>
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
    <title>用户管理</title>

    <!-- Bootstrap -->
    <link href="js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="js/bootstrapTable/dist/bootstrap-table.min.css" rel="stylesheet">

    <link href="js/jquery/jquery-confirm-v3.1.1/jquery-confirm-master/dist/jquery-confirm.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row  alert alert-info" role="alert"  align="center" id ="errormsg" hidden></div>
    <%--页头--%>
    <div class="row page-header" id="pageName">
        <div class="col-md-4"></div>
        <div class="col-md-4" align="center"><h2>用户信息浏览页面</h2></div>
        <div class="col-md-4" align="right" id="navCol" style="bottom :-24px;" hidden>

            <%--<p class="navbar-text">Signed in as <a href="#" class="navbar-link">Mark Otto</a></p>--%>
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
                <li class="dropdown" style="bottom:44px; left:251px">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"> <i class="glyphicon glyphicon-user" id="userName"></i><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" >
                        <li><a  class ="changePassword" href="javascript:void(0)">修改密码</a></li>
                        <li><a  class ="logout" href="javascript:void(0)">退 出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <%--标签页--%>
    <div class="row" id="tabRow" hidden>
       <%-- <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a id="ka">卡客服用户列表</a></li>
            <li role="presentation"><a id="zong">综合客服用户列表</a></li>
            <li role="presentation"><a id="wai">外呼用户列表</a></li>
        </ul>--%>
    </div>

    <div class="row" id="tableRow">
        <table id="table"></table>
    </div>
</div> <!-- /container -->

<%--新增用户模态框--%>
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


<%--修改用户模态框--%>
<div class="modal fade bs-example-modal-lg" id="modifyUserInfo" tabindex="-1" role="dialog" aria-labelledby="modifyUserInfoModalLabe">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modifyUserInfoModalLabe">修改用户信息</h4>
            </div>

            <div class="modal-body" id ="modifyUserModalBody">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" id ="saveUserInfo">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<%--修改密码modal--%>
<div class="modal fade bs-example-modal-lg" id="changePasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabe2">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabe2">修改密码</h4>
            </div>

            <div class="modal-body" id ="changePasswordModalBody">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" id ="savePassword">保存</button>
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

    if(access_token === null || access_token ===""){
        redirectLoginPage();
    }else{
        //alert("access_token123  ="+access_token);
    }

    var systemFlag = "";
    var baseUrl = "<%=basePath%>";
    var $table = $("table");

    var platformInfoAndRolesStr;
    var platformInfoAndRolesStr2;
    var LocalRolesDtoList;

    var localPlatformInfoList;

    var roleDescriptionStr;


    $(function () {
        /*登录成功后，组装标签页*/
        AssembleTabPage();

        /*登录成功后，显示导航栏*/
        $("#navCol").show();

        var userName = window.localStorage.getItem("userName");
        $("#userName").text(userName);

        /*登录成功后，默认加载一个页面的数据*/
        setTimeout(function () {
            getUserInfoBySystemFlag();
        },1000);

        /*为标签添加点击事件*/
        setTimeout(function () {
            clickForTabBtn();
        },1500);



        /*为新增用户按钮添加点击事件*/
         clickForBtnAddUser();
         clickForBtnRoleManager();

        changePassword();
        logout();


        modifySaveBtnClick();

    });//end jquery


    function clickForTabBtn() {
        $("li[role ='presentation']").each(function (index, element) {

            $(this).click(function () {
                $("li[class ='active'] ").removeClass("active");
                $(this).addClass("active");

                /*发送相应的请求*/
               // var flag = $("li[class ='active'] a").attr("id");


                getUserInfoBySystemFlag();
            });
        });

    }//end clickForTabBtn


    function AssembleTabPage() {
        $.ajax({
            type :"get",
            url:"users/getAllPlatformMarkInfo",
            dataType :"json",
            headers:{access_token:access_token},
            success:function (res) {
                localPlatformInfoList = res;
                var tabStr = '<ul class="nav nav-tabs">'

                $.each(localPlatformInfoList, function (index, platformInfo) {

                    if(index ===0){
                        tabStr +='<li role="presentation" class="active"><a id="'+platformInfo.shortName+'">'+platformInfo.describe+'</a></li>';
                    }else{
                        tabStr +='<li role="presentation" ><a id="'+platformInfo.shortName+'">'+platformInfo.describe+'</a></li>';
                    }

                })//end each


                tabStr +='</ul>';

                $("#tabRow").html(tabStr);

                $("#tabRow").show();
            },
            error:function (msg) {
                $("#errormsg").html("加载平台信息失败...").show(300).delay(3000).hide(300);
            }
        });//end ajax
    }//end AssembleTabPage

    function  redirectLoginPage() {

        window.localStorage.setItem("access_token","");

        var localHref = window.location.href;
        var tempIndex = localHref.lastIndexOf("/");

        var newLocalHref = localHref.substr(0,tempIndex)+"/login";

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
                "data-toggle":"modal" ,
                "data-target":"#changePasswordModal"
            });

        });//end click

        $btnSavePassword.click(function () {
            var userName = $("#userName").text();
            var oldPassword =$.md5($("#oldPassword").val());
            var newPassword1 = $.md5($("#newPassword1").val());
            var newPassword2 = $.md5($("#newPassword2").val());

           var userDto = {
                "username":userName,
                "password":oldPassword,
                "newPassword1":newPassword1,
                "newPassword2":newPassword2
            }


            $.ajax({
                type:"post",
                dataType:"json",
                data: JSON.stringify(userDto),
                url:"users/modifyPassword",
                headers:{access_token : access_token},
                contentType: "application/json; charset=utf-8",
                success:function (msg) {

                    if(msg.message ==="修改成功，请重新登录"){
                        alert(msg.message);
                        //$("#errormsg").html(msg.message).show(300).delay(3000).hide(300);
                        redirectLoginPage();
                    }else{
                        alert(msg.message);
                        //$("#errormsg").html(msg.message).show(300).delay(3000).hide(300);
                    }
                },
                error:function (msg) {
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
                theme :'bootstrap',
                buttons: {
                    ok: {
                        text: "确定",
                        btnClass: 'btn-success',
                        keys: ['enter'],
                        action: function(){
                            redirectLoginPage();
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


        });//end click
    }//end logout


    function clickForBtnAddUser() {

        $.ajax({
            type :"get",
            url:"users/getAllRoles",
            dataType :"json",
            headers:{access_token:access_token},
            success:function (res) {
                LocalRolesDtoList = res;

                platformInfoAndRolesStr = "";

                $.each(LocalRolesDtoList, function (index, rolesDto) {
                    platformInfoAndRolesStr += '<div class="form-group">'
                        + '<label for="platform' + rolesDto.platforminfo.level + '" class="col-sm-2 control-label">' + rolesDto.platformMark + '</label>'
                        + '<div class="col-sm-10">'
                        + '<select class="form-control" id="platform' + rolesDto.platforminfo.level + '" >'
                        + '<option>未分配角色</option>'
                        + '<option>无</option>';

                    $.each(rolesDto.rolesList, function (roleIndex, role) {
                        platformInfoAndRolesStr += '<option>' + role.description + '</option>';
                    })//end inner each

                    platformInfoAndRolesStr += '</select>'
                        + '</div>'
                        + '</div>';
                })//end each
            },
            error:function (msg) {
                $("#errormsg").html(msg).show(300).delay(3000).hide(300);
            }

        });//end ajax

        $("#btnAddUser").click(function () {
            //alert("platformInfoAndRolesStr = "+platformInfoAndRolesStr);

            <%--新增用户信息的隐藏表单--%>
            var addUserForm =
                ' <form class="form-horizontal" id="addUserForm" >'
                + '<div class="panel panel-info">'
                + '<div class="panel-heading">'
                + '<h3 class="panel-title">新增用户信息</h3>'
                + '</div>'
                + '<div class="panel-body">'

                + '<div class="form-group">'
                + '<label for="newUserName" class="col-sm-2 control-label">用户名</label>'
                + '<div class="col-sm-10">'
                + '<input type="text" class="form-control" id="newUserName"   maxlength ="50" placeholder="用户名（50个字符以内，必输项）">'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label for="note" class="col-sm-2 control-label">友情提示：</label>'
                + '<div class="col-sm-10">'
                //+ '<input type="password" class="form-control" id="newUserPassword" placeholder="密码">'
                +'  <p class="text-danger"  id ="note">     新增用户密码默认为"123456",添加成功后，请提醒用户修改密码      </p>'
                + '</div>'
                + '</div>'

              /*  + '<div class="form-group">'
                + '<label for="newUserPassword2" class="col-sm-2 control-label">确认密码</label>'
                + '<div class="col-sm-10">'
                + '<input type="password" class="form-control" id="newUserPassword2" placeholder="确认密码">'
                + '</div>'
                + '</div>'*/

                + '</div>'
                + '</div>'



                + '<div class="panel panel-success">'
                + '<div class="panel-heading">'
                + '<h3 class="panel-title">配置用户角色</h3>'
                + '</div>'
                + '<div class="panel-body" id ="rolePanelBody" >'
                + platformInfoAndRolesStr
                + '</div>'
                + '</div>'
                + '</form>';



            $(".modal-body").html(addUserForm);
            /*为按钮添加模态框属性*/
            $(this).attr({
                "data-toggle":"modal" ,
                "data-target":"#myModal"
            });

        });//end click


        /*模态框的保存按钮添加点击事件*/
        $("#saveNewUserInfo").click(function () {
            var userDto = getNewUserInfo();

            $.ajax({
                type :"post",
                url:"users/addNewUser",
                dataType :"json",
                headers:{access_token:access_token},
                contentType: "application/json; charset=utf-8",
                data : JSON.stringify(userDto),

                success: function (res) {

                    //alert(res.message);
                    $("#errormsg").html(res.message).show(300).delay(3000).hide(300);
                    //var flag = $("li[class ='active'] a").attr("id");
                    getUserInfoBySystemFlag();


                },
                error:function (msg) {
                    //alert(msg.message);
                    $("#errormsg").html(res.message).show(300).delay(3000).hide(300);
                }

            });//end ajax





            //alert("保存成功！"+newUserInfo.userName+"_"+newUserInfo.userPassword+"_"+platform1SelectedRolesValue);
        });

    }//end clickForBtnAddUser

    function getNewUserInfo() {

        var rolesDtoListTemp = [];
        var platformSelectedRolesValue;
        //$("#platform1 option:selected").val();

        $.each(LocalRolesDtoList, function (roleIndex, roleDto) {
            platformSelectedRolesValue = $("#platform" + roleDto.platforminfo.level + " option:selected").val();
            //alert("platformSelectedRolesValue = "+platformSelectedRolesValue+" roleDto.platforminfo.level = "+roleDto.platforminfo.level);
            if ("未分配角色" === platformSelectedRolesValue || "无" === platformSelectedRolesValue) {
                //alert("未分配角色123 = "+platformSelectedRolesValue);
                return;
            } else {
                //alert("roleDto.rolesList.getItem(platformSelectedRolesValue) = "+roleDto.rolesList.getItem(platformSelectedRolesValue));

                var role1;
                $.each(roleDto.rolesList, function (roleIndex, role) {
                    if (role.description === platformSelectedRolesValue) {
                        role1 = role;
                        //alert("roleDescrip = "+role1.description);
                    } else {
                        //alert("no ");
                        return
                    }
                });//end each

                var roleDtoTemp = {
                    "platformMark": roleDto.platformMark,
                    "platforminfo": roleDto.platforminfo,
                     "rolesList": [role1]
                }
                rolesDtoListTemp.push(roleDtoTemp);
            }
        })//end each

        var userDto = {
            "userName" :  $("#newUserName").val(),
            "newPassword1": $.md5("123456"),
            "newPassword2": $.md5("123456"),
            /*"newPassword1": $.md5($("#newUserPassword").val()),
            "newPassword2": $.md5($("#newUserPassword2").val()),*/
            "rolesDtoList" : rolesDtoListTemp
        }
        //alert("userDto = "+userDto.rolesDtoList[0].rolesList[0].description);

        return userDto;
    }//end getNewuserInfo

    function clickForBtnRoleManager() {
        var $btnRoleManger = $("#btnRoleManager");

        $btnRoleManger.click(function () {
           // window.localStorage.setItem("access_token",access_token);

            var localHref = window.location.href;
            var tempIndex = localHref.lastIndexOf("/");

            var newLocalHref = localHref.substr(0,tempIndex)+"/roleManager";
            window.location.href = newLocalHref;
        });
    }//end clickForBtnRoleManager

    // /*根据用户平台获得用户信息及其对应的角色信息*/
    function getUserInfoBySystemFlag() {
        //清除数据
        var flag = $("li[class ='active'] a").attr("id");
        systemFlag = flag;

        /*重新设置表格的参数*/
        $table.bootstrapTable('refreshOptions', {
            queryParams: function (params) {
                params = {
                    systemFlag: systemFlag
                }
                return params;
            }

        });//end refresh



        $table.bootstrapTable({
            url: "<%=basePath%>users/getUserInfoBySystemFlag",
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
                order: 'asc'//The default sort order, can only be 'asc' or 'desc'.
            }, {
                field: "rolesname",
                title: "角色名称0",
                align: "center",
                visible: false
            }, {
                field: "description",
                title: "角色名称",
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
            /* onLoadSuccess: function(data){  //加载成功时执行
             alert("加载成功"+data);
             },
             onLoadError: function(){  //加载失败时执行
             //layer.msg("加载数据失败", {time : 1500, icon : 2});
             alert("加载数据失败");
             }*/

        });//end bootstrapTable

    }//end getUserInfoBySystemFlag

    /*处理参数*/
    function queryParamsFunction(params) {
        params = {
            systemFlag: systemFlag
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

    function modifySaveBtnClick() {

        /*为修改用户信息模态框的保存按钮添加点击事件*/
        $("#saveUserInfo").click(function () {

            var userDto = getModifyUserInfo();

            //alert("保存成功！username :"+userDto.username+ "  _  uuid :"+userDto.uuid);

            //alert("userDto.description = "+userDto.rolesDtoList[0].rolesList[0].description +"  userName = "+userDto.userName);

             $.ajax({
             type :"post",
             url:"users/modifyUser",
             dataType :"json",
             headers:{access_token:access_token},
             contentType: "application/json; charset=utf-8",
             data : JSON.stringify(userDto),
             success: function (res) {
                 $("#errormsg").html(res.message).show(300).delay(3000).hide(300);

                // var flag = $("li[class ='active'] a").attr("id");
                 getUserInfoBySystemFlag();
             },
             error:function (msg) {
             //alert(msg.message);
                 $("#errormsg").html(res.message).show(300).delay(3000).hide(300);
             }
             });//end ajax

            //alert("保存成功！username :"+userDto.username+ "  _  uuid :"+userDto.uuid+"  _   decription :"+ userDto.rolesDtoList[0].platformMark);
        });
    }//end modifySaveBtnClick

    function getModifyUserInfo() {
        var rolesDtoListTemp = [];
        var platformSelectedRolesValue;
        //$("#platform1 option:selected").val();

        $.each(LocalRolesDtoList, function (roleIndex, roleDto) {
            platformSelectedRolesValue = $("#modifyPlatform" + roleDto.platforminfo.level + " option:selected").val();
            //alert("platformSelectedRolesValue = "+platformSelectedRolesValue+" roleDto.platforminfo.level = "+roleDto.platforminfo.level);
            if ("未分配角色" === platformSelectedRolesValue || "无" === platformSelectedRolesValue) {
                //alert("未分配角色123 = "+platformSelectedRolesValue);
                return;
            } else {
                //alert("roleDto.rolesList.getItem(platformSelectedRolesValue) = "+roleDto.rolesList.getItem(platformSelectedRolesValue));

                var role1;
                $.each(roleDto.rolesList, function (roleIndex, role) {
                    if (role.description === platformSelectedRolesValue) {
                        role1 = role;
                        //alert("roleDescrip = "+role1.description);
                    } else {
                        //alert("no ");
                        return
                    }
                });//end each

                var roleDtoTemp = {
                    "platformMark": roleDto.platformMark,
                    "platforminfo": roleDto.platforminfo,
                    "rolesList": [role1]
                }
                rolesDtoListTemp.push(roleDtoTemp);
            }
        })//end each


        var resetPassordFlag = $('input[name="inlineRadioOptions"]:checked ').val();
        var resetPassword = "";
         if(resetPassordFlag === "是"){
             resetPassword = $.md5("123456");
         }

       //  alert("resetPassordFlag = "+resetPassordFlag+"   resetPassword = "+ resetPassword);

        var userDto = {
            "userName" :  $("#username").val(),
             "uuid" :     $("#uuid").val(),
            "newPassword1" : resetPassword ,
            "rolesDtoList" : rolesDtoListTemp
        }


        //alert("username = "+userDto.username+"  uuid ="+userDto.uuid +"    resetPassordFlag ="+resetPassordFlag);

        //alert("userDto.description = "+userDto.rolesDtoList[0].rolesList[0].description +"  userName = "+userDto.userName);
        return userDto;
    }//end ModifyUserInfo




    window.operateEvents = {
        'click .edit': function (e, value, row, index) {
           //alert('You click like action, row: ' + JSON.stringify(row)+" event = "+e +"  value ="+value+"  index ="+index);
            //alert("username ="+row.username+" roleName = "+row.rolesname+"  roleDescription = "+row.description);
            var roleDtoList =[];

            roleDescriptionStr ="";

            $.ajax({
                cache: false,
                async: false,// 太关键了，学习了，同步和异步的参数
                type:"get",
                dataType:"json",
                data: {userName: row.username},
                url:"users/getAllRoleDescriptionByUserName",
                headers:{access_token : access_token},
                contentType: "application/json; charset=utf-8",
                success:function (resRoleDtoList) {
                    roleDtoList = resRoleDtoList;
                },
                error:function (msg) {
                    //alert(msg.message);
                }
            });//end ajax

            $.each(roleDtoList,function (index,roleDto) {
                roleDescriptionStr += roleDto.description;
            });//end each

            platformInfoAndRolesStr2 = "";

            $.each(LocalRolesDtoList, function (index, rolesDto) {
                platformInfoAndRolesStr2 += '<div class="form-group">'
                    + '<label for="modifyPlatform' + rolesDto.platforminfo.level + '" class="col-sm-2 control-label">' + rolesDto.platformMark + '</label>'
                    + '<div class="col-sm-10">'
                    + '<select class="form-control" id="modifyPlatform' + rolesDto.platforminfo.level + '" >'
                    + '<option>未分配角色</option>'
                    + '<option>无</option>';

                $.each(rolesDto.rolesList, function (roleIndex, role) {
                    if(roleDescriptionStr.indexOf(role.description) >=0){
                        platformInfoAndRolesStr2 += '<option  selected>'+ role.description + '</option>';
                    }else{
                        platformInfoAndRolesStr2 += '<option>' + role.description + '</option>';
                    }
                });//end inner each

                platformInfoAndRolesStr2 += '</select>'
                    + '</div>'
                    + '</div>';
            });//end each




            <%--修改用户信息的隐藏表单--%>
            var modifyUserForm = '<form class="form-horizontal" id="modifyUserForm" >'
                + '<div class="panel panel-info">'
                + '<div class="panel-heading">'
                + '<h3 class="panel-title">修改用户信息</h3>'
                + '</div>'
                + '<div class="panel-body">'

                + '<div class="form-group">'
                + '<label for="username" class="col-sm-2 control-label">用户名</label>'
                + '<div class="col-sm-10">'
                + '<input type="text" class="form-control" id="username"  maxlength ="50" placeholder="用户名（50个字符以内，必输项）"  value="'+row.username+'"/>'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label for="resetPassword" class="col-sm-2 control-label">重置密码</label>'
                + '<div class="col-sm-10">'
                +    '<span id ="resetPassword">'
                +        '<label class="radio-inline">'
                +            '<input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="是">是 '
                +        '</label>'
                +       '<label class="radio-inline">'
                +           '<input type="radio"  name="inlineRadioOptions" id="inlineRadio2" value="否" checked> 否'
                +       '</label>'
                +      '</span>'
                + '</div>'
                + '</div>'



                + '<div class="form-group" hidden>'
                + '<label for="uuid" class="col-sm-2 control-label">uuid</label>'
                + '<div class="col-sm-10">'
                + '<input type="text" class="form-control" id="uuid" placeholder="uuid" value="'+row.uuid+'"/>'
                + '</div>'
                + '</div>'
                + '</div>'
                + '</div>'



                + '<div class="panel panel-success">'
                + '<div class="panel-heading">'
                + '<h3 class="panel-title">修改用户角色信息</h3>'
                + '</div>'
                + '<div class="panel-body" id ="modifyRolePanelBody" >'
                + platformInfoAndRolesStr2
                + '</div>'
                + '</div>'
                + '</form>';

            $("#modifyUserModalBody").html(modifyUserForm);

            /*为按钮添加模态框属性*/
            $(this).attr({
                "data-toggle":"modal" ,
                "data-target":"#modifyUserInfo"
            });

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
                            removeUserFromDataBaseAndRemoveRow(row);
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

    function removeUserFromDataBaseAndRemoveRow(row) {

        $.ajax({
            type:"get",
            dataType:"json",
            data: { userName: row.username },
            url:"users/deleteUserByUserName",
            headers:{access_token : access_token},
            //contentType: "application/json; charset=utf-8",
            success:function (msg) {
                //alert(msg.message);

                $("#errormsg").html(msg.message).show(300).delay(3000).hide(300);
                $table.bootstrapTable('remove', {
                    field: 'id',
                    values: [row.id]
                });
            },
            error:function (msg) {
                //alert(msg.message);
                $("#errormsg").html(msg.message).show(300).delay(3000).hide(300);
            }
        });//end ajax
    }//end removeUserFromDataBase

    /*处理bootstrapTable的响应数据*/
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

  /*  function getAllRoleDescriptionByUserName(row){

        roleDescriptionStr ="";

        $.ajax({
            type:"get",
            dataType:"json",
            data: { userName: row.username },
            url:"users/getAllRoleDescriptionByUserName",
            headers:{access_token : access_token},
            //contentType: "application/json; charset=utf-8",
            success:function (roleDtoList) {
                $.each(roleDtoList,function (index,roleDto) {
                    roleDescriptionStr += roleDto.description;
                });//end each
            },
            error:function (msg) {
                //alert(msg.message);
            }
        });//end ajax

        //alert("roleDescriptionStr = "+roleDescriptionStr);
    }//edn getAllRoleDescriptionByUserName*/

</script>
</body>
</html>