package de.akquinet.jpapitfalls.experiment.serializedcollection;

import de.akquinet.jpapitfalls.experiment.serializedcollection.model.FieldOfStudy;
import de.akquinet.jpapitfalls.experiment.serializedcollection.model.FieldOfStudyWrong;
import de.akquinet.jpapitfalls.experiment.serializedcollection.model.Student;
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

    @PersistenceContext
    private EntityManager em;

    public SerializedCollectionsOperations() {
    }

    @TransactionAttribute(REQUIRES_NEW)
    public void listStudentsFromCollectionAndFromDatabase(FieldOfStudy fieldOfStudy, StringBuilder sb) {
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

    @TransactionAttribute(REQUIRES_NEW)
    public void setAllStudentsToPassed(FieldOfStudy fieldOfStudy, StringBuilder sb) {
        for (Student student : fieldOfStudy.getStudents()) {
            sb
                    .append("set passed to true on ")
                    .append(student.getName())
                    .append("<br>");
            student.setPassed(true);
        }
    }

    @TransactionAttribute(REQUIRES_NEW)
    public FieldOfStudy createFieldOfStudyAndStudentsTheWrongWay() {
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

        return fieldOfStudy;
    }

    public Student createAndPersistStudent(String name) {
        final Student student = Student.builder().name(name).build();
        em.persist(student);
        em.flush();
        LOG.debugf("created student: %s", student);
        return student;
    }
}