package com.zzz.sell.configure;

import com.zzz.sell.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzz
 * @description springsecurity自定义登录配置
 * @date 2020/2/28
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfigure extends WebSecurityConfigurerAdapter {
    /**
     * 自定义登录认证
     */
    @Autowired
    private SelfAuthenticationProvider authenticationProvider;

    /**
     * 自定义登录成功处理器
     */
    @Autowired
    private UrlAuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 自定义登录失败处理器
     */
    @Autowired
    private UrlAuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 自定义注销处理器
     */
    @Autowired
    private UrlLogoutSuccessHandler logoutSuccessHandler;
    /**
     * 自定义权限资源类
     */
    @Autowired
    private SelfSecurityMetadataSource selfSecurityMetadataSource;
    /**
     * 自定义授权失败处理器
     */
    @Autowired
    private UrlAccessFailureHandler urlAccessFailureHandler;
    /**
     * 登录认证
     * @param auth 登陆管理器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        //添加自定义登陆认证
        auth.authenticationProvider(authenticationProvider);
    }
    /**
     * 具体配置登陆细节
     * @param http 登陆访问对象
     * @throws Exception 登陆异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf
        http.csrf().disable()
                //开放api路径
                .authorizeRequests().antMatchers("/public/**","/register").
                permitAll()
                .anyRequest().authenticated()
                .and()
                //添加自定义过滤器实现登录使用json格式报文
                .addFilterAt(selfUsernamePasswordAuthencationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 允许跨域
                .cors()
                .and()
                //关闭匿名登录
                .anonymous().disable()
                //开启自动配置的登陆功能
                //自定义登录请求路径(post请求)
                .formLogin()
                .loginProcessingUrl("/login")
                //验证成功处理器
                .successHandler(authenticationSuccessHandler)
                //验证失败处理器
                .failureHandler(authenticationFailureHandler).permitAll()
                .and()
                //关闭拦截未登录自动跳转,改为返回json信息
                .exceptionHandling().authenticationEntryPoint(selfLoginUrlAuthenticationEntryPoint())
                .accessDeniedHandler(urlAccessFailureHandler)
                //开启自动配置的注销功能
                .and()
                .logout()
                .logoutUrl("/logout")
                //注销成功处理器
                .logoutSuccessHandler(logoutSuccessHandler).permitAll()
                //使用自定义权限管理器和自定义metadatasource
                .and().authorizeRequests().withObjectPostProcessor(
                        new ObjectPostProcessor<FilterSecurityInterceptor>() {

                            @Override
                            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                                o.setSecurityMetadataSource(selfSecurityMetadataSource);
                                o.setAccessDecisionManager(accessDecisionManager());
                                return o;
                            }
                    });
    }

    /**
     * 身份认证失败处理类
     * @return AuthenticationEntryPoint
     */
    @Bean
    public AuthenticationEntryPoint selfLoginUrlAuthenticationEntryPoint() {
        return new SelfLoginUrlAuthenticationEntryPoint();
    }

    /**
     * 重写方法，是上下文可以获取本地缓存对象
     * @return AuthenticationManager  本地缓存对象
     * @throws Exception 异常
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 将自定义voter加入accessmanager
     * @return
     */
    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                new SelfRoleVoter(),
                new WebExpressionVoter(),
                new RoleVoter(),
                new AuthenticatedVoter()
        );
        return new UnanimousBased(decisionVoters);
    }

    /**
     * 允许跨域配置
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }
    @Bean
    public SelfUsernamePasswordAuthencationFilter selfUsernamePasswordAuthencationFilter() throws Exception{
        SelfUsernamePasswordAuthencationFilter filter = new SelfUsernamePasswordAuthencationFilter();
        //自定义认证成功处理器
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        //自定义认证失败处理器
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationManager(authenticationManagerBean()) ;
        return filter;
    }

}
