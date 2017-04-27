package com.zup.test.developer.data.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="POI")
public class Poi implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "POI_SQ")
    @SequenceGenerator(name = "POI_SQ", sequenceName = "POI_SQ", initialValue=8, allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "X_COORDANATE", nullable = false)
    private Long x_coordanate;

    @Column(name = "Y_COORDANATE", nullable = false)
    private Long y_coordanate;

    public Poi(String name, Long x_coordanate, Long y_coordanate) {
        this.name = name;
        this.x_coordanate = x_coordanate;
        this.y_coordanate = y_coordanate;
    }

    public Poi() {

    }

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

    public Long getX_coordanate() {
        return x_coordanate;
    }

    public void setX_coordanate(Long x_coordanate) {
        this.x_coordanate = x_coordanate;
    }

    public Long getY_coordanate() {
        return y_coordanate;
    }

    public void setY_coordanate(Long y_coordanate) {
        this.y_coordanate = y_coordanate;
    }

    @Override
    public String toString() {
        return "Poi{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", x_coordanate=" + x_coordanate +
                ", y_coordanate=" + y_coordanate +
                '}';
    }
}
