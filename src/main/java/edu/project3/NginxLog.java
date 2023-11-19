package edu.project3;

import java.time.OffsetDateTime;

public record NginxLog(String remoteAddr, String remoteUser,
                       OffsetDateTime timestamp, String requestType,
                       String requestResource, String requestProtocol,
                       int status, long bodyBytesSent, String httpReferer,
                       String httpUserAgent) {
}
