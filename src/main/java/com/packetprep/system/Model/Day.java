package com.packetprep.system.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Day {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long dayId;
    @NotBlank(message = "Day Name cannot be empty or Null")
    private String dayName;
    @Nullable
    private String url;
    @Nullable
    @Lob
    private String description;
    private Integer voteCount = 0;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    private Instant createdDate;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Batch batch;
    @ManyToMany(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "day_student",
        joinColumns = { @JoinColumn(name = "day_id")},
        inverseJoinColumns = { @JoinColumn (name = "student_id")}
    )
    private List<Student> students;
}
