package com.gumtree.example02;

import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock private UsersGateway mockUsersGateway;
    @Mock private UserAdapter mockUserAdapter;
    @Mock private DbUser mockDbUser;
    @Mock private User mockUser;

    // System Under Test (SUT)
    private UserServiceImpl sut;

    @BeforeMethod
    public void setup() {
        mockUsersGateway = mock(UsersGateway.class);
        mockUserAdapter = mock(UserAdapter.class);
        mockDbUser = mock(DbUser.class);
        mockUser = mock(User.class);
        sut = new UserServiceImpl(mockUsersGateway, mockUserAdapter);
    }

    @Test(testName = "When DbUser exists the UserService returns an Optional containing the adapted User")
    public void getUser_HappyPath_User_Exists() {
        when(mockUsersGateway.getById(anyLong())).thenReturn(Optional.of(mockDbUser));
        when(mockUserAdapter.adapt(any(DbUser.class))).thenReturn(mockUser);
        Assert.assertEquals(sut.getUser(10L), Optional.of(mockUser));
    }

    @Test(testName = "When DbUser does not exist the UserService returns an empty Optional")
    public void getUser_HappyPath_User_Does_Not_Exists() {
        when(mockUsersGateway.getById(anyLong())).thenReturn(Optional.empty());
        Assert.assertEquals(sut.getUser(10L), Optional.empty());
    }
}
