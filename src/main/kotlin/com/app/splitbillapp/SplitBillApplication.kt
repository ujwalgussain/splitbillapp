package com.app.splitbillapp
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class SplitBillApplication {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(SplitBillApplication::class.java)
        }
    }
}