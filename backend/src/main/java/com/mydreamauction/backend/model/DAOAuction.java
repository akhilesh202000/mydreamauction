package com.mydreamauction.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class DAOAuction {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @Column
    private String category;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "startDate")
    private java.sql.Timestamp startDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "endDate")
    private java.sql.Timestamp endDate;

    @Column
    private String description;

    @Column
    private String currHighestPrice;

    @Column
    private String username;

    @Column
    private boolean isLocked;

    @OneToMany(targetEntity = DAOProduct.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id",referencedColumnName = "id")
    private List<DAOProduct> products;
}
