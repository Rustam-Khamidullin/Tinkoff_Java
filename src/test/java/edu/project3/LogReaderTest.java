package edu.project3;

import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LogReaderTest {

    @Test
    void testLogReaderEmpty() {
        String localPath = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        OffsetDateTime fromDate = OffsetDateTime.parse("2022-01-01T00:00:00Z");
        OffsetDateTime toDate = OffsetDateTime.parse("2023-01-01T00:00:00Z");

        List<NginxLog> logs = LogReader.readLogs(localPath, fromDate, toDate);

        assertNotNull(logs);
        assertTrue(logs.isEmpty());
    }

    @Test
    void testLogReader17th() {
        String url = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        OffsetDateTime fromDate = OffsetDateTime.parse("2015-05-17T00:00:00Z");
        OffsetDateTime toDate = OffsetDateTime.parse("2015-05-18T00:00:00Z");

        List<NginxLog> logs = LogReader.readLogs(url, fromDate, toDate);

        assertNotNull(logs);
        assertEquals(1966, logs.size());
    }
}
