package com.tawe.crowd.constant;

/**
 * @ClassName CrowdConstant
 * @Description 常量字段
 * @Author Administrator
 * @Date 9/24/2020 5:58 PM
 * @Version 1.0
 **/
public enum CrowdConstant {
    /**
     * 常量字段
     */
    MESSAGE_LOGIN_FAILED("登录失败! 请确认账号密码是否正确!"),
    MESSAGE_LOGIN_FAILED_USER_NOT_EXIST("登录失败! 用户不存在!"),
    MESSAGE_LOGIN_FAILED_USER_NOT_UNIQUE("登录失败! 数据库中存在重复用户名!"),
    MESSAGE_STRING_INVALIDATE("字符串为空! 请重新输入!"),
    MESSAGE_LOGIN_ACCT_ALREADY_IN_USE("用户名已存在，请更换后重试！"),
    MESSAGE_LOGIN_PSWD_IS_NULL("密码未提供，请输入密码后重试！"),

    ATTR_NAME_EXCEPTION("exception"),
    ATTR_NAME_LOGIN_ADMIN("admin"),
    ATTR_NAME_PAGE_INFO("pageInfo"),

    ATTR_NAME_ADD("add"),
    ATTR_NAME_EDIT("edit"),

    MESSAGE_DATABASE_ERROR("数据库错误，无法保存！");

    public final String msg;
    CrowdConstant(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
