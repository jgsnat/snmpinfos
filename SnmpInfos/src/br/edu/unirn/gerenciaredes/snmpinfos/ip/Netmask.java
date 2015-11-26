package br.edu.unirn.gerenciaredes.snmpinfos.ip;

/**
 * Utilitários para cálculos envolvendo endereços de rede.
 *
 * @author Rodrigo
 */
public class Netmask {

    private OctetSet octets;

    Netmask(String maskString) {
        this.octets = OctetSet.parse(maskString);
    }

    OctetSet getOctets() {
        return new OctetSet(octets);
    }

    @Override
    public String toString() {
        return octets.format();
    }
}
