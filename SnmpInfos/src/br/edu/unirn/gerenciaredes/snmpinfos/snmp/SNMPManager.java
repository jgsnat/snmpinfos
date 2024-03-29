package br.edu.unirn.gerenciaredes.snmpinfos.snmp;

import java.io.IOException;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SNMPManager {

    private String comunidade;
    private String address;
    Snmp snmp = null;
    //String address = null;

    /**
     * Constructor
     *
     * @param comunidade
     */
    public SNMPManager(String comunidade) {
        this.comunidade = comunidade;        
    }
    
    public String getPorOID(String oid, String ip) throws IOException {
        this.address = "udp:" + ip + "/161";
        try {
            return this.getAsString(new OID(oid));//"1.3.6.1.2.1.1.5.0"
        } catch (Exception ex) {
            ex.printStackTrace();
            /*try {                
                return this.getAsString(new OID(oid + ".0"));//"1.3.6.1.2.1.1.5.0"
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }*/
        }
        return "";
    }

    /*public static void main(String[] args) throws IOException {
        /**
         * Port 161 is used for Read and Other operations Port 162 is used for
         * the trap generation
         *-/
    //snmpget -v 1 -c demopublic test.net-snmp.org system.sysUpTime.0
        SNMPManager client = new SNMPManager("udp:test.net-snmp.org/161");
        client.start();
        /**
         * OID - .1.3.6.1.2.1.1.1.0 => SysDec OID - .1.3.6.1.2.1.1.5.0 =>
         * SysName => MIB explorer will be usefull hermi, e, as discussed in
         * previous article
         *-/
//String sysDescr = client.getAsString(new OID(".1.3.6.1.2.1.1.3.0"));
        String sysDescr = client.getAsString(new OID("1.3.6.1.2.1.1.5.0"));
        System.out.println(sysDescr);
    }*/

    /**
     * Start the Snmp session. If you forget the listen() method you will not
     * get any answers because the communication is asynchronous and the
     * listen() method listens for answers.
     *
     * @throws IOException
     */
    public void start() throws IOException {
        TransportMapping transport = new DefaultUdpTransportMapping();
        this.snmp = null;
        this.snmp = new Snmp(transport);
        // Do not forget this line!
        transport.listen();
    }

    /**
     * Method which takes a single OID and returns the response from the agent
     * as a String.
     *
     * @param oid
     * @return
     * @throws IOException
     */
    public String getAsString(OID oid) throws IOException {
        ResponseEvent event = get(new OID[]{oid});
        return event.getResponse().get(0).getVariable().toString();
    }

    /**
     * This method is capable of handling multiple OIDs
     *
     * @param oids
     * @return
     * @throws IOException
     */
    public ResponseEvent get(OID oids[] ) throws IOException {
        PDU pdu = new PDU();
        for (OID oid : oids) {
            pdu.add(new VariableBinding(oid));
        }
        pdu.setType(PDU.GET);
        ResponseEvent event = snmp.send(pdu, getTarget(), null);
        if (event != null) {
            return event;
        }
        throw new RuntimeException("GET timed out");
    }

    /**
     * This method returns a Target, which contains information about where the
     * data should be fetched and how.
     *
     * @return
     */
    private Target getTarget() {
        Address targetAddress =  GenericAddress.parse(this.address);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(this.comunidade));
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version2c);
        return target;
    }
}
