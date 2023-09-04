package ru.javabegin.backend.todo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/*
Все доступные роли, которые будут привязаны к пользователю
*/


@Entity
@Table(name = "role", schema = "public", catalog = "habrdb")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@json_id")
public class Role extends GenericModel {

	private String name; // название роли


	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "users_roles",
			joinColumns = @JoinColumn(name = "role_id"),
			foreignKey = @ForeignKey(name = "FK_ROLES_USERS"),
			inverseJoinColumns = @JoinColumn(name = "user_data_id"),
			inverseForeignKey = @ForeignKey(name = "FK_USERS_ROLES"))
	private Set<UserData> users;





}
