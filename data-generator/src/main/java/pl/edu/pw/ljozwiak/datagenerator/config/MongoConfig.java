package pl.edu.pw.ljozwiak.datagenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoConfig {

  @Bean
  public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) throws Exception {
    MongoTypeMapper typeMapper = new DefaultMongoTypeMapper(null);
    MappingMongoConverter converter =
        new MappingMongoConverter(mongoDatabaseFactory, new MongoMappingContext());
    converter.setTypeMapper(typeMapper);

    MongoTemplate mongoTemplate = new MongoTemplate(mongoDatabaseFactory, converter);
    return mongoTemplate;
  }
}
