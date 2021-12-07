package com.DASH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DASH.model.Slots;

@Repository
public interface SlotRepository extends JpaRepository<Slots, Long>{
public List<Slots> findSlotsByStatusIsNot(@Param("val")Integer val);
public List<Slots> findSlotsByUserContains(@Param("user")String user);
public List<Slots> findSlotsByWorkerContains(@Param("user")String user);
}
