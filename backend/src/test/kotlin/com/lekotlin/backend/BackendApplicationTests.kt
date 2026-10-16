
package com.lekotlin.backend

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    properties = [
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "jwt.secret=VGhpc0lzQVRlc3RTZWNyZXRLZXlGb3JUZXN0aW5nMTIzNDU2Nzg="
    ]
)
class BackendApplicationTests {

    @Test
    fun contextLoads() {
    }
}
