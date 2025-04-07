package com.dw.chat.config;

import com.dw.chat.common.utils.SpringContextHolder;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 项目配置类
 *
 * @author dawei
 */

@Data
@Configuration
@Import({SpringContextHolder.class})
public class DwChatAppConfig {

}
