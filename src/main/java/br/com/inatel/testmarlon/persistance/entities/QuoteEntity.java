package br.com.inatel.testmarlon.persistance.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quotes")
public class QuoteEntity {

    @Id
    @Column(name = "id", nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "stock_id", nullable = false)
    private String stockId;

    @ElementCollection
    @CollectionTable(name = "daily_quotations", joinColumns = @JoinColumn(name = "quotes"))
    @MapKeyColumn(name = "date")
    @Column(name = "quantity")
    private Map<LocalDate, Integer> quotes;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QuoteEntity that = (QuoteEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
