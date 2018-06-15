package com.data.dao;

import com.data.util.Page;
import com.data.util.PageData;

import java.util.List;
import java.util.Map;

/**
 * Created by AndrewH on 2017/10/12.
 */
public interface BaseDAO {
    /**
     * 保存数据对象
     * @param entity
     * @return
     */
    boolean save(Object entity);
    /**
     * 根据id查询
     * @param t
     * @param id
     * @return
     */
    <T> T findByid(Class<T> t, Object id);

    /**
     * 根据hql查询单个实体list
     * @param hql
     * @param clazz
     * @param param
     * @param <T>
     * @return
     */
    <T>List<T> findByHql(String hql, Class<T> clazz, List<?> param);

    /**
     * 根据hql查询单个实体list
     * @param hql
     * @param clazz
     * @param params
     * @param <T>
     * @return
     */
    <T>List<T> findByHql(String hql, Class<T> clazz, Object... params);

    /**
     * 根据hql查询多表关联
     * @param hql
     * @param params
     * @return
     */
    List<Object[]> findByHql(String hql, Object... params);

    /**
     * 根据hql查询多表关联
     * @param hql
     * @param param
     * @return
     */
    List<Object[]> findByHql(String hql, List<?> param);


//    <T>T findByCondition(T o);


    /**
     * 多字段查询分页
     * @param hql
     * @param clazz
     * @param start 第几页
     * @param pageNumber 一个页面的条数
     * @param params
     * @return
     */
    <T>List<T> findPageByHql(String hql, Class<T> clazz, int start, int pageNumber, List<?> params);
    /**
     * 多字段查询分页
     * @param hql
     * @param clazz
     * @param start 第几页
     * @param pageNumber 一个页面的条数
     * @param param
     * @return
     * */
    <T>List<T> findPageByHql(String hql, Class<T> clazz, int start, int pageNumber, Object... param);
    List<?> findPageByHql(String hql, int start, int pageNumber, List<?> params);
//    /**
//     * 一个字段的分页
//     * @param  tablename 表名
//     * @param filed 字段名
//     * @param o 字段参数
//     * @param start 第几页
//     * @param pageNumer 一个页面多少条数据
//     * @return
//     */
//    List<T> findpages(String tablename,String filed,Object o,int start,int pageNumer);
    /**
     * 根据表的id删除数据
     * @param  entity
     */
    boolean delete(Object entity);
    /**
     * 更新对象
     * @param e
     * @return
     */
    boolean update(Object e);

    /**
     * @param hql
     * @return
     */
    Number count(String hql, Object... param);
    Number count(String hql, List<?> params);
    List<Map<String,Object>>selectMapfromSql(String sql, List params);
    List<Map<String,Object>>selectMapfromSql(String sql, Object... param);
    List selectListfromSql(String sql, List params);
    List selectListfromSql(String sql, Object... param);
    List selectPageListfromSql(String sql, int start, int page, List params);



    /**
     * 返回 实体
     *
     * @param clazz
     * @param id
     * @return
     */
   <T> T loadByid(Class<T> clazz, Integer id);





    void saveOrUpdate(Object object);

    /**
     * 查询语句
     * @return
     */
    public abstract List getListByHsql(String hql, Object... values);

    /**
     * 删除语句
     * @param hql
     */
    public abstract void deleteHsql(String hql);

    /**
     * <根据HQL得到记录数>
     *
     * @param hql
     *            HQL语句
     * @param values
     *            查询条件 设值
     * @return
     */
    public abstract Long getListCountByHql(String hql, Object... values);




    public List getpageByHql(String hql, Integer page, Integer offset, Object[] objects);

    public abstract Long getCountByHql(String hql, Object[] objects);

    /**
     * 查询语句(SQL语句)
     * @return
     */
    public abstract List getListBySql(String sql);



    /**
     * 执行 hql 语句
     *
     * @param hql
     */
    public void executeHql(String hql);

    void executeHql(String hql, Object... params);

    /**
     * 执行 hql 语句
     *
     * @param sql
     */
    public void executeSql(String sql, Object... params);


    /**
     * sql语句 、实体类   查询实体列表
     * @param sql
     * @param clazz
     * @return
     */
    public abstract List getEntityBySql(String sql, Class clazz);

    public Object getEntityByHql(String hql, Object... values);

    /**
     * 传入的hql必须要带select from ,不能带order by
     * @param hql
     * @param clazz
     * @param page
     * @param params
     * @param <T>
     * @return
     */
    <T> PageData<T> findPageDataByHql(String hql, Class<T> clazz, Page page, List<?> params);

    /**
     * 传入的hql必须要带select from ,不能带order by
     * @param hql
     * @param clazz
     * @param page
     * @param param
     * @param <T>
     * @return
     */
    <T> PageData<T> findPageDataByHql(String hql, Class<T> clazz, Page page, Object... param);
}