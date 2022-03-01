package com.dayosoft.excel.config;

import com.dayosoft.excel.renderer.parser.ExpressionParser;
import com.dayosoft.excel.renderer.parser.FirstFunctionParser;
import com.dayosoft.excel.renderer.parser.ObjectExpressionParser;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserConfig extends ParserEvaluatorManager {

    @Override
    protected void registerParsers() {
        register(ObjectExpressionParser.class);
        register(FirstFunctionParser.class);
        register(ExpressionParser.class);
    }
}
