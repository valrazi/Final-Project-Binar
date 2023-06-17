package org.bejb4.finalproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCity;

    private String cityName;

    private String cityCode;

    private String cityAirport;

}
