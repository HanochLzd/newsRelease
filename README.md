# newsRelease
<b>新闻发布</b><br>
无后台框架、纯Servlet+JSP+Zui+LayerUI等开发<br>
适合熟悉javaweb项目开发流程及运行流程<br>
Mysql 使用最新的8.0.11，需注意其连接驱动名<br>
<hr>
sql:<br>
<pre>
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_news`;
CREATE TABLE `tb_news`  (
  `news_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '新闻id',
  `news_theme_id` int(10) NOT NULL COMMENT '新闻类型id',
  `news_author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '新闻作者',
  `news_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '新闻标题',
  `news_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `news_up` bigint(20) NULL DEFAULT 0 COMMENT '新闻点赞数',
  `news_down` bigint(20) NULL DEFAULT 0 COMMENT '踩',
  `news_create_time` datetime(0) NULL DEFAULT NULL COMMENT '新闻创建时间',
  PRIMARY KEY (`news_id`) USING BTREE,
  INDEX `news_theme_id`(`news_theme_id`) USING BTREE,
  CONSTRAINT `tb_news_ibfk_1` FOREIGN KEY (`news_theme_id`) REFERENCES `tb_theme` (`theme_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_praise
-- ----------------------------
DROP TABLE IF EXISTS `tb_praise`;
CREATE TABLE `tb_praise`  (
  `praise_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '点赞唯一ID',
  `praise_type` int(1) NOT NULL COMMENT '1:赞 0:踩',
  `praise_news_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '点赞的新闻',
  `praise_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '点赞IP',
  PRIMARY KEY (`praise_id`) USING BTREE,
  INDEX `tb_praise_ibfk_1`(`praise_news_id`) USING BTREE,
  CONSTRAINT `tb_praise_ibfk_1` FOREIGN KEY (`praise_news_id`) REFERENCES `tb_news` (`news_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_theme
-- ----------------------------
DROP TABLE IF EXISTS `tb_theme`;
CREATE TABLE `tb_theme`  (
  `theme_id` int(10) NOT NULL COMMENT '新闻主题id',
  `theme_name` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '新闻主题名字',
  `theme_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '新闻主题描述',
  `theme_level` int(10) NOT NULL COMMENT '新闻主题等级',
  `theme_create_time` datetime(0) NULL DEFAULT NULL COMMENT '新闻主题创建时间',
  PRIMARY KEY (`theme_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户唯一id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
</pre>
