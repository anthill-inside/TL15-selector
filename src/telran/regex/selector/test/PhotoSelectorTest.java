package telran.regex.selector.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import telran.regex.selector.tools.PhotoSelector;

public abstract class PhotoSelectorTest {

    protected String[] pictures = {
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


    protected abstract String[] select(String[] input,String regex);

    @Test
    void TestEuropePictures() {
        String regex = "(Paris|London).*";
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
        String[] actual = select(pictures, regex);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void TestAutumnPictures() {
        String regex = ".*\\\\\\d{4}(09|1[01]).*";
        String[] expected = {
                "London\\20151001_110023.jpg",
                "London\\20151001_121705.jpg",
                "London\\20151001_231035.jpg",
                "Chicago\\20151111_232000.png"};
        String[] actual = select(pictures, regex);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void TestSpring2015Pictures() {
        String regex = ".*20150[3-5]\\d{2}.*";
        String[] expected = {
                "Paris\\20150301_211035.jpg",
                "Paris\\20150401_110023.gif",
                "Paris\\20150401_181705.jpg",
                "Paris\\20150501_231035.gif",
                "Chicago\\20150301_120001.png"
        };
        String[] actual = select(pictures, regex);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void TestNightPictures() {
        String regex = ".*\\d{8}_(1[8-9]|2[0-3]).*";
        String[] expected = {
                "Paris\\20150301_211035.jpg",
                "Paris\\20150401_181705.jpg",
                "Paris\\20150501_231035.gif",
                "London\\20140601_211035.gif",
                "London\\20151001_231035.jpg",
                "Chicago\\20151111_232000.png"
        };
        String[] actual = select(pictures, regex);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void TestNightChicagoPictures() {
        String regex = "Chicago\\\\\\d{8}_(1[8-9]|2[0-3]).*";
        String[] expected = {"Chicago\\20151111_232000.png"};
        String[] actual = select(pictures, regex);

        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    void TestPngJpgPictures() {
        String regex = ".*\\.(jpg|png)";
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
        String[] actual = select(pictures, regex);
        Assertions.assertArrayEquals(expected, actual);
    }
}

class PhotoSelectorSimpleTest extends PhotoSelectorTest {
    @Override
    protected String[] select(String[] pictures, String regex) {
        return PhotoSelector.select(pictures, regex);
    }
}

class PhotoSelectorSBTest extends PhotoSelectorTest {
    @Override
    protected String[] select(String[] pictures, String regex) {
        return PhotoSelector.selectSB(pictures, regex);
    }
}
class PhotoSelectorMatcherTest extends PhotoSelectorTest {
    @Override
    protected String[] select(String[] pictures, String regex) {
        return PhotoSelector.selectMatcher(pictures, regex);
    }
}



