# Dubbo-Demo-Server

------

Dubbo-Demo-Server是基于Spring MVC+MyBatis+Shiro+Dubbo开发的分布式后台管理系统，Dubbo-Demo-Server包含了如下功能特点：

> * 基于Dubbo实现分布式应用
> * 无状态Web应用，使用JWT管理身份认证
> * 前后端分离，使用Shiro管理RESTful访问权限

## 使用方法

### 创建数据库

在MySQL中执行sql目录下的 dubbo-demo.sql

### 在Eclipse中启动Dubbo服务

 1. 导入项目到Eclipse（Import Existing Maven Projects）
 2. 修改数据库配置信息：dubbo-demo-system-provider\src\main\conf\properties\db.properties
 3. 右键dubbo-demo-system-provider，Run AS->Java Application，第一次运行会提示设置配置文件路径，再次右键dubbo-demo-system-provider，Run Configurations->在左侧选择Java Application节点下选择本项目的启动节点，点击右侧的Arguments页签，在VM arguments中添加 -Dfile.encoding=utf-8 -Dconf.home=x:\dubbo-demo-system-provider路径\src\main\conf -DLogback.configurationFile=x:\dubbo-demo-system-provider路径\src\main\conf\logback.xml，点击Run按钮启动Dubbo服务。

### 在Eclipse中启动后端Web服务

右键dubbo-demo-backend，Run AS->Run On Server

### 打包运行

1. mvn package dubbo-demo
2. 解压缩dubbo-demo-system-provider的target目录下的dubbo-demo-system-provider-1.0.zip文件，执行bin文件夹下的run.bat即可运行Dubbo服务（linux下执行run.sh）。
3. 将dubbo-demo-backend的target下的dubbo-demo-backend.war拷贝到tomcat的webapps下，运行tomcat即可运行Web服务。

### 其他事项
1. 前端应用请参见 [dubbo-demo-client](https://github.com/lining90567/dubbo-demo-client)
2. 为了方便单机开发测试，没有使用zookeeper注册dubbo服务，如果需要使用zookeeper，需要修改dubbo-demo-system-providerde的spring-dubbo-provider.xml文件与dubbo-demo-backend的spring-dubbo-consumer.xml中的dubbo:registry配置。
3. 数据库使用了json类型，请使用MySQL 5.7以上版本（含5.7）。
 
**作者信息**

博客：[http://blog.csdn.net/hyxhbj1](http://blog.csdn.net/hyxhbj1)

邮箱：lining90567@sina.com

QQ：328616209