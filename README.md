# spring-netty-gateway
A netty gateway demo;

## 说明
适用于要使用netty做TCP长连接项目的小伙伴们，与SpringBoot整合，实现了tls1.2等。
这是一个基础的框架，实现了大部分tcp长连接项目时基本的一些步骤，没有业务代码，大家可以根据自己的业务场景拓展。
## 特色
- 实现在程序销毁（被kill 进程号命令杀死）后，能执行某些销毁前的清除动作，如断开所有客户端连接。
- ChannelAttrKey维护报文轨迹信息、channel健康数据、channel生命状态

## issues
有任何需要改进和不合理的地方，可以一起改进哟~

