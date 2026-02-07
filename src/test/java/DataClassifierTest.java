import com.dariaert.classifier.DataClassifier;
import com.dariaert.classifier.DataType;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;


public class DataClassifierTest {

    @Test
    public void testClassify() {
        DataClassifier classifier = new DataClassifier();

        assertEquals(DataType.INTEGER, classifier.classify("42"));
        assertEquals(DataType.INTEGER, classifier.classify("0"));
        assertEquals(DataType.INTEGER, classifier.classify("1234567890123456789"));

        assertEquals(DataType.FLOAT, classifier.classify("3.14"));
        assertEquals(DataType.FLOAT, classifier.classify("-0.001"));
        assertEquals(DataType.FLOAT, classifier.classify("1.528535047E-25"));

        assertEquals(DataType.STRING, classifier.classify("Hello World"));
        assertEquals(DataType.STRING, classifier.classify("Lorem Ipsum"));
        assertEquals(DataType.STRING, classifier.classify("123abc"));
    }

}
