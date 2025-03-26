package com.evnit.ttpm.stdv.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        // Tạo object và cấu hình
        ModelMapper modelMapper = new ModelMapper();

        // STANDARD: không quan tâm thứ tự, mọi từ trong tên thuộc tính nguồn phải tồn tại trong tên thuộc tính đích
        // LOOSE: không quan tâm thứ tự, từ cuối cùng phải có trong tên thuộc tính đích
        // STRICT: thứ tự phải đúng, mọi từ trong thuộc tính nguồn phải khớp với toàn bộ từ
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper;
    }
}
