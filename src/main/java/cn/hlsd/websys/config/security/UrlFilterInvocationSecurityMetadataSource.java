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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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
        // TODO 忽略url请放在此处进行过滤放行
        if ("/login".equals(requestUrl) || requestUrl.contains("logout")) {
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
            // 保存该url对应角色权限信息
            return SecurityConfig.createList(roles.toArray(new String[roles.size()]));
        }
        /*for (Permission permission : permissionList) {
            // 获取该url所对应的权限
            List<Permission> permissionList = permissionMapper.selectList(null);
            if (requestUrl.equals(permission.getUrl())) {
                List<RoleMenu> permissions = rolePermissionMapper.selectList(new EntityWrapper<RoleMenu>().eq("permission_id", permission.getId()));
                List<String> roles = new LinkedList<>();
                if (!CollectionUtils.isEmpty(permissions)) {
                    Integer roleId = permissions.get(0).getRoleId();
                    Role role = roleMapper.selectById(roleId);
                    roles.add(role.getCode());
                }
                // 保存该url对应角色权限信息
                return SecurityConfig.createList(roles.toArray(new String[roles.size()]));
            }
        }*/
        // 如果数据中没有找到相应url资源则为非法访问，要求用户登录再进行操作
        return SecurityConfig.createList(Constants.ROLE_LOGIN);
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