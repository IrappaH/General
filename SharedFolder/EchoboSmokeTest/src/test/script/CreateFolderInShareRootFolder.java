package test.script;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cocoro.lib.uiauto.DriverFactory;
import cocoro.testng.listener.PriorityInClass;
import pages.HomePage;
import pages.LoginPage;
import pages.PersonalFolderDelete;
import pages.SharedFolder;
import pages.SharedFolderCreation;
import pages.SharedFolderDelete;
import tests.InitialTestSetup;

public class CreateFolderInShareRootFolder extends InitialTestSetup {

	private SharedFolder sharedFolder = null;
	private SharedFolderCreation sharedFolderCreation = null;
	private HomePage homePage = null;
	
	@BeforeClass(alwaysRun = true)
	public void login(ITestContext context) throws Exception {
		setupClassCommonSteps(context);
		this.config.ReadConfig(context);

		setDriver(new DriverFactory().createDriver(this.config).getDriver());
		LoginPage loginPage = goToLoginPage();
		loginPage.userNameTxtbox().sendKeys("test5.cocoro@gmail.com");
		loginPage.passwordTxtbox().sendKeys("Demo88Demo");
		homePage = loginPage.login();
		Thread.sleep(5000);

	}

	@BeforeMethod(alwaysRun = true)
	public void verifyFolderIsEmpty() throws Exception {
		sharedFolder = homePage.gotoSharedFolder();
		Thread.sleep(2000);
		System.out.println("This is NoData from App: " + sharedFolder.uiControl("noData").getText());
		System.out.println("This is NoData from Expected: " + getLocalizedMessage("noFilesInTable").trim());
		if (sharedFolder.uiControl("noData").getText().trim().equals(getLocalizedMessage("noFilesInTable").trim())) {
			System.out.println("No folders are found..!");
		
		} else {
			sharedFolder.selectAllCheckBox().click();
			SharedFolderDelete deleteFolder = sharedFolder.gotoSharedFolderDelete();
			deleteFolder.okBtn().click();
			Thread.sleep(5000);
			assertEquals(sharedFolder.uiControl("noData").getText(), getLocalizedMessage("noFilesInTable").trim());
		}
	}

	@AfterMethod(alwaysRun = true)
	public void deleteAllFolders() throws Exception {
		// System.out.println("This is Expected result:
		// "+getLocalizedMessage("noFilesInTable").trim());
		if ((sharedFolder.uiControl("noData").getText().trim()).equals(getLocalizedMessage("noFilesInTable").trim())) {
			System.out.println("After Method: No data");
			//this.getDriver().navigate().refresh();
			sharedFolder.uiControl("homeLink").click();
			Thread.sleep(2000);
		} else {
			sharedFolder.selectAllCheckBox().click();
			SharedFolderDelete deleteFolder = sharedFolder.gotoSharedFolderDelete();
			deleteFolder.okBtn().click();
			Thread.sleep(2000);
			// assertEquals(personalFolder.uiControl("noData").getText(),
			// getLocalizedMessage("noData"));
			//this.getDriver().navigate().refresh();
			sharedFolder.uiControl("homeLink").click();
			Thread.sleep(2000);
		}
	}
	
	  @Test(groups = { "CreateFolder" }, alwaysRun = true)
	  
	  @PriorityInClass(1) public void verifyFolderIsCreated() throws Exception {
	  setTestDesc(
	  "Verify that Admin can create folder in the root folder.Folder should be create in the root folder"
	  ); sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  assertEquals(sharedFolder.firstRowName().getText(), "AutoTest");
	  
	  }
	 
	
	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(2)
	public void verifySpecialCharFolderIsCreated() throws Exception {
		setTestDesc(
				"Verify that Admin can create folder in the root folder.Folder should be create in the root folder");		
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("#@$");
		assertEquals(sharedFolder.firstRowName().getText(), "#@$");
		
	}
	
	
/*	
	
	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(3)
	public void verify84LengthEngCharFolderIsCreatedSingleByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length English 84 characters of single byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("");
		assertTrue(sharedFolder.checkFolder("nameList", ""));
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(4)
	public void verify84LengthEngCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length English 84 characters of multi byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("");
		assertTrue(sharedFolder.checkFolder("nameList", ""));
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(5)
	public void verify84LengthKatakanaCharFolderIsCreatedSingleByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Katakana 84 characters of single byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("");
		assertTrue(sharedFolder.checkFolder("nameList", ""));
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(6)
	public void verify84LengthKatakanaCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Katakana 84 characters of multi byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("");
		assertTrue(sharedFolder.checkFolder("nameList", ""));
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(7)
	public void verify84LengthHiraganaCharFolderIsCreatedSingleByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Hiragana 84 characters of single byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("");
		assertTrue(sharedFolder.checkFolder("nameList", ""));
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(8)
	public void verify84LengthHiraganaCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Hiragana 84 characters of multi byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("");
		assertTrue(sharedFolder.checkFolder("nameList", ""));
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(9)
	public void verify84LengthKanjiCharFolderIsCreatedSingleByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Kanji 84 characters of single byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("");
		assertTrue(sharedFolder.checkFolder("nameList", ""));
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(10)
	public void verify84LengthKanjiCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Kanji 84 characters of multi byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("");
		assertTrue(sharedFolder.checkFolder("nameList", ""));
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(11)
	public void verifyErrorMsgIsDisplayedForBackSlash() throws Exception {
		setTestDesc(
				"Verify that below all unsupported characters to create folder in root folder '\' (half-width backslash).Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(12)
	public void verifyErrorMsgIsDisplayedForFontSlash() throws Exception {
		setTestDesc("'/'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("/");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(13)
	public void verifyErrorMsgIsDisplayedForColon() throws Exception {
		setTestDesc("':'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(":");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(14)
	public void verifyErrorMsgIsDisplayedForMultiplication() throws Exception {
		setTestDesc("'*'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(":");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(15)
	public void verifyErrorMsgIsDisplayedForQuestionMark() throws Exception {
		setTestDesc("'?'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("?");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(16)
	public void verifyErrorMsgIsDisplayedForDoubleQuotes() throws Exception {
		setTestDesc("'DoubleQuotes'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(17)
	public void verifyErrorMsgIsDisplayedForLessThan() throws Exception {
		setTestDesc("'LessThan'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("<");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(18)
	public void verifyErrorMsgIsDisplayedForGreaterThan() throws Exception {
		setTestDesc("'GreaterThan'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(">");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(19)
	public void verifyErrorMsgIsDisplayedForPipe() throws Exception {
		setTestDesc("'|'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("|");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(20)
	public void verifyErrorMsgIsDisplayedForSmallCharStartingDotUnderScore() throws Exception {
		setTestDesc("'.remote_access_backup'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(21)
	public void verifyErrorMsgIsDisplayedForUpperCharStartingDotUnderScore() throws Exception {
		setTestDesc("'.REMOTE_ACCESS_BACKUP'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(22)
	public void verifyErrorMsgIsDisplayedForSmallChar() throws Exception {
		setTestDesc("'trashbox'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(23)
	public void verifyErrorMsgIsDisplayedForCamelcaseChar() throws Exception {
		setTestDesc("'trashBox'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(24)
	public void verifyErrorMsgIsDisplayedForUpperChar() throws Exception {
		setTestDesc("'TRASHBOX'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(25)
	public void verifyErrorMsgIsDisplayedForSmallCharStartingDot() throws Exception {
		setTestDesc("'.snapshot'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(26)
	public void verifyErrorMsgIsDisplayedForUpperCharStartingDot() throws Exception {
		setTestDesc("'.SNAPSHOT'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(27)
	public void verifyErrorMsgIsDisplayedForDotsnapshots() throws Exception {
		setTestDesc("'.snapshots'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(28)
	public void verifyErrorMsgIsDisplayedForDotSNAPSHOTS() throws Exception {
		setTestDesc("'.SNAPSHOTS'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(29)
	public void verifyErrorMsgIsDisplayedForSnapshot() throws Exception {
		setTestDesc("'.Snapshot'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(30)
	public void verifyErrorMsgIsDisplayedForCON() throws Exception {
		setTestDesc("'CON'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(31)
	public void verifyErrorMsgIsDisplayedForPRN() throws Exception {
		setTestDesc("'PRN'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(32)
	public void verifyErrorMsgIsDisplayedForAUX() throws Exception {
		setTestDesc("'AUX'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(33)
	public void verifyErrorMsgIsDisplayedForNUL() throws Exception {
		setTestDesc("'NUL'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(34)
	public void verifyErrorMsgIsDisplayedForCLOCK$() throws Exception {
		setTestDesc("'CLOCK$'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(35)
	public void verifyErrorMsgIsDisplayedForCOM0() throws Exception {
		setTestDesc("'COM0'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(36)
	public void verifyErrorMsgIsDisplayedForCOM1() throws Exception {
		setTestDesc("'COM1'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(37)
	public void verifyErrorMsgIsDisplayedForCOM2() throws Exception {
		setTestDesc("'COM2'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(38)
	public void verifyErrorMsgIsDisplayedForCOM3() throws Exception {
		setTestDesc("'COM3'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(39)
	public void verifyErrorMsgIsDisplayedForCOM4() throws Exception {
		setTestDesc("'COM4'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(40)
	public void verifyErrorMsgIsDisplayedForCOM5() throws Exception {
		setTestDesc("'COM5'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(41)
	public void verifyErrorMsgIsDisplayedForCOM6() throws Exception {
		setTestDesc("'COM6'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(42)
	public void verifyErrorMsgIsDisplayedForCOM7() throws Exception {
		setTestDesc("'COM7'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(43)
	public void verifyErrorMsgIsDisplayedForCOM8() throws Exception {
		setTestDesc("'COM8'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(44)
	public void verifyErrorMsgIsDisplayedForCOM9() throws Exception {
		setTestDesc("'COM9'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(45)
	public void verifyErrorMsgIsDisplayedForLPT0() throws Exception {
		setTestDesc("'LPT0'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(46)
	public void verifyErrorMsgIsDisplayedForLPT1() throws Exception {
		setTestDesc("'LPT1'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(47)
	public void verifyErrorMsgIsDisplayedForLPT2() throws Exception {
		setTestDesc("'LPT2'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(48)
	public void verifyErrorMsgIsDisplayedForLPT3() throws Exception {
		setTestDesc("'LPT3'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(49)
	public void verifyErrorMsgIsDisplayedForLPT4() throws Exception {
		setTestDesc("'LPT4'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(50)
	public void verifyErrorMsgIsDisplayedForLPT5() throws Exception {
		setTestDesc("'LPT5'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(51)
	public void verifyErrorMsgIsDisplayedForLPT6() throws Exception {
		setTestDesc("'LPT6'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(52)
	public void verifyErrorMsgIsDisplayedForLPT7() throws Exception {
		setTestDesc("'LPT7'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(53)
	public void verifyErrorMsgIsDisplayedForLPT8() throws Exception {
		setTestDesc("'LPT8'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(54)
	public void verifyErrorMsgIsDisplayedForLPT9() throws Exception {
		setTestDesc("'LPT9'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(55)
	public void verifyErrorMsgIsDisplayedForcon() throws Exception {
		setTestDesc("'con'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(56)
	public void verifyErrorMsgIsDisplayedForprn() throws Exception {
		setTestDesc("'prn'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(57)
	public void verifyErrorMsgIsDisplayedForaux() throws Exception {
		setTestDesc("'aux'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(58)
	public void verifyErrorMsgIsDisplayedFornul() throws Exception {
		setTestDesc("'nul'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(59)
	public void verifyErrorMsgIsDisplayedForclock$() throws Exception {
		setTestDesc("'clock$'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(60)
	public void verifyErrorMsgIsDisplayedForcom0() throws Exception {
		setTestDesc("'com0'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(61)
	public void verifyErrorMsgIsDisplayedForcom1() throws Exception {
		setTestDesc("'com1'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(62)
	public void verifyErrorMsgIsDisplayedForcom2() throws Exception {
		setTestDesc("'com2'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(63)
	public void verifyErrorMsgIsDisplayedForcom3() throws Exception {
		setTestDesc("'com3'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(64)
	public void verifyErrorMsgIsDisplayedForcom4() throws Exception {
		setTestDesc("'com4'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(65)
	public void verifyErrorMsgIsDisplayedForcom5() throws Exception {
		setTestDesc("'com5'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(66)
	public void verifyErrorMsgIsDisplayedForcom6() throws Exception {
		setTestDesc("'com6'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(67)
	public void verifyErrorMsgIsDisplayedForcom7() throws Exception {
		setTestDesc("'com7'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(68)
	public void verifyErrorMsgIsDisplayedForcom8() throws Exception {
		setTestDesc("'com8'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(69)
	public void verifyErrorMsgIsDisplayedForcom9() throws Exception {
		setTestDesc("'com9'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(70)
	public void verifyErrorMsgIsDisplayedForlpt0() throws Exception {
		setTestDesc("'lpt0'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(71)
	public void verifyErrorMsgIsDisplayedForlpt1() throws Exception {
		setTestDesc("'lpt1'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(72)
	public void verifyErrorMsgIsDisplayedForlpt2() throws Exception {
		setTestDesc("'lpt2'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(73)
	public void verifyErrorMsgIsDisplayedForlpt3() throws Exception {
		setTestDesc("'lpt3'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(74)
	public void verifyErrorMsgIsDisplayedForlpt4() throws Exception {
		setTestDesc("'lpt4'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(75)
	public void verifyErrorMsgIsDisplayedForlpt5() throws Exception {
		setTestDesc("'lpt5'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(76)
	public void verifyErrorMsgIsDisplayedForlpt6() throws Exception {
		setTestDesc("'lpt6'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(77)
	public void verifyErrorMsgIsDisplayedForlpt7() throws Exception {
		setTestDesc("'lpt7'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(78)
	public void verifyErrorMsgIsDisplayedForlpt8() throws Exception {
		setTestDesc("'lpt8'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(79)
	public void verifyErrorMsgIsDisplayedForlpt9() throws Exception {
		setTestDesc("'lpt9'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(80)
	public void verifyErrorMsgIsDisplayedForNameEndindWithDot() throws Exception {
		setTestDesc("'Name ending with '.'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(81)
	public void verifyErrorMsgIsDisplayedForNameEndingWithHalfWidthSpace() throws Exception {
		setTestDesc("'Name ending with half-width space'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(82)
	public void verifyErrorMsgIsDisplayedForNameStartingWithHalfWidthSpace() throws Exception {
		setTestDesc("'lpt2'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}
*/
}