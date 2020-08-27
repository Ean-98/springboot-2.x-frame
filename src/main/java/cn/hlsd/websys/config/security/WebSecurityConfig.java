package cn.hlsd.websys.config.security;

import cn.hlsd.websys.entity.Permission;
import cn.hlsd.websys.service.IPermissionService;
import cn.hlsd.websys.util.JwtTokenUtils;
import cn.hlsd.websys.util.SpringContextUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtTokenUtils jwtTokenUtils;
    public static List<Permission> ANNO_URL_LIST = new ArrayList();

    /*获取匿名可访问数组*/
    public static List<Permission> getANNO_URL_LIST() {
        if (ANNO_URL_LIST.size() <= 0) {
            IPermissionService iPermissionService = (IPermissionService) SpringContextUtil.getBean(IPermissionService.class);
            ANNO_URL_LIST = iPermissionService.list(new QueryWrapper<Permission>().eq("pid", -1));
        }
        return ANNO_URL_LIST;
    }

    /*更新匿名可访问数组*/
    public static void flushANNO_URL_LIST() {
        IPermissionService iPermissionService = (IPermissionService) SpringContextUtil.getBean(IPermissionService.class);
        ANNO_URL_LIST = iPermissionService.list(new QueryWrapper<Permission>().eq("pid", -1));
    }

    /**
     * 获取访问url所需要的角色信息
     */
    private final UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
    /**
     * 认证权限处理 - 将上面所获得角色权限与当前登录用户的角色做对比，如果包含其中一个角色即可正常访问
     */
    private final UrlAccessDecisionManager urlAccessDecisionManager;
    /**
     * 自定义访问无权限接口时403响应内容
     */
    private final UrlAccessDeniedHandler urlAccessDeniedHandler;

    public WebSecurityConfig(JwtAccessDeniedHandler jwtAccessDeniedHandler, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtTokenUtils jwtTokenUtils, UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource, UrlAccessDecisionManager urlAccessDecisionManager, UrlAccessDeniedHandler urlAccessDeniedHandler) {

        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtTokenUtils = jwtTokenUtils;
        this.urlFilterInvocationSecurityMetadataSource = urlFilterInvocationSecurityMetadataSource;
        this.urlAccessDecisionManager = urlAccessDecisionManager;
        this.urlAccessDeniedHandler = urlAccessDeniedHandler;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 禁用 CSRF
                .csrf().disable()
                // 授权异常
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                // 防止iframe 造成跨域
                .and()
                .headers()
                .frameOptions()
                .disable()
                // 不创建会话
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 所有请求都需要认证
                .anyRequest().authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                o.setAccessDecisionManager(urlAccessDecisionManager);
                return o;
            }
        });
        ;

        // 登录过后访问无权限的接口时自定义403响应内容
        httpSecurity.exceptionHandling().accessDeniedHandler(urlAccessDeniedHandler);
        ;
        // 登录过后访问无权限的接口时自定义403响应内容
        httpSecurity.exceptionHandling().accessDeniedHandler(urlAccessDeniedHandler);
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 添加JWT filter
        httpSecurity.apply(new TokenConfigurer(jwtTokenUtils));

    }

    public class TokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

        private final JwtTokenUtils jwtTokenUtils;

        public TokenConfigurer(JwtTokenUtils jwtTokenUtils) {
            this.jwtTokenUtils = jwtTokenUtils;
        }

        @Override
        public void configure(HttpSecurity http) {
            JwtAuthenticationTokenFilter customFilter = new JwtAuthenticationTokenFilter(jwtTokenUtils);
            http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }

}
