package com.java.butterfly.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * TODO(操作java bean工具类).
 *
 * @author 许路
 * @version v 1.0
 * @ClassName: BeanUtils
 * @date: 2016年4月22日 上午9:26:55
 */
public class BeanUtils {
    
    /**
     * TODO(对象值拷贝)    .
     *
     * @author 许路
     * @param source 源对象
     * @param target 目标对象
     * @throws Exception the exception
     * @Title: copyProperties
     */
    public static void copyProperties(Object source, Object target) throws Exception {
        copyPropertiesExclude(source, target, null);
    }
    
    /**
     * 复制对象属性.
     *
     * @author mali
     * @param from the from
     * @param to the to
     * @param excludsArray            排除属性列表
     * @throws Exception the exception
     * @title: copyPropertiesExclude
     * @date: 2016-5-20 15:11:53
     */
    public static void copyPropertiesExclude(Object from, Object to, String[] excludsArray) throws Exception {
        List<String> excludesList = null;
        if (excludsArray != null && excludsArray.length > 0) {
            excludesList = Arrays.asList(excludsArray); // 构造列表对象
        }
        Method[] fromMethods = from.getClass().getDeclaredMethods();
        Method[] toMethods = to.getClass().getDeclaredMethods();
        Method fromMethod = null, toMethod = null;
        String fromMethodName = null, toMethodName = null;
        for (int i = 0; i < fromMethods.length; i++) {
            fromMethod = fromMethods[i];
            fromMethodName = fromMethod.getName();
            if (!fromMethodName.contains("get")) {
                continue;
            }
            // 排除列表检测
            if (excludesList != null && excludesList.contains(fromMethodName.substring(3).toLowerCase())) {
                continue;
            }
            toMethodName = "set" + fromMethodName.substring(3);
            //检验被赋值的对象属性是否存在
            toMethod = findMethodByName(toMethods, toMethodName);
            if (toMethod == null) {
                continue;
            }
            //不拷贝空数据
            Object value = fromMethod.invoke(from, new Object[0]);
            if (value == null) {
                continue;
            }
            /*if (value instanceof String) {
                if (EmptyUtils.isEmpty(value.toString())) {
                    continue;
                }
            }
            System.out.println(value);*/
            //如果类型不匹配，不相互拷贝
            //			if(!instanceOfProperties( from, fromMethod, to, toP)){
            //				continue;
            //			}
            // 集合类判空处理
            if (value instanceof Collection) {
                Collection<?> newValue = (Collection<?>) value;
                if (newValue.size() <= 0) {
                    continue;
                }
            }
            toMethod.invoke(to, new Object[] {value});
        }
    }
    
    /**
     * 对象属性值复制，仅复制指定名称的属性值.
     *
     * @author mali
     * @param from the from
     * @param to the to
     * @param includsArray the includs array
     * @throws Exception the exception
     * @title: copyPropertiesInclude
     * @date: 2016-5-20 15:11:53
     */
    public static void copyPropertiesInclude(Object from, Object to, String[] includsArray) throws Exception {
        List<String> includesList = null;
        if (includsArray != null && includsArray.length > 0) {
            includesList = Arrays.asList(includsArray);
        } else {
            return;
        }
        Method[] fromMethods = from.getClass().getDeclaredMethods();
        Method[] toMethods = to.getClass().getDeclaredMethods();
        Method fromMethod = null, toMethod = null;
        String fromMethodName = null, toMethodName = null;
        for (int i = 0; i < fromMethods.length; i++) {
            fromMethod = fromMethods[i];
            fromMethodName = fromMethod.getName();
            if (!fromMethodName.contains("get")) {
                continue;
            }
            // 排除列表检测
            String str = fromMethodName.substring(3);
            if (!includesList.contains(str.substring(0, 1).toLowerCase() + str.substring(1))) {
                continue;
            }
            toMethodName = "set" + fromMethodName.substring(3);
            toMethod = findMethodByName(toMethods, toMethodName);
            if (toMethod == null) {
                continue;
            }
            Object value = fromMethod.invoke(from, new Object[0]);
            if (value == null) {
                continue;
            }
            // 集合类判空处理
            if (value instanceof Collection) {
                Collection<?> newValue = (Collection<?>) value;
                if (newValue.size() <= 0) {
                    continue;
                }
            }
            toMethod.invoke(to, new Object[] {value});
        }
    }
    
    /**
     * 从方法数组中获取指定名称的方法.
     *
     * @author mali
     * @param methods the methods
     * @param name the name
     * @return Method
     * @title: findMethodByName
     * @date: 2016-5-20 15:11:53
     */
    public static Method findMethodByName(Method[] methods, String name) {
        for (int j = 0; j < methods.length; j++) {
            if (methods[j].getName().equals(name)) {
                return methods[j];
            }
        }
        return null;
    }
    
    /**
     * TODO(判断属性类型是否一致)    .
     *
     * @author 许路
     * @param from the from
     * @param fromP the from p
     * @param to the to
     * @param toP the to p
     * @return boolean
     * boolean 返回值
     * @Title: instanceOfProperties
     */
    public static boolean instanceOfProperties(Object from, String fromP, Object to, String toP) {
        try {
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 判断指定的Class对象是否存在对应的属性字段.
     *
     * @author mali
     * @param clazz 目标class
     * @param propertyName 属性名
     * @return boolean
     * @title: existProperty
     * @date: 2016-5-20 15:11:53
     */
    public static boolean existProperty(Class clazz, String propertyName) {
        Field[] fields = clazz.getDeclaredFields();
        if (fields == null || fields.length <= 0) {
            return false;
        }
        for (Field field : fields) {
            if (field.getName().equals(propertyName)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }
    
    /**
     * TODO(日期转换)    
     * @author 许路    
     * @date: 2016年5月25日 下午1:43:07
     * @Title: parseStringToDate    
     * @param dateStr
     * @return
     * @throws ParseException Date 返回值
     */
    public static Date parseStringToDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(dateStr);
    }
}
