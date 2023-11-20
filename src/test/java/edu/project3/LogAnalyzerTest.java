package edu.project3;

import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LogAnalyzerTest {

    @Test
    public void testGetLogReportWithEmptyLogs() {
        List<NginxLog> logs = Collections.emptyList();

        assertNull(LogAnalyzer.getLogReport(logs));
    }

    @Test
    public void testGetLogReportWithValidLogs1() {
        List<NginxLog> logs = Arrays.asList(
            new NginxLog("", "", OffsetDateTime.now(), "GET", "resource1", "HTTP/1.1", 200, 100, "", ""),
            new NginxLog("", "", OffsetDateTime.now(), "GET", "resource2", "HTTP/1.1", 304, 200, "", ""),
            new NginxLog("", "", OffsetDateTime.now(), "GET", "resource3", "HTTP/1.1", 404, 300, "", ""),
            new NginxLog("", "", OffsetDateTime.now(), "POST", "resource1", "HTTP/1.1", 404, 200, "", "")
        );

        LogAnalyzer.LogReport report = LogAnalyzer.getLogReport(logs);

        assertNotNull(report);
        assertEquals(2, report.requestResources().get("resource1"));
        assertEquals(1, report.requestResources().get("resource2"));
        assertEquals(1, report.requestResources().get("resource3"));

        assertEquals(2, report.responseCode().get(404));
        assertEquals(1, report.responseCode().get(304));
        assertEquals(1, report.responseCode().get(200));

        assertEquals(2, report.responseCode().get(404));
        assertEquals(1, report.responseCode().get(304));
        assertEquals(1, report.responseCode().get(200));

        assertEquals(4, report.requestProtocol().get("HTTP/1.1"));

        assertEquals(3, report.requestType().get("GET"));
        assertEquals(1, report.requestType().get("POST"));

        assertEquals(4, report.totalRequests());

        assertEquals(200, report.averageBodyBytesSent());
    }
}
