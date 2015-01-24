package edu.uci.ics.crawler4j.examples.basic;

import org.junit.Test;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestBasicCrawlController {

	private static final String CRAWL_STORAGE_FOLDER = "/home/thosan/java/crawl-data";	
	private static final Integer NUMBER_OF_CRAWLERS = 2;
	private static final String [] WEB_SITES_CRAWL = new String [] {"http://sanju.org"};
	
	@Test
	public void shouldCrawl() throws Exception{
		BasicCrawlController.crawl(CRAWL_STORAGE_FOLDER, NUMBER_OF_CRAWLERS, WEB_SITES_CRAWL);
	}
}
