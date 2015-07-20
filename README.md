# redis-cluster-utils

## Prints out all keys of a redis cluster.


### Requirements:
1. JDK 8

### Usage:
1. Clone this repository locally
2. cd into the cloned directory
3. `mvn clean compile assembly:single`
4. `java -jar target/redis-cluster-utils.jar [redis_host] [redis_port]`

Note that `redis_host` and `redis_port` can be any node of a redis cluster and it will figure out the other nodes by itself.

### Example:
```
 14:11:28:eidan@Eidan-SSD:~ $ java -jar target/redis-cluster-utils.jar 172.17.0.4 7000
 07-20 13:56:23.673INFO [main] Main - Found hostAndPort = 172.17.0.4:7002
 07-20 13:56:23.674INFO [main] Main - Found hostAndPort = 172.17.0.4:7005
 07-20 13:56:23.674INFO [main] Main - Found hostAndPort = 172.17.0.4:7004
 07-20 13:56:23.674INFO [main] Main - Found hostAndPort = 172.17.0.4:7003
 07-20 13:56:23.674INFO [main] Main - Found hostAndPort = 172.17.0.4:7001
 07-20 13:56:23.674INFO [main] Main - Found hostAndPort = 172.17.0.4:7000
 07-20 13:56:23.675INFO [main] Main - Retrieving keys of all nodes....
 07-20 13:56:23.681INFO [main] Main - Keys of Node 172.17.0.4:7001 = [keyJobTimestamp:PendingScoringTaskJob:0]
 07-20 13:56:23.682INFO [main] Main - Keys of Node 172.17.0.4:7000 = [flowCompCache:0, keyJobTimestamp:SendMailJob:0]
 07-20 13:56:23.682INFO [main] Main - Keys of Node 172.17.0.4:7005 = [keyJobTimestamp:TimeDrivenJob:0, keyJobTimestamp:SentMessagesTtlJob:0]
 ```

