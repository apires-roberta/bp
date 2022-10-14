package com.bp.feedbcd;

import com.bp.feedbcd.data.dto.feed.ReadFeedDto;
import com.bp.feedbcd.entidade.Feed;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        TypeMap<Feed, ReadFeedDto> propertyMapper = mapper.createTypeMap(Feed.class, ReadFeedDto.class);

        propertyMapper.addMappings(
                mapping -> mapping.map(Feed::getOng, ReadFeedDto::setOng)
        );

        return mapper;
    }
}