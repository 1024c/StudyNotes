<%--
  Project: 众筹网模板
  User: Administrator
  Date: 10/14/2020
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div id="roleAssignAuthModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">众筹网系统弹窗</h4>
            </div>
            <div class="modal-body">
                <ul id="authTree" class="ztree">
                </ul>
            </div>
            <div class="modal-footer">
                <button id="saveAuthBtn" type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>