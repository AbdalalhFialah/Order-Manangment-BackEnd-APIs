package com.example.webserviseprojects.DTO;

import com.example.webserviseprojects.entity.Customer;
import com.example.webserviseprojects.entity.ProductOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class OrderDto {
    private long id;
    private LocalDateTime dateTime;

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                '}';
    }

}
