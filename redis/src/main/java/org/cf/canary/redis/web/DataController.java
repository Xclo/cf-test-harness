package org.cf.canary.redis.web;

import org.cf.canary.redis.service.DataService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Controller
@RestController
public class DataController {

	 Logger logger = LoggerFactory.getLogger(DataController.class);

	private static final String PREFIX = "CANARY-REDIS";
	private static final String KEY_PATTERN = PREFIX + "*";
	private RedisSerializer<String> serializer = new StringRedisSerializer();


	@Autowired
	private DataService dataservice;

    private static final String template = "Key: %s";
    private static final String templateView = template +  " Value: %s";

    @RequestMapping("/retrieve")
    public @ResponseBody String retrieve(
            @RequestParam(value="name", required=false, defaultValue="name") String name)
    {
    	String value = dataservice.retrieveValue(name);
    	String returnValue = String.format(templateView, name, value);
    	logger.info(returnValue);
        return value;
    }

		@RequestMapping("/retrieveAll")
		public @ResponseBody List<String> retrieveAll()
		{

			List<String> keys = dataservice.retrieveAll();
			return keys;
		}


    @RequestMapping("/store")
    public @ResponseBody String store(
            @RequestParam(value="name", required=false, defaultValue="name") String name,
            @RequestParam(value="value", required=false, defaultValue="value") String value)
    {
    	dataservice.storeValue(name, value);
    	String returnValue = String.format(template, name);
    	logger.info(returnValue);
    	return returnValue;
    }

		@RequestMapping("/randomKeysTest")
		public @ResponseBody List<String> randomKeysTest()
		{
			dataservice.generateRandomKeys(1000);
			List<String> keys = dataservice.retrieveKeys(KEY_PATTERN);
			dataservice.flushDb();
			return keys;
		}

}
