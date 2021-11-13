CREATE TABLE `user_info` (
  `id` int(11) NOT NULL,
  `name` varchar(64) CHARACTER SET latin1 NOT NULL DEFAULT '' COMMENT '用户名称',
  `gender` tinyint(4) DEFAULT '0' COMMENT '性别 0-位置, 1-男 2-女',
  `age` smallint(6) DEFAULT '0' COMMENT '年龄',
  `mobile` varchar(20) CHARACTER SET latin1 DEFAULT '' COMMENT '手机',
  `reg_mode` tinyint(4) NOT NULL DEFAULT '1' COMMENT '注册模式 1:手机注册 2:微信 3:QQ 4:支付宝 ....',
  `third_party_user_id` varchar(64) CHARACTER SET latin1 NOT NULL DEFAULT '' COMMENT '第三方应用用户ID',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0:未删除 1:已删除',
  `add_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基础信息表';


CREATE TABLE `cnc`.`user_secret_info` (
    `id` int(0) NOT NULL,
    `user_id` int(0) NOT NULL COMMENT '用户ID',
    `encrypt_password` int(0),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户隐私信息表';


