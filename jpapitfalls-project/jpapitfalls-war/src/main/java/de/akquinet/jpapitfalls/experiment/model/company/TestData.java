package de.akquinet.jpapitfalls.experiment.model.company;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Michael Bouschen on 19.12.2016.
 */
public class TestData {

    public static void createTestData(EntityManager em) {

        // Entities

        Department rd = new Department("Research & Development");
        Department hr = new Department("Human Resources");
        Department dept99 = new Department("Unused");

        Employee emp1 = new FullTimeEmployee("emp1First", "emp1Last", 40d, 30000d);
        Employee emp2 = new FullTimeEmployee("emp2First", "emp2Last", 40d, 25000d);
        Employee emp3 = new PartTimeEmployee("emp3First", "emp3Last", 15d, 10000d);
        Employee emp4 = new PartTimeEmployee("emp4First", "emp4Last", 20d, 12000d);
        Employee emp5 = new FullTimeEmployee("emp5First", "emp5Last", 40d, 45000d);
        Employee emp99 = new FullTimeEmployee("emp99First", "emp99Last", 40d, 100000d);

        Address addr1 = new Address("Unter den Linden 1", "12345", "Berlin");
        Address addr2 = new Address("Bülowstr. 66", "10783", "Berlin");

        Insurance ins1 = new Insurance("emp1 Insurance");
        Insurance ins2 = new Insurance("emp2 Insurance");
        Insurance ins3 = new Insurance("emp3 Insurance");
        Insurance ins4 = new Insurance("emp4 Insurance");
        Insurance ins5 = new Insurance("emp5 Insurance");
        Insurance ins99 = new Insurance("Unused");

        Project proj1 = new Project("Project 1");
        Project proj2 = new Project("Project 2");
        Project proj3 = new Project("Project 3");
        Project proj99 = new Project("Unused");

        // Employee -> Address
        emp1.setAddress(addr1);
        emp2.setAddress(addr2);
        emp3.setAddress(addr1);
        emp4.setAddress(addr2);
        emp5.setAddress(addr1);

        // Department <-> Employee
        rd.addEmployee(emp1);
        rd.addEmployee(emp2);
        rd.addEmployee(emp3);
        emp1.setDepartment(rd);
        emp2.setDepartment(rd);
        emp3.setDepartment(rd);

        hr.addEmployee(emp4);
        hr.addEmployee(emp5);
        emp4.setDepartment(hr);
        emp5.setDepartment(hr);

        // Employee <-> Insurance
        emp1.setInsurance(ins1);
        ins1.setEmployee(emp1);
        emp2.setInsurance(ins2);
        ins2.setEmployee(emp2);
        emp3.setInsurance(ins3);
        ins3.setEmployee(emp3);
        emp4.setInsurance(ins4);
        ins4.setEmployee(emp4);
        emp5.setInsurance(ins5);
        ins5.setEmployee(emp5);

        // Employee <-> Projects
        proj1.addEmployee(emp1);
        proj1.addEmployee(emp2);
        proj1.addEmployee(emp3);
        emp1.addProject(proj1);
        emp2.addProject(proj1);
        emp3.addProject(proj1);

        proj2.addEmployee(emp2);
        proj2.addEmployee(emp3);
        emp2.addProject(proj2);
        emp3.addProject(proj2);

        proj3.addEmployee(emp4);
        proj3.addEmployee(emp5);
        emp4.addProject(proj3);
        emp5.addProject(proj3);

        // persist
        em.persist(rd);
        em.persist(hr);

        em.persist(dept99);
        em.persist(emp99);
        em.persist(ins99);
        em.persist(proj99);

    }

    public static void createMoreTestData(EntityManager em) {

        Department sales = new Department("Sales");
        em.persist(sales);

        Address a1 = new Address("Undinestr. 27A", "12203", "Berlin");
        Address a2 = new Address("Königsallee 1", "40212", "Düsseldorf");
        Address a3 = new Address("Am Kölner Rathaus", "50667", "Köln");
        Address a4 = new Address("Paul-Stritter-Weg 5", "22297", "Hamburg");

        for (int i = 100; i < 200; i++) {
            FullTimeEmployee ft = new FullTimeEmployee(
                    "emp" + i + "First", "emp" + i + "Last", 40.0d, 40000.0d);
            ft.setDepartment(sales);
            ft.setAddress((i % 2 == 0) ? a1 : a2);
            Insurance ftins = new Insurance("FT carrier" + i);
            ft.setInsurance(ftins);
            ftins.setEmployee(ft);
            em.persist(ft);
            em.persist(ftins);

            PartTimeEmployee pt = new PartTimeEmployee(
                    "emp" + i + "FirstPT", "emp" + i + "Last", 20.0d, 20000.0d);
            pt.setDepartment(sales);
            pt.setAddress((i % 2 == 0) ? a3 : a4);
            Insurance ptins = new Insurance("PT carrier" + i);
            pt.setAddress((i % 2 == 0) ? a3 : a4);
            pt.setInsurance(ptins);
            ptins.setEmployee(pt);
            em.persist(pt);
            em.persist(ptins);
        }
    }

    public static void clearTestData(EntityManager em) {
        em.createQuery("DELETE FROM Project").executeUpdate();
        em.createQuery("DELETE FROM Insurance").executeUpdate();
        em.createQuery("DELETE FROM Employee").executeUpdate();
        em.createQuery("DELETE FROM Department").executeUpdate();
    }

    public static Employee getEmployee(EntityManager em) {
        Query q = em.createNamedQuery("findEmployeeByLastname");
        q.setParameter("lastname", "emp2Last");
        List<Employee> result = q.getResultList();
        if (result.size() == 1) {
            return result.get(0);
        }
        throw new RuntimeException("unexpected query result " + result);
    }

    public static Department getDepartment(EntityManager em) {
        Query q = em.createQuery("SELECT d FROM Department d where d.name = 'Research & Development'");
        List<Department> result = q.getResultList();
        if (result.size() == 1) {
            return result.get(0);
        }
        throw new RuntimeException("unexpected query result " + result);
    }


    public static Insurance getInsurance(EntityManager em) {
        Query q = em.createQuery("SELECT i FROM Insurance i where i.carrier = 'emp2 Insurance'");
        List<Insurance> result = q.getResultList();
        if (result.size() == 1) {
            return result.get(0);
        }
        throw new RuntimeException("unexpected query result " + result);
    }
}
