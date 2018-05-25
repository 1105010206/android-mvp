# android-mvp
该工程使用MVP模式：

M:对数据的管理，例如：sharefreference、dbdata以及文件等；

V:使用activity和fragment充当view的角色；

P:控制逻辑，充当view和model之间通信的枢纽；

网络访问使用了retrofit框架，结合rxjava使用非常简洁方便；

异步使用了rxjava，事件发布和线程切换都非常简单好用；

数据库使用了objectbox，该项目只是演示了objectbox的基础使用，并没有在代码中实质去调用生成数据；
