package com.example.capstone3.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nutrition_plans")
public class NutritionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private String items;

    @NotEmpty
    @Column(columnDefinition = "text")
    private String summary;

    @NotEmpty
    private String reason;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

   /* @OneToOne
    @JsonIgnore
    private User user;*/
   @OneToOne
   @JoinColumn(name = "user_id", nullable = false, unique = true)
   @JsonIgnore
   private User user;


}