###################################
#定义基础环境，后续执行在此基础上进行的，即jdk环境
FROM openjdk:8u232-stretch

#指定作者
MAINTAINER  Eric

RUN  mkdir /data/aliyunDnsSetting

#文件到镜像中
COPY  dist/*  /data/aliyunDnsSetting



#同步宿主时区
# volumes:  
#      - /etc/localtime:/etc/localtime 

#暴露给容器外的端口: http 
EXPOSE 8015 

#执行的命令
ENTRYPOINT  ["java","-jar","-Duser.timezone=GMT+08","/data/aliyunDnsSetting/joyconn_aliyundns-1.0-SNAPSHOT.jar"]   