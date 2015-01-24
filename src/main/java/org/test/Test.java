package org.test;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Test {

	public static void main(String[] args) {
		CrawlConfig crawlConfig = new CrawlConfig();
		crawlConfig.setCrawlStorageFolder("crawler4jStorage");
		System.out.println(crawlConfig.toString());
		PageFetcher pageFetcher = new PageFetcher(crawlConfig);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		try {
			CrawlController crawlController = new CrawlController(crawlConfig, pageFetcher, robotstxtServer);
			crawlController.addSeed("http://www.ics.uci.edu/");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
