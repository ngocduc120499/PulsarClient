package Model;

import java.util.Date;
import java.util.Objects;

public class Ticket {
    private Integer id;
    private String name;
    private String description;
    private Boolean status;
    private Date dateStart;
    private Integer point;

    public Ticket(Integer id, String name, String description, Boolean status, Date dateStart, Integer point) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.dateStart = dateStart;
        this.point = point;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(name, ticket.name) && Objects.equals(description, ticket.description) && Objects.equals(status, ticket.status) && Objects.equals(dateStart, ticket.dateStart) && Objects.equals(point, ticket.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status, dateStart, point);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", dateStart=" + dateStart +
                ", point=" + point +
                '}';
    }

}
