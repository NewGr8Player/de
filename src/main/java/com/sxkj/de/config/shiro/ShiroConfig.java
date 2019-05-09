package com.sxkj.de.config.shiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro配置
 *
 * @author NewGr8Player
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        return new UserRealm();
    }

    @Bean
    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setUsePrefix(true);
        return creator;
    }

    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录。
     * 这里只做鉴权，不做权限控制，因为权限用注解来做。
     *
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        //哪些请求可以匿名访问
        chain.addPathDefinition("/login", "anon");
        chain.addPathDefinition("/static/**", "anon");
        chain.addPathDefinition("/doc.html", "anon");
        chain.addPathDefinition("/swagger-ui.html", "anon");
        chain.addPathDefinition("/webjars/**", "anon");
        chain.addPathDefinition("/swagger-resources/**", "anon");
        chain.addPathDefinition("/v2/api-docs/**", "anon");
        chain.addPathDefinition("/v2/api-docs-ext/**", "anon");
        chain.addPathDefinition("/api/**", "anon");

        //除了以上的请求外，其它请求都需要登录
        chain.addPathDefinition("/**", "authc");

        return chain;
    }

}
