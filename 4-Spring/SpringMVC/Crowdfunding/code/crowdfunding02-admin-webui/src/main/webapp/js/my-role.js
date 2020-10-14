// 执行分页, 生成页面效果, 任何时候调用这个函数都会重新加载页面
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
                } else if (response.result === "FAILURE") {
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



    // 删除的全选&全不选逻辑 - selectAll checkbox
    $("#selectAll").click(function () {
        $("tbody input:checkbox").prop("checked", this.checked);
    })
    // 删除的全选&全不选逻辑 - eachone checkbox
    $("tbody input:checkbox").click(function () {
        // 这个判断太 6 了
        // 通过选中的个数和总个数进行比较,如果全选则相等,此时条件为 true, 可以直接给 selectAll 赋值
        $("#selectAll").prop("checked", $("tbody input:checkbox").length === $("tbody input:checkbox:checked").length)
    })

    // 删除数组中的所有 role
    function removeSelected(roleIds) {
        $.ajax({
            url: "role/removes.json",
            type: "post",
            data: JSON.stringify(roleIds),
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            success: function (response) {
                if (response.result === "SUCCESS") {
                    layer.msg("操作成功!");
                    // window.pageNum = 65535;
                    // let pageInfo = generatePage();
                    location.reload();
                } else if (response.result === "FAILURE") {
                    layer.msg("操作失败!" + response.message);
                }

            },
            error: function (response) {

                layer.msg(response.status + "" + response.statusText);
            },
        })
    }
    // 选中删除
    $("#deleteRoles").click(function () {
        // 1. 获取被选中的删除对象
        let roleIds = [];
        let roleNames = "";
        let removes = $("tbody input:checkbox:checked");
        removes.each(function () {
            let roleId = $.trim($(this).closest("tr").find("[name=roleId]").text());
            let roleName = $.trim($(this).closest("tr").find("[name=roleName]").text());
            roleIds.push(roleId);
            roleNames += roleName + "<br\>";
        })
        if (roleIds.length === 0) {
            return;
        }
        layer.confirm("确定删除以下记录:<br\>" + roleNames, {
            btn: ["确定", "取消"],
            btn1: function(){
                removeSelected(roleIds);
            }
        })
    })
    // 单个删除
    // 绑定删除角色点击事件
    $(".glyphicon-remove").click(function () {
        // layer.msg("remove");
        let roleId = []
        roleId.push($(this).closest("tr").find("[name=roleId]").text());
        layer.confirm("确定删除本条记录?", {
            btn: ["确定", "取消"],
            btn1: function(){
                removeSelected(roleId);
            }
        })
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
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            return data.data;
        },
        error: function (textStatus, errorThrown) {
            layer.msg("失败! ");
            return null;
        }
    });
}

