package com.softtek.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softtek.entity.Tourist;
import com.softtek.errors.TouristNotFoundException;
import com.softtek.repository.ITouristRepo;
import com.softtek.service.ITouristService;

@Service
public class TouristServiceImpl implements ITouristService {
	@Autowired
	private ITouristRepo touristrepo;

	@Override
	public String registertourist(Tourist tourist) {
		Integer tid = touristrepo.save(tourist).getTid();
		return "Tourist is registered having the value :" + tid;
	}

	@Override
	public List<Tourist> fetchAlltourist() {
		List<Tourist> list = touristrepo.findAll();
		list.sort((t1, t2) -> t1.getTid().compareTo(t2.getTid()));
		return list;
	}

	@Override
	public Tourist fetchById(Integer tid) throws TouristNotFoundException {

		return touristrepo.findById(tid).orElseThrow(() -> new TouristNotFoundException(tid + "tourist not found"));
	}

	@Override
	public String updateTouristDetails(Tourist tourist) throws TouristNotFoundException {

		Optional<Tourist> findById = touristrepo.findById(tourist.getTid());
		if (findById.isPresent()) {
			touristrepo.save(tourist);
			return tourist.getTid() + "Tourist is updated";
		} else {
			throw new TouristNotFoundException(tourist.getTid() + "Tourist not found");
		}

	}

	@Override
	public String deleteTourist(Integer id) throws TouristNotFoundException {
		if(touristrepo.findById(id).isPresent())
    {
	touristrepo.deleteById(id);
	//return ResponseEntity<String>()
	return "Id with " + id+ "deleted";
    }
    else {
  throw  new TouristNotFoundException(id+"Tourist not found");
	}

}

	@Override
	public String updateTouristBudgetBYId(Integer id, Float hikePercent) throws TouristNotFoundException {
		Optional<Tourist> opt = touristrepo.findById(id);
		if(opt.isPresent())
		{
			Tourist tourist = opt.get();
			tourist.setBudget(tourist.getBudget()+tourist.getBudget()*hikePercent/100);
			touristrepo.save(tourist);
			return "tourist budget is updated";
		}
		else {
			throw  new TouristNotFoundException(id+"Tourist not found");
		}
		
	}
}
