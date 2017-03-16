package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by brian on 15/03/17.
 */
public class SkidTest {

    private Skid skid;
    private int numOfSkids = 4;

    @Before
    public void setUp() throws Exception {
        ArrayList<Double> skidList = new ArrayList<>(Arrays.asList(60.0, 50.0, 40.0, 30.0));
        skid = new Skid(numOfSkids, skidList);
    }

    @Test
    public void setAverageSkidDistance() throws Exception {

        assertThat(45, is(skid.getAverageSkidDistance()));

    }

}