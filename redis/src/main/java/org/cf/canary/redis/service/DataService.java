package org.cf.canary.redis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.*;

import java.util.UUID;

@Service
public class DataService {

	Logger logger = LoggerFactory.getLogger(DataService.class);
	private static final String PREFIX = "CANARY-REDIS";

	@Autowired
	 private RedisConnectionFactory redisConnectionFactory;

	@Autowired
	StringRedisTemplate redisTemplate;

	private final static String template = "Key: %s Value: %s";
	private final static String viewTemplate = "Key: %s";

	public String retrieveValue(String keyName) {
		logger.error(String.format(viewTemplate, keyName));
		return redisTemplate.opsForValue().get(keyName);
	}

	public void storeValue(String keyName, String value) {
		logger.info(String.format(template, keyName,value));
		redisTemplate.opsForValue().set(keyName, value);
	}

	public void flushDb() {
		redisTemplate.getConnectionFactory().getConnection().flushDb();
	}

	public void generateRandomKeys(int nrKeys)
	{
		for (int i = 0; i < nrKeys; i++) {
			String name = PREFIX + "-" + i;
			String value = UUID.randomUUID().toString();
			storeValue(name,value);
		}
	}


	public List<String> retrieveKeys(String pattern) {
		Set<String> redisKeys = redisTemplate.keys(pattern);
		// Store the keys in a List
		List<String> keysList = new ArrayList<>();
		Iterator<String> it = redisKeys.iterator();
		while (it.hasNext()) {
				 String data = it.next();
				 keysList.add(data);
		}
		return keysList;
	}

	public List<String> retrieveAll() {
		Set<String> redisKeys = redisTemplate.keys("*");
		// Store the keys in a List
		List<String> keysList = new ArrayList<>();
		Iterator<String> it = redisKeys.iterator();
		while (it.hasNext()) {
				 String data = it.next();
				 keysList.add(data);
		}
		return keysList;
	}

}
