package com.skuymaen.features.team.entities;

import com.skuymaen.shared.classes.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Table(name = "cities")
public class City extends BaseEntity {
    @Getter
    @Setter
    @Column(name = "city_name", nullable = false)
    private String cityName;

    public City() {}

    public City(String cityName) {
        this.cityName = cityName;
    }
}