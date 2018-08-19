package io.vendhan.social.dao.entity;

import io.vendhan.social.constant.ValidationConstant;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "PERSON")
public class Person implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "EMAIL")
    @Email(message = ValidationConstant.EMAIL)
    @NotBlank(message = ValidationConstant.EMAIL)
    private String email;

    @Column(name = "FIRST_NAME")
    @NotBlank(message = ValidationConstant.FIRST_NAME)
    private String firstName;

    @Column(name = "LAST_NAME")
    @NotBlank(message = ValidationConstant.LAST_NAME)
    private String lastName;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Person() {
    }

    public Person(
            @Email(message = ValidationConstant.EMAIL)
            @NotBlank(message = ValidationConstant.EMAIL) String email,
            @NotBlank(message = ValidationConstant.FIRST_NAME) String firstName,
            @NotBlank(message = ValidationConstant.LAST_NAME) String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @PrePersist
    public void createdAt() {
        this.createDate = this.updateDate = new Date();
    }

    @PreUpdate
    public void updatedAt() {
        this.updateDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
