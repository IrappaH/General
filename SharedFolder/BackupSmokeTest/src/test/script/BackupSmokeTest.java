package test.script;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
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
import pages.SharedFolder;
import pages.SharedFolderCreation;
import pages.SharedFolderDelete;
import pages.SharedFolderSearch;
import tests.FileUpload;
import tests.InitialTestSetup;

public class BackupSmokeTest extends InitialTestSetup {
	private SharedFolderCreation sharedFolderCreation = null;
	private SharedFolder sharedFolder = null;
	private HomePage homePage = null;
	String projectPath = System.getProperty("user.dir");
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
		sharedFolder = homePage.gotoBackupFolder();
		Thread.sleep(2000);
		
	}

	@AfterMethod(alwaysRun = true)
	public void deleteAllFolders() throws Exception {
		
		 
		 	sharedFolder.uiControl("homeLink").click();
			
			Thread.sleep(2000);
		
	}
	
	/*
	 * @Test(priority = 1,groups = { "Functional" }, enabled = true)
	 * //@PriorityInClass(3) public void verifySingleFileFolderDownload() throws
	 * Exception { setTestDesc(
	 * "Verify that Admin can download sinlge folder/file from the folder.");
	 * 
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.selectAllCheckBox().click(); Thread.sleep(2000);
	 * sharedFolder.downloadBtn().click(); Thread.sleep(6000);
	 * 
	 * }
	 * 
	 * @Test(priority = 2,groups = { "Functional" }, enabled = true)
	 * //@PriorityInClass(3) public void verifySingleFolderDownload() throws
	 * Exception { setTestDesc(
	 * "Verify that Admin can download sinlge file from the root folder.file/folder should be available in the root folder"
	 * );
	 * 
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.selectSinlgeCheckBox().click(); Thread.sleep(2000);
	 * sharedFolder.downloadBtn().click(); Thread.sleep(3000);
	 * 
	 * }
	 * 
	 * @Test(priority = 3,groups = { "Functional" }, enabled = true)
	 * 
	 * @PriorityInClass(3) public void verifyMultiFolderDownload() throws Exception
	 * { setTestDesc(
	 * "Verify that Admin can download multiple file from the root folder.Folder should be available in the root folder"
	 * ); sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.selectAllCheckBox().click(); Thread.sleep(3000);
	 * sharedFolder.downloadBtn().click(); Thread.sleep(8000);
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority = 4,groups = { "Functional" }, enabled = true)
	 * //@PriorityInClass(3) public void verifyFolderSearch() throws Exception {
	 * setTestDesc(
	 * "Verify that Admin can search folder/file from the root folder.Folder should be available in the root folder"
	 * ); String fileName = "Filename.jpg"; sharedFolder.OpenFolder().click();
	 * Thread.sleep(1000); sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000); SharedFolderSearch
	 * gotoSearchSharedFolder = sharedFolder.gotoSearchSharedFolder(fileName);
	 * String searchResultStr = gotoSearchSharedFolder.getSearchResultStr();
	 * Thread.sleep(1000); assertEquals(searchResultStr,"検索結果");
	 * assertEquals(sharedFolder.firstRowName().getText().substring(0, 12),
	 * fileName);
	 * 
	 * }
	 * 
	 * @Test(priority = 5,groups = { "Functional" }, enabled = true)
	 * 
	 * @PriorityInClass(3) public void verifySearchPageReturnButton() throws
	 * Exception { setTestDesc(
	 * "Verify that Admin can search folder/file from the root folder and click on Return button to previous page"
	 * ); String fileName = "Filename.jpg"; sharedFolder.OpenFolder().click();
	 * Thread.sleep(1000); sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000); SharedFolderSearch
	 * gotoSearchSharedFolder = sharedFolder.gotoSearchSharedFolder(fileName);
	 * Thread.sleep(1000); gotoSearchSharedFolder.returnBtn().click();
	 * Thread.sleep(1000); assertEquals(sharedFolder.getRootfolder().getText(),
	 * "IRAPPA");
	 * 
	 * }
	 * 
	 * @Test(priority = 6,groups = { "Functional" }, enabled = true)
	 * //@PriorityInClass(3) public void verifyFilePreview() throws Exception {
	 * setTestDesc( "Verify that Image files are previewed"); String fileName =
	 * "Filename.jpg"; sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000); SharedFolderSearch
	 * gotoSearchSharedFolder = sharedFolder.gotoSearchSharedFolder(fileName);
	 * sharedFolder.firstRowName().click(); Thread.sleep(1000);
	 * assertTrue(sharedFolder.fileClose().isDisplayed()); Thread.sleep(1000);
	 * sharedFolder.fileClose().click();
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Test(priority = 7,groups = { "Functional" }, enabled = true)
	 * 
	 * @PriorityInClass(3) public void verifyFileVersionGeneration() throws
	 * Exception { setTestDesc(
	 * "Verify that Based on the file generations in the settings page, File version should be displayed "
	 * ); String BeforeDltfileCnt = sharedFolder.getFileCount();
	 * 
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.gotoSearchSharedFolder("version"); Thread.sleep(1000);
	 * sharedFolder.OpenFileVersion().click(); Thread.sleep(1000);
	 * assertEquals(sharedFolder.getFileVersionHeader().getText(),"以前のバージョン");
	 * sharedFolder.CloseFileVersion().click();
	 * 
	 * }
	 * 
	 * @Test(priority = 8,groups = { "Functional" }, enabled = true)
	 * 
	 * @PriorityInClass(3) public void verifyBackupDeviceManagementFolderList()
	 * throws Exception { setTestDesc(
	 * "Verify that Backup Device Management device/folder list ");
	 * sharedFolder.OpenDeviceManagement().click(); Thread.sleep(1000);
	 * assertEquals(sharedFolder.deviceManagementheader(),"デバイス管理");
	 * //assertEquals(sharedFolder.listOfDevices(),"デバイス管理"); Thread.sleep(1000);
	 * 
	 * assertTrue(sharedFolder.firstRowName().isDisplayed());
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority = 9,groups = { "Functional" }, enabled = true)
	 * //@PriorityInClass(3) public void verifySingleFolderDelete() throws Exception
	 * { setTestDesc(
	 * "Verify that Admin can delete sinlge file from the folder.file/folder should be available in the  folder"
	 * );
	 * 
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(2000); String
	 * BeforeDltfileCnt = sharedFolder.getFileCount();
	 * sharedFolder.selectSinlgeCheckBox().click(); Thread.sleep(1000);
	 * sharedFolder.deleteBkBtn().click(); Thread.sleep(1000);
	 * sharedFolder.deleteOKBtn().click(); Thread.sleep(1000); String
	 * AfterDltfileCnt = sharedFolder.getFileCount();
	 * assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt); }
	 * 
	 * @Test(priority = 10,groups = { "Functional" }, enabled = true)
	 * 
	 * @PriorityInClass(3) public void verifyMultiFolderDelete() throws Exception {
	 * setTestDesc(
	 * "Verify that Admin can delete multiple file from the root folder.Folder should be available in the  folder"
	 * ); String BeforeDltfileCnt = sharedFolder.getFileCount();
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(1000);
	 * sharedFolder.OpenFolder().click(); Thread.sleep(2000);
	 * sharedFolder.selectAllCheckBox().click(); Thread.sleep(1000);
	 * sharedFolder.deleteBkBtn().click(); Thread.sleep(1000);
	 * sharedFolder.deleteOKBtn().click(); Thread.sleep(3000); String
	 * AfterDltfileCnt = sharedFolder.getFileCount();
	 * assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);
	 * 
	 * }
	 * 
	 * @Test(priority = 11,groups = { "Functional" }, enabled = true)
	 * 
	 * @PriorityInClass(3) public void verifySettings() throws Exception {
	 * setTestDesc( "Verify that Change the values in the settings and set ");
	 * Thread.sleep(1000); sharedFolder.ClickSettings().click(); Thread.sleep(1000);
	 * sharedFolder.ClickOnNumberGenerationBk().click(); Thread.sleep(1000);
	 * sharedFolder.Select5option().click(); Thread.sleep(1000);
	 * sharedFolder.ClickonSet().click(); Thread.sleep(1000);
	 * sharedFolder.deleteOKBtn().click(); Thread.sleep(1000);
	 * assertEquals(sharedFolder.getStringMsg().getText(),"設定値を登録しました。");
	 * Thread.sleep(1000); sharedFolder.shareOkBtn().click(); Thread.sleep(2000); }
	 */
	
	@Test(priority = 12,groups = { "Functional" }, enabled = true)
	@PriorityInClass(3)
	public void verifySingleDeviceDelete() throws Exception {
		setTestDesc(
				"Verify that Select single device and delete ");
		sharedFolder.OpenDeviceManagement().click();
		Thread.sleep(1000);	
		sharedFolder.backupdeviceCheckBox().click();
		Thread.sleep(1000);			
		sharedFolder.deleteBkBtn().click();
		Thread.sleep(1000);	
		sharedFolder.deleteOKBtn().click();
		Thread.sleep(3000);	
		assertEquals(sharedFolder.Emptydevice().getText(),"データはありません。");
		
	}
	
	}