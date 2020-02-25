package com.gumtree.example02;

import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserAdapterImplTest {

    @Mock
    private DbUser mockDbUser;

    // System Under Test (SUT)
    private final UserAdapterImpl SUT = new UserAdapterImpl();

    @BeforeClass
    public void setup() {
        mockDbUser = mock(DbUser.class);
        when(mockDbUser.getId()).thenReturn(10L);
        when(mockDbUser.getFirstname()).thenReturn("John");
        when(mockDbUser.getSurname()).thenReturn("Smith");
        when(mockDbUser.isPrivate()).thenReturn(true);
    }

    @Test(testName = "Adapted User's id should match provided DbUser's id")
    public void adapt_HappyPath_UserId_Matches_DbUserId() {
        Assert.assertEquals(mockDbUser.getId(), SUT.adapt(mockDbUser).getId());
    }

    @Test(testName = "Adapted User's full name should match the pattern \"DbUser.firstname DbUser.surname\"")
    public void adapt_HappyPath_UserFullName_Matches_Expectation() {
        Assert.assertEquals(String.format("%s %s", mockDbUser.getFirstname(), mockDbUser.getSurname()), SUT.adapt(mockDbUser).getFullName());
    }

    @Test(testName = "Adapted User's isPrivate status should match provided DbUser's isPrivate status")
    public void adapt_HappyPath_UserIsPrivate_Matches_DbUserIsPrivate() {
        Assert.assertEquals(mockDbUser.isPrivate(), SUT.adapt(mockDbUser).isPrivate());
    }
}
