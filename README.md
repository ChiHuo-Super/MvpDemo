# MvpDemo
以MVP设计模型搭建的项目结构，其封装了rxjava、retrofit、gson、greenDao、butterknife、glide、eventbus、zxing等框架  
详情  
mvp的全称为Model-View-Presenter，Model提供数据，View负责显示，Controller/Presenter负责逻辑的处理。MVP与MVC有着一个重大的区别：在MVP中View并不直接使用Model，它们之间的通信是通过Presenter (MVC中的Controller)来进行的，所有的交互都发生在Presenter内部，而在MVC中View会直接从Model中读取数据而不是通过 Control
