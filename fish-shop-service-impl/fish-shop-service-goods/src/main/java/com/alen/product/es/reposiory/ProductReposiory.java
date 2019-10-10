package com.alen.product.es.reposiory;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.alen.product.es.entity.ProductEntity;

public interface ProductReposiory extends ElasticsearchRepository<ProductEntity, Long> {

}
