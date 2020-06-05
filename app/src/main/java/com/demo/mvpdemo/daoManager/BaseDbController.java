package com.demo.mvpdemo.daoManager;

import java.util.List;

public abstract class BaseDbController<T> {

    /**
     * 会自动判定是插入还是替换 insertOrReplace(T)
     *
     * @param t
     */
    public abstract void insertOrReplace(T t);

    /**
     * 插入一条记录，表里面要没有与之相同的记录 insert
     *
     * @param t
     */
    public abstract long insert(T t);

    /**
     * 更新数据 update
     *
     * @param t
     */
    public abstract void update(T t);

    /**
     * 查询所有数据 queryBuilder().list()
     */
    public abstract List<T> searchAll();

    /**
     * 按条件查询数据
     * queryBuilder
     */
    public abstract List<T> searchByWhere(String whereCondition);

    /**
     * 查询指定区域数据 queryBuilder().list()
     *
     * @param where 操作类型
     * @param page  页码
     * @param size  条目数
     * @return
     */
    public abstract List<T> searchAll(int where, int page, int size);

    /**
     * 删除数据
     * buildDelete
     */
    public abstract void delete(String whereCondition);


    public abstract void delete(T t);
}