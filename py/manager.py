# -*- coding: utf-8 -*-
import threading
import time
import os
class CThread (threading.Thread):
    def __init__(self, pythonPath, pythonName, logName, statePath, successFlagPath, failFlagSuffix):
        threading.Thread.__init__(self)  #重写父类方法
        self.pythonPath = pythonPath
        self.pythonName = pythonName
        self.logName = logName;
        self.statePath = statePath
        self.successFlagPath = successFlagPath
        self.failFlagSuffix = failFlagSuffix

    def run(self):
        print(self.pythonPath + self.pythonName)
        file = open(self.statePath, mode = 'w')
        file.write("2")
        file.close()
        str = 'python3 ' + self.pythonPath + self.pythonName + ' > /Users/jiapeng/Desktop/植物研究所/garden_file/output_log/' + self.logName + '.log'
        print('str = ' + str)
        code = os.system(str)
        print(code)
        if code == 0:
            print(self.successFlagPath)
            file = open(self.successFlagPath, mode = 'w')
            file.write("")
            file.close()
        else:
            print(self.failFlagSuffix)
            file = open(self.failFlagSuffix, mode = 'w')
            file.write("")
            file.close()
        file = open(self.statePath, mode = 'w')
        file.write("3")
        file.close()


def getPythonName(command):
    # param: 2020StartTime_1673164949954_test.py
    print(command)
    splits = command.split('-')
    return splits[2]


if __name__ == "__main__":

    # TODO 配置项
    # pythonPath = "/Users/jiapeng/Desktop/garden_file/zwbxzsj/"
    # commandPath = "/Users/jiapeng/MyCode/garden_code/jeesite-v4.6.0/command/"

    pythonPath = "/Users/jiapeng/MyCode/garden_code/jeesite-v4.6.0/web/src/main/webapp/WEB-INF/classes/script_code/"
    commandPath = "/Users/jiapeng/MyCode/garden_code/jeesite-v4.6.0/web/src/main/webapp/WEB-INF/classes/script_code/"

    stateSuffix = "/state"
    successFlagSuffix = "/_.success"
    failFlagSuffix = "/_.fail"

    while True: 
        # 遍历所有Java发出的命令
        for fileName in os.listdir(commandPath):
    #         print(file_name)
            if fileName == '.DS_Store':
                continue;
            if os.path.isdir(commandPath + fileName) == False:
                continue;
            statePath = commandPath + fileName + stateSuffix
            existFlag = os.path.exists(statePath)
            if existFlag == False:
                # init
                file = open(statePath, mode = 'w')
                file.write("0")
                file.close()
                # 截取python文件名；拼接python路径；
                pythonName = getPythonName(fileName)
                # 开启线程执行
                successFlagPath = commandPath + fileName + successFlagSuffix
                failFlagSuffix = commandPath + fileName + failFlagSuffix
                ct = CThread(pythonPath, pythonName, fileName, statePath, successFlagPath, failFlagSuffix)
                ct.start()
                file = open(statePath, mode = 'w')
                file.write("1")
                file.close()
                # TODO 线程放到线程池；
                # TODO 监控进程，更新命令执行状态

        # sleep 1s
        time.sleep(1)




