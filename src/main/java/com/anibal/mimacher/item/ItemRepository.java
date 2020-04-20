package com.anibal.mimacher.item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	Optional<Item> findById(Long id);
	
	
	  List<Item> findAllByDescricaoContainingIgnoreCase(String descricao);
	

}
