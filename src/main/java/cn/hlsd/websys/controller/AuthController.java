package cn.hlsd.websys.controller;

import cn.hlsd.websys.util.JwtTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@Api(tags = "系统授权接口")
public class AuthController {

    private final JwtTokenUtils jwtTokenUtils;

    public AuthController(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @ApiOperation("登录授权")
    @GetMapping(value = "/login")
    public String login(String user, String password) {
        Map map = new HashMap();
        map.put("user", user);
        map.put("password", password);
        return jwtTokenUtils.createToken(map);
    }
}
