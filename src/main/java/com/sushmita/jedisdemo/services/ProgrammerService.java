package com.sushmita.jedisdemo.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sushmita.jedisdemo.model.Programmer;

public interface ProgrammerService {
	public void setProgrammerAsString(String idKey, String programmer);
	public String getProgrammerAsString(String idKey);
	
	public void addProgrammerInList(Programmer programmer);
	public List<Programmer> getProgrammerList();
	public long getProgrammerCount();
	
	public void addProgrammerToSet(Programmer... programmers);
	public Set<Programmer> getProgrammerSet();
	public boolean isMemberOfSet(Programmer programmer);
	
	public void saveInHash(Programmer programmer);
	public void updateInHash(Programmer programmer);
	public Map<String, Programmer> getHash();
	public Programmer findInHash(String id);
	public void deleteFromHash(String id);
}
