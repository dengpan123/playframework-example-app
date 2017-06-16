package Repository;

import com.avaje.ebean.Ebean;
import models.Computer;

/**
 * Created by admin on 17/6/15.
 */
public class ComputerDAO {
    public Computer getCouponByCode(Integer id) {
       return Computer.find.where().eq("id",id).findUnique();
    }

    public  void create(Computer computer){
        computer.save();
    }
    public void update(Computer computer){
        computer.update();
    }

    public  void delete(Long id){
        Computer.find.ref(id).delete();
    }
}
