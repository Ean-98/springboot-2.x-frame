package cn.hlsd.websys.controller;

import cn.hlsd.websys.config.security.WebSecurityConfig;
import cn.hlsd.websys.entity.Permission;
import cn.hlsd.websys.service.IPermissionService;
import cn.hlsd.websys.util.JwtTokenUtils;
import cn.hlsd.websys.util.SpringContextUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@Api(tags = "系统授权接口")
public class AuthController {

    private final JwtTokenUtils jwtTokenUtils;
    @Autowired
    IPermissionService iPermissionService;

    public AuthController(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @ApiOperation("登录授权")
    @PostMapping(value = "/login")
    public String login(String user, String password) {
        Map map = new HashMap();
        map.put("user", user);
        map.put("password", password);
        return jwtTokenUtils.createToken(map);
    }

    @ApiOperation("刷新匿名url")
    @GetMapping("flushANNO_URL")
    public String flushANNO_URL() {
        IPermissionService iPermissionService = (IPermissionService) SpringContextUtil.getBean(IPermissionService.class);
        List<Permission> ANNO_URL_LIST = iPermissionService.list(new QueryWrapper<Permission>().eq("pid", -1));
        WebSecurityConfig.ANNO_URL_LIST = ANNO_URL_LIST;
        return "flush ok";
    }
}
