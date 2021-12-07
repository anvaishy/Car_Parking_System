package com.DASH.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.DASH.model.*;
import com.DASH.repository.*;
@Service
public class SlotServiceImpl implements SlotService {
	@Autowired
	private SlotRepository slotsRepository;
	@Override
	public List<Slots> getallslots() {
		return slotsRepository.findAll();
	}
	@Override
	public void saveslots(Slots slots) {
		this.slotsRepository.save(slots);
	}
	@Override
	public Slots getslotsbyid(long id) {
		Optional<Slots> optional = slotsRepository.findById(id);
		Slots slots = null;
		if (optional.isPresent()) {
			slots = optional.get();
		} else {
			throw new RuntimeException(" Slots not found for id :: " + id);
		}
		return slots;
	}
	@Override
	public void deleteslotsbyid(long id) {
		this.slotsRepository.deleteById(id);
	}
	@Override
	public Page<Slots> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.slotsRepository.findAll(pageable);
	}
}
