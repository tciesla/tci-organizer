package pl.tciesla.organizer.repository;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import pl.tciesla.organizer.model.Task;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import static java.util.stream.Collectors.toList;

@Repository(TaskRepositoryXml.BEAN_NAME)
public class TaskRepositoryXml implements TaskRepository {

    public static final String BEAN_NAME = "taskRepositoryXml";

    private final String xmlFileRepositoryPath;

    public TaskRepositoryXml(@Value("${tasks.repository.xml}") String xmlFileRepositoryPath) {
        this.xmlFileRepositoryPath = xmlFileRepositoryPath;
    }

    @PostConstruct
    public void postConstruct() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TasksWrapper.class);
        createMarshallerWithProperties(context);
        unmarshaller = context.createUnmarshaller();
        createXmlFileRepositoryIfNotExists();
    }

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    @NoArgsConstructor
    @AllArgsConstructor
    @XmlRootElement(name = "tasks")
    public static class TasksWrapper {
        @Getter @Setter
        private List<Task> tasks = Lists.newArrayList();
}

    private void createMarshallerWithProperties(JAXBContext context) throws JAXBException {
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }

    private void createXmlFileRepositoryIfNotExists() throws JAXBException {
        Path xmlStorage = Paths.get(xmlFileRepositoryPath);
        if (Files.notExists(xmlStorage)) {
            TasksWrapper tasksWrapper = new TasksWrapper(Collections.emptyList());
            marshaller.marshal(tasksWrapper, xmlStorage.toFile());
        }
    }

    @Override
    public Long nextId() {
        OptionalLong maxIdOptional = findAll().stream().mapToLong(Task::getId).max();
        return maxIdOptional.isPresent() ? maxIdOptional.getAsLong() + 1 : 1L;
    }

    @Override
    public List<Task> findAll() {
        try {
            File xmlRepositoryFile = Paths.get(xmlFileRepositoryPath).toFile();
            TasksWrapper tasksWrapper = (TasksWrapper) unmarshaller.unmarshal(xmlRepositoryFile);
            return tasksWrapper.getTasks();
        } catch (JAXBException e) {
            // TODO handle error
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }

    @Override
    public Optional<Task> find(Long taskId) {
        return findAll().stream()
                .filter(task -> task.getId().equals(taskId))
                .findAny();
    }

    @Override
    public void save(Task task) {
        List<Task> tasks = findAll().stream()
                .filter(t -> !t.getId().equals(task.getId()))
                .collect(toList());
        tasks.add(task);
        try {
            File xmlRepositoryFile = Paths.get(xmlFileRepositoryPath).toFile();
            Files.deleteIfExists(xmlRepositoryFile.toPath());
            marshaller.marshal(new TasksWrapper(tasks), xmlRepositoryFile);
        } catch (JAXBException | IOException e) {
            // TODO handle error
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long taskId) {
        List<Task> tasks = findAll().stream()
                .filter(task -> !task.getId().equals(taskId))
                .collect(toList());
        try {
            File xmlRepositoryFile = Paths.get(xmlFileRepositoryPath).toFile();
            marshaller.marshal(new TasksWrapper(tasks), xmlRepositoryFile);
        } catch (JAXBException e) {
            // TODO handle error
            e.printStackTrace();
        }
    }
}
