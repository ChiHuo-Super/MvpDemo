package com.demo.mvpdemo.daoManager;

import com.demo.mvpdemo.App;
import com.demo.mvpdemo.bean.dao.IdcardTable;
import com.demo.mvpdemo.greenDao.DaoSession;
import com.demo.mvpdemo.greenDao.IdcardTableDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @projectName：MvpDemo
 * @createTime：2020/6/5 19:50
 * @author：chihuo
 * @company：
 * @e-mail：
 * @description：身份证信息数据表控制器
 */
public class IdcardTableController extends BaseDbController<IdcardTable> {
    private final DaoSession daoSession;
    private final IdcardTableDao idcardTableDao;
    private static IdcardTableController idcardTableController;

    public IdcardTableController() {
        this.daoSession = App.getInstance().getDaoSession();
        this.idcardTableDao = daoSession.getIdcardTableDao();
    }

    /**
     * @return 保证数据库唯一
     */
    public static IdcardTableController getInstance() {
        synchronized (IdcardTableController.class) {
            if (idcardTableController == null) {
                idcardTableController = new IdcardTableController();
            }
            return idcardTableController;
        }
    }

    @Override
    public void insertOrReplace(IdcardTable idcardTable) {
        idcardTableDao.insertOrReplace(idcardTable);
    }

    @Override
    public long insert(IdcardTable idcardTable) {
        return idcardTableDao.insert(idcardTable);
    }

    @Override
    public void update(IdcardTable idcardTable) {
        idcardTableDao.update(idcardTable);
    }

    @Override
    public List<IdcardTable> searchAll() {
        return idcardTableDao.loadAll();
    }

    @Override
    public List<IdcardTable> searchByWhere(String whereCondition) {
        QueryBuilder<IdcardTable> queryBuilder = idcardTableDao.queryBuilder();
        queryBuilder.orderDesc(IdcardTableDao.Properties.CreadDate);
        queryBuilder.where(IdcardTableDao.Properties.Id.eq(whereCondition));
        return queryBuilder.list();
    }

    @Override
    public List<IdcardTable> searchAll(int where, int page, int size) {
        QueryBuilder<IdcardTable> queryBuilder = idcardTableDao.queryBuilder();
        queryBuilder.orderDesc(IdcardTableDao.Properties.CreadDate);
        queryBuilder.where(IdcardTableDao.Properties.Id.eq(where));
        queryBuilder.offset(page * size).limit(size);
        return queryBuilder.list();
    }

    @Override
    public void delete(String whereCondition) {
    }

    @Override
    public void delete(IdcardTable idcardTable) {
        idcardTableDao.delete(idcardTable);
    }
}
