package com.tnt.onlinestore.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    //Set of RoleEntities to add to UserEntity
    Set<RoleEntity> RoleSet = new HashSet<>();

    //Set of UserEntities to add to RoleEntity
    Set<UserEntity> userSet = new HashSet<>();

    //RoleEntities to add to RoleEntity Set
    RoleEntity adminRole = new RoleEntity(1L, "ROLE_ADMIN", null);
    RoleEntity userRole = new RoleEntity(2L,"ROLE_USER", null);

    //Initialise UserEntity for testing using RoleEntity Set
    UserEntity user = new UserEntity(1L,"name","email@e.se","abcde","address", RoleSet,new CartEntity(1L,null, null));

    //Before each test, add the user to a Set, and add the Set to each RoleEntity, so the tests can compile
    @BeforeEach
    void setup() {
        userSet.add(user);
        adminRole.setUsers(userSet);
        userRole.setUsers(userSet);
    }

    @Test
    void addAdminRole() {
        user.addRole(adminRole);
        assertTrue(user.getRoles().contains(adminRole));
    }

    @Test
    void addUserRole() {
        user.addRole(userRole);
        assertTrue(user.getRoles().contains(userRole));
    }

    @Test
    void addUserRoleAndCheckingForAdminShouldReturnFalse() {
        user.addRole(userRole);
        assertFalse(user.getRoles().contains(adminRole));
    }

    @Test
    void addingBothRolesShouldContainBothRoles() {
        user.addRole(userRole);
        user.addRole(adminRole);
        assertTrue((user.getRoles().contains(adminRole)) && (user.getRoles().contains(userRole)));
    }

    @Test
    void addingSingleRoleGivesCorrectNumberOfRoles() {
        user.addRole(adminRole);
        assertEquals(user.getRoles().size(), 1);
    }

    @Test
    void addingBothRolesGivesCorrectNumberOfRoles() {
        user.addRole(userRole);
        user.addRole(adminRole);
        assertEquals(user.getRoles().size(), 2);
    }

    @Test
    void removeRoleClearsTheRoleSetIfOnlyOneRolePresentAtStart() {
        user.addRole(userRole);
        user.removeRole(userRole);
        assertTrue(user.getRoles().isEmpty());
    }

    @Test
    void removeRoleRemovesOnlyOneRoleIfTwoArePresentAtStart() {
        user.addRole(userRole);
        user.addRole(adminRole);
        user.removeRole(userRole);
        assertEquals(user.getRoles().size(), 1);
    }

    @Test
    void removeRoleShouldRemoveCorrectRole() {
        user.addRole(userRole);
        user.addRole(adminRole);
        user.removeRole(adminRole);
        assertTrue(user.getRoles().contains(userRole));
        assertFalse(user.getRoles().contains(adminRole));
    }

}