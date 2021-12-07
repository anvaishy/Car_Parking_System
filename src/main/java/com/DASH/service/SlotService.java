package com.DASH.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.DASH.model.Slots;

public interface SlotService {
	List<Slots> getallslots();
	void saveslots(Slots slots);
	Slots getslotsbyid(long id);
	void deleteslotsbyid(long id);
	Page<Slots> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}