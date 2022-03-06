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

    private List<Parser> registeredParsers = new ArrayList<>();

    private final FirstFunctionParser firstFunctionParser;
    private final ColArrParser colArrParser;
    private final AnotherRowParser anotherRowParser;
    private final RefParser refParser;
    private final DivideParser divideParser;

    @PostConstruct
    protected void registerParsers() {
        registeredParsers.add(firstFunctionParser);
        registeredParsers.add(colArrParser);
        registeredParsers.add(anotherRowParser);
        registeredParsers.add(refParser);
        registeredParsers.add(divideParser);
    }

    @Bean
    public List<Parser> getRegisteredParsers() {
        return registeredParsers;
    }
}
