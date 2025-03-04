package in.redBus;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {

	int i = 0; 
	int j = 1;
	public boolean retry(ITestResult result) {
		if(i<j) {
			i++;
			System.out.println("Failed Test case : "+result.getName());
		return true;
		}
		return false;
	}

}
