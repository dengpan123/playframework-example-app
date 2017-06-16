package service;

import Repository.ComputerDAO;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import models.Computer;
import play.Logger;

import javax.inject.Inject;

/**
 * Created by admin on 17/6/15.
 */
public class JsonTestService {

    @Inject
    private ComputerDAO computerDAO;

    public void setComputerDAO(ComputerDAO computerDAO) {
        this.computerDAO = computerDAO;
    }

    public Computer getCouponByCode(Integer id) {
        return computerDAO.getCouponByCode(id);
    }

    public  Computer queryComputer(Integer id){
        return this.computerDAO.getCouponByCode(id);
    }

    public  void create(Computer computer){
        Transaction txn = Ebean.beginTransaction();
        try{
            this.computerDAO.create(computer);
            txn.commit();
        }catch (Exception e){
            Logger.info("create fail!",e);
            txn.rollback();
        }finally {
            txn.end();
        }

    }
    public void update(Computer computer){
        Logger.info("execute  update");
        Transaction txn = Ebean.beginTransaction();
        try{
            this.computerDAO.update(computer);
            txn.commit();
        }catch (Exception e){
            Logger.info("update fail!",e);
            txn.rollback();
        }finally {
            txn.end();
        }
    }

    public  void delete(Long id){
        Transaction txn = Ebean.beginTransaction();
        try{
            this.computerDAO.delete(id);
            txn.commit();
        }catch (Exception e){
            Logger.info("delete fail!",e);
            txn.rollback();
        }finally {
            txn.end();
        }
    }
}
