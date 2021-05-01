package com.example.demo.AccessPoint;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessPointTests {

    @Test
    void given_a_geolocation_for_the_access_point_XY_should_be_correct() {
        AccessPoint accessPoint = new AccessPoint();
        accessPoint.setPosition(new Position(37.993741495845455, 23.732428854348367));
        double x = accessPoint.getPosition().getPositionX();
        double y = accessPoint.getPosition().getPositionY();
        assertEquals(0.1, Math.round(y * 100.0)/ 100.0 );
        assertEquals(40.02, Math.round(x * 100.0)/ 100.0 );
    }

}