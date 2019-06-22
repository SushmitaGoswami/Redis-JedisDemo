package com.sushmita.jedisdemo.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import com.sushmita.jedisdemo.model.Programmer;

@Repository
public class ProgrammerRepositoryImpl implements ProgrammerRepository{

	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	@Autowired
	@Qualifier("listOperations")
	private ListOperations<String, Programmer> listOp;
	
	@Autowired
	@Qualifier("setOperations")
	private SetOperations<String, Programmer> setOp;
	
	@Autowired
	@Qualifier("hashOperations")
	private HashOperations<String, String, Programmer>  hashOp;
	
	private final String PROGRAMMERLIST_REDIS_KEY="PROGRAMMER_LIST";
	private final String PROGRAMMERSET_REDIS_KEY="PROGRAMMER_SET";
	private final String PROGRAMMERHASH_REDIS_KEY="PROGRAMMER_HASH";
	
	@Override
	public void setProgrammerAsString(String idKey, String programmer) {
		redisTemplate.opsForValue().set(idKey, programmer);
		redisTemplate.expire(idKey, 100, TimeUnit.SECONDS);
		
	}

	@Override
	public String getProgrammerAsString(String idKey) {
		return (String) redisTemplate.opsForValue().get(idKey);
	}

	@Override
	public void addProgrammerInList(Programmer programmer) {
		listOp.leftPush(PROGRAMMERLIST_REDIS_KEY, programmer);
		
	}

	@Override
	public List<Programmer> getProgrammerList() {
		return listOp.range(PROGRAMMERLIST_REDIS_KEY, 0, -1);
	}

	@Override
	public long getProgrammerCount() {
		return listOp.size(PROGRAMMERLIST_REDIS_KEY);
	}

	@Override
	public void addProgrammerToSet(Programmer... programmers) {
		setOp.add(PROGRAMMERSET_REDIS_KEY, programmers);
	}

	@Override
	public Set<Programmer> getProgrammerSet() {
		return setOp.members(PROGRAMMERSET_REDIS_KEY);
	}

	@Override
	public boolean isMemberOfSet(Programmer programmer) {
		return setOp.isMember(PROGRAMMERSET_REDIS_KEY, programmer);
	}

	@Override
	public void saveInHash(Programmer programmer) {
		hashOp.put(PROGRAMMERHASH_REDIS_KEY, programmer.getId(), programmer);
		
	}

	@Override
	public void updateInHash(Programmer programmer) {
		hashOp.put(PROGRAMMERHASH_REDIS_KEY, programmer.getId(), programmer);
		
	}

	@Override
	public Map<String, Programmer> getHash() {
		return hashOp.entries(PROGRAMMERHASH_REDIS_KEY);
	}

	@Override
	public Programmer findInHash(String id) {
		return hashOp.get(PROGRAMMERHASH_REDIS_KEY, id);
	}

	@Override
	public void deleteFromHash(String id) {
		hashOp.delete(PROGRAMMERHASH_REDIS_KEY, id);
	}

}
