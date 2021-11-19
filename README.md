## 简单Spring企业级服务化项目尝试



### 项目模块介绍

##### commons
基础依赖, 原则上所有项目都需要依赖这module
- 公共异常
- 全局响应


##### provider-api

dubbo 服务接口与传输DTO等


##### gateway 
API网关
- 鉴权认证
- 熔断
- 限流
- 路由

##### echo
功能测试专用项目

##### fp-user
用户服务前台

- 账户相关
- 权限相关
