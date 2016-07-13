package tk.jingzing.controller;

import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.jingzing.ResultModel;
import tk.jingzing.authorization.annotation.CurrentUser;
import tk.jingzing.authorization.manager.TokenManager;
import tk.jingzing.authorization.model.TokenModel;
import tk.jingzing.config.ResultStatus;
import tk.jingzing.domain.User;
import tk.jingzing.repository.UserRepository;

/**
 * 获取和删除token的请求地址，在Restful设计中其实就对应着登录和退出登录的资源映射
 * Created by Louis Wang on 2016/7/13.
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "登录")
    public ResponseEntity<ResultModel> login(@RequestParam String username, @RequestParam String password){
        Assert.notNull(username, "username can not be empty");
        Assert.notNull(password, "password can not be empty");

        User user = userRepository.findByUsername(username);

        if (user == null ||  //未注册
                !user.getPassword().equals(password)) {  //密码错误
            //提示用户名或密码错误
            return new ResponseEntity<>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }

        //生成一个token，保存用户登录状态
        TokenModel model = tokenManager.createToken(user.getId());
        return new ResponseEntity<>(ResultModel.ok(model), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @tk.jingzing.authorization.annotation.Authorization
    @ApiOperation(value = "退出登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
    })
    public ResponseEntity<ResultModel> logout(@CurrentUser User user) {
        tokenManager.deleteToken(user.getId());
        return new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
    }
}
