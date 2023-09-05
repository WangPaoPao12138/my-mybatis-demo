package com.wjx.plugin;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * 记录消费时间插件
 *
 * @author Gin
 * @description
 * @date 2023/9/5 21:57
 */
@Component
@Intercepts({
        @Signature(
                type = ParameterHandler.class,
                method = "setParameters",
                args = {PreparedStatement.class}),
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class}),
        @Signature(
                type = StatementHandler.class,
                method = "update",
                args = {Statement.class}),
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}),})

@Slf4j
public class MyCostTimePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        String parameterHandlerName = invocation.getTarget().getClass().getName();
        String statementName = invocation.getArgs()[0].getClass().getName();
        String methodName = invocation.getMethod().getName();
        Object returnVal = invocation.proceed();
        long end = System.currentTimeMillis();
        if ("prepare".equals(methodName)) {
            log.info("StatementHandler.prepare耗时: " + (end - start) + ", 其中start =" + start + ", end =" + end);
        }
        if ("setParameters".equals(methodName)) {
            log.info("参数装填时间" + (end - start) + ", 参数处理器类型为: " + parameterHandlerName + " ,statement类型为" + statementName + ", 其中start =" + start + ", end =" + end);
        }
        if ("update".equals(methodName)) {
            log.info("update耗时: " + (end - start) + ", 其中start =" + start + ", end =" + end);
        }
        return returnVal;
    }
}
