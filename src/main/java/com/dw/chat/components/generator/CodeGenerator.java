package com.dw.chat.components.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.nio.file.Paths;

/**
 * 代码生成器
 *
 * https://baomidou.com/guides/new-code-generator/
 *
 * @author dawei
 */
public class CodeGenerator {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/dwc";
        String username = "root";
        String password = "*";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> builder
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java") // 输出目录
                        .author("dawei") // 作者
                        .commentDate("yyyy-MM-dd") // 注释日期格式
                        .dateType(DateType.TIME_PACK) // 设置时间类型策略
                        .disableOpenDir() // 禁止生成后自动打开目录
                )
                .packageConfig(builder -> builder
                        .parent("com.dw.chat.components.generator.code") // 设置父包名
                        .entity("model.entity") // 设置实体类包名
                        .mapper("dao")          // 设置 Mapper 接口包名
                        .service("service")     // 设置 Service 接口包名
                        .serviceImpl("service.impl") // 设置 Service 实现类包名
                        .xml("mapper.xml")          // 设置 Mapper XML 文件包名
                )
                .strategyConfig(builder -> builder
                        .addInclude("dwc_user")// 需要配置的表
                        // 实体类配置
                        .entityBuilder()
                        .enableLombok() // 启用 Lombok
                        //.enableTableFieldAnnotation() // 启用字段注解
                        .idType(IdType.ASSIGN_ID)
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE)
                        )
                        .logicDeleteColumnName("deleted") // 逻辑删除字段
                        .enableFileOverride() // 覆盖字段
                        // controller配置
                        .controllerBuilder()
                        .enableFileOverride() // 覆盖已生成文件
                        .enableRestStyle() // 开启生成@RestController
                        // service配置
                        .serviceBuilder()
                        .enableFileOverride() // 覆盖已生成文件
                        //.superServiceClass(null)
                        //.superServiceImplClass("")
                        // Mapper 策略配置
                        .mapperBuilder()
                        .enableFileOverride()
                        .enableBaseResultMap()  // xml生成返回字段映射
                        .enableBaseColumnList() // xml生成表的全部字段
                        //.formatMapperFileName("%sDao")
                        //.formatXmlFileName("%sXml")
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
