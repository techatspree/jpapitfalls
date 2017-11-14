package de.akquinet.jpapitfalls.experiment.mapping.serializedcollection;

import de.akquinet.jpapitfalls.experiment.mapping.serializedcollection.model.FieldOfStudy;
import de.akquinet.jpapitfalls.experiment.mapping.serializedcollection.model.FieldOfStudyRight;
import de.akquinet.jpapitfalls.experiment.mapping.serializedcollection.model.FieldOfStudyWrong;
import de.akquinet.jpapitfalls.experiment.mapping.serializedcollection.model.Student;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

@Stateless
public class SerializedCollectionsOperations {

    private static Logger LOG = Logger.getLogger(SerializedCollectionsOperations.class);

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public SerializedCollectionsOperations() {
    }

    @TransactionAttribute(REQUIRES_NEW)
    public Long createFieldOfStudyAndStudentsTheWrongWay() {
        final Student student1 = createAndPersistStudent("student 1");
        final Student student2 = createAndPersistStudent("student 2");

        HashSet<Student> students = new HashSet<>();
        students.add(student1);
        students.add(student2);

        final FieldOfStudyWrong fieldOfStudy = FieldOfStudyWrong.builder()
                .name("field of study")
                .students(students).build();
        em.persist(fieldOfStudy);

        LOG.debugf("created %s", fieldOfStudy);

        return fieldOfStudy.getId();
    }

    @TransactionAttribute(REQUIRES_NEW)
    public void executeRightExperiment(Long fieldOfStudyRightId, StringBuilder result, SerializedCollectionExperiment serializedCollectionExperiment) {
        final FieldOfStudy fieldOfStudyRight = em.find(FieldOfStudyRight.class, fieldOfStudyRightId);

        setPassedAndReadPassedFromCollectionAndFromDatabase(fieldOfStudyRight, result, serializedCollectionExperiment);
    }

    @TransactionAttribute(REQUIRES_NEW)
    public void executeWrongExperiment(Long fieldOfStudyWrongId, StringBuilder result, SerializedCollectionExperiment serializedCollectionExperiment) {
        final FieldOfStudyWrong fieldOfStudyWrong = em.find(FieldOfStudyWrong.class, fieldOfStudyWrongId);
        setPassedAndReadPassedFromCollectionAndFromDatabase(fieldOfStudyWrong, result, serializedCollectionExperiment);
    }

    @TransactionAttribute(REQUIRES_NEW)
    public Long createFieldOfStudyAndStudentsTheRightWay() {
        final Student student3 = createAndPersistStudent("student 3");
        final Student student4 = createAndPersistStudent("student 4");

        final FieldOfStudyRight fieldOfStudyRight = FieldOfStudyRight.builder()
                .name("field of study correctly done")
                .student(student3)
                .student(student4)
                .build();

        em.persist(fieldOfStudyRight);

        return fieldOfStudyRight.getId();
    }

    private void listStudentsFromCollectionAndFromDatabase(FieldOfStudy fieldOfStudy, StringBuilder sb) {
        for (Student student : fieldOfStudy.getStudents()) {
            LOG.debugf("list %s", student);
            final Student studentFromDatabase = em.find(Student.class, student.getId());
            sb
                    .append("student '")
                    .append(student.getName())
                    .append("'")
                    .append(": from collection passed = ")
                    .append(student.getPassed())
                    .append(", from database passed = ")
                    .append(studentFromDatabase.getPassed())
                    .append("<br>");
        }
    }

    private void setAllStudentsToPassed(FieldOfStudy fieldOfStudy, StringBuilder sb) {
        for (Student student : fieldOfStudy.getStudents()) {
            sb
                    .append("set passed to true on ")
                    .append(student.getName())
                    .append("<br>");
            student.setPassed(true);
        }
    }

    private Student createAndPersistStudent(String name) {
        final Student student = Student.builder().name(name).build();
        em.persist(student);
        em.flush();
        LOG.debugf("created student: %s", student);
        return student;
    }

    private void setPassedAndReadPassedFromCollectionAndFromDatabase(FieldOfStudy fieldOfStudy, StringBuilder resultSB, SerializedCollectionExperiment serializedCollectionExperiment) {
        setAllStudentsToPassed(fieldOfStudy, resultSB);
        listStudentsFromCollectionAndFromDatabase(fieldOfStudy, resultSB);
    }
}