package com.yan.controller.user;

import com.yan.persist.data.JsonResult;
import com.yan.persist.service.UserService;
import com.yan.persist.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by YZT on 2018/5/23.
 */
@Controller
public class EmailController {

    @Autowired
    private VerifyService verifyService;
    @Autowired
    private UserService userService;

    /**
     * 绑定邮箱
     * 通过查找数据库匹配验证码信息并返回
     * @param email
     * @param code
     * @return
     */
    @RequestMapping(value = "/email/bind/{email}/{code}",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult bind(@PathVariable("email")String email, @PathVariable("code")String code){
        JsonResult jsonResult = new JsonResult();

        try {
            verifyService.verify(email,code);
            userService.updateStatusByEmail(email,1);
        }catch (Exception e){
            jsonResult.setErrorCode("1");
            jsonResult.setMessage(e.getMessage());
            return jsonResult;
        }

        jsonResult.setMessage("邮箱验证成功");
        return jsonResult;
    }

}
