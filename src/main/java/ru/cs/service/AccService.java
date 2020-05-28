package ru.cs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ru.cs.entity.Acc;
import ru.cs.entity.dao.AccRepository;

@Service
public class AccService {

	@Autowired
	AccRepository accRepository;
	
	public boolean isAccess(Long id, Long myFirmId) {
		Optional<Acc> oAcc = accRepository.findById(id);
		
		//Не указан firm
		if (myFirmId==null) return false;
		//Нет в БД
		if (oAcc.isPresent()==false) return false;
		//Чужой firm
		if (oAcc.get().getFirmId()!=myFirmId) return false;
		
		return true;
	}
	
	public Page<Acc> findAllByFirmIdOrderById(Long firmId, Pageable pageable) {
		return accRepository.findAllByFirmIdOrderById(firmId, pageable);
	}
	
	public Optional<Acc> findById(Long id) {
		return accRepository.findById(id); 
	}
	
	public void deleteById(Long accId) {
		accRepository.deleteById(accId);
	}
	
	public void save(Acc acc) {
		accRepository.save(acc);
	}

}
