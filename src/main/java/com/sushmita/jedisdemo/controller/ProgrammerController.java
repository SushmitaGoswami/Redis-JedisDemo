package com.sushmita.jedisdemo.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sushmita.jedisdemo.model.Programmer;
import com.sushmita.jedisdemo.services.ProgrammerService;

@RestController
public class ProgrammerController {

	@Autowired
	private ProgrammerService programmerService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/programmer")
	public void addProgrammer(@RequestBody Programmer programmer) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		programmerService.setProgrammerAsString(programmer.getId(), mapper.writeValueAsString(programmer));
	}
	
	@RequestMapping(value = "/programmer/{id}")
	public String getProgrammer(@PathVariable String id) {
		return programmerService.getProgrammerAsString(id);
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/programmer-list")
	public void addProgrammerToList(@RequestBody Programmer programmer) throws JsonProcessingException {
		programmerService.addProgrammerInList(programmer);
	}
	
	@RequestMapping(value = "/programmer-list")
	public List<Programmer> getProgrammerList() {
		return programmerService.getProgrammerList();
	}
	
	@RequestMapping(value = "/programmer-list-count")
	public long getProgrammerListCount() {
		return programmerService.getProgrammerCount();
	}
	
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/programmer-set")
	public void addProgrammerToSet(@RequestBody Programmer... programmers) throws JsonProcessingException {
		programmerService.addProgrammerToSet(programmers);
	}
	
	@RequestMapping(value = "/programmer-set")
	public Set<Programmer> getProgrammerSet() {
		return programmerService.getProgrammerSet();
	}
	
	@RequestMapping(value = "/programmer-member/{member}")
	public boolean getProgrammerSet(@PathVariable Programmer programmer) {
		return programmerService.isMemberOfSet(programmer);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/programmer-hash")
	public void saveInHash(@RequestBody Programmer programmer) {
		programmerService.saveInHash(programmer);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/programmer-hash")
	public void updateInHash(@RequestBody Programmer programmer) {
		programmerService.updateInHash(programmer);
	}
	
	
	@RequestMapping(value = "/programmer-hash")
	public Map<String, Programmer> getHash(){
		return programmerService.getHash();
	}
	
	@RequestMapping(value = "/programmer-hash/{id}")
	public Programmer findInHash(@PathVariable String id){
		return programmerService.findInHash(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/programmer-hash/{id}")
	public void deleteFromHash(@PathVariable String id) {
		programmerService.deleteFromHash(id);
	}
	
	
	
	
	
	
	
}
