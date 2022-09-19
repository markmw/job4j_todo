package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStore {
    private final SessionFactory sf;

    public int executeDone(int id) {
        Session session = sf.openSession();
        Transaction tx = null;
        int res;
        try {
            tx = session.beginTransaction();
            res = session.createQuery("update tasks set done = :fDone where id = :fId")
                    .setParameter("fDone", true)
                    .setParameter("fId", id)
                    .executeUpdate();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return res;
    }

    public int delete(int id) {
        Session session = sf.openSession();
        Transaction tx = null;
        int res;
        try {
            tx = session.beginTransaction();
            res = session.createQuery("delete from tasks where id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return res;
    }

    public int update(Task task) {
        Session session = sf.openSession();
        Transaction tx = null;
        int res;
        try {
            tx = session.beginTransaction();
            res = session.createQuery("update tasks set description = :fDescription, done = :fDone where id = :fId")
                    .setParameter("fDescription", task.getDescription())
                    .setParameter("fDone", task.isDone())
                    .setParameter("fId", task.getId())
                    .executeUpdate();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return res;
    }

    public Optional<Task> findById(int id) {
        Session session = sf.openSession();
        Transaction tx = null;
        Optional<Task> task;
        try {
            tx = session.beginTransaction();
            task = session.createQuery("from tasks where id = :fId", Task.class)
                    .setParameter("fId", id).uniqueResultOptional();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return task;
    }

    public Task add(Task task) {
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            task.setCreated(LocalDateTime.now());
            session.save(task);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return task;
    }

    public List<Task> getAllTask() {
        List<Task> list;
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            list = session.createQuery("from tasks", Task.class).list();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

    private List<Task> getByCondDone(boolean cond) {
        List<Task> list;
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            list = session.createQuery("from tasks where done = :fDone", Task.class)
                    .setParameter("fDone", cond)
                    .list();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

    public List<Task> getDone() {
        return getByCondDone(true);
    }

    public List<Task> getNew() {
        return getByCondDone(false);
    }
}
