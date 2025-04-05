package com.gowri.blitz.modal;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

/*
 * @author NaveenWodeyar
 * @date 05-04-2025
 */


@Entity
@Table(name = "TEACHER")
public class Teacher {

    @Id
    private Integer teacherId;
    private String firstName;
    private String lastName;
    private String email;
    private String subject;

    // One-to-Many relationship with Student
    @OneToMany(mappedBy = "teacher")
    private List<Student> students;

    // Default Constructor
    public Teacher() {
    }

    // Constructor with parameters
    public Teacher(Integer teacherId, String firstName, String lastName, String email, String subject) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.subject = subject;
    }

    // Getter and Setter methods
    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // toString method
    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(teacherId, firstName, lastName, email, subject);
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherId, teacher.teacherId) &&
                Objects.equals(firstName, teacher.firstName) &&
                Objects.equals(lastName, teacher.lastName) &&
                Objects.equals(email, teacher.email) &&
                Objects.equals(subject, teacher.subject);
    }
}
