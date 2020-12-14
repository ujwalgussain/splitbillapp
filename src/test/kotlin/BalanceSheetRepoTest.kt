import com.app.splitbillapp.SplitBillApplication
import com.app.splitbillapp.entities.AppUser
import com.app.splitbillapp.entities.BalanceSheet
import com.app.splitbillapp.repo.BalanceSheetRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJpaTest
@ContextConfiguration(classes = [SplitBillApplication::class])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
class BalanceSheetRepoTest(
    @Autowired
    private val entityManager: TestEntityManager,
    @Autowired
     val repo : BalanceSheetRepository
) {
    @BeforeEach
    fun setup()
    {
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun findTotalDueTest()
    {

        val ans:Int = repo.findTotalDue(6).first()
        Assertions.assertEquals(ans,-200)
    }

    @Test
    fun findAllDuesTest()
    {
        //stub
        val expected:Collection<BalanceSheet> = repo.findAllDues(6)
        Assertions.assertEquals(expected.size,2)
        val list:ArrayList<BalanceSheet> = ArrayList(expected)
        Assertions.assertEquals(list.get(0).amount,-100)
        Assertions.assertEquals(list.get(1).amount,-100)
    }
}