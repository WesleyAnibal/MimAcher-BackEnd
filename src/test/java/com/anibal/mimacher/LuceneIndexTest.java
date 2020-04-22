package com.anibal.mimacher;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.IndexSearcher;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.anibal.mimacher.item.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LuceneIndexTest {
	
	  private static final Logger log = LoggerFactory.getLogger(LuceneIndexTest.class);

	    @Autowired
	    private EntityManager entityManager;

	    @Test
	    public void testQueryIndex() {

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
	                .onField("descricao").matching("Casaco Nike branco preto")
	                .createQuery();
	        

	        org.hibernate.search.jpa.FullTextQuery fullTextQuery
	                = fullTextEntityManager.createFullTextQuery(query, Item.class);

	        fullTextQuery.setProjection( FullTextQuery.SCORE, FullTextQuery.THIS, "item.descricao");
	        //returns JPA managed entities
	        
	        List persons = fullTextQuery.getResultList();
	        Object[] firstResult = (Object[]) persons.get(0);
	        Object[] firstResult1 = (Object[]) persons.get(1);
	        log.info("Found itens: "+ firstResult[0] +" descricao: "+ firstResult[1]);
	        log.info("Found itens: "+ firstResult1[0]+" descricao: "+ firstResult1[1]);
	        

	        
	        
	    }

}
