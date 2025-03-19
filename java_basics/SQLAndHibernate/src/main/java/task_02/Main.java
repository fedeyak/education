package task_02;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.management.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> criteriaQuery = criteriaBuilder.createQuery(PurchaseList.class);
        criteriaQuery.from(PurchaseList.class);
        List<PurchaseList> purchaseLists = session.createQuery(criteriaQuery).getResultList();

        Transaction transaction = session.beginTransaction();

        for (PurchaseList purchase : purchaseLists) {
            Student student = session.byNaturalId(Student.class).using("name", purchase.getStudentName()).load();
            Course course = session.byNaturalId(Course.class).using("name", purchase.getCourseName()).load();

            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            linkedPurchaseList.setLinkedPurchaseListKey(new LinkedPurchaseListKey(student.getId(), course.getId()));
            session.save(linkedPurchaseList);
        }

        transaction.commit();
        sessionFactory.close();
    }
}
