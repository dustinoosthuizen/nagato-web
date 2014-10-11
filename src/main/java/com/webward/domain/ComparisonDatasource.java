package com.webward.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A Datasource.
 */
@Entity
@Table(name = "T_DATASOURCE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ComparisonDatasource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Size(min = 1, max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Size(min = 1, max = 50)
    @Column(name = "classname", length = 50)
    private String classname;

    @Size(min = 1, max = 255)
    @Column(name = "url", length = 255)
    private String url;

    @Size(min = 1, max = 50)
    @Column(name = "username", length = 50)
    private String username;

    @Size(min = 1, max = 50)
    @Column(name = "password", length = 50)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComparisonDatasource datasource = (ComparisonDatasource) o;

        if (id != datasource.id) {
            return false;
        }

        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Datasource{" +
                "id=" + id +
                ", name=" + name +
                ", classname=" + classname +
                ", url=" + url +
                ", username=" + username +
                ", password=" + password +
                '}';
    }
}
