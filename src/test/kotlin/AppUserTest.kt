import com.app.splitbillapp.entities.AppUser
import com.app.splitbillapp.repo.AppUserRepository
import com.app.splitbillapp.service.AppUserService
import org.json.JSONObject
import org.junit.jupiter.api.Assertions
//import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mock
import org.mockito.InjectMocks
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.MockitoAnnotations
import org.springframework.test.context.junit.jupiter.SpringExtension
//import sun.jvm.hotspot.utilities.Assert

@ExtendWith(SpringExtension::class)
class AppUserTest(
    @Mock private val appUserRepository:AppUserRepository,
    @InjectMocks val appUserService: AppUserService

) {
    @BeforeAll
    fun setup()
    {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testUserCreationMethod()
    {
       // val repo:AppUserRepository = Mockito.
        val appUserService:AppUserService = Mockito.mock(AppUserService::class.java)
        //appUserService.createUser(User)
        appUserService.createUser(JSONObject("{\n" +
                "\t\"userName\":\"rohan.d\",\n" +
                "\t\"firstName\":\"rohan\",\n" +
                "\t\"lastName\":\"D\",\n" +
                "\t\"email\":\"rohan.d@email.com\",\n" +
                "\t\"contact\":\"90909009\"\n" +
                "}\n"))


    }

    @Test
    fun findUserByIdTest()
    {
        val appUser:AppUser = appUserRepository.findById(5).get()
        Assertions.assertEquals(appUser.id,5)
    }
}