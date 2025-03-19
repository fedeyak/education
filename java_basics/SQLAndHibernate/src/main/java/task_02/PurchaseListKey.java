package task_02;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
@ToString
public class PurchaseListKey implements Serializable {
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "student_name")
    private String studentName;
}
