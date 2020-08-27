package cn.hlsd.websys.config.security;

import cn.hlsd.websys.entity.Permission;
import cn.hlsd.websys.entity.Role;
import cn.hlsd.websys.entity.RolePermission;
import cn.hlsd.websys.service.IPermissionService;
import cn.hlsd.websys.service.IRolePermissionService;
import cn.hlsd.websys.service.IRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Ean
 * @version 0.1.0
 * @Description: 这个类也比较重要。重写的是security的接口权限判断方法。
 * getAttributes函数会去数据库查询某个url所需要的权限
 * @return
 * @date 2020/8/27 18:07
 * @since 0.1.0
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    IPermissionService iPermissionService;
    @Autowired
    IRolePermissionService iRolePermissionService;
    @Autowired
    IRoleService iRoleService;

    /***
     * 返回该url所需要的用户权限信息
     *
     * @param object: 储存请求url信息
     * @return: null：标识不需要任何权限都可以访问
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取当前请求url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        /*放行匿名URL*/
        if (incloudUrl(requestUrl)) {
            return null;
        }
        // 数据库中所有url
        Permission permission = iPermissionService.getOne(new QueryWrapper<Permission>().eq("url", requestUrl));
        if (null != permission) {
            List<RolePermission> rolePermissions = iRolePermissionService.list(new QueryWrapper<RolePermission>().eq("permission_id", permission.getPermissionId()));
            List<String> roles = new LinkedList<>();
            if (!CollectionUtils.isEmpty(rolePermissions)) {
                Role role;
                for (RolePermission rolePermission : rolePermissions) {
                    role = iRoleService.getById(rolePermission.getRoleId());
                    roles.add(role.getRoleTitle());
                }
            }
            if (roles.size() == 0) {
                roles.add(Constants.ROLE_LOGIN);
            }
            // 保存该url对应角色权限信息
            return SecurityConfig.createList(roles.toArray(new String[roles.size()]));
        }

        // 如果数据中没有找到相应url资源则为非法访问，要求用户登录再进行操作
//        return SecurityConfig.createList(Constants.ROLE_LOGIN);
        String [] roleArr={"user"};
        return SecurityConfig.createList(roleArr);
    }

    private boolean incloudUrl(String requestUrl) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean flag;
        for (Permission permission : WebSecurityConfig.getANNO_URL_LIST()) {
            flag = antPathMatcher.match(permission.getUrl(), requestUrl);
            if (flag) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}