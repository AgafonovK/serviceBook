package com.xvr.serviceBook.entity;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "app_user")
public class AppUser{

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", length = 32, nullable = false)
    private String userName;

    @Column(name = "encryptedPassword", nullable = false)
    private String encryptedPassword;

    @Column(name = "enable_user", length = 1, nullable = false)
    private int enabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<AppRole> appRole;

    public void addAppRole(AppRole appRole){
        this.appRole.add(appRole);
        appRole.getAppUsers().add(this);
    }

    public void deleteAppRole(AppRole appRole){
        this.appRole.remove(appRole);
        appRole.getAppUsers().remove(this);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @Override
    public String toString() {
        return "AppUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", enabled=" + enabled +
                ", appRole=" + appRole.toString() +
                ", worker=" + worker.toString() +
                '}';
    }
}
