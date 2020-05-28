package ru.cs.entity.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ru.cs.entity.Acc;

@Repository
public interface AccRepository extends PagingAndSortingRepository<Acc, Long>{

	Page<Acc> findAllByFirmIdOrderById(Long firmId, Pageable pageable);
}
