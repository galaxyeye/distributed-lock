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
package ai.platon.scent.common.lock.example.trigger

import org.h2.api.Trigger
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.SQLException
import java.util.*

class LoggingDatabaseTrigger : Trigger {
    private val log = LoggerFactory.getLogger(LoggingDatabaseTrigger::class.java)

    @Throws(SQLException::class)
    override fun init(connection: Connection, s: String, s1: String, s2: String, b: Boolean, i: Int) {
    }

    @Throws(SQLException::class)
    override fun fire(connection: Connection, objects: Array<Any>, objects1: Array<Any>) {
        log.info("Inserting new lock table entry: {}", Arrays.toString(objects1))
    }

    @Throws(SQLException::class)
    override fun close() {
    }

    @Throws(SQLException::class)
    override fun remove() {
    }
}
