package com.questionnaire.web.action;

import com.questionnaire.dao.impl.Criterion;
import com.questionnaire.entity.User;
import com.questionnaire.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    private static final long serialVersionUID = 1L;
    @Autowired
    private IUserService userService;

    public String find() {
        return this.refreshGridModel();
    }

    @Override
    public long getResultSize() {
        return userService.getUsersCount();
    }

    @Override
    public List<User> listResults(int from, int length) {
        return userService.getUsers(from, length);
    }

    @Override
    public long getResultSize(String groupOp, List<Criterion> criteria) {
        return (int) userService.getUsersCount(groupOp, criteria);
    }

    @Override
    public List<User> listResults(String groupOp, List<Criterion> criteria,
                                  int from, int length) {
        return userService.getUsers(groupOp, criteria, from, length);
    }
}
