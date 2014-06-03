package com.questionnaire.service.impl;

import com.questionnaire.entity.Question;
import com.questionnaire.entity.Questionnaire;
import com.questionnaire.entity.User;
import com.questionnaire.service.*;
import com.questionnaire.vo.AppBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Service("appService")
@Path("/")
@Produces("application/json;charset=UTF-8")
public class AppService implements IAppService {

    @Autowired
    private IUserService userService;
    @Autowired
    private IQuestionnaireService questionnaireService;
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IUserQuestionnaireService userQuestionnaireService;

    @Override
    @GET
    @Path("/questionnaires/{page}/{userId}/")
    public AppBean getQuestionnaires(@PathParam("page") int page,
                                     @PathParam("userId") int userId) {
        AppBean bean = new AppBean();
        try {
            User user = userService.getEntity(User.class, userId);
            if (user == null) {
                bean.setMessage("获取问卷失败，用户不存在！");
                bean.setStatus(Status.BAD_REQUEST);
                return bean;
            }

            int length = 5;
            int from = (page - 1) * 5;

            List<Questionnaire> result = questionnaireService
                    .getQuestionnairesByUser(user, from, length);
            bean.setMessage("获取问卷成功");
            bean.setStatus(Status.OK);
            bean.setData(result);
        } catch (Exception e) {
            bean.setMessage(e.getMessage());
            bean.setStatus(Status.BAD_REQUEST);
            e.printStackTrace();
        }
        return bean;

    }

    @Override
    @GET
    @Path("/questionnaire/{questionnaireId}/questions/")
    public AppBean getQuestionsByQuestionnaire(
            @PathParam("questionnaireId") String questionnaireId) {
        AppBean bean = new AppBean();
        try {
            int id = Integer.parseInt(questionnaireId);
            List<Question> result = questionService
                    .getQuestionsByQuestionnaire(id);
            bean.setMessage("获取问题成功");
            bean.setStatus(Status.OK);
            bean.setData(result);
        } catch (NumberFormatException e) {
            bean.setMessage(e.getMessage());
            bean.setStatus(Status.BAD_REQUEST);
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    @GET
    @Path("/login/{username}/{password}/")
    public AppBean login(@PathParam("username") String username,
                         @PathParam("password") String password) {
        AppBean bean = new AppBean();
        try {
            User user = userService.findUserByUsernameAndPassword(username,
                    password);
            if (user == null) {
                bean.setMessage("登陆失败");
                bean.setStatus(Status.UNAUTHORIZED);
                return bean;
            }
            bean.setMessage("登陆成功");
            bean.setStatus(Status.OK);
            bean.setData(user);
        } catch (Exception e) {
            bean.setMessage(e.getMessage());
            bean.setStatus(Status.BAD_REQUEST);
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    @POST
    @Path("/register/")
    @Consumes("application/x-www-form-urlencoded;encoding=utf-8")
    public AppBean register(@FormParam("username") String username,
                            @FormParam("password") String password,
                            @FormParam("age") String age,
                            @FormParam("education") String education,
                            @FormParam("gender") String gender,
                            @FormParam("income") String income,
                            @FormParam("occupation") String occupation,
                            @FormParam("email") String email, @FormParam("imei") String imei) {
        AppBean bean = new AppBean();
        try {
            if (StringUtils.isEmpty(username)) {
                bean.setStatus(Status.NO_CONTENT);
                bean.setMessage("用户名不能为空");
                return bean;
            }
            if (StringUtils.isEmpty(password)) {
                bean.setStatus(Status.NO_CONTENT);
                bean.setMessage("密码不能为空");
                return bean;
            }
            User user = userService.findUserByUsername(username);
            if (user != null) {
                bean.setStatus(Status.CONFLICT);
                bean.setMessage("用户名已存在");
                return bean;
            }
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setAge(age);
            user.setEducation(education);
            user.setGender(gender);
            user.setIncome(income);
            user.setOccupation(occupation);
            user.setEmail(email);
            user.setImei(imei);

            user = userService.createUser(user);
            bean.setMessage("注册用户成功");
            bean.setStatus(Status.OK);
            bean.setData(user);
        } catch (Exception e) {
            bean.setMessage(e.getMessage());
            bean.setStatus(Status.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return bean;
    }

    @POST
    @Path("/questionnaire/user/answer/")
    @Override
    @Consumes("application/x-www-form-urlencoded;encoding=utf-8")
    public AppBean saveUserQuestionnaire(@FormParam("questionnaireId") int questionnaireId, @FormParam("userId") int userId,
                                         @FormParam("answer") String answer) {
        AppBean bean = new AppBean();
        try {

            Questionnaire questionnaire = questionnaireService.getEntity(
                    Questionnaire.class, questionnaireId);
            if (questionnaire == null) {
                bean.setStatus(Status.NOT_FOUND);
                bean.setMessage("问卷不存在");
                return bean;
            }
            User user = userService.getEntity(User.class, userId);
            if (user == null) {
                bean.setStatus(Status.FORBIDDEN);
                bean.setMessage("用户不存在");
                return bean;
            }
            if (StringUtils.isEmpty(answer)) {
                bean.setStatus(Status.NO_CONTENT);
                bean.setMessage("答案为空");
                return bean;
            }

            userQuestionnaireService.saveUserQuestionnaire(questionnaire, user,
                    answer);
            bean.setStatus(Status.OK);
            bean.setMessage("提交成功");
        } catch (Exception e) {
            bean.setMessage(e.getMessage());
            bean.setStatus(Status.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }

        return bean;
    }

}
