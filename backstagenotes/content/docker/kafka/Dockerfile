FROM lujiahao/jdk1.7
ENV CREATE_TIME 2017-03-21
MAINTAINER lujiahao
RUN yum install -y wget vim
RUN wget http://archive.apache.org/dist/kafka/0.10.0.1/kafka_2.10-0.10.0.1.tgz
RUN tar -zxvf kafka_2.10-0.10.0.1.tgz
RUN rm -rf kafka_2.10-0.10.0.1.tgz
RUN mv kafka_2.10-0.10.0.1 /usr/local/kafka

#创建一些目录
RUN mkdir -p /usr/local/kafka/log/kafka /usr/local/kafka/zookeeper /usr/local/kafka/log/zookeeper

#环境变量设置
ENV JAVA_HOME /usr/local/jdk1.7
ENV JRE_HOME /usr/local/jdk1.7/jre
ENV CLASSPATH .:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV KAFKA_HOME /usr/local/kafka
ENV PATH $PATH:$JAVA_HOME/bin:$JRE_HOME/bin:$KAFKA_HOME/bin:$KAFKA_HOME/conf

#把一些配置文件复制到/tmp/kafka目录下
ADD kafka /tmp/kafka/
RUN ls -l /tmp/kafka/

#配置kafka和zookeeper
WORKDIR /usr/local/kafka/config
RUN mv server.properties server.properties.bak
RUN mv zookeeper.properties zookeeper.properties.bak
RUN cp /tmp/kafka/config/server.properties server.properties
RUN cp /tmp/kafka/config/zookeeper.properties zookeeper.properties

#切换工作目录
WORKDIR /usr/local/kafka/bin
RUN cp /tmp/kafka/bin/kafkastart.sh kafkastart.sh
RUN cp /tmp/kafka/bin/kafkastop.sh kafkastop.sh
RUN chmod a+x kafkastart.sh kafkastop.sh

#开放端口
EXPOSE 9092 2181