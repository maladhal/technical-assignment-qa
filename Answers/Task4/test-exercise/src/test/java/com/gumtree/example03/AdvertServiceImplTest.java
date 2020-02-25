package com.gumtree.example03;

import com.gumtree.example03.gateway.AdvertsGateway;
import com.gumtree.example03.gateway.AdvertsRepository;
import com.gumtree.example03.gateway.UsersRepository;
import com.gumtree.example03.model.Advert;
import com.gumtree.example03.service.AdvertServiceImpl;
import com.gumtree.example03.service.UserAdapterImpl;
import com.gumtree.example03.service.UserServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

public class AdvertServiceImplTest {

    // Build an Advert that has a UserId that does not correspond to a user in the UserService
    private final Advert ADVERT_WITHOUT_USER = Advert.Builder
            .anAdvert()
            .withId(11)
            .withUserId(4)
            .withIsExpired(true)
            .withDescription("Advert without a user")
            .build();

    private UserServiceImpl userService;
    private AdvertsGateway advertsGateway;

    // System Under Test (SUT)
    private AdvertServiceImpl sut;

    @BeforeMethod
    public void setup() {
        userService = new UserServiceImpl(new UsersRepository(), new UserAdapterImpl());
        advertsGateway = new AdvertsRepository();
        sut = new AdvertServiceImpl(userService, advertsGateway);
    }

    @Test(testName = "Returns a valid `List<Advert>` of all adverts associated with the given `sellerId`")
    public void getAdverts_Returns_The_Expected_Number_Of_Adverts_For_Existing_User() {
        Assert.assertEquals(sut.getAdverts(1L).size(), 5);
    }

    @Test(testName = "Returns an empty `List<Advert>` if no adverts are found for the given `sellerId`")
    public void getAdverts_Returns_The_Expected_Number_Of_Adverts_For_Non_Existent_User() {
        Assert.assertEquals(sut.getAdverts(100L).size(), 0);
    }

    @Test(testName = "Asserts that an Advert that we know can be reposted, can be reposted.")
    public void canRepostForFree_Returns_True_For_Repostable_Advert() {
        Assert.assertTrue(sut.canRepostForFree(10L));
    }

    @Test(testName = "Asserts that an Advert that we known cannot be reposted, cannot be reposted")
    public void canRepostForFree_Returns_False_For_Non_Repostable_Advert() {
        Assert.assertFalse(sut.canRepostForFree(1L));
    }

    @Test(testName = "An Advert that does not exist cannot be reposted, and throws a NoSuchElementException")
    public void canRepostForFree_Throws_Exception_For_Non_Existent_Advert() {
        Assert.assertThrows(NoSuchElementException.class, () -> sut.canRepostForFree(100L));
    }

    @Test(testName = "Ad Advert whose UserId does not correspond to a known User throws a NoSuchElementException")
    public void canRepostForFree_Throws_Exception_For_Advert_With_Non_Existent_User() {
        advertsGateway.updateAdvert(ADVERT_WITHOUT_USER);
        Assert.assertThrows(NoSuchElementException.class, () -> sut.getAdverts(11L));
    }

    @Test(testName = "Ensure the repostForFree flag has been set on a given advertId, and is stored.")
    public void repostForFree_Can_Successfully_Repost_Advert() {
        long advertId = 10L;
        // Assert that the advert exists and can be reposted for free
        Optional<Advert> advert = advertsGateway.getById(advertId);
        Assert.assertTrue(advert.isPresent());
        Assert.assertTrue(advert.get().isExpired());
        Assert.assertTrue(sut.canRepostForFree(advertId));

        sut.repostForFree(advertId);

        // Assert that the advert has been reposted
        advert = advertsGateway.getById(advertId);
        Assert.assertTrue(advert.isPresent());
        Assert.assertFalse(advert.get().isExpired());
        Assert.assertFalse(sut.canRepostForFree(advertId));
    }

    @Test(testName = "Ensure nothing has changed in the backend when a invalid advertId (a range of invalids).")
    public void repostForFree_Throws_Exception_When_Reposting_Non_Repostable_Advert() {
        long advertId = 3L;
        // Assert that the advert exists and can be reposted for free
        Optional<Advert> advert = advertsGateway.getById(advertId);
        Assert.assertTrue(advert.isPresent());
        Assert.assertTrue(advert.get().isExpired());
        Assert.assertFalse(sut.canRepostForFree(advertId));
        Assert.assertThrows(IllegalStateException.class, () -> sut.repostForFree(advertId));
    }
}
