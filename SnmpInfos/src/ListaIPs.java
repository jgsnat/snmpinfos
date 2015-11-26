
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

class ListaIPs {

    /*public static void main(String[] args) throws UnknownHostException, SocketException {
        InetAddress end = InetAddress.getLocalHost();
        String host = end.getHostName();
        String endereco = end.getHostAddress();

        /*Enumeration<NetworkInterface> nets
                = NetworkInterface.getNetworkInterfaces();
        /*for (NetworkInterface netint : Collections.list(nets)) {
            displayInterfaceInformation(netint);
        }*-/
        
        NetworkInterface networkInterface = null;  
        InetAddress localhost = InetAddress.getLocalHost();  
        networkInterface = NetworkInterface.getByInetAddress(localhost);  
        List<InterfaceAddress> addresses = networkInterface.getInterfaceAddresses();  
        for (InterfaceAddress add :  addresses) {  
            InetAddress inetAddress = add.getBroadcast();  
            if (inetAddress instanceof Inet4Address) {  
                Inet4Address inet4Address = (Inet4Address)add.getBroadcast();  
                System.out.println(inet4Address.getCanonicalHostName());  
            }  
        } 

        String ipStr = endereco;// "192.168.1.208";  
        String maskStr = "255.255.255.0";
/*
        IPAddress ipAddress = new IPAddress(ipStr);
        Netmask netmask = new Netmask(maskStr);

        System.out.println("IP fornecido : " + ipAddress.toString() + "/" + netmask.toString());
        System.out.println("IP da Rede   : " + NetworkUtils.getBaseNetworkIP(ipAddress, netmask));
        System.out.println("IP Broadcast : " + NetworkUtils.getBroadcastIP(ipAddress, netmask));*-/

        System.out.println("Listando todos os IPs validos da rede: ");
        /*for (IPAddress ipp : NetworkUtils.listAvailableIPAddresses(ipAddress, netmask)) {  
         System.out.println("- " + ipp);  
         }  *-/
    }

    private static void displayInterfaceInformation(
            NetworkInterface netint) throws SocketException {
        System.out.printf(
                "Display name: %s%n", netint.getDisplayName());
        System.out.printf("Name: %s%n", netint.getName());
        Enumeration<InetAddress> inetAddresses
                = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(
                inetAddresses)) {
            System.out.printf("InetAddress: %s%n", inetAddress);
        }
        System.out.printf("%n");
    }*/
}
