package HpaDependables;

import java.nio.file.Paths;

import com.microsoft.playwright.Page;

public class TestUtils {
	
	public static void ApiListener(Page page) {
		
		page.onResponse(response ->{
			int status = response.status();
			
			if(status>=400) {
				throw new AssertionError("API Failed:  "+ response.url()+ "-->"+ status+ response.statusText());
			}
			
//			if(status>=300 && status<400) {
//				System.out.println("Redirection detetced: "+ response.url()+ "--> "+ status);
//			}
			
		});
		
//		page.onRequestFailed(request ->{
//			throw new AssertionError("Network Failed: " + request.url()+"--> "+ request.failure());
//		});
	}
	
	public static void takeScreenshot(Page page, String FileName) {
		try {
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Screenshots/"+ FileName +".png")).setFullPage(true));
		}
		catch(Exception e) {
			System.out.println("Failed to capture Screenshot" + e.getMessage());
		}
	}

}
