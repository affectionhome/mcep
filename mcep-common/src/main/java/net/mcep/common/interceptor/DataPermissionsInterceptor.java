package net.mcep.common.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import net.mcep.common.util.ReflectUtil;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

//import net.mcep.upms.rpc.api.UpmsResourceService;


/**
 * 方法名称 : net.mcep.common.interceptor.DataPermissionsInterceptor
 * 作   者 : admin
 * 创建时间 : 2018/1/26 11:51
 * 方法描述 : 数据权限拦截器
 * <p>
 * 修改作者 :
 * 修改时间 :
 * 修改描述 :
 */
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class,Integer.class }) })
public class DataPermissionsInterceptor implements Interceptor {


   // @Autowired
   // UpmsResourceService urs;

    /**
     * 拦截后要执行的方法
     */
    public Object intercept(Invocation invocation) throws Throwable {


        StatementHandler handler = (StatementHandler)invocation.getTarget();
        //由于mappedStatement为protected的，所以要通过反射获取
        //mappedStatement中有我们需要的方法id
        PreparedStatementHandler preparedStatementHandler = (PreparedStatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
        MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(preparedStatementHandler, "mappedStatement");
        //获取方法id
        String id = mappedStatement.getId();
        //获得方法类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (id.endsWith("ToDataPermissions")) {
            //获取sql
            BoundSql boundSql = handler.getBoundSql();
            String sql = boundSql.getSql();
            Object obj = boundSql.getParameterObject();
            MapperMethod.ParamMap args = (MapperMethod.ParamMap)obj;
            String userid = (String)args.get("userid");
            /******************根据userid去获取用户对应的资源*********************/
           // String resources = urs.getResourceByUserId(Integer.valueOf(userid), "station");
            //TODO

            /*******************************************************************/
            //增强sql代码块
            //if ("SELECT".equals(sqlCommandType)) {
                //如果是select就将sql转成SELECT对象
                //Select select = (Select)parserManager.parse(new StringReader(sql));
                //访问各个visitor
                //select.getSelectBody().accept(new SelectVisitorImpl());
            if(sql.lastIndexOf("LIMIT")>0){
                //sql = "select * from ("+sql.substring(0,sql.lastIndexOf("LIMIT"))+") tdp where station_id in("+resources+") " + sql.substring(sql.lastIndexOf("LIMIT"));
            }else{
               // sql = "select * from ("+sql+") tdp where station_id = in("+resources+") ";
            }

            //将增强后的sql放回
            ReflectUtil.setFieldValue(boundSql, "sql", sql);
            //}
        }
        return invocation.proceed();
    }


    /**
     * 拦截器对应的封装原始对象的方法
     */
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置注册拦截器时设定的属性
     */
    public void setProperties(Properties properties) {

    }


}
