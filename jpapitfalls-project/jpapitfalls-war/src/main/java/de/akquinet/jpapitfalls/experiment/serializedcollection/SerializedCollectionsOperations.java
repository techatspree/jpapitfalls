package de.akquinet.jpapitfalls.experiment.serializedcollection;

import de.akquinet.jpapitfalls.experiment.serializedcollection.model.FieldOfStudyWrong;
import de.akquinet.jpapitfalls.experiment.serializedcollection.model.StudentWrong;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
    public void listStudentsPassedGrade(Long fieldOfStudyId, StringBuilder sb) {
        final FieldOfStudyWrong fieldOfStudy = em.find(FieldOfStudyWrong.class, fieldOfStudyId);
        for (StudentWrong student : fieldOfStudy.getStudents()) {
            LOG.debugf("list %s", student);
            final StudentWrong studentFromDatabase = em.find(StudentWrong.class, student.getId());
            sb
                    .append(student.getName())
                    .append(": from collection passed = ")
                    .append(student.getPassed())
                    .append(", from database passed = ")
                    .append(studentFromDatabase.getPassed())
                    .append("<br>");
        }
    }

    @TransactionAttribute(REQUIRES_NEW)
    public void setAllStudentsToPassed(Long fieldOfStudyId) {
        final FieldOfStudyWrong fieldOfStudy = em.find(FieldOfStudyWrong.class, fieldOfStudyId);
        for (StudentWrong student : fieldOfStudy.getStudents()) {
            LOG.debugf("set passed on %s", student);
            student.setPassed(true);
        }
    }

    @TransactionAttribute(REQUIRES_NEW)
    public Long createFieldOfStudyAndStudentsTheWrongWay() {
        final StudentWrong student1 = createAndPersistStudent("student 1");
        final StudentWrong student2 = createAndPersistStudent("student 2");

        HashSet<StudentWrong> students = new HashSet<StudentWrong>();
        students.add(student1);
        students.add(student2);

        final FieldOfStudyWrong fieldOfStudy = FieldOfStudyWrong.builder()
                .name("field of study")
                .students(students).build();
        em.persist(fieldOfStudy);

        LOG.debugf("created %s", fieldOfStudy);

        return fieldOfStudy.getId();
    }

    public StudentWrong createAndPersistStudent(String name) {
        final StudentWrong student = StudentWrong.builder().name(name).build();
        em.persist(student);
        em.flush();
        LOG.debugf("created student: %s",student);
        return student;
    }
}