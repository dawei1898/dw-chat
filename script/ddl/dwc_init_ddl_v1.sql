
-- 初始化建表

SET NAMES utf8mb4;


-- 用户表

CREATE TABLE `dwc_user` (
  `id` bigint(20) NOT NULL COMMENT '用户ID',
  `name` varchar(128) NOT NULL COMMENT '用户昵称',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `phone` bigint(16) DEFAULT NULL COMMENT '手机号',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(2) DEFAULT '0' COMMENT '是否删除（0-否，1-是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';



-- 聊天会话记录表

CREATE TABLE `dwc_chat_record` (
  `chat_id` varchar(64) NOT NULL COMMENT '聊天会话ID',
  `chat_name` varchar(128) NOT NULL COMMENT '会话名称',
  `user_id` bigint(20) DEFAULT NULL COMMENT '所属用户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(2) DEFAULT '0' COMMENT '是否删除（0-否，1-是）',
  PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聊天会话记录表';


-- 聊天消息表

CREATE TABLE `dwc_chat_message` (
  `msg_id` bigint(20) NOT NULL COMMENT '消息ID',
  `raw_msg_id` varchar(64) NOT NULL COMMENT '原始消息ID',
  `chat_id` varchar(64) DEFAULT NULL COMMENT '聊天会话ID',
  `type` varchar(4) NOT NULL COMMENT '消息类型（user-用户提问，ai-机器回答）',
  `role` varchar(32) DEFAULT NULL COMMENT '角色（user-用户，assistant-AI助手）\n',
  `content_type` varchar(16) DEFAULT NULL COMMENT '消息内容格式（text-文本，image-图像）',
  `content` text NOT NULL COMMENT '消息内容',
  `reasoning_content` text DEFAULT NULL COMMENT '思考内容',
  `tokens` int(20) DEFAULT NULL COMMENT '消耗token数',
  `model_group` varchar(32) DEFAULT NULL COMMENT '模型厂商',
  `model_id` varchar(128) DEFAULT NULL COMMENT '模型ID',
  `create_user` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0-否，1-是）',
  PRIMARY KEY (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聊天消息表';


-- 点赞/踩记录表

CREATE TABLE `dwc_vote_record` (
  `vote_id` bigint(20) NOT NULL COMMENT '唯一标识',
  `content_id` varchar(64) NOT NULL COMMENT '内容ID（消息、评论等）',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `vote_type` varchar(8) DEFAULT NULL COMMENT 'up-点赞，down-点踩',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞/踩记录表';




