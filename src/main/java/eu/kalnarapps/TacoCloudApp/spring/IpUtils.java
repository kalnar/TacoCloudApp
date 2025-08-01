package eu.kalnarapps.TacoCloudApp.spring;

import jakarta.servlet.http.HttpServletRequest;

public class IpUtils {

    public static String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        
        // If the header is null or "unknown", try other headers or getRemoteAddr()
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        
        // If all else fails, use the direct remote address
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        // If X-Forwarded-For contains a comma-separated list, the first IP is the client
        if (ipAddress != null && ipAddress.contains(",")) {
            // Take the first IP
            ipAddress = ipAddress.split(",")[0];
        }

        return ipAddress;
    }
}