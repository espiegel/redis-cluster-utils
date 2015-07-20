# redis-cluster-utils

Requirements:
1. JDK 8

Prints out all keys of a redis cluster.

1. Clone this repository locally
2. cd into the cloned directory
3. `mvn clean compile assembly:single`
4. `java -jar target/redis-cluster-utils.jar [redis_host] [redis_port]`

Note that `redis_host` and `redis_port` can be any node of a redis cluster and it will figure out the other nodes by itself.
