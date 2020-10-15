$(function () {
    // 增加权限
    $("#toRightBtn").click(function () {
        // select 标签选择器
        // eq 选择页面上的第几个
        // > 子元素
        // :selected 选中的元素
        // appendTo 追加到哪个元素
        const selectElements = $("select:eq(0)>option:selected");

        // toRight 时 提交 左边选中的表单
        // toLeft 时 提交 右边选中的表单
        $("#adminAssignRoleForm select:eq(0)").attr("name", "roleIds");
        $("#adminAssignRoleForm select:eq(1)").removeAttr("name");
        // 通过 ajax 提交 form 表单
        $.ajax({
            url: "assign/admin/to/role/add.json",
            type: "post",
            data: $("#adminAssignRoleForm").serialize(),
            // contentType: "application/json; charset=UTF-8",
            dataType: "json",
            success: function (response) {
                if (response.result === "SUCCESS") {
                    layer.msg("操作成功!");
                    selectElements.appendTo("select:eq(1)");
                } else if (response.result === "FAILURE") {
                    layer.msg("操作失败!" + response.message);
                }
            },
            error: function (response) {
                layer.msg(response.status + "" + response.statusText);
            },
        });

        // 手写 ajax 请求 => 由以上 form 表单 ajax 提交替代
        // let roleIds = [];
        // for (let i=0; i<selectElements.length; i++) {
        //     roleIds.push(selectElements[i].value);
        // }
        // if (roleIds.length === 0) {
        //     return;
        // }
        //
        // const adminId = $("#adminId").val();
        //
        // $.ajax({
        //     url: "assign/admin/to/role/" + adminId + "/add.json",
        //     type: "post",
        //     data: JSON.stringify(roleIds),
        //     contentType: "application/json; charset=UTF-8",
        //     dataType: "json",
        //     success: function (response) {
        //         if (response.result === "SUCCESS") {
        //             layer.msg("操作成功!");
        //             selectElements.appendTo("select:eq(1)");
        //         } else if (response.result === "FAILURE") {
        //             layer.msg("操作失败!" + response.message);
        //         }
        //     },
        //     error: function (response) {
        //         layer.msg(response.status + "" + response.statusText);
        //     },
        //
        // });
    });

    // 移除权限
    $("#toLeftBtn").click(function () {
        const selectElements = $("select:eq(1)>option:selected");

        // toRight 时 提交 左边选中的表单
        // toLeft 时 提交 右边选中的表单
        $("#adminAssignRoleForm select:eq(1)").attr("name", "roleIds");
        $("#adminAssignRoleForm select:eq(0)").removeAttr("name");

        // 通过 ajax 提交 form 表单
        $.ajax({
            url: "assign/admin/to/role/remove.json",
            type: "post",
            data: $("#adminAssignRoleForm").serialize(),
            // contentType: "application/json; charset=UTF-8",
            dataType: "json",
            success: function (response) {
                if (response.result === "SUCCESS") {
                    layer.msg("操作成功!");
                    selectElements.appendTo("select:eq(0)");
                } else if (response.result === "FAILURE") {
                    layer.msg("操作失败!" + response.message);
                }
            },
            error: function (response) {
                layer.msg(response.status + "" + response.statusText);
            },
        });

        // 手写 Ajax 请求 => 由以上 form 表单 ajax 提交替代
        // let roleIds = [];
        // for (let i=0; i<selectElements.length; i++) {
        //     roleIds.push(selectElements[i].value);
        // }
        //
        // if (roleIds.length === 0) {
        //     return;
        // }
        //
        // const adminId = $("#adminId").val();
        //
        // $.ajax({
        //     url: "assign/admin/to/role/" + adminId + "/remove.json",
        //     type: "post",
        //     data: JSON.stringify(roleIds),
        //     contentType: "application/json; charset=UTF-8",
        //     dataType: "json",
        //     success: function (response) {
        //         if (response.result === "SUCCESS") {
        //             layer.msg("操作成功!");
        //             selectElements.appendTo("select:eq(0)");
        //         } else if (response.result === "FAILURE") {
        //             layer.msg("操作失败!" + response.message);
        //         }
        //     },
        //     error: function (response) {
        //         layer.msg(response.status + "" + response.statusText);
        //     },
        // });
    });
});

