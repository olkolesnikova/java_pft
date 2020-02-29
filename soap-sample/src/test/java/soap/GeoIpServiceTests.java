package soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

    @Test

    public void testMyIp() {

        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("46.163.153.17");
        System.out.println(ipLocation);

    }
}
