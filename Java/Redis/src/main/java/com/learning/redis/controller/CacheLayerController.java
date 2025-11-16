package com.learning.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learning.redis.dto.CacheDTO;

@RestController
@RequestMapping("/redis")
public class CacheLayerController {
	
//	@Autowired
//    private StringRedisTemplate redisTemplate;
	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
	@PostMapping("/pushToRedis")
	public ResponseEntity<String> pushToRedis(@RequestBody CacheDTO cacheDTO){
		redisTemplate.opsForValue().set(cacheDTO.getKey(), cacheDTO);
		return ResponseEntity.ok("Inserted Value Successfully");
	}
	
	@PostMapping("/retriveFromRedis")
	public ResponseEntity<?> retriveFromRedis(@RequestBody CacheDTO apirequest){
		CacheDTO value=(CacheDTO) redisTemplate.opsForValue().get(apirequest.getKey());
		if(value==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(value);
	}

}
