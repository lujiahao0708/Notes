# Kafka
> 该容器的前提依赖是使用我的JDK的镜像的依赖,后面我会把镜像同步到线上.

## 构建镜像
下载好Dockerfile文件执行

	docker build -t="lujiahao/kafkatest" .
## 启动容器

	docker run --name test_kafka -it -p 2181:2181 -p 9092:9092 lujiahao/kafkatest /bin/bash
## 额外配置
容器中使用kafka还需要将容器的内部IP配置到`config/server.properties`中

	docker inspcet test_kafka查看容器的内部IP
然后更改config目录中的server.properties中的三个配置
	
	#容器的ip地址
	host.name=172.17.0.1
	#容器的ip地址
	listeners=PLAINTEXT://172.17.0.1:9092
	#宿主机的IP地址,用于外部Producer和Consumer使用(公司服务器你懂得)
	advertised.listeners=PLAINTEXT://xx.xx.xx.xx:9092
## 启动Kafka

	./kafkastart.sh

## 错误总结
### 1.无法分配内存
	Java HotSpot(TM) 64-Bit Server VM warning: INFO: os::commit_memory(0x00000000c0000000, 1073741824, 0) failed;
	error='Cannot allocate memory' (errno=12)
解决方案
	
	vi zookeeper-server-start.sh 
		将 export KAFKA_HEAP_OPTS="-Xmx512M -Xms512M"
		改成 export KAFKA_HEAP_OPTS="-Xmx512M -Xms128M"
	vi kafka-server-start.sh 
		将 export KAFKA_HEAP_OPTS="-Xmx1G -Xms1G"
		改成 export KAFKA_HEAP_OPTS="-Xmx512M -Xms128M"
	把这两个值改成相同的就行了
[参考资料](http://stackoverflow.com/questions/34966739/kafka-failed-to-map-1073741824-bytes-for-committing-reserved-memory)
### 2.无法发送消息
	Error when sending message to topic TESTTOPIC with key: null, value: 9 bytes with error: 
	Failed to update metadata after 60000 ms. (org.apache.kafka.clients.producer.internals.ErrorLoggingCallback)
解决方案
	
	在 server.properties 中添加 advertised.listeners=PLAINTEXT://xx.xx.xx.xx:9092 ,对应的IP为宿主机的IP
[参考资料](https://community.cloudera.com/t5/Cloudera-Manager-Installation/Error-publishing-a-message-after-upgrading-kafka-parcel/td-p/37640)

## Kafka的基本操作
1. 创建topic

		kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
2. 查看topic

		kafka-topics.sh --list --zookeeper localhost:2181
3. 生产者

		kafka-console-producer.sh --broker-list localhost:9092 --topic test
4. 消费者

		kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning
