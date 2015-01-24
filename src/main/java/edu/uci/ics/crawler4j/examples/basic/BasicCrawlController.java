/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.uci.ics.crawler4j.examples.basic;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * @author Yasser Ganjisaffar [lastname at gmail dot com]
 * 
 * @author Sanju Thomas [lastname dot firstname at gmail dot com]
 * 
 */
public class BasicCrawlController {
	
  private static final Logger logger = LoggerFactory.getLogger(BasicCrawlController.class);

  public static void crawl(final String crawlStorageFolder, final Integer numberOfCrawlers, final String [] webSites) throws Exception {

	if(logger.isInfoEnabled()){
		logger.info("crawlStorageFolder: "+crawlStorageFolder);
		logger.info("numberOfCrawlers: "+numberOfCrawlers);
		logger.info("webSites: "+Arrays.toString(webSites));
	}
	  
    final CrawlConfig config = new CrawlConfig();
    config.setCrawlStorageFolder(crawlStorageFolder);
    config.setPolitenessDelay(1000);
    config.setIncludeBinaryContentInCrawling(true);
    config.setResumableCrawling(false);
    config.setMaxDepthOfCrawling(1);

    final PageFetcher pageFetcher = new PageFetcher(config);
    final RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
    final RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
    final CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

    for(final String webSite : webSites){
    	controller.addSeed(webSite);
    }
    
    controller.start(BasicCrawler.class, numberOfCrawlers);
  }
}
