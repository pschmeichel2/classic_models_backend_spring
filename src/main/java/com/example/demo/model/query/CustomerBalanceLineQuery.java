package com.example.demo.model.query;

import javax.persistence.*;
import java.time.LocalDateTime;
import org.springframework.data.annotation.ReadOnlyProperty;

import com.example.demo.model.keys.OrderDetailPK;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBalanceLineQuery {
    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    private LocalDateTime transactionDate; 
    @Getter
    private String status; 
    @Getter
    private Float amount; 
}
