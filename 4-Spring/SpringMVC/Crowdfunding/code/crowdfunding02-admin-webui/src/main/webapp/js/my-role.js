﻿// 执行分页, 生成页面效果, 任何时候调用这个函数都会重新加载页面
$(function () {
    // 1. 为分页操作准备初始化数据
    window.pageNum = 1;
    window.pageSize = 5;
    window.keyword = "";

    // 2. 调用执行分页的函数, 显示分页效果
    //  generatePage();

    // 绑定点击新增按钮打开模态框事件
    $("#showAddModalBtn").click(function () {
        // JQuery 没有重置表单的操作, 调用 DOM 的 reset 方法
        $("#roleForm")[0].reset();
        $("#addModal").modal("show");
    });

    // 绑定新增角色保存按钮事件
    $("#saveRoleBtn").click(function () {
        // 1. 获取用户在文本框输入的角色名称
        // 1.1 #addModal 找到整个模态框
        // 1.2. 空格标识在后代元素中继续查找
        // 1.3. [name=roleName] 标识匹配 name 属性
        const roleName = $.trim($("#addModal [name=roleName]").val());
        const roleId = $.trim($("#addModal [name=roleId]").val());
        // 2. 发送 ajax 请求
        $.ajax({
            url: "role/save.json",
            type: "post",
            data: {
                "id": roleId,
                "roleName": roleName
            },
            dataType: "json",
            success: function (response) {
                if (response.result === "SUCCESS") {
                    layer.msg("操作成功!");
                    // window.pageNum = 65535;
                    // let pageInfo = generatePage();
                    location.reload();
                } else if (response.result === "FAILED") {
                    layer.msg("操作失败!" + response.message);
                }

            },
            error: function (response) {

                layer.msg(response.status + "" + response.statusText);
            },
        })
        // 关闭模态框
        $("#addModal").modal("hide");
        // 清理模态框
        $("#addModal [name=roelName]").val("");
    });

    // 绑定修改角色点击事件
    $(".glyphicon-pencil").click(function () {
        const roleId = $(this).closest("tr").find("[name=roleId]").text();
        const roleName = $(this).closest("tr").find("[name=roleName]").text();
        $("#addModal [name=roleId]").val(roleId);
        $("#addModal [name=roleName]").val(roleName);
        $("#addModal").modal("show");
        // layer.msg(roleName);
    })

    // 绑定删除角色点击事件
    $(".glyphicon-remove").click(function () {
        // layer.msg("remove");
    })
});

// 任何时候调用这个函数都会重新加载页面数据
// 远程访问服务端 pageInfo 数据
function generatePage() {
    // 1. 发送 ajax 请求获取 pageInfo
    $.ajax({
        url:"role/page/get/info.json",
        type: "post",
        data: {
            "pageNum": window.pageNum,
            "pageSize": window.pageSize,
            "keyword": window.keyword
        },
        async: true,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            return data.data;
        },
        error: function (textStatus, errorThrown) {
            layer.msg("失败! ");
            return null;
        }
    });
}
