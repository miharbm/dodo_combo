package ru.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "general_menu")
public class GeneralMenu {

    // Getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String category;

    @Column(unique = true)
    private String title;

    @Column()
    private Long price;

    @Override
    public String toString() {
        return "GeneralMenu{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
