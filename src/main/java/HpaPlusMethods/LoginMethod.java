package HpaPlusMethods;

import com.microsoft.playwright.Page;

import HpaDependables.Values;
import io.qameta.allure.Step;

public class LoginMethod {

	private Page page;
	Values value = new Values();

	public LoginMethod(Page page1) {
		this.page = page1;
	}

	// Elements starts here

	private String Username = "#input-username";
	private String Password = "#password-input";
	private String LoginButton = "//button[@type= 'submit']";

	@Step("Perform the Login with the valid Login name and Password")
	public void LoginMethodHpa() {

		page.locator(Username).fill(value.LoginEmail);

		page.locator(Password).fill(value.Loginpassword);

		page.locator(LoginButton).click();

	}
}
