package pl.tciesla.organizer.model;

import lombok.Getter;

import java.util.Date;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public class Task {

    @Getter private Long id;
    @Getter private String name;
    @Getter private Status status;
    @Getter private Date created;
    private Date finished;

    public enum Status {
        NEW, COMPLETED
    }

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

    public Optional<Date> getFinished() {
        return Optional.ofNullable(finished);
    }

}
