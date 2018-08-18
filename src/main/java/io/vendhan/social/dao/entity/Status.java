package io.vendhan.social.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "STATUS")
public class Status implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "LABEL")
    @NotBlank
    private String label;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Status() {
    }

    public Status(@NotBlank String label) {
        this.label = label;
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

    public String getLabel() {
        return label;
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
        if (!(o instanceof Status)) return false;
        Status status = (Status) o;
        return Objects.equals(id, status.id) &&
                Objects.equals(label, status.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
