package org.test;

public class TestTest {
	
	public static void main(String[] args) {
        CrawlConfig crawlConfig = new CrawlConfig();
        crawlConfig.setCrawlStorageFolder("C:\\asp\\crawler4jStorage");
        System.out.println(crawlConfig.toString());
        PageFetcher pageFetcher = new PageFetcher(crawlConfig);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        try {
                CrawlController crawlController = new CrawlController(crawlConfig,
                        pageFetcher, robotstxtServer);

}
