package pages;

import java.util.List;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;

import cocoro.lib.uiauto.UIControl;
import cocoro.pages.BasePage;

public class PersonalFolder extends BasePage {

	public PersonalFolder(RemoteWebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public UIControl searchTxtBox() throws Exception {
		return this.uiControl("searchTxtBox");
	}
	
	public UIControl OperationMenu() throws Exception {
		return this.uiControl("operationMenu");
	}
	
	public String getFileCount() throws Exception {
		return this.uiControl("fileCount").getText().trim();
	}
	
	public String getSearchTxtBoxValue() throws Exception {
		return this.uiControl("searchTxtBox").getAttribute("value");
	}

	public UIControl searchButton() throws Exception {
		return this.uiControl("searchButton");
	}

	public UIControl homeLink() throws Exception {
		return this.uiControl("homeLink");
	}

	public String getSaredFolderName() throws Exception {
		return this.uiControl("folderName").getText().trim();
	}
	public UIControl downloadFromContextBtn() throws Exception {
		return this.uiControl("downloadFrmContextBtn");
	}
	public UIControl renameInput() throws Exception {
		return this.uiControl("renameInput");
	}
	

	public UIControl fileClose() throws Exception {
		return this.uiControl("fileClose");
	}
	
	
	public UIControl openFilePreview() throws Exception {
		return this.uiControl("openFilePreview");
	}
	
	public UIControl renameFromContextBtn() throws Exception {
		return this.uiControl("renameFromContextBtn");
	}
	
	public UIControl downloadBtn() throws Exception {
		return this.uiControl("downloadBtn");
	}

	public UIControl changeSaveDestinationBtn() throws Exception {
		return this.uiControl("changeSaveDestinationBtn");
	}
	
	
	public UIControl SelectFileMovetoHome() throws Exception {
		return this.uiControl("selectFileMovetoHome");
	}
	
	
	public UIControl SelectFileMovetoSharedFolder() throws Exception {
		return this.uiControl("selectFileMovetoSharedFolder");
	}
	
	public UIControl changeSaveDestinationBtnfrmContext() throws Exception {
		return this.uiControl("changeSaveDestinationBtnFrmContext");
	}
	
	
	public UIControl SelectFolderInSameLevel() throws Exception {
		return this.uiControl("SelectFolderInTheSameLevel");
	}

	public UIControl deleteBtn() throws Exception {
		return this.uiControl("deleteBtn");
	}
	
	public UIControl deleteBtnfromContext() throws Exception {
		return this.uiControl("deletefrmContext");
	}
	
	public UIControl uploadBtn() throws Exception {
		return this.uiControl("uploadBtn");
	}

	public UIControl createNewFolderBtn() throws Exception {
		return this.uiControl("createNewFolderBtn");
	}

	public UIControl topDropDownBtn() throws Exception {
		return this.uiControl("topDropDownBtn");
	}

	public List getTopDropDownList() throws Exception {
		return this.uiControls("topDropDownList");
	}

	public String getTopPagination() throws Exception {
		return this.uiControl("topPagination").getText();
	}

	public UIControl topStatingPageBtn() throws Exception {
		return this.uiControl("topStatingPageBtn");
	}

	public UIControl topDeforePageBtn() throws Exception {
		return this.uiControl("topDeforePageBtn");
	}

	public UIControl topEndingPageBtn() throws Exception {
		return this.uiControl("topEndingPageBtn");
	}

	public UIControl topAfterPageBtn() throws Exception {
		return this.uiControl("topAfterPageBtn");
	}

	public UIControl selectAllCheckBox() throws Exception {
		return this.uiControl("selectAllCheckBox");
	}
	
	public UIControl selectSecondCheckBox() throws Exception {
		return this.uiControl("selectSecondCheckBox");
	}
	
	public UIControl selectSinlgeCheckBox() throws Exception {
		return this.uiControl("selectSingleCheckBox");
	}

	public List graph() throws Exception {
		return this.uiControls("headerList");
	}

	public List checkBoxList() throws Exception {
		return this.uiControls("checkBoxList");
	}

	public List nameList() throws Exception {
		return this.uiControls("nameList");
	}

	public List sizeList() throws Exception {
		return this.uiControls("sizeList");
	}

	public List updatedDateAndtimeList() throws Exception {
		return this.uiControls("updatedDateAndtimeList");
	}

	public List menuButtonList() throws Exception {
		return this.uiControls("menuButtonList");
	}

	public UIControl firstRowMenuBtn() throws Exception {
		return this.uiControl("firstRowMenuBtn");
	}

	public List rowMenuList() throws Exception {
		return this.uiControls("rowMenuList");
	}

	public UIControl footerDropDownBtn() throws Exception {
		return this.uiControl("footerDropDownBtn");
	}

	public List footerDropDownList() throws Exception {
		return this.uiControls("footerDropDownList");
	}

	public String getFooterPagination() throws Exception {
		return this.uiControl("footerPagination").getText();
	}

	public UIControl footerStatingPageBtn() throws Exception {
		return this.uiControl("footerStatingPageBtn");
	}

	public UIControl footerDeforePageBtn() throws Exception {
		return this.uiControl("footerDeforePageBtn");
	}

	public UIControl footerEndingPageBtn() throws Exception {
		return this.uiControl("footerEndingPageBtn");
	}

	public UIControl footerAfterPageBtn() throws Exception {
		return this.uiControl("footerAfterPageBtn");
	}

	public UIControl breadcrumRoot() throws Exception {
		return this.uiControl("breadcrumRoot");
	}

	public UIControl openRootFolder() throws Exception {
		return this.uiControl("openRootFolde");

	}

	public PersonalFolderSearch gotoSearchPersonalFolder(String value) throws Exception {
		this.uiControl("searchTxtBox").sendKeys(value);
		this.uiControl("searchButton").click();
		Thread.sleep(2000);
		return new PersonalFolderSearch(this.getDriver());
	}

	public PersonalFolderCreation gotoPersonalFolderCreation() throws Exception {
		this.uiControl("createNewFolderBtn").click();
		return new PersonalFolderCreation(this.getDriver());
	}

	public PersonalFolderChangeSaveDestination gotoPersonalFolderChangeSaveDestination() throws Exception {
		this.uiControl("changeSaveDestinationBtn").click();
		return new PersonalFolderChangeSaveDestination(this.getDriver());
	}

	public SharedFolderDelete gotoSharedFolderDelete() throws Exception {
		this.uiControl("deleteBtn").click();
		return new SharedFolderDelete(this.getDriver());
	}

	private void selectValue(String folderList, String value) throws Exception {
		List<UIControl> allValues = this.uiControls(folderList);
		for (UIControl uiControl : allValues) {
			if (uiControl.getText().trim().equals(value.trim())) {
				uiControl.click();
				break;
			}
		}
	}

	public PersonalFolderDelete gotoPersonalFolderDelete(String value) throws Exception {
		selectValue("rowMenuList", value);
		Thread.sleep(2000);
		return new PersonalFolderDelete(this.getDriver());
	}

	public PersonalFolderDelete gotoPersonalFolderDelete() throws Exception {
		this.uiControl("deleteBtn").click();
		Thread.sleep(2000);
		return new PersonalFolderDelete(this.getDriver());
	}

	public UIControl firstRowName() throws Exception {
		return this.uiControl("firstRowName");
	}


	public UIControl OpenCreatedFolder() throws Exception {
		return this.uiControl("OpenCreatedFolder");
	}
	
	public UIControl errorMsginvalid() throws Exception {
		return this.uiControl("errorMsginvalid");
	}

	public UIControl errorMsg() throws Exception {
		return this.uiControl("errorMsg");
	}

	public UIControl errorMsgOkBtn() throws Exception {
		return this.uiControl("errorMsgOkBtn");
	}
	public UIControl renameOkBtn() throws Exception {
		return this.uiControl("renameOkBtn");
	}
	
	public UIControl shareOkBtn() throws Exception {
		return this.uiControl("shareOkBtn");
	}
	
	public UIControl fileShare() throws Exception {
		return this.uiControl("fileShare");
	}
	
	public UIControl shareFileHeader() throws Exception {
		return this.uiControl("shareFileHeader");
	}
	
	public UIControl clickShareBtn() throws Exception {
		return this.uiControl("clickShareBtn");
	}
	
	public UIControl deleteMsgOkBtn() throws Exception {
		return this.uiControl("deleteMsgOkBtn");
	}
	public static void uploadFileWithRobot(String imagePath) throws InterruptedException {
		Thread.sleep(3000);
		StringSelection stringSelection = new StringSelection(imagePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(150);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public void waitForControlNotToDisplay() throws Exception {	

		boolean exists = this.uiControl("waitNotToDisplay").exists();
		if(!exists) {
			System.out.println("Wait foe 10 seconds");
			Thread.sleep(10000);
		}
		else
		{
			System.out.println("Progress done");
		}
	}
	
//
	public UIControl getRootfolder() throws Exception {
		
		return this.uiControl("rootFoldername");
	}

	

}