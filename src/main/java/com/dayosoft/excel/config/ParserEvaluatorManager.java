package com.dayosoft.excel.config;

import com.dayosoft.excel.renderer.parser.Parser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public abstract class ParserEvaluatorManager {

    private List<Parser> registeredParsers = new ArrayList<>();

    protected void register(Class<? extends Parser> parser){
        try {
            registeredParsers.add(parser.getDeclaredConstructor().newInstance());
        } catch (InvocationTargetException e) {
            log.error(e.getMessage(), e);
        } catch (InstantiationException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }
    }

    protected abstract void registerParsers();

    @Bean
    public List<Parser> getRegisteredParsers() {
        return registeredParsers;
    }
}
