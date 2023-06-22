package com.softtek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softtek.entity.Tourist;
import com.softtek.errors.TouristNotFoundException;
import com.softtek.service.ITouristService;

@RestController
public abstract class TouristOperationController {
	
	@Autowired
	private ITouristService touristService;
	
	@PostMapping("/register")
	public ResponseEntity<String> enrollTourist(@RequestBody Tourist tourist)
	{
		try {
			String registeredMessage = touristService.registertourist(tourist);
			return new ResponseEntity<String>(registeredMessage,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Problem in tourist enrollment",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/findAll")
	public ResponseEntity<?> fetchallSorted(){
		try
		{
			List<Tourist> fetchAlltourist = touristService.fetchAlltourist();
			return new ResponseEntity<List<Tourist>>(fetchAlltourist,HttpStatus.OK);
		}
		 catch (Exception e) {
			 return new ResponseEntity<String>("tourist list is empty",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/find/{id}")
	public ResponseEntity<?> DisplayTouristById(@PathVariable("id") Integer id)
	{
		try {
			Tourist tourist = touristService.fetchById(id);
			return new ResponseEntity<Tourist>(tourist,HttpStatus.OK);
			} 
//		catch (TouristNotFoundException t) {
//			   return  new ResponseEntity<String>(t.getMsg(),HttpStatus.INTERNAL_SERVER_ERROR);
//		}	
		catch (Exception e) {
			return  new ResponseEntity<String>(e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update")
	public ResponseEntity<String> modiftyTourist(@RequestBody Tourist tourist)
	{
		ResponseEntity<String> responseEntity=null;
		try {
			String msg =touristService.updateTouristDetails(tourist);
			responseEntity =  new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch (Exception e) {
		 responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> removeTourist(@PathVariable("id") Integer id)
	{
		try {
			String msg = touristService.deleteTourist(id);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		}
	}
	
	@PatchMapping("/budgetModify/{id}/{hike}")
	public ResponseEntity<String> modifyTouristbudgetById(@PathVariable("id")Integer id,@PathVariable("hike") float hikePercent)
	{
		try
		{
			String msg = touristService.updateTouristBudgetBYId(id, hikePercent);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
