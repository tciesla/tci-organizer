package pl.tciesla.organizer.model;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class Task {

    @Getter private Long id;
    @Getter private String name;
    @Getter private Status status;
    @Getter private Date created;
    @Getter private boolean important;
    private Date finished;

    public enum Status {
        NEW, COMPLETED
    }

    // JAXB
    @SuppressWarnings("unused")
    private Task() {}

    public Task(Long id, String name) {
        this.id = checkNotNull(id, "id == null");
        this.name = checkNotNull(name, "name == null");
        this.status = Status.NEW;
        this.created = new Date();
    }

    public void toggleImportant() {
        this.important = !this.important;
    }

    public void complete() {
        this.status = Status.COMPLETED;
        this.finished = new Date();
    }

    public Optional<Date> getFinished() {
        return Optional.ofNullable(finished);
    }

}
