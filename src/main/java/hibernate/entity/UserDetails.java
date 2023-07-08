package hibernate.entity;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "details_id")
    private Long detailsId;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "email")
    private String email;
    @Column(name = "city")
    private String city;

    public UserDetails() {

    }

    public Long getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Long detailsId) {
        this.detailsId = detailsId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return Objects.equals(detailsId, that.detailsId) && Objects.equals(lastName, that.lastName) && Objects.equals(age, that.age) && Objects.equals(email, that.email) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailsId, lastName, age, email, city);
    }

    @Override
    public String toString() {
        return "UsersDetails{" +
                "detailsId=" + detailsId +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
