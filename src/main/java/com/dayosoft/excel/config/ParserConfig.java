package com.dayosoft.excel.config;

import com.dayosoft.excel.expression.parser.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ParserConfig {

    private List<ParserEvaluator> registeredParsers = new ArrayList<>();

    private final ObjectExpressionParser objectExpressionParser;
    private final FirstFunctionParser firstFunctionParser;

    @PostConstruct
    protected void registerParsers() {
        registeredParsers.add(objectExpressionParser);
        registeredParsers.add(firstFunctionParser);
    }

    @Bean
    public List<ParserEvaluator> getRegisteredParsers() {
        return registeredParsers;
    }
}
