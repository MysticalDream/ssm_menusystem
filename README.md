# ssm_menusystem
基于SSM的菜谱管理系统

[![Build Status](https://img.shields.io/travis/markdown-it/markdown-it-emoji/master.svg?style=flat)](https://travis-ci.org/MysticalDream/ssm_menusystem)
[![NPM version](https://img.shields.io/npm/v/markdown-it-emoji.svg?style=flat)](https://www.npmjs.org/package/ssm_menusystem)
[![Coverage Status](https://coveralls.io/repos/MysticalDream/ssm_menusystem/badge.svg?branch=master&service=github)](https://coveralls.io/github/MysticalDream/ssm_menusystem?branch=master)

# 运行项目
修改项目的配置文件`src/main/resources/application.yml`

```xml
#配置服务器和静态资源路径
ssmServer:
  #配置服务器的端口
  port: 8080 
  #配置静态资源路径，即您需要将项目提供的`menu_system_resources`文件下的两个文件`avatar和menu_img`放到`imgLocation`所配置的路径
  imgLocation: C:/menu_system_resources 
#配置日志打印根路径
log4j:
  #如需更改日志打印路径，您可以通过修改该配置选项来实现
  basePath: D:/mylogs 

```

# 
