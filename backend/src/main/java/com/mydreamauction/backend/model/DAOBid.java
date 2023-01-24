package com.mydreamauction.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class DAOBid {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String price;

    @Column
    private String quantity;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "deliverydate")
    private java.sql.Timestamp deliverydate;

    @Column
    private String name;

    @Column
    private boolean isAccepted;
}
