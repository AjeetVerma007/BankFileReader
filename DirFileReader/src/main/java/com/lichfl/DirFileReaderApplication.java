package com.lichfl;

import java.io.File;
import java.io.FileFilter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

import com.lichfl.entity.BankFile;
import com.lichfl.service.BankService;

@SpringBootApplication
public class DirFileReaderApplication extends SpringBootServletInitializer {

	private final static Logger logger = LoggerFactory.getLogger(DirFileReaderApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(DirFileReaderApplication.class);
	}

	public static void main(String[] args) {

		System.out.println("hi this is main program ");

		SpringApplication.run(DirFileReaderApplication.class, args);

	}

	@Component
	public class saveFileName implements CommandLineRunner {
		@Autowired
		private BankService bankService;

		private BankFile bankFile;

		@Override
		public void run(String[] args) throws Exception {

			final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");

			Date date = new Date();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			// pathToScan ="D:\\auditor\\datamatics\\Out";
			String pathToScan = "D:\\BankFiles";
			String matchPattern = "919020003182009";
			String matchPattern2 = "LICHF447";
			List<String> dbFiles = new ArrayList<String>();
			List<BankFile> fileList = null;
			final DecimalFormat df = new DecimalFormat("0.00");
			try {

				fileList = bankService.getBankFiles();

				for (BankFile csvFile : fileList) {
					dbFiles.add(csvFile.getFileName());
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			File folderToScan = new File(pathToScan);

			FileFilter fileFilter = new FileFilter() {

				@Override
				public boolean accept(File file) {

					if (sdf.format(file.lastModified()).equalsIgnoreCase(sdf.format(new Date()))
							&& (file.getName().endsWith(".txt") && file.getName().startsWith(matchPattern)
									|| (file.getName().endsWith(".csv") && file.getName().startsWith(matchPattern2)))) {
						return true;
					}
					return false;
				}

			};
			File[] listOfFiles = folderToScan.listFiles(fileFilter);

			long dirChangeCounter = 0;

			if (listOfFiles != null) {
				
				for (File file : listOfFiles) {
					if (file.isFile()) {
						if (dbFiles.size() == 0 || !dbFiles.contains(file.getName())) {

							// logger.info("New file found on path"+dbFiles.get(0));

							bankFile = BankFile.builder().fileName(file.getName()).fileSize(file.length())
									.createdDate(sdf1.format(timestamp)).status('N').build();

							logger.info("File name :" + file.getName() + " , File size :" + file.length());

							logger.info("bankFile" + bankFile);
							bankService.saveFile(bankFile);
							dirChangeCounter++;

						}
					}
				}
			}
			if (dirChangeCounter > 0) {
				String outMsg = bankService.sendMail();
				logger.info("Controller outMsg : " + outMsg);
				return;

			}
			
			return;
			//public void parseFile(String Filename,)
			
		}
	}

}
