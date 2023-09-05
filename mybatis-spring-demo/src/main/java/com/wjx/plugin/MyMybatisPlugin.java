package com.wjx.plugin;

import com.wjx.annotation.TuoFeng;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @author Gin
 * @description
 * @date 2023/8/31 10:48
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MyMybatisPlugin implements Interceptor {
    private DefaultResultSetHandler target;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Class<?> type = ms.getResultMaps().get(0).getType();
        if (type.isAnnotationPresent(TuoFeng.class)){
            TuoFeng annotation = type.getAnnotation(TuoFeng.class);
            Configuration configuration = ms.getConfiguration();
            configuration.setMapUnderscoreToCamelCase(annotation.value());
        }
        Object proceed = invocation.proceed();
        return proceed;
    }

    //必须实现这个方法将本方法包装
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
