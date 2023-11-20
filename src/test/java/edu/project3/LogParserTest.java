package edu.project3;

import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class LogParserTest {
    @Test
    void testParseLogRecordValidLog() {
        String log =
            "127.0.0.1 - user [12/Oct/2022:10:30:45 +0300] \"GET /index.html HTTP/1.1\" 200 1024 \"referer\" \"user-agent\"";
        NginxLog nginxLog = LogParser.parseLogRecord(log);

        assertNotNull(nginxLog);
        assertEquals("127.0.0.1", nginxLog.remoteAddr());
        assertEquals("user", nginxLog.remoteUser());
        assertEquals(OffsetDateTime.parse("2022-10-12T10:30:45+03:00"), nginxLog.timestamp());
        assertEquals("GET", nginxLog.requestType());
        assertEquals("/index.html", nginxLog.requestResource());
        assertEquals("HTTP/1.1", nginxLog.requestProtocol());
        assertEquals(200, nginxLog.status());
        assertEquals(1024, nginxLog.bodyBytesSent());
        assertEquals("referer", nginxLog.httpReferer());
        assertEquals("user-agent", nginxLog.httpUserAgent());
    }

    @Test
    void testParseLogRecordInvalidLog1() {
        String log =
            "127.0.0.1 - user [12/Oct/2022:10:30:45 +0300] \"GET /index.html HTTP/1.1\" 200 \"referer\" \"user-agent\"";
        NginxLog nginxLog = LogParser.parseLogRecord(log);

        assertNull(nginxLog);
    }

    @Test
    void testParseLogRecordInvalidLog2() {
        String invalidLogLine = "Invalid log line";
        NginxLog nginxLog = LogParser.parseLogRecord(invalidLogLine);

        assertNull(nginxLog);
    }
}
