<%--
  Project: 众筹网模板
  User: Administrator
  Date: 10/10/2020
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="addModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">系统弹窗</h4>
            </div>
            <div class="modal-body">
                <form id="roleForm" action="role/page/add.html">
                    <div class="form-group has-success has-feedback">
                        <input id="roleId" name="roleId" type="hidden"></input>
                        <label for="roleName">角色名称:</label>
                        <input id="roleName" type="text" name="roleName" class="form-control" placeholder="请输入角色名称" autofocus />
                    </div>
                </form>
            </div>
            <div class="modal-footer">
            <%--两种方式:--%>
            <%--方式一: 通过 form submit 提交表单;--%>
            <%--    <button type="submit" form="roleForm" class="btn btn-primary">保存</button>--%>
            <%--方式二: 通过 ajax 请求 json 数据,手动填充;--%>
                <button id="saveRoleBtn" type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>