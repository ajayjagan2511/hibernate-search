package org.acme;

import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurationContext;
import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurer;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("myAnalysisConfigurer")
public class AnalysisConfigurer implements ElasticsearchAnalysisConfigurer {

    @Override
    public void configure(ElasticsearchAnalysisConfigurationContext context) {
        context.analyzer("name").custom()
                .tokenizer("standard")
                .tokenFilters("asciifolding", "lowercase", "edge_ngram_filter");
        context.tokenFilter("edge_ngram_filter")
                .type("edgeNGram")
                .param("side", "front")
                .param("max_gram", 6)
                .param("min_gram", 3);


        context.analyzer("english").custom()
                .tokenizer("standard")
                .tokenFilters("asciifolding", "lowercase", "porter_stem");

        context.normalizer("sort").custom()
                .tokenFilters("asciifolding", "lowercase");
    }
}