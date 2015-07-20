package com.espiegel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by eidan on 7/20/15.
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        if(args.length != 2) {
            logger.error("Arguments must be: [host] [port]");
            return;
        }


        String host = args[0];
        int port = Integer.parseInt(args[1]);

        List<HostAndPort> nodes = new ArrayList<>();
        nodes.add(new HostAndPort(host, port));

        Jedis jedis = new Jedis(host, port);
        String nodeInfo = jedis.clusterNodes();
        jedis.close();

        String[] split = nodeInfo.split("\n");
        for(String line : split) {
            String[] split2 = line.split(" ");
            String[] split3 = split2[1].split(":");
            String tmpHost = split3[0];
            String tmpPort = split3[1];

            HostAndPort hostAndPort = new HostAndPort(tmpHost, Integer.parseInt(tmpPort));
            logger.info("Found hostAndPort = {}", hostAndPort);
            nodes.add(hostAndPort);
        }

        logger.info("Retrieving keys of all nodes....");
        Map<String, HostAndPort> keyMap = new TreeMap<>();
        for(HostAndPort hostAndPort : nodes) {
            String curHost = hostAndPort.getHost();
            int curPort = hostAndPort.getPort();

            jedis = new Jedis(curHost, curPort);

            Set<String> keys = jedis.keys("*");

            for(String key : keys) {
                keyMap.put(key, hostAndPort);
            }
        }

        Map<HostAndPort, Set<String>> hostMap = new HashMap<>();

        for(String key : keyMap.keySet()) {
            HostAndPort hostPort = keyMap.get(key);

            if(hostMap.get(hostPort) == null) {
                hostMap.put(hostPort, new HashSet<>());
            }

            hostMap.get(hostPort).add(key);
        }

        for(HostAndPort hostPort : hostMap.keySet()) {
            logger.info("Keys of Node {} = {}", hostPort, hostMap.get(hostPort));
        }
    }
}
