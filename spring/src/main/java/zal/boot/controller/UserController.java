package zal.boot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zal.boot.common.Result;
import zal.boot.entity.User;
import zal.boot.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserMapper userMapper;

    @PostMapping("login")
    public Result login(@RequestBody Map<String,Object> param) {
        String userName = (String) param.get("username");
        String password = (String) param.get("password");
        User user = userMapper.login(userName);
        System.out.println(user);
        if (null == user) {
            return Result.error("用户名错误");
        }else {
            if (user.getPassword().equals(password)) {
                return Result.success(user.getId());
            }else {
                return Result.error("密码错误");
            }
        }

    }
}
