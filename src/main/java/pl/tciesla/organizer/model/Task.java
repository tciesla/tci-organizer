package pl.tciesla.organizer.model;

import lombok.Getter;
import pl.tciesla.organizer.util.LocalDateTimeXmlAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class Task implements Comparable<Task> {

    @Getter private Long id;
    @Getter private String name;
    @Getter private Status status;
    @Getter private Integer priority;
    @Getter private boolean important;

    @Getter
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime created;

    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime finished;

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
        this.priority = 0;
        this.created = LocalDateTime.now();
    }

    public void toggleImportant() {
        this.important = !this.important;
    }

    public void prioritize(int votes) {
        this.priority += votes;
    }

    public void complete() {
        this.status = Status.COMPLETED;
        this.finished = LocalDateTime.now();
    }

    public Optional<LocalDateTime> getFinished() {
        return Optional.ofNullable(finished);
    }

    @Override
    public int compareTo(Task otherTask) {
        return otherTask.priority.compareTo(priority);
    }

}
