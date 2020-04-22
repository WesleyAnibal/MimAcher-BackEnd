package com.anibal.mimacher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class LuceneIndexConfig {
	
	@Bean
    public LuceneIndexServiceBean luceneIndexServiceBean(EntityManagerFactory EntityManagerFactory){
        LuceneIndexServiceBean luceneIndexServiceBean = new LuceneIndexServiceBean(EntityManagerFactory);
        luceneIndexServiceBean.triggerIndexing();
        return luceneIndexServiceBean;
    }

}
