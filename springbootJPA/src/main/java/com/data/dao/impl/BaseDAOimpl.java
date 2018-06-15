package com.data.dao.impl;

import com.data.dao.BaseDAO;
import com.data.util.Page;
import com.data.util.PageData;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by AndrewH on 2017/10/12.
 */
@Repository
public class BaseDAOimpl implements BaseDAO {



    @PersistenceContext
    private EntityManager entityManager;

//    public static void main(String[] args) {
//        Object[] aa=null;
//        Arrays.asList(aa);
//    }
    public boolean save(Object entity){
        boolean flag=false;
        try {
            entityManager.persist(entity);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    public <T> T findByid(Class<T> o, Object id) {
        return entityManager.find(o,id);
    }


    public  <T>List<T> findByHql(String sql, Class<T> clazz, List<?>param) {
        Query query=entityManager.createQuery(sql);
        if(param!=null){
            for(int i=0;i<param.size();i++){
                query.setParameter(i+1,param.get(i));
            }
        }
        List<T> list= query.getResultList();
        return list;
    }
    public  List<?> findbyhql(String sql, List<?>param) {
        Query query=entityManager.createQuery(sql);
        if(param!=null){
            for(int i=0;i<param.size();i++){
                query.setParameter(i+1,param.get(i));
            }
        }
        List<?> list= query.getResultList();
        return list;
    }
    public  <T>List<T> findByHql(String sql, Class<T> clazz, Object...params) {
        List param= Arrays.asList(params);
       return findByHql(sql,clazz,param);
    }

    public List<Object[]> findByHql(String sql, Object... params) {
        return findByHql(sql,Object[].class,params);
    }


    public List<Object[]> findByHql(String sql, List<?> param) {
        return findByHql(sql,Object[].class,param);
    }

//    public <T> T findByCondition(T o) {
//       return null;
//    }

    //

    public List selectMapfromSql(String sql,List params){
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        for(int i=0;i<params.size();i++){
            query.setParameter(i+1,params.get(i));
        }
        return query.getResultList();
    }
    public List<Map<String,Object>>selectMapfromSql(String sql,Object...param){
        List params=Arrays.asList(param);
        return selectMapfromSql(sql,params);
    }

    @Override
    public List selectListfromSql(String sql, List params) {
        Query query = entityManager.createNativeQuery(sql);
        for(int i=0;i<params.size();i++){
            query.setParameter(i+1,params.get(i));
        }
        return query.getResultList();
    }

    @Override
    public List selectListfromSql(String sql, Object... param) {
        List params=Arrays.asList(param);
        return selectListfromSql(sql,params);

    }

    @Override
    public List selectPageListfromSql(String sql, int start, int pagesize, List params) {
        Query query = entityManager.createNativeQuery(sql);
        for(int i=0;i<params.size();i++){
            query.setParameter(i+1,params.get(i));
        }
        query.setFirstResult(start);
        query.setMaxResults(pagesize);
        return query.getResultList();
    }



    public <T>List<T> findPageByHql(String hql, Class<T>clazz, int start, int pageNumber, List<?>params) {
        Query query=entityManager.createQuery(hql);
        if(params!=null){
            for (int i=0;i<params.size();i++){
                query.setParameter(i+1,params.get(i));
            }
        }

        query.setFirstResult((start-1)*pageNumber);
        query.setMaxResults(pageNumber);
        List<T> listRe= query.getResultList();
        ;
        return listRe;
    }
    public  <T> PageData<T> findPageDataByHql(String hql, Class<T>clazz, Page page, List<?>params) {
        String hql1=page.gethql(hql);
        Query query=entityManager.createQuery(hql1);
        if(params!=null){
            for (int i=0;i<params.size();i++){
                query.setParameter(i+1,params.get(i));
            }
        }
        query.setFirstResult((page.getCurrent()-1)*page.getPageSize());
        query.setMaxResults(page.getPageSize());
        List<T> listRe= query.getResultList();
        page.setTotal(count(page.getcountHql(),params));
        ;
        return new PageData<T>(page,listRe);
    }

    public <T> PageData<T> findPageDataByHql(String hql, Class<T> clazz,  Page page, Object... param) {
        List params=Arrays.asList(param);
        return findPageDataByHql(hql,clazz,page,params);
    }

    @Override
    public List<?> findPageByHql(String hql, int start, int pageNumber, List<?> params) {
        Query query=entityManager.createQuery(hql);
        if(params!=null){
            for (int i=0;i<params.size();i++){
                query.setParameter(i+1,params.get(i));
            }
        }
        query.setFirstResult((start-1)*pageNumber);
        query.setMaxResults(pageNumber);
        List<?> listRe= query.getResultList();
        ;
        return listRe;
    }

    public <T>List<T> findPageByHql(String hql, Class<T>clazz, int start, int pageNumber, Object...param) {
        List params=Arrays.asList(param);
        return findPageByHql(hql,clazz,start,pageNumber,params);
    }
//    public List<T> findpages(String tablename, String filed, Object o, int start, int pageNumer) {
//        String sql="from "+tablename+" u WHERE u."+filed+"=?";
//        System.out.println(sql+"--------page--sql语句-------------");
//        List<T> list=new ArrayList<>();
//        try {
//            Query query=entityManager.createQuery(sql);
//            query.setParameter(1,o);
//            query.setFirstResult((start-1)*pageNumer);
//            query.setMaxResults(pageNumer);
//            list= query.getResultList();
//            ;
//        }catch (Exception e){
//            System.out.println("------------分页错误---------------");
//        }
//
//        return list;
//    }
    public boolean update(Object entity) {
        boolean flag = false;
        try {
            entityManager.merge(entity);
            flag = true;
        } catch (Exception e) {
            System.out.println("---------------更新出错---------------");
        }
        return flag;
    }
//    public Integer updateMoreFiled(String tablename, LinkedHashMap<String, Object> map) {
//        String sql="UPDATE "+tablename+" AS u SET ";
//        Set<String> set=null;
//        set=map.keySet();
//        List<String> list=new ArrayList<>(set);
//        for (int i=0;i<list.size()-1;i++){
//            if (map.get(list.get(i)).getClass().getTypeName()=="java.lang.String"){
//                System.out.println("-*****"+map.get(list.get(i))+"------------"+list.get(i));
//                sql+="u."+list.get(i)+"='"+map.get(list.get(i))+"' , ";
//            }else {
//                sql+="u."+list.get(i)+"="+map.get(list.get(i))+" , ";
//            }
//        }
//        sql=sql.substring(0,sql.length()-2);
//        sql+="where u.id=? ";
//        System.out.println(sql+"--------sql语句-------------");
//        int resurlt=0;
//        try {
//            Query query=entityManager.createQuery(sql);
//            query.setParameter(1,map.get("id"));
//            resurlt= query.executeUpdate();
//        }catch (Exception e){
//            System.out.println("更新出错-----------------------");
//            e.printStackTrace();
//
//        }
//        return resurlt;
//    }
//
    public boolean delete(Object entity) {
        boolean flag=false;
        try {
            entityManager.remove(entityManager.merge(entity));
            flag=true;
        }catch (Exception e){
            System.out.println("---------------删除出错---------------");
        }
        return flag;
    }



    @Override
    public Long count(String hql, Object... param) {
        return findByHql(hql,Long.class,param).get(0);
    }
    @Override
    public Long count(String hql, List<?> params) {
        return findByHql(hql,Long.class,params).get(0);
    }

    //
//    @Override
//    public Object findCount(String tablename, LinkedHashMap<String, Object> map) {
//        String sql="select count(u) from "+tablename+" u WHERE ";
//        Set<String> set=null;
//        set=map.keySet();
//        List<String> list=new ArrayList<>(set);
//        List<Object> filedlist=new ArrayList<>();
//        for (String filed:list){
//            sql+="u."+filed+"=? and ";
//            filedlist.add(filed);
//        }
//        sql=sql.substring(0,sql.length()-4);
//        System.out.println(sql+"--------sql语句-------------");
//        Query query=entityManager.createQuery(sql);
//        for (int i=0;i<filedlist.size();i++){
//            query.setParameter(i+1,map.get(filedlist.get(i)));
//        }
//        return query.getSingleResult();
//    }


    @Override
    public Object loadByid(Class clazz, Integer id) {
        return findByid(clazz,id);
    }

    @Override
    public void saveOrUpdate(Object object) {
        entityManager.merge(object);
    }

    @Override
    public List getListByHsql(String hql, Object... values) {
        List params=Arrays.asList(values);
        return findbyhql(hql,params);
    }

    @Override
    public void deleteHsql(String hql) {
         entityManager.createQuery(hql).executeUpdate();
    }

    @Override
    public Long getListCountByHql(String hql, Object... values) {
        return count(hql,values);
    }



    @Override
    public List getpageByHql(String hql, Integer page, Integer offset, Object[] objects) {
		if(objects==null)objects=new Object[0];
		return findPageByHql(hql,page,offset,Arrays.asList(objects));
    }

    @Override
    public Long getCountByHql(String hql, Object[] objects) {
        return count(hql,objects);
    }

    @Override
    public List getListBySql(String sql) {
        return selectListfromSql(sql);
    }



    @Override
    public void executeHql(String hql) {
        entityManager.createQuery(hql).executeUpdate();
    }

    @Override
    public void executeHql(String hql, Object... params) {
        Query query=entityManager.createQuery(hql);
        int i=1;
        for(Object o:params){
            query.setParameter(i++,o);
        }
        query.executeUpdate();
    }

    @Override
    public void executeSql(String sql,Object... params) {
        Query query=entityManager.createNativeQuery(sql);
        int i=1;
        for(Object o:params){
            query.setParameter(i++,o);
        }
        query.executeUpdate();
    }



    @Override
    public List getEntityBySql(String sql, Class clazz) {
        return entityManager.createNativeQuery(sql,clazz).getResultList();
    }

    @Override
    public Object getEntityByHql(String hql, Object... values) {
        Query query= entityManager.createQuery(hql);
        for(int i=0;i<values.length;i++){
            query.setParameter(i+1,values[i]);
        }
       try{
           Object o=query.getSingleResult();
           return o;
       }catch (Exception e){
            e.printStackTrace();;
            return null;
       }
    }


}
