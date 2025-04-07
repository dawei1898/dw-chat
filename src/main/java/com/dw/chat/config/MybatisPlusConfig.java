package com.dw.chat.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * MybatisPlus配置
 *
 * @author dawei
 */

@Configuration
@MapperScan("com.dw.chat.dao")
public class MybatisPlusConfig {


    /**
     * 插件配置
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页查询配置
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));  // 如果配置多个插件, 切记分页最后添加
        return interceptor;
    }

    @Bean
    public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
        return plusProperties -> plusProperties.getGlobalConfig()
                /* 字段自动填充配置 */
                .setMetaObjectHandler(new MetaObjectHandler() {
                    @Override
                    public void insertFill(MetaObject metaObject) {
                        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
                        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
                    }
                    @Override
                    public void updateFill(MetaObject metaObject) {
                        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
                    }
                });
    }

}
