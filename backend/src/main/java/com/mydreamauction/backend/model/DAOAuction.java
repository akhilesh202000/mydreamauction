package com.mydreamauction.backend.model;

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
    private String status;

    @OneToMany(targetEntity = DAOProduct.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id",referencedColumnName = "id")
    private List<DAOProduct> products;

}
