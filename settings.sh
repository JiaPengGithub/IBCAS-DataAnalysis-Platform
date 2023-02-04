#!/usr/bin/env bash
# /**
#  * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
#  * No deletion without permission, or be held responsible to law.
#  *
#  * Author: ThinkGem@163.com
#  */
echo ""
echo "[INFO] 设置Maven版本库路径"
echo ""

WORK_DIR="$(cd $(dirname $0); pwd)"

MAVEN_HOME="$WORK_DIR/maven3"

echo "[INFO] 正在设置..."
echo ""

txt="$WORK_DIR/repository"

echo "[INFO] 版本库路径为：$txt"
echo ""

cp $MAVEN_HOME/conf/settings.xml.default $MAVEN_HOME/conf/settings.xml
sed -i "s|@REPO_DIR@|$txt|g" $MAVEN_HOME/conf/settings.xml

echo "[INFO] 版本库设置完成。"
echo ""
echo "[INFO] 《《《请务必按照以下说明继续操作》》》"
echo ""
echo "[INFO] 1）配置系统环境变量："
echo ""
echo "          # vi /etc/profile"
echo ""
echo "          在文档最后添加："
echo ""
echo "          export PATH=\"\$PATH:$MAVEN_HOME/bin\""
echo ""
echo "          保存，退出，然后运行："
echo ""
echo "          # source /etc/profile"
echo ""
echo "          授予可执行权限："
echo ""
echo "          # chmod -R a+x ./maven3/bin"
echo ""
echo "[INFO] 2）Eclipse 配置方法，依次操作如下："
echo ""
echo "          若没有安装 Maven 插件，则先安装 Maven 插件，"
echo "          Window --> Preferences --> Maven --> User Settings"
echo "                 --> 填写路径：$MAVEN_HOME/conf/settings.xml"
echo "          点击 OK 按钮。 "
echo ""
echo "[INFO] 3）IDEA 配置方法，依次操作如下："
echo ""
echo "          File --> Settings --> Bulid, Execution, Deployment "
echo "               --> Bulid Tools --> Maven --> User settings file"
echo "               --> 填写路径：$MAVEN_HOME/conf/settings.xml"
echo "          点击 OK 按钮。"
echo ""
echo ""
echo "[INFO] 注意：只需配置 settings.xml 即可，由于每个人的 IDE 版本不同，"
echo "             所以对应 IDE 匹配的 Maven 版本也不同，使用 IDE 内置的"
echo "             Maven 工具，可减少出错率。"
echo ""
echo ""

echo -n "请按任意键继续 ... "
read text
