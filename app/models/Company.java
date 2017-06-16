package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity
@Table(name = "company")
public class Company extends com.avaje.ebean.Model {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    public Long id;
    
    @Constraints.Required
    @Column(name = "name")
    public String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Generic query helper for entity Company with id Long
     */
    public static Find<Long,Company> find = new Find<Long,Company>(){};

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Company c: Company.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }

}

