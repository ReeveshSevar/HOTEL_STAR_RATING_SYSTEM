package com.hotel.HotelService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Hotels")
public class Hotel {
    @Id
    @UuidGenerator
    private String id;
    private String name;
    private String location;
    private String about;
}
