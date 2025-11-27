package com.faisal.hotel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="hotels")
public class Hotel {
    @Id
    @Column(name="ID")
    private String hotelId;
    @Column(name="HOTELNAME", length=30)
    private String hotelName;
    @Column(name="LOCATION")
    private String location;
    @Column(name="ABOUT")
    private String about;
}
