package chaplinskiy.jdbccrud.repository.hibernate;

import chaplinskiy.jdbccrud.model.Post;
import chaplinskiy.jdbccrud.model.Region;
import chaplinskiy.jdbccrud.repository.RegionRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static chaplinskiy.jdbccrud.util.HibernateSessionFactory.getSessionFactory;

public class RegionRepositoryHibernate implements RegionRepository {

    @Override
    public Region create(Region region) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.save(region);
        transaction.commit();
        session.close();
        return region;
    }

    @Override
    public Region update(Region region) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Region getRegion = session.get(Region.class, region.getId());
        getRegion.setName(region.getName());

        session.update(getRegion);
        transaction.commit();
        session.close();
        return region;
    }

    @Override
    public Region getById(Long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Region region = (Region) session.get(Region.class, id);
        transaction.commit();
        session.close();
        return region;
    }

    @Override
    public List<Region> getAll() {
        Session session = getSessionFactory().openSession();

        Transaction transaction = null;

        transaction = session.beginTransaction();
        List regions = session.createQuery("from Region").getResultList();

        transaction.commit();
        session.close();
        return regions;
    }

    @Override
    public void deleteById(Long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Region region = (Region) session.get(Region.class, id);

        session.delete(region);
        transaction.commit();
        session.close();
    }
}
