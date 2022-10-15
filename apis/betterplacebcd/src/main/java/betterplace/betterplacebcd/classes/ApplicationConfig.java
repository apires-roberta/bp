package betterplace.betterplacebcd.classes;

import feign.okhttp.OkHttpClient;
import org.modelmapper.ModelMapper;
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
        return mapper;
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
