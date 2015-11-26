package br.edu.unirn.gerenciaredes.snmpinfos.ip;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilitários para cálculos envolvendo endereços de rede.
 *
 * @author Rodrigo
 */
public class NetworkUtils {

    static IPAddress getBaseNetworkIP(IPAddress ipAddress, Netmask netmask) {
        OctetSet ipRedeOctets = ipAddress.getOctets();
        ipRedeOctets.and(netmask.getOctets());
        return new IPAddress(ipRedeOctets);
    }

    /**
     * Retorna o IP de broadcast para a rede
     *
     * @param ipAddress
     * @param netmask
     * @return
     */
    static IPAddress getBroadcastIP(IPAddress ipAddress, Netmask netmask) {
        OctetSet ipBroadcastOctets = ipAddress.getOctets();

        OctetSet flippedMask = netmask.getOctets();
        flippedMask.flipAll();

        ipBroadcastOctets.or(flippedMask);

        return new IPAddress(ipBroadcastOctets);
    }

    /**
     * Lista os enderecos IP validos na mesma rede do endereco informado
     *
     * @param ipAddress
     * @param netmask
     * @return
     */
    static List<IPAddress> listAvailableIPAddresses(IPAddress ipAddress, Netmask netmask) {
        // Range vai do IP da rede até o IP de broadcast  
        OctetSet ip = getBaseNetworkIP(ipAddress, netmask).getOctets();
        OctetSet limit = getBroadcastIP(ipAddress, netmask).getOctets();
        
        //System.out.println(limit.format());
        //System.out.println(ip.format());

        List<IPAddress> ips = new ArrayList<IPAddress>();
        while (true) {
            ip.addOne();
            if (ip.equals(limit)) {
                break;
            }
            ips.add(new IPAddress(new OctetSet(ip)));
        }

        return ips;
    }
}
