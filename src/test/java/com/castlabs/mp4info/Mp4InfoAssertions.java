package com.castlabs.mp4info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class Mp4InfoAssertions {

    public static void assertMp4BoxStructure(List<Mp4Box> actual, List<Mp4Box> expected) {
        // Check the number of Mp4Box elements in the list
        assertEquals(expected.size(), actual.size());

        // Iterate through each Mp4Box and assert its properties
        for (int i = 0; i < expected.size(); i++) {
            Mp4Box expectedBox = expected.get(i);
            Mp4Box actualBox = actual.get(i);

            assertEquals(expectedBox.type, actualBox.type);
            assertEquals(expectedBox.size, actualBox.size);

            List<Mp4Box> expectedSubBoxes = expectedBox.subBoxes;
            List<Mp4Box> actualSubBoxes = actualBox.subBoxes;

            // Recursively check the sub-boxes if they exist
            if (expectedSubBoxes != null) {
                assertMp4BoxStructure(actualSubBoxes, expectedSubBoxes);
            }
        }
    }
}
