./bin/redis-cli -h 192.168.10.221 -p 6380 shutdown
./bin/redis-cli -h 192.168.10.221 -p 6381 shutdown
./bin/redis-cli -h 192.168.10.221 -p 6382 shutdown

echo '查询Redis相关进程'
ps -ef | grep redis
