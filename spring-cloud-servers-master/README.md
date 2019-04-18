# spring-cloud-servers
spring cloud项目，集成gateway+权限系统+注册中心consul+netty自定义通讯
## 项目结构：
- spring-cloud-center 注册中心(consul实现)，可支持集群
- spring-cloud-auth 权限,jwt权限系统，可支持单点登录
- spring-cloud-gateway 路由中心，集成spring-cloud-starter-gateway
- spring-cloud-netty netty通讯，自定义通讯协议，可在此基础上实现IM即时通讯
- spring-cloud-moudle 业务模块系统，有实例，必须经由auth认证
- spring-cloud-api 主要放置一些静态变量及POJO


