/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : manage

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 22/08/2025 17:05:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `admin_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '管理员账户',
  `admin_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for atten
-- ----------------------------
DROP TABLE IF EXISTS `atten`;
CREATE TABLE `atten`  (
  `atten_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '考勤id',
  `atten_teacherid` int(0) NOT NULL COMMENT '考勤老师id',
  `atten_teachername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '考勤老师姓名',
  `atten_time` datetime(0) DEFAULT NULL COMMENT '考勤时间',
  `atten_hours` int(0) DEFAULT NULL COMMENT '考勤',
  `atten_masterid` int(0) NOT NULL COMMENT '考勤校长id',
  PRIMARY KEY (`atten_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `class_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '学科id',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学科名字',
  `class_teacherid` int(0) DEFAULT NULL COMMENT '所属老师',
  `class_masterid` int(0) DEFAULT NULL COMMENT '所属校长',
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for coll
-- ----------------------------
DROP TABLE IF EXISTS `coll`;
CREATE TABLE `coll`  (
  `coll_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '账单id',
  `coll_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP COMMENT '账单时间',
  `coll_stuname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账单所属学生姓名',
  `coll_stuid` int(0) NOT NULL COMMENT '账单所属学生id',
  `coll_collname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账单操作人员名字',
  `coll_collid` int(0) NOT NULL COMMENT '账单操作人员id',
  `coll_num` double(10, 2) NOT NULL COMMENT '账单金额',
  `coll_classid` int(0) NOT NULL COMMENT '账单所属学科id',
  `coll_classname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账单所属学科名字',
  `coll_fromname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账单付款人',
  `coll_hours` int(0) DEFAULT NULL COMMENT '增加课时',
  `coll_masterid` int(0) DEFAULT NULL COMMENT '所属校长',
  `coll_state` int(0) DEFAULT NULL COMMENT '订单状态',
  PRIMARY KEY (`coll_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for commiss
-- ----------------------------
DROP TABLE IF EXISTS `commiss`;
CREATE TABLE `commiss`  (
  `commiss_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '上课id',
  `commiss_masterid` int(0) DEFAULT NULL COMMENT '上课校长id',
  `commiss_teacherid` int(0) DEFAULT NULL COMMENT '上课老师id',
  `commiss_times` int(0) DEFAULT NULL COMMENT '上课次数',
  `commiss_time` datetime(0) DEFAULT NULL COMMENT '上课时间',
  `commiss_teachername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '上课老师名字',
  PRIMARY KEY (`commiss_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kaoqin
-- ----------------------------
DROP TABLE IF EXISTS `kaoqin`;
CREATE TABLE `kaoqin`  (
  `kaoqin_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '考勤id',
  `kaoqin_masterid` int(0) DEFAULT NULL COMMENT '考勤校长id',
  `kaoqin_teacherid` int(0) DEFAULT NULL COMMENT '考勤老师id',
  `kaoqin_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '考勤时间',
  `kaoqin_days` int(0) DEFAULT NULL COMMENT '上班天数',
  `kaoqin_tentime` int(0) DEFAULT NULL COMMENT '迟到早退10分钟内',
  `kaoqin_onehours` int(0) DEFAULT NULL COMMENT '迟到早退一小时内',
  `kaoqin_twohours` int(0) DEFAULT NULL COMMENT '迟到早退两小时内',
  `kaoqin_threehours` int(0) DEFAULT NULL COMMENT '迟到早退三小时内',
  `kaoqin_bantian` int(0) DEFAULT NULL COMMENT '请假半天',
  `kaoqin_qingjia` int(0) DEFAULT NULL COMMENT '请假一天',
  `kaoqin_ying` int(0) DEFAULT NULL COMMENT '应该上班的天数',
  `kaoqin_num` double(255, 2) DEFAULT NULL COMMENT '考勤金额',
  `kaoqin_teachernum` double(255, 2) DEFAULT NULL COMMENT '老师底薪',
  `kaoqin_kaoqinnum` double(255, 2) DEFAULT NULL COMMENT '全勤奖',
  `kaoqin_teachername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '老师姓名',
  `kaoqin_state` int(0) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`kaoqin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for latent
-- ----------------------------
DROP TABLE IF EXISTS `latent`;
CREATE TABLE `latent`  (
  `latent_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '邀约id',
  `latent_stuname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邀约学生名字',
  `latent_from` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邀约来源',
  `latent_fromname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邀约人',
  `latent_telname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电话联系人',
  `latent_teachername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '试听课老师',
  `latent_talkname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '约谈老师',
  `latent_speech` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '宣讲人',
  `latent_endpeoplename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最后操作记录人',
  `latent_endpeopleid` int(0) DEFAULT NULL COMMENT '最后操作记录人id',
  `latent_state` int(0) DEFAULT NULL COMMENT '状态',
  `latent_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `latent_tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '家长联系电话',
  `latent_contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '家长姓名',
  `latent_num` double(255, 2) DEFAULT 0.00 COMMENT '邀约成功分成',
  `latent_stusex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '性别',
  `latent_age` int(0) DEFAULT NULL COMMENT '年龄',
  `latent_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '预约课程',
  `latent_delete` int(0) DEFAULT NULL COMMENT '删除',
  `latent_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `latent_ytime` timestamp(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `latent_ctime` datetime(0) DEFAULT NULL COMMENT '成交时间',
  `latent_masterid` int(0) DEFAULT NULL COMMENT '校长id',
  PRIMARY KEY (`latent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for leads
-- ----------------------------
DROP TABLE IF EXISTS `leads`;
CREATE TABLE `leads`  (
  `leads_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '潜在id',
  `leads_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '潜在学校名字 ',
  `leads_other` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '潜在学生亲属',
  `leads_othertel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '潜在学生亲属电话',
  `leads_from` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '潜在学生来源',
  `leads_com` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '潜在学生联系人',
  `leads_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '潜在学生状态',
  PRIMARY KEY (`leads_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for master
-- ----------------------------
DROP TABLE IF EXISTS `master`;
CREATE TABLE `master`  (
  `master_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '校长id',
  `master_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '校长账号',
  `master_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '校长密码',
  `master_lv` int(0) DEFAULT NULL COMMENT '校长等级',
  `master_gu` int(0) DEFAULT NULL COMMENT '股份',
  PRIMARY KEY (`master_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary`  (
  `salary_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '薪资id',
  `salary_masterid` int(0) DEFAULT NULL COMMENT '薪资所属校长',
  `salary_teacherid` int(0) DEFAULT NULL COMMENT '老师名字',
  `salary_teachername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '老师id',
  `salary_dixin` double(255, 2) DEFAULT NULL COMMENT '老师底薪',
  `salary_kaoqin` double(255, 2) DEFAULT NULL COMMENT '老师考勤',
  `salary_jixiao` double(255, 2) DEFAULT NULL COMMENT '老师绩效',
  `salary_ticheng` double(255, 2) DEFAULT NULL COMMENT '邀约提成',
  `salary_baoxian` double(255, 2) DEFAULT NULL COMMENT '保险',
  `salary_gongling` int(0) DEFAULT NULL COMMENT '工龄',
  `salary_gongsalary` double(255, 2) DEFAULT NULL COMMENT '工龄奖金',
  `salary_gufen` double(255, 2) DEFAULT NULL COMMENT '股份分红',
  `salary_extra` double(255, 2) DEFAULT NULL COMMENT '其他奖罚',
  `salary_num` double(255, 2) DEFAULT NULL COMMENT '总工资',
  `salary_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `salary_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '时间',
  `salary_jixiaonum` double(255, 2) DEFAULT NULL COMMENT '绩效奖金',
  `salary_endnum` double(255, 2) DEFAULT NULL COMMENT '实发工资',
  `salary_state` int(0) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`salary_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shouzhi
-- ----------------------------
DROP TABLE IF EXISTS `shouzhi`;
CREATE TABLE `shouzhi`  (
  `shouzhi_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '收支id',
  `shouzhi_masterid` int(0) DEFAULT NULL COMMENT '收支校长id',
  `shouzhi_jixiao` double(255, 2) DEFAULT NULL COMMENT '收支绩效',
  `shouzhi_gongzi` double(255, 2) DEFAULT NULL COMMENT '工资',
  `shouzhi_fangzu` double(255, 2) DEFAULT NULL COMMENT '房租',
  `shouzhi_shui` double(255, 2) DEFAULT NULL COMMENT '水',
  `shouzhi_zhi` double(255, 2) DEFAULT NULL COMMENT '支出',
  `shouzhi_shou` double(255, 2) DEFAULT NULL COMMENT '收入',
  `shouzhi_num` double(255, 2) DEFAULT NULL COMMENT '纯利润',
  `shouzhi_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '时间',
  `shouzhi_remake` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `shouzhi_dian` double(255, 2) DEFAULT NULL COMMENT '电',
  `shouzhi_wuye` double(255, 2) DEFAULT NULL COMMENT '物业',
  `shouzhi_state` int(0) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`shouzhi_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `student_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学生名字',
  `student_sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学生性别',
  `student_ma` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学生母亲',
  `student_fa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学生父亲',
  `student_other` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学生亲属',
  `student_matel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学生母亲电话',
  `student_fatel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学生父亲电话',
  `student_othertel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学生亲属电话',
  `student_teacherid` int(0) DEFAULT NULL COMMENT '学生所属老师',
  `student_classid` int(0) DEFAULT NULL COMMENT '学生所属学科',
  `student_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP COMMENT '学生入校时间',
  `student_hours` int(0) DEFAULT NULL COMMENT '学生所剩课时',
  `student_age` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学生年龄',
  `student_classname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '课程名称',
  `student_state` int(0) DEFAULT NULL COMMENT '状态',
  `student_teachername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '老师姓名',
  `student_masterid` int(0) DEFAULT NULL COMMENT '校长姓名',
  `student_classcoll` double(10, 2) UNSIGNED ZEROFILL NOT NULL COMMENT '课时费',
  `student_zhours` int(0) DEFAULT 0 COMMENT '总课时',
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for taxes
-- ----------------------------
DROP TABLE IF EXISTS `taxes`;
CREATE TABLE `taxes`  (
  `taxes_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '消课id',
  `taxes_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `taxes_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消课备注',
  `taxes_teacherid` int(0) NOT NULL COMMENT '申请老师id',
  `taxes_teachername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '申请老师姓名',
  `taxes_masterid` int(0) DEFAULT NULL COMMENT '消课校长id',
  `taxes_stuidlist` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '消课学生id组',
  `taxes_stunamelist` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '消课学生姓名组',
  `taxes_classid` int(0) DEFAULT NULL COMMENT '消课班级id',
  `taxes_classname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消课班级名字',
  `taxes_state` int(0) DEFAULT NULL COMMENT '状态',
  `taxes_datetime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消课日期',
  PRIMARY KEY (`taxes_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacher_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '老师id',
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '老师账号',
  `teacher_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '老师密码',
  `teacher_masterid` int(0) DEFAULT NULL COMMENT '老师所属校长',
  `teacher_state` int(0) DEFAULT NULL COMMENT '老师在职状态',
  `teacher_tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '老师电话',
  `teacher_time` datetime(0) DEFAULT NULL COMMENT '入职时间',
  `teacher_num` double(255, 2) DEFAULT NULL COMMENT '底薪',
  `teacher_baostate` int(0) DEFAULT NULL COMMENT '保险状态',
  `teacher_baonum` double(255, 2) DEFAULT NULL COMMENT '保险金额',
  `teacher_baogong` double(255, 2) DEFAULT NULL COMMENT '保险公司承担',
  `teacher_jixiao` int(0) DEFAULT NULL COMMENT '绩效区间4种',
  `teacher_kaoqin` double(255, 2) DEFAULT NULL COMMENT '全勤奖金',
  `teacher_gu` int(0) DEFAULT NULL COMMENT '持有股份',
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
