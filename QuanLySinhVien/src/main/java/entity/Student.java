package entity;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Student {
    private int id;
    private String name;
    private int age;
    private String address;
    private float gpa;
}
