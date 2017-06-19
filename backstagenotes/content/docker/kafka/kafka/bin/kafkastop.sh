#!/bin/sh

# 关闭zookeeper
/usr/local/kafka/bin/zookeeper-server-stop.sh /usr/local/kafka/config/zookeeper.properties &
sleep 3

# 关闭kafuka
/usr/local/kafka/bin/kafka-server-stop.sh /usr/local/kafka/config/server.properties &
