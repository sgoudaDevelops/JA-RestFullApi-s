package com.softtek.service;

import java.util.List;

import com.softtek.entity.Tourist;
import com.softtek.errors.TouristNotFoundException;

public interface ITouristService {
	public String registertourist(Tourist tourist);
	public List<Tourist> fetchAlltourist();
	public  Tourist fetchById(Integer tid) throws TouristNotFoundException;
	public String updateTouristDetails(Tourist tourist) throws TouristNotFoundException;
	public String deleteTourist(Integer id) throws TouristNotFoundException;
	public String updateTouristBudgetBYId(Integer id,Float hikePercent) throws TouristNotFoundException;
}
