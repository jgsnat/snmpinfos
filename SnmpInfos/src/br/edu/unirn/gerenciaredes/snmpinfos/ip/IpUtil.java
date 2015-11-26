package br.edu.unirn.gerenciaredes.snmpinfos.ip;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

public class IpUtil {

    public static boolean temConexao(IPAddress ip) {
        try {
            return InetAddress.getByName(ip.toString()).isReachable(2000);
        } catch (UnknownHostException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
    }
    public static String getIpLocal() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static String getMascaraRedeLocal() throws UnknownHostException, SocketException {
        NetworkInterface networkInterface;
        InetAddress localhost = InetAddress.getLocalHost();
        networkInterface = NetworkInterface.getByInetAddress(localhost);
        List<InterfaceAddress> addresses = networkInterface.getInterfaceAddresses();
        for (InterfaceAddress add : addresses) {
            InetAddress inetAddress = add.getBroadcast();
            if (inetAddress instanceof Inet4Address) {
                return fromNetworkPrefixLength(add.getNetworkPrefixLength()).getCanonicalHostName();
            }
        }

        return null;
    }

    public static String getIpRedeLocal() throws UnknownHostException, SocketException {
        return NetworkUtils.getBaseNetworkIP(new IPAddress(IpUtil.getIpLocal()), new Netmask(IpUtil.getMascaraRedeLocal())).toString();
    }

    public static String getIpBroadcast() throws UnknownHostException, SocketException {
        return NetworkUtils.getBroadcastIP(new IPAddress(IpUtil.getIpLocal()), new Netmask(IpUtil.getMascaraRedeLocal())).toString();
    }

    public static List<IPAddress> getIpsRedeLocal() throws UnknownHostException, SocketException {
        return NetworkUtils.listAvailableIPAddresses(new IPAddress(IpUtil.getIpLocal()), new Netmask(IpUtil.getMascaraRedeLocal()));
    }

    private static InetAddress fromNetworkPrefixLength(short npl) {
        byte[] bytes = new byte[4];
        int n = npl > 0 ? (0x80000000 >> (npl - 1)) : 0;
        bytes[0] = (byte) ((n >>> 24) & 0xFF);
        bytes[1] = (byte) ((n >>> 16) & 0xFF);
        bytes[2] = (byte) ((n >>> 8) & 0xFF);
        bytes[3] = (byte) (n & 0xFF);
        try {
            return InetAddress.getByAddress(bytes);
        } catch (java.net.UnknownHostException ex) {
            return null; /* não deve entrar aqui*/
        }
    }
}
