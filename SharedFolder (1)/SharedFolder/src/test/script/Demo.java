package test.script;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import cocoro.enums.ConfigType;
import cocoro.lib.utility.DataUtils;

public class Demo {
	
	
	private Map<ConfigType, String> configuration = new HashMap<ConfigType, String>();
	private String cName = null;
	@Test
	public void ReadConfig(ITestContext context) {
		Properties config = new Properties();
		FileInputStream fis = null;
		
		System.out.println(context.getCurrentXmlTest().getParameter(("configfile").toLowerCase()));
		
		Map<String, String> params = context.getCurrentXmlTest().getAllParameters();
		if(!params.isEmpty()) {
		for (String key : params.keySet()) {

			if (params.get(key).toLowerCase().endsWith(".properties")) {
				try {
					System.out.println(DataUtils.joinPaths(System.getProperty("user.dir"),context.getCurrentXmlTest().getParameter(("configfile").toLowerCase())));
					fis = new FileInputStream(DataUtils.joinPaths(System.getProperty("user.dir"),context.getCurrentXmlTest().getParameter(("configfile").toLowerCase())));
					config.load(fis);

					for (Entry<Object, Object> entry : config.entrySet()) {
						System.out.println((String) entry.getKey() + "  : " + (String) entry.getValue());
						configuration.put(ConfigType.valueOf(entry.getKey().toString().contains(".")?((String) entry.getKey()).replace(".", "_").toUpperCase():((String) entry.getKey()).toUpperCase()), ((String) entry.getValue()));
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {

				try {
					configuration.put(ConfigType.valueOf(key.replace(".", "_").toUpperCase()), params.get(key));
				} catch (Exception e) {
					System.err.println(e.getStackTrace());
					// System.exit(1);
				}
			}
		}
	}
		
	}

}
