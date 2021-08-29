package org.example.REST_CRUD.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "uploadDate")
    private Date uploaded;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File file;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }


    public Date getUploaded() {
        return uploaded;
    }

    public void setUploaded(Date uploaded) {
        this.uploaded = uploaded;
    }

    public Event(long id, User user, Date uploaded, File file) {
        this.id = id;
        this.user = user;
        this.uploaded = uploaded;
        this.file = file;
    }

    public Event() {
    }
}
