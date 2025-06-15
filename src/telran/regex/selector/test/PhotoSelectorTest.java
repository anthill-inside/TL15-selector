package telran.regex.selector.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import telran.regex.selector.tools.PhotoSelector;

public class PhotoSelectorTest {

    String[] pictures = {
            "Paris\\20140101_090000.jpg",
            "Paris\\20140201_121005.jpg",
            "Paris\\20150301_211035.jpg",
            "Paris\\20150401_110023.gif",
            "Paris\\20150401_181705.jpg",
            "Paris\\20150501_231035.gif",
            "London\\20140205_090000.jpg",
            "London\\20140205_121005.jpg",
            "London\\20140601_211035.gif",
            "London\\20151001_110023.jpg",
            "London\\20151001_121705.jpg",
            "London\\20151001_231035.jpg",
            "Chicago\\20150301_120001.png",
            "Chicago\\20151111_232000.png"
    };


    @Test
    void TestEuropePictures() {
        String mask = "(Paris|London).*";
        String[] expected = {
                "Paris\\20140101_090000.jpg",
                "Paris\\20140201_121005.jpg",
                "Paris\\20150301_211035.jpg",
                "Paris\\20150401_110023.gif",
                "Paris\\20150401_181705.jpg",
                "Paris\\20150501_231035.gif",
                "London\\20140205_090000.jpg",
                "London\\20140205_121005.jpg",
                "London\\20140601_211035.gif",
                "London\\20151001_110023.jpg",
                "London\\20151001_121705.jpg",
                "London\\20151001_231035.jpg"

        };
        String[] actual = PhotoSelector.select(pictures, mask);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void TestAutumnPictures() {
        String mask = ".*\\\\\\d{4}(09|1[01]).*";
        String[] expected = {
                "London\\20151001_110023.jpg",
                "London\\20151001_121705.jpg",
                "London\\20151001_231035.jpg",
                "Chicago\\20151111_232000.png"};
        String[] actual = PhotoSelector.select(pictures, mask);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void TestSpring2015Pictures() {
        String mask = ".*20150[3-5]\\d{2}.*";
        String[] expected = {
                "Paris\\20150301_211035.jpg",
                "Paris\\20150401_110023.gif",
                "Paris\\20150401_181705.jpg",
                "Paris\\20150501_231035.gif",
                "Chicago\\20150301_120001.png"
        };
        String[] actual = PhotoSelector.select(pictures, mask);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void TestNightPictures() {
        String mask = ".*\\d{8}_(1[8-9]|2[0-3]).*";
        String[] expected = {
                "Paris\\20150301_211035.jpg",
                "Paris\\20150401_181705.jpg",
                "Paris\\20150501_231035.gif",
                "London\\20140601_211035.gif",
                "London\\20151001_231035.jpg",
                "Chicago\\20151111_232000.png"
        };
        String[] actual = PhotoSelector.select(pictures, mask);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void TestNightChicagoPictures() {
        String mask = "Chicago\\\\\\d{8}_(1[8-9]|2[0-3]).*";
        String[] expected = {"Chicago\\20151111_232000.png"};
        String[] actual = PhotoSelector.select(pictures, mask);

        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    void TestPngJpgPictures() {
        String mask = ".*\\.(jpg|png)";
        String[] expected = {
                "Paris\\20140101_090000.jpg",
                "Paris\\20140201_121005.jpg",
                "Paris\\20150301_211035.jpg",
                "Paris\\20150401_181705.jpg",
                "London\\20140205_090000.jpg",
                "London\\20140205_121005.jpg",
                "London\\20151001_110023.jpg",
                "London\\20151001_121705.jpg",
                "London\\20151001_231035.jpg",
                "Chicago\\20150301_120001.png",
                "Chicago\\20151111_232000.png"
        };
        String[] actual = PhotoSelector.select(pictures, mask);
        Assertions.assertArrayEquals(expected, actual);
    }
}
