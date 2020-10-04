package com.tawe.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tawe.crowd.constant.CrowdConstant;
import com.tawe.crowd.dao.AdminMapper;
import com.tawe.crowd.entity.Admin;
import com.tawe.crowd.entity.AdminExample;
import com.tawe.crowd.exception.LoginAcctAlreadyInUseException;
import com.tawe.crowd.exception.LoginFailedException;
import com.tawe.crowd.exception.LoginPswdIsNullException;
import com.tawe.crowd.service.AdminService;
import com.tawe.crowd.util.CrowdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 9/23/2020 2:00 PM
 * @Version 1.0
 **/

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int save(Admin admin) throws LoginAcctAlreadyInUseException, LoginPswdIsNullException{
        // 1. 生成当前系统时间
        Date date = new Date();
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        admin.setCreateTime(createTime);
        // 2. 针对登录密码进行加密
        String userPswd = admin.getUserPswd();
        if (userPswd == null || userPswd.equals("")) {
            throw new LoginPswdIsNullException(CrowdConstant.MESSAGE_LOGIN_PSWD_IS_NULL.getMsg());
        }
        admin.setUserPswd(CrowdUtil.md5(userPswd));
        // 3. 执行保存
        int cols = 0;
        try {
            cols = adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            // 检测当前捕获的异常对象，如果是 DuplicateKeyException 则说明是账号重复
            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseException(CrowdConstant.Message_LOGIN_ACCT_ALREADY_IN_USE.getMsg());
            }
            // 如果不是 DuplicateKeyException，则继续向上抛出异常
            throw e;
        }
        return cols;
    }

    @Override
    public List<Admin> selectAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin selectById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String loginPaswd) {
        // 1. 根据登录账号查询 Admin 对象;
        //     1.1 创建 AdminExample 对象;
        AdminExample adminExample = new AdminExample();
        //     1.2 创建 Criteria 对象;
        //     1.3 在 Criteria 对象中封装查询条件;
        adminExample.createCriteria().andLoginAcctEqualTo(loginAcct);
        //     1.4 调用 AdminMapper 方法执行查询;
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        // 2. 判断 Admin 对象是否为空;
        if (admins == null || admins.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED_USER_NOT_EXIST.getMsg());
        }

        // 2.1 返回 Admin 对象大于 1 则说明数据库中存在重复数据 抛异常
        if (admins.size() > 1) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED_USER_NOT_UNIQUE.getMsg());
        }
        // 3. 如果 Admin 对象为 null 则抛出异常;
        Admin admin = admins.get(0);
        if (admin == null) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED_USER_NOT_EXIST.getMsg());
        }
        // 4. 如果  Admin 对象不为 null 则将数据库密码从 Admin 对象中取出;
        String adminPswdDB = admin.getUserPswd();

        // 5. 将表单提交的明文密码加密进行比较;
        String adminPswdForm = CrowdUtil.md5(loginPaswd);
        if (!Objects.equals(adminPswdDB, adminPswdForm)) {
            // 6. 如果密码不一致则抛出异常;
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED.getMsg());
        }
        // 7. 如果一致则返回 admin 对象;
        return admin;
    }

    @Override
    public PageInfo<Admin> getAdminPage(String keyword, Integer pageNum, Integer pageSize) {
        // 1. 开启分页功能；
        PageHelper.startPage(pageNum, pageSize);
        // 2. 查询 Admin 数据；
        List<Admin> admins = adminMapper.selectByKeyword(keyword);
        //     2.1. 辅助代码： 打印 AdminList 的全类名；
        System.out.println(admins);
        Logger logger = LoggerFactory.getLogger(AdminService.class);
        logger.info("Admin 类名：" + admins.getClass().getSimpleName());

        // 3. 将 AdminList 封装为 PageInfo
        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        return adminPageInfo;
    }

    @Override
    public int removeById(Integer id) {
        return adminMapper.deleteByPrimaryKey(id);
    }
}
