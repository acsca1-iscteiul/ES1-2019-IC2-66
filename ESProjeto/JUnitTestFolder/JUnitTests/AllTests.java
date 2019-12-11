package JUnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JUnitAvaliador_ThresholdsTest.class, JUnitComparador_is_long_method_Test.class,
		JUnitComparador_is_long_method_ThresholdsTest.class })
public class AllTests {

}
