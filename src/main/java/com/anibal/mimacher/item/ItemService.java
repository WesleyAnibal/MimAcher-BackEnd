package com.anibal.mimacher.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public List<Item> index() {
		return this.itemRepository.findAll();
	}

	public void create(Item item) {
		try {
			this.itemRepository.save(item);
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException();
		}
	}

	public Item update(Long id, Item item) {
		if (!this.itemRepository.existsById(id)) {
			throw new RuntimeException();
		}
		item.setId(id);
		return this.itemRepository.save(item);
	}

	public void delete(Long id) {
		if (!this.itemRepository.existsById(id)) {
			throw new RuntimeException();
		}
		this.itemRepository.deleteById(id);
	}

	@ReadOnlyProperty
	public Item show(Long id) {
		if (!this.itemRepository.existsById(id)) {
			throw new RuntimeException();
		}
		try {
			Item item = this.itemRepository.findById(id).orElseThrow(RuntimeException::new);
			return item;
		} catch (IllegalArgumentException iae) {
			throw new RuntimeException("");
		}
	}
	
	public List<Item> indexFilterByName(String descricao) {
        return this.itemRepository.findAllByDescricaoContainingIgnoreCase(descricao);
    }
}
