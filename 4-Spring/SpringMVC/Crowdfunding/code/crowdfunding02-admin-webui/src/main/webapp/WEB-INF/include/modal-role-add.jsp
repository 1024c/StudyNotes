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
                        <label for="roleName">角色名称:</label>
                        <input id="roleName" type="text" name="roleName" class="form-control" placeholder="请输入角色名称" autofocus />
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" form="roleForm" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>