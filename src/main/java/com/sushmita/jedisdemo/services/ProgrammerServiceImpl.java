package com.sushmita.jedisdemo.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushmita.jedisdemo.dao.ProgrammerRepository;
import com.sushmita.jedisdemo.model.Programmer;

@Service
public class ProgrammerServiceImpl implements ProgrammerService {

	@Autowired
	private ProgrammerRepository programmerRepository;
	@Override
	public void setProgrammerAsString(String idKey, String programmer) {
		programmerRepository.setProgrammerAsString(idKey, programmer);
	}

	@Override
	public String getProgrammerAsString(String idKey) {
		return programmerRepository.getProgrammerAsString(idKey);
	}

	@Override
	public void addProgrammerInList(Programmer programmer) {
		programmerRepository.addProgrammerInList(programmer);
	}

	@Override
	public List<Programmer> getProgrammerList() {
		return programmerRepository.getProgrammerList();
	}

	@Override
	public long getProgrammerCount() {
		return programmerRepository.getProgrammerCount();
	}

	@Override
	public void addProgrammerToSet(Programmer... programmers) {
		programmerRepository.addProgrammerToSet(programmers);
	}

	@Override
	public Set<Programmer> getProgrammerSet() {
		return programmerRepository.getProgrammerSet();
	}

	@Override
	public boolean isMemberOfSet(Programmer programmer) {
		return programmerRepository.isMemberOfSet(programmer);
	}

	@Override
	public void saveInHash(Programmer programmer) {
		programmerRepository.saveInHash(programmer);
	}

	@Override
	public void updateInHash(Programmer programmer) {
		programmerRepository.updateInHash(programmer);
	}

	@Override
	public Map<String, Programmer> getHash() {
		return programmerRepository.getHash();
	}

	@Override
	public Programmer findInHash(String id) {
		return programmerRepository.findInHash(id);
	}

	@Override
	public void deleteFromHash(String id) {
		programmerRepository.deleteFromHash(id);
	}

}
