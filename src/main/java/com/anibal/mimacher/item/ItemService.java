package com.anibal.mimacher.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;


@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	 @Autowired
	 private EntityManager entityManager;
	 
	 private static final Logger log = LoggerFactory.getLogger(ItemService.class);

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
		 //Get the FullTextEntityManager
        FullTextEntityManager fullTextEntityManager
                = Search.getFullTextEntityManager(entityManager);

        //Create a Hibernate Search DSL query builder for the required entity
        org.hibernate.search.query.dsl.QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Item.class)
                .get();

        //Generate a Lucene query using the builder
        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .onField("descricao").matching(descricao)
                .createQuery();
        

        org.hibernate.search.jpa.FullTextQuery fullTextQuery
                = fullTextEntityManager.createFullTextQuery(query, Item.class);

        fullTextQuery.setProjection( FullTextQuery.SCORE, FullTextQuery.THIS, FullTextQuery.DOCUMENT_ID);
        //returns JPA managed entities
        
        log.info("Found 123123: "+ fullTextQuery.getResultStream());
        List persons = fullTextQuery.getResultList();
        Object[] firstResult = (Object[]) persons.get(0);
        Object[] firstResult1 = (Object[]) persons.get(1);
        log.info("Found 123123: ");

        log.info("Found itens: "+ firstResult[0] +" descricao: "+ firstResult[1] +" dteste: "+ fullTextQuery.explain((int) firstResult[2]) );
        log.info("Found itens: "+ firstResult1[0]+" descricao: "+ firstResult1[1]);
        List<Item> saida = new ArrayList<Item>();
        for (int i = 0; i < persons.size(); i++) {
            Item item = (Item) ((Object[]) persons.get(i))[1];
            item.setScore((Float) ((Object[]) persons.get(i))[0]);
            saida.add(item);
		}
       
        
        return saida  ;
    }
}
