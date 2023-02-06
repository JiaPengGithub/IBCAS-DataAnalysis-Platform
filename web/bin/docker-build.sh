#!/bin/sh
# /**
#  * Copyright (c)
#  * No deletion without permission, or be held responsible to law.
#  *
#  * Author: joey_huang@yeah.net
#  * 
#  */
echo ""
echo "[信息] 打包Web工程，编译Docker镜像。"
echo ""

cd ..
mvn clean package docker:remove docker:build -Dmaven.test.skip=true -U

cd bin