package br.edu.unirn.gerenciaredes.snmpinfos.ip;

/**
 * Representacao de um endereco IP, baseado em um conjunto de octetos.
 *
 * @author Rodrigo
 */
public class IPAddress {

    private OctetSet octets;

    IPAddress(OctetSet octetSet) {
        this.octets = octetSet;
    }

    public IPAddress(String ipString) {
        this.octets = OctetSet.parse(ipString);
    }

    OctetSet getOctets() {
        return new OctetSet(octets);
    }

    @Override
    public String toString() {
        return octets.format();
    }
}
