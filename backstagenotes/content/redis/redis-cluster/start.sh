./bin/redis-server ./conf/redis-6380.conf
./bin/redis-server ./conf/redis-6381.conf
./bin/redis-server ./conf/redis-6382.conf

echo '查看Redis进程'
ps -ef | grep redis
