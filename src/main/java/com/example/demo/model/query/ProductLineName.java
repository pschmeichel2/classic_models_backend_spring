package com.example.demo.model.query;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductLineName {
    @Id @Getter 
    private String productLineName; // int(11) NOT NULL,

}
