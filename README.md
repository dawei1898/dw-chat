# Dw Chat

Dw Chat 是一个接入 DeepSeek 大模型的极简 AI 对话系统，旨在为用户提供高效、便捷的对话体验。

## 演示地址

- 网页演示地址：[https://dw-chat.dw1898.top](https://dw-chat.dw1898.top)
- 效果图：
  ![demo1.png](docs/demo1.png)

## 主要技术栈

1. **AI 集成**：DeepSeek LLM
2. **后端框架**：Java 21 + Spring Boot 3.4
3. **数据访问**：MyBatis Plus
4. **数据库**：MySQL
5. **其他工具**：spring-ai-deepseek（用于集成 DeepSeek）

## 项目系列生态

该项目 dw-chat 是后端工程，完整的项目生态包括以下版本：

| 工程名称 | 描述 | GitHub 地址 |
| --- | --- | --- |
| dw-chat-web-lite | 纯前端版工程 | https://github.com/dawei1898/dw-chat-web-lite |
| dw-chat-web | 前端工程 | https://github.com/dawei1898/dw-chat-web |
| dw-chat | 后端工程 | https://github.com/dawei1898/dw-chat |
| dw-chat-next | Next.js 全栈版工程 | https://github.com/dawei1898/dw-chat-next|
| dw-chat-nest | NestJS 后端工程 | https://github.com/dawei1898/dw-chat-nest |

## 数据库初始化

MySQL 数据库初始化建表 SQL 文件位于：`script/ddl/dwc_init_ddl_v1.sql`。

请在部署前运行该脚本以初始化数据库结构。