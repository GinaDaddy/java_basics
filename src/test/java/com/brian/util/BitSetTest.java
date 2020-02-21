package com.brian.util;

import java.util.BitSet;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/*
https://www.tutorialspoint.com/java/java_bitset_class.htm
The BitSet class creates a special type of array that holds bit values. The BitSet array can increase in size as needed.
 */
public class BitSetTest {

    @Test
    public void testBitSet() {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        // set some bits
        for (int i = 0; i < 16; i++) {
            if ((i % 2) == 0) bits1.set(i);
            if ((i % 5) != 0) bits2.set(i);
        }

        assertThat(bits1).extracting(i -> String.valueOf(i)).isEqualTo("{0, 2, 4, 6, 8, 10, 12, 14}");
        assertThat(bits2).extracting(i -> String.valueOf(i)).isEqualTo("{1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14}");

        bits2.and(bits1);
        assertThat(bits2).extracting(i -> String.valueOf(i)).isEqualTo("{2, 4, 6, 8, 12, 14}");

        bits2.or(bits1);
        assertThat(bits2).extracting(i -> String.valueOf(i)).isEqualTo("{0, 2, 4, 6, 8, 10, 12, 14}");

        bits2.xor(bits1);
        assertThat(bits2).extracting(i -> String.valueOf(i)).isEqualTo("{}");
    }
}
