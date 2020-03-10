package com.taotao.rest.redis;

public interface JedisClient {

	long hset(String hkey, String key, String value);

	String get(String key);

	String set(String key, String value);

	String hget(String hkey, String key);

	long incr(String key);

	long expire(String key, int second);

	long ttl(String key);
	
	long hdel(String hkey, String key);
	

}
