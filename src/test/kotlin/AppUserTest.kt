import com.app.splitbillapp.repo.AppUserRepository
import com.app.splitbillapp.service.AppUserService
//import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mock
import org.mockito.InjectMocks
import org.junit.jupiter.api.BeforeAll
import org.mockito.MockitoAnnotations

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


    }
}