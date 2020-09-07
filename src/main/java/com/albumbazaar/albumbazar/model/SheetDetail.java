package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.core.annotation.Order;

import javax.persistence.*;

@Entity
@Table(
        uniqueConstraints =
                @UniqueConstraint(columnNames = {"paper_index", "order_id"})
)
public class SheetDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "paper_index")
    private Long paperIndex;
    private String paperQuality;
    private String paperPrice;
    @Column(name = "order_id")
    private Long orderId;
}
