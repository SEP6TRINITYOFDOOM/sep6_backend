package com.sep6.app.model;

import jakarta.persistence.*;

@Entity(name = "friendship")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"requester_id", "target_id"})
})
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User requester;

    @ManyToOne
    private User target;

    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    public Friendship(User requester, User target, FriendshipStatus status) {
        this.requester = requester;
        this.target = target;
        this.status = status;
    }

    public Friendship() {

    }

    public void setStatus(FriendshipStatus status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public User getRequester() {
        return requester;
    }

    public User getTarget() {
        return target;
    }

    public FriendshipStatus getStatus() {
        return status;
    }
}
