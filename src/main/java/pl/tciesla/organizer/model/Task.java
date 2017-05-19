package pl.tciesla.organizer.model;

import java.util.Date;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public class Task {

    private Long id;
    private String name;
    private Status status;
    private Date created;
    private Date finished;

    public enum Status {NEW, COMPLETED}

    public Task(Long id, String name) {
        this.id = checkNotNull(id, "id == null");
        this.name = checkNotNull(name, "name == null");
        this.status = Status.NEW;
        this.created = new Date();
    }

    public void complete() {
        this.status = Status.COMPLETED;
        this.finished = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Date getCreated() {
        return created;
    }

    public Optional<Date> getFinished() {
        return Optional.ofNullable(finished);
    }

}
