package edu.hw6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
public class Task6 {

    private static final String PRINT_FORMAT = "%-10s%-7d%-30s\n";
    static Map<Integer, String> portServicesMap = new HashMap<>();

    static {
        portServicesMap.put(123, "Network Time Protocol (NTP)");
        portServicesMap.put(161, "Simple Network Management Protocol (SNMP)");
        portServicesMap.put(162, "Simple Network Management Protocol (SNMP Trap)");
        portServicesMap.put(389, "Lightweight Directory Access Protocol (LDAP)");
        portServicesMap.put(443, "HTTP Secure (HTTPS)");
        portServicesMap.put(587, "Mail Message Submission (SMTP)");
        portServicesMap.put(135, "EPMAP");
        portServicesMap.put(137, "Служба имен NetBIOS");
        portServicesMap.put(138, "Служба датаграмм NetBIOS");
        portServicesMap.put(139, "Служба сеансов NetBIOS");
        portServicesMap.put(445, "Microsoft-DS Active Directory");
        portServicesMap.put(843, "Adobe Flash");
        portServicesMap.put(1900, "Simple Service Discovery Protocol (SSDP)");
        portServicesMap.put(3702, "Динамическое обнаружение веб-служб");
        portServicesMap.put(5353, "Многоадресный DNS");
        portServicesMap.put(5355, "Link-Local Multicast Name Resolution (LLMNR)");
        portServicesMap.put(17500, "Dropbox");
        portServicesMap.put(27017, "MongoDB");
    }

    private Task6() {
    }

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        checkPorts();
    }

    private static void checkPorts() {
        System.out.println("Протокол  Порт   Сервис");
        for (int port = 0; port < 49152; port++) {
            String protocol;
            String portService = portServicesMap.getOrDefault(port, "");

            if (!isFreeTCP(port)) {
                protocol = "TCP";
                System.out.printf(PRINT_FORMAT, protocol, port, portService);
            }
            if (!isFreeUDP(port)) {
                protocol = "UDP";
                System.out.printf(PRINT_FORMAT, protocol, port, portService);
            }
        }
    }

    private static boolean isFreeTCP(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isFreeUDP(int port) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(port);
            datagramSocket.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
