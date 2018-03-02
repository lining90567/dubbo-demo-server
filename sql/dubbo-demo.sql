-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        5.7.17 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5237
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 dubbo_demo 的数据库结构
CREATE DATABASE IF NOT EXISTS `dubbo_demo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dubbo_demo`;

-- 导出  表 dubbo_demo.sys_resource 结构
CREATE TABLE IF NOT EXISTS `sys_resource` (
  `id` smallint(6) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL,
  `url` varchar(200) NOT NULL,
  `permission` varchar(200) NOT NULL,
  `is_locked` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `router_id` smallint(5) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- 正在导出表  dubbo_demo.sys_resource 的数据：~15 rows (大约)
/*!40000 ALTER TABLE `sys_resource` DISABLE KEYS */;
INSERT INTO `sys_resource` (`id`, `name`, `code`, `url`, `permission`, `is_locked`, `router_id`) VALUES
	(1, '查询用户', 'users', '/users', 'users:read', 0, 2),
	(2, '添加用户', 'users', '/users', 'users:create', 0, 2),
	(3, '修改用户', 'users', '/users', 'users:update', 0, 2),
	(4, '删除用户', 'users', '/users', 'users:delete', 0, 2),
	(5, '查询路由', 'routers', '/routers', 'routers:read', 0, 5),
	(6, '添加路由', 'routers', '/routers', 'routers:create', 0, 5),
	(7, '修改路由', 'routers', '/routers', 'routers:update', 0, 5),
	(8, '删除路由', 'routers', '/routers', 'routers:delete', 0, 5),
	(9, '查询角色', 'roles', '/roles', 'roles:read', 0, 4),
	(10, '修改角色', 'roles', '/roles', 'roles:update', 0, 4),
	(11, '添加角色', 'roles', '/roles', 'roles:create', 0, 4),
	(12, '更新资源', 'resources', '/resources', 'resources:update', 0, 3),
	(13, '添加资源', 'resources', '/resources', 'resources:create', 0, 3),
	(14, '删除资源', 'resources', '/resources', 'resources:delete', 0, 3),
	(15, '查询资源', 'resources', '/resources', 'resources:read', 0, 3),
	(16, '删除角色', 'roles', '/roles', 'roles:delete', 0, 4);
/*!40000 ALTER TABLE `sys_resource` ENABLE KEYS */;

-- 导出  表 dubbo_demo.sys_resource_permission 结构
CREATE TABLE IF NOT EXISTS `sys_resource_permission` (
  `role_id` smallint(6) unsigned NOT NULL,
  `resource_id` smallint(6) unsigned NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  dubbo_demo.sys_resource_permission 的数据：~20 rows (大约)
/*!40000 ALTER TABLE `sys_resource_permission` DISABLE KEYS */;
INSERT INTO `sys_resource_permission` (`role_id`, `resource_id`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(1, 5),
	(1, 6),
	(1, 7),
	(1, 8),
	(1, 9),
	(1, 10),
	(1, 11),
	(1, 12),
	(1, 13),
	(1, 14),
	(1, 15),
	(1, 16),
	(2, 1),
	(2, 2),
	(2, 3),
	(2, 4);
/*!40000 ALTER TABLE `sys_resource_permission` ENABLE KEYS */;

-- 导出  表 dubbo_demo.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `is_locked` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  dubbo_demo.sys_role 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id`, `code`, `name`, `is_locked`) VALUES
	(1, 'admin', '系统管理员', 0),
	(2, 'sales', '销售员', 0),
	(3, 'finance', '财务人员', 0);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 导出  表 dubbo_demo.sys_router 结构
CREATE TABLE IF NOT EXISTS `sys_router` (
  `id` smallint(6) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `parent_id` smallint(5) unsigned NOT NULL,
  `level` tinyint(3) unsigned NOT NULL,
  `is_locked` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `display_order` smallint(5) unsigned NOT NULL DEFAULT '0',
  `properties` json DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_url_parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 正在导出表  dubbo_demo.sys_router 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `sys_router` DISABLE KEYS */;
INSERT INTO `sys_router` (`id`, `name`, `code`, `url`, `parent_id`, `level`, `is_locked`, `display_order`, `properties`) VALUES
	(1, '系统管理', NULL, NULL, 0, 1, 0, 1, '{"meta": {"requiresAuth": false}, "cssClass": "el-icon-setting", "nameFullPath": "系统管理"}'),
	(2, '用户管理', 'user', 'userList', 1, 2, 0, 5, '{"meta": {"requiresAuth": true}, "cssClass": "el-icon-menu", "component": "UserView", "nameFullPath": "系统管理/用户管理"}'),
	(3, '资源管理', 'resource', 'resourceList', 1, 2, 0, 2, '{"meta": {"requiresAuth": true}, "cssClass": "el-icon-menu", "component": "ResourceView", "nameFullPath": "系统管理/资源管理"}'),
	(4, '角色管理', 'role', 'roleList', 1, 2, 0, 4, '{"meta": {"requiresAuth": true}, "cssClass": "el-icon-menu", "component": "RoleView", "nameFullPath": "系统管理/角色管理"}'),
	(5, '路由管理', 'router', 'routerList', 1, 2, 0, 1, '{"meta": {"requiresAuth": true}, "cssClass": "el-icon-menu", "component": "RouterView", "nameFullPath": "系统管理/路由管理"}');
/*!40000 ALTER TABLE `sys_router` ENABLE KEYS */;

-- 导出  表 dubbo_demo.sys_router_permission 结构
CREATE TABLE IF NOT EXISTS `sys_router_permission` (
  `role_id` smallint(6) unsigned NOT NULL,
  `router_id` smallint(6) unsigned NOT NULL,
  PRIMARY KEY (`role_id`,`router_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  dubbo_demo.sys_router_permission 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `sys_router_permission` DISABLE KEYS */;
INSERT INTO `sys_router_permission` (`role_id`, `router_id`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(1, 5),
	(2, 1),
	(2, 2);
/*!40000 ALTER TABLE `sys_router_permission` ENABLE KEYS */;

-- 导出  表 dubbo_demo.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `login_name` varchar(32) NOT NULL,
  `salt` varchar(64) DEFAULT NULL,
  `login_password` varchar(64) NOT NULL,
  `is_locked` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` smallint(5) unsigned NOT NULL,
  `deleted_at` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  dubbo_demo.sys_user 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `name`, `login_name`, `salt`, `login_password`, `is_locked`, `created_at`, `created_by`, `deleted_at`) VALUES
	(1, '系统管理员', 'system', '248e594ef02913de01abedb6ff6310fa', '7fa8c18dad72eaac9cdd50d4c020807c', 0, '2017-10-02 10:26:28', 1, 0),
	(2, '销售员1', 'sales1', 'cf7389fbeae819e97cb893738cbcd6a9', 'c77d38deb0e6f4192123195cad7c3c3f', 0, '2017-11-13 13:22:41', 1, 0),
	(3, '出纳1', 'finance1', '1f0b5c815553885af26c7eba10315079', '7c27db969fe08288f115595618dba65f', 0, '2017-11-13 13:23:05', 1, 0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 dubbo_demo.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `user_id` smallint(5) unsigned NOT NULL,
  `role_id` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  dubbo_demo.sys_user_role 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
