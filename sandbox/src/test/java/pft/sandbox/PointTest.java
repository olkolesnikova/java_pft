package pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test

    public void testLength() {

        Point p1 = new Point(1, 4);
        Point p2 = new Point(4, 8);
        Assert.assertEquals(p1.distance(p2), 6.0);
    }

}
