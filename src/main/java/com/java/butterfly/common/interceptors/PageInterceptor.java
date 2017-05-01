package com.java.butterfly.common.interceptors;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.log4j.Logger;

import com.java.butterfly.common.dto.PageInfo;
import com.java.butterfly.common.util.EmptyUtils;

/**
 * TODO(mybatis分页拦截器)    
 * @ClassName: PageInterceptor    
 * @author xl    
 * @date 2017年1月13日 上午11:23:00    
 * @version  v 1.0
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {
    private static Logger logger = Logger.getLogger(PageInterceptor.class);
    
    /**
     * sqlIdByPageRegex：正则表达式用了筛选所有分页的sql语句
     * dbType：数据库类型
     * mybatis-config.xml中定义
     */
    public String sqlIdByPageRegex = "";
    
    public String dbType;
    
    /**
     * TODO(分页拦截器)    
     * @author xl    
     * @Title: intercept    
     * @param invocation
     * @throws Exception 
     * @Return: Object 返回值
     */
    public Object intercept(Invocation invocation) throws Throwable {
        // 获得拦截的对象
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject
            .forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 选择性过滤分页sql
        String sqlId = mappedStatement.getId();
        if (sqlId.matches(sqlIdByPageRegex)) {
            BoundSql boundSql = statementHandler.getBoundSql();
            //获得执行sql、参数
            String sql = boundSql.getSql();
            //组装分页sql
            String pageSql =
                this.getPageSql(new StringBuffer(sql), this.getPageInfoByParms(boundSql.getParameterObject()));
            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }
        // 让被拦截的方法继续执行
        return invocation.proceed();
    }
    
    public Object plugin(Object target) {
        //给当前对象设置拦截器
        return Plugin.wrap(target, this);
    }
    
    public void setProperties(Properties properties) {
        // 可读取到配置文件中定义的属性以及属性值
        this.sqlIdByPageRegex = (String) properties.get("sqlIdByPageRegex");
        this.dbType = properties.getProperty("dbType").toUpperCase();
    }
    
    /**
     * TODO(根据不同的参数列表组装pageInfo对象)    
     * @author 许路    
     * @date: 2017年1月6日 上午11:43:04
     * @Title: getPageInfoByParms    
     * @param map
     * @return PageInfo 返回值
     */
    public PageInfo getPageInfoByParms(Object parametersObj) {
        if (PageInfo.class.isAssignableFrom(parametersObj.getClass())) {
            //如果参数就是pageInfo子类的封装
            return newPageInfo(((PageInfo) parametersObj).getPageNo(), ((PageInfo) parametersObj).getPageSize());
        } else {
            //如果参数是map
            Map<?, ?> parameterMap = (Map<?, ?>) parametersObj;
            int pageNo = Integer.parseInt(String.valueOf(parameterMap.get("pageNo")));
            int pageSize = Integer.parseInt(String.valueOf(parameterMap.get("pageSize")));
            return newPageInfo(pageNo, pageSize);
        }
    }
    
    /**
     * TODO(new PageInfo)    
     * @date: 2017年1月11日 上午11:26:06
     * @Title: newPageInfo    
     * @param pageNo
     * @param pageSize
     * @return PageInfo 返回值
     */
    public PageInfo newPageInfo(Object pageNo, Object pageSize) {
        PageInfo pageinfo = new PageInfo();
        pageinfo.setPageNo(Integer.parseInt(String.valueOf(pageNo)));
        pageinfo.setPageSize(Integer.parseInt(String.valueOf(pageSize)));
        return pageinfo;
    }
    
    /**
     * TODO(根据数据库类型组装分页sql)    
     * @author 许路    
     * @date: 2017年1月6日 上午11:34:08
     * @Title: getPageSql    
     * @param sql
     * @return String 返回值
     */
    public String getPageSql(StringBuffer sql, PageInfo pageinfo) {
        if (null == pageinfo || EmptyUtils.isEmpty(pageinfo.getPageNo())
            || EmptyUtils.isEmpty(pageinfo.getPageSize())) {
            logger.error("********调用分页sql但是未传递分页参数，系统默认不执行sql*******");
            return null;
        }
        if ("ORACLE".equals(dbType)) {
            int startRecode = (pageinfo.getPageNo() - 1) * pageinfo.getPageSize() + 1;
            int endRecode = pageinfo.getPageNo() * pageinfo.getPageSize() + 1;
            sql.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(endRecode);
            return sql.insert(0, "select * from (").append(") where r >= ").append(startRecode).toString();
        } else if ("MYSQL".equals(dbType)) {
            int startRecode = (pageinfo.getPageNo() - 1) * pageinfo.getPageSize();
            int endRecode = pageinfo.getPageNo() * pageinfo.getPageSize();
            return sql.append(" limit ").append(startRecode).append(",").append(endRecode).toString();
        } else {
            logger.info("********找不到/不匹配 mybatis-config里面配置的dbType,当前分页只支持oracle and mysql********");
            return sql.toString();
        }
    }
}
