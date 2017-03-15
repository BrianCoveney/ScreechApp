package model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by brian on 15/03/17.
 */
public class BreakingTest {

    private Breaking breaking;
    private Skid skid1, skid2, skid3, skid4;

    @Before
    public void setUp() throws Exception {

        skid1 = new Skid(); skid1.setNumberOfSkids(4);
        skid2 = new Skid(); skid2.setNumberOfSkids(3);
        skid3 = new Skid(); skid3.setNumberOfSkids(2);
        skid4 = new Skid(); skid4.setNumberOfSkids(1);

        breaking = new Breaking();
        breaking.getNumberSkidMarks(skid1);

    }

    @Test
    public void setBreakingEfficiency() throws Exception {

        assertThat(1.0, is(breaking.setBreakingEfficiency(skid1.getNumberOfSkids())));
        assertThat(0.7, is(breaking.setBreakingEfficiency(skid2.getNumberOfSkids())));
        assertThat(0.6, is(breaking.setBreakingEfficiency(skid3.getNumberOfSkids())));
        assertThat(0.3, is(breaking.setBreakingEfficiency(skid4.getNumberOfSkids())));

    }

}