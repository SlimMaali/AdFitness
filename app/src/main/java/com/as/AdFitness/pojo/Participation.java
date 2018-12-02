package com.as.AdFitness.pojo;

public class Participation {

    private int id;
    private User user;
    private Session session;

    public Participation() {
    }

    public Participation(User user, Session session) {
        this.user = user;
        this.session = session;
    }

    public Participation(int id, User user, Session session) {
        this.id = id;
        this.user = user;
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participation that = (Participation) o;

        if (getId() != that.getId()) return false;
        if (getUser() != null ? !getUser().equals(that.getUser()) : that.getUser() != null)
            return false;
        return getSession() != null ? getSession().equals(that.getSession()) : that.getSession() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getSession() != null ? getSession().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "id=" + id +
                ", user=" + user +
                ", session=" + session +
                '}';
    }
}
