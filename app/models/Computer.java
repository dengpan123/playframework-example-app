package models;

import java.util.*;
import javax.naming.Name;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

/**
 * Computer entity managed by Ebean
 */
@Entity
@Table(name = "computer")
public class Computer extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public Long id;

    @Constraints.Required
    @Column(name = "name")
    public String name;

    @Formats.DateTime(pattern="yyyy-MM-dd")
    @Column(name = "introduced")
    public Date introduced;

    @Formats.DateTime(pattern="yyyy-MM-dd")
    @Column(name = "discontinued")
    public Date discontinued;

    @ManyToOne
    @JoinColumn(name = "company_id")
    public Company company;

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

    public Date getIntroduced() {
        return introduced;
    }

    public void setIntroduced(Date introduced) {
        this.introduced = introduced;
    }

    public Date getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Date discontinued) {
        this.discontinued = discontinued;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Generic query helper for entity Computer with id Long
     */
    public static Find<Long,Computer> find = new Find<Long,Computer>(){};

    /**
     * Return a paged list of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static PagedList<Computer> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.where()
                        .ilike("name", "%" + filter + "%")
                        .orderBy(sortBy + " " + order)
                        .fetch("company")
                        .findPagedList(page, pageSize);
    }

}

