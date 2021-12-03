package org.apache.hadoop.ozone.ha;

import org.apache.hadoop.net.NetUtils;

import java.net.InetSocketAddress;

public class KubernetesUtils {

    /**
     * In kubernetes, check if the input FQDN's host name matches local host name
     * @param addr an address with FQDN
     * @return
     */
    public static boolean isAddressHostNameLocal(final InetSocketAddress addr) {
        if (addr == null) {
            return false;
        }
        final String hostNameWithoutDomain = getHostNameWithoutDomain(addr.getHostName());
        return NetUtils.getLocalHostname().equals(hostNameWithoutDomain);
    }

    public static InetSocketAddress getAddressWithHostName(final InetSocketAddress addr) {
        final String fqdn = addr.getHostName();
        final String hostName = getHostNameWithoutDomain(fqdn);
        return NetUtils.createSocketAddr(hostName, addr.getPort());
    }

    private static String getHostNameWithoutDomain(final String fqdn) {
        return fqdn.split("\\.")[0];
    }
}
