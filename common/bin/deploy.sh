#!/bin/sh
# /**
#  * Copyright (c)
#  * No deletion without permission, or be held responsible to law.
#  *
#  * Author: joey_huang@yeah.net
#  * 
#  */
echo ""
echo "[信息] 部署工程版本到Nexus服务器。"
echo ""

cd ..
mvn clean deploy -Dmaven.test.skip=true -Pdeploy

cd bin