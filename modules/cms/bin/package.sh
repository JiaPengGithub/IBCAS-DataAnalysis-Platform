#!/bin/sh
# /**
#  * Copyright (c)
#  * No deletion without permission, or be held responsible to law.
#  *
#  * Author: joey_huang@yeah.net
#  * 
#  */
echo ""
echo "[信息] 打包Web工程，生成war/jar包文件。"
echo ""

cd ..
mvn clean install -Dmaven.test.skip=true -Ppackage

cd bin