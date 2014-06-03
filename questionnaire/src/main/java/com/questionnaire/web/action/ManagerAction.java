package com.questionnaire.web.action;

import com.questionnaire.dao.impl.Criterion;
import com.questionnaire.entity.Manager;
import com.questionnaire.service.IManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class ManagerAction extends BaseAction<Manager> {
    private static final long serialVersionUID = 1L;
    private Manager manager;
    @Autowired
    private IManagerService managerService;
    private String ajaxResult;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    public String modifyPassword() {
        boolean flag = true;
        if (StringUtils.isEmpty(currentPassword)) {
            messages.put("error_current_password", "当前密码不能为空！");
            flag = false;
        } else if (!managerService.validatePassword(currentPassword)) {
            messages.put("error_current_password", "当前密码不正确！");
            flag = false;
        }
        if (StringUtils.isEmpty(newPassword)) {
            messages.put("error_new_password", "新密码不能为空！");
            flag = false;
        } else if (newPassword.length() < 6) {
            messages.put("error_new_password", "新密码长度不能小于6位！");
            flag = false;
        }
        if (StringUtils.isEmpty(confirmPassword)) {
            messages.put("error_confirm_password", "确认密码不能为空！");
            flag = false;
        } else if (!newPassword.equals(confirmPassword)) {
            messages.put("error_confirm_password", "两次密码不一致！");
            flag = false;
        }
        if (!flag) {
            ajaxResult = "更新密码数据验证失败！";
            return ERROR;
        }

        try {
            managerService.modifyPassword(newPassword);
            ajaxResult = SUCCESS;
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            this.addActionError(e.getMessage());
            ajaxResult = "更新密码失败!" + e.getMessage();
            return ERROR;

        }
    }

    @Override
    public long getResultSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Manager> listResults(int from, int length) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getResultSize(String groupOp, List<Criterion> criteria) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Manager> listResults(String groupOp, List<Criterion> criteria,
                                     int from, int length) {
        // TODO Auto-generated method stub
        return null;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getAjaxResult() {
        return ajaxResult;
    }

    public void setAjaxResult(String ajaxResult) {
        this.ajaxResult = ajaxResult;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
