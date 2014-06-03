package com.questionnaire.service;

import com.questionnaire.entity.Manager;

public interface IManagerService extends IBaseService<Manager> {

    public boolean validatePassword(String currentPassword);

    public void modifyPassword(String newPassword);

}
