package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.search.pojo.SearchResult;

public interface SearchDao {
	public SearchResult search(SolrQuery query) throws Exception;
}
