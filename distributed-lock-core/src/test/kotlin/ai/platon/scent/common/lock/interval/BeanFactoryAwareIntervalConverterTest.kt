/*
 * MIT License
 *
 * Copyright (c) 2020 Alen Turkovic
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package ai.platon.scent.common.lock.interval

import ai.platon.scent.common.lock.Interval
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension

@DirtiesContext
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = ["locked.interval=10"])
class BeanFactoryAwareIntervalConverterTest {
    @Autowired
    private val intervalConverter: IntervalConverter? = null
    @Test
    @ai.platon.scent.common.lock.Interval(value = "10")
    fun shouldResolveStatic() {
        Assertions.assertThat(intervalConverter!!.toMillis(object : Any() {}.javaClass.enclosingMethod.getAnnotation(ai.platon.scent.common.lock.Interval::class.java)))
                .isEqualTo(10)
    }

    @Test
    @ai.platon.scent.common.lock.Interval(value = "\${locked.interval}")
    fun shouldResolveProperty() {
        Assertions.assertThat(intervalConverter!!.toMillis(object : Any() {}.javaClass.enclosingMethod.getAnnotation(ai.platon.scent.common.lock.Interval::class.java)))
                .isEqualTo(10)
    }

    @SpringBootApplication
    open class BeanFactoryAwareIntervalConverterTestApplication {
        @Bean
        open fun intervalConverter(configurableListableBeanFactory: ConfigurableListableBeanFactory?): IntervalConverter {
            return BeanFactoryAwareIntervalConverter(configurableListableBeanFactory!!)
        }
    }
}