1.登陆账号和密码都是root,务必确认其长度为4.
2.login界面下有一个下拉选项，用于选择要查看的数据库
3.运行程序之前请做以下几点
    a).请解压文件至某一目录下，并用idea打开此项目.
        运行环境的要求：
            IntelliJ IDEA 2018.2.4 (Ultimate Edition)
            Build #IU-182.4505.22, built on September 18, 2018
            JRE: 1.8.0_152-release-1248-b8 amd64
            确保JDK版本高于或低于 1.8.0_152 以支持 lambda 语法
            Windows 10 10.0
    b).在idea中运行程序之前务必请建立好数据库(务必保证数据库为mysql 8.0)，在使用机器上利用例如Navicat for mysql
        或其他类似软件建立2个本机的数据库(分别取名为smallrelations和largerelations)连接让后将项目目录"/src/main/resources/SQL/"下的sql文件按照
        DDL.sql — smallrelationsinsertFile.sql/largerelationsinsertFile(务必确认第二个导入的sql文件与数据库名对应).顺序导入相应的数据.
4.程序的主类为"/src/main/java/sample/Main".运行入口函数为该主类下的main函数.
5.具体操作需知,详情请看与项目源文件夹一起压缩的help.pdf
6.如项目运行有问题可以将报错信息log或截图发送至以下邮箱：739012175@qq.com