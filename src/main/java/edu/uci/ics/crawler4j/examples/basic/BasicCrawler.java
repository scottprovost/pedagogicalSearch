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

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.Set;
import java.util.regex.Pattern;

import org.apache.http.Header;

/**
 * @author Yasser Ganjisaffar [lastname at gmail dot com]
 * 
 * @author Sanju Thomas [lastname dot firstname at gmail dot com]
 */
public class BasicCrawler extends WebCrawler {

  private final static Pattern BINARY_FILES_EXTENSIONS =
        Pattern.compile(".*\\.(bmp|gif|jpe?g|png|tiff?|pdf|ico|xaml|pict|rif|pptx?|ps" +
        "|mid|mp2|mp3|mp4|wav|wma|au|aiff|flac|ogg|3gp|aac|amr|au|vox" +
        "|avi|mov|mpe?g|ra?m|m4v|smil|wm?v|swf|aaf|asf|flv|mkv" +
        "|zip|rar|gz|7z|aac|ace|alz|apk|arc|arj|dmg|jar|lzip|lha)" +
        "(\\?.*)?$"); // For url Query parts ( URL?q=... )

  /**
   * You should implement this function to specify whether the given url
   * should be crawled or not (based on your crawling logic).
   */
  @Override
  public boolean shouldVisit(final Page page, final WebURL url) {
    return !BINARY_FILES_EXTENSIONS.matcher(url.getURL().toLowerCase()).matches();
  }

  /**
   * This function is called when a page is fetched and ready to be processed
   * by your program.
   */
  @Override
  public void visit(final Page page) {
	  
    final int docid = page.getWebURL().getDocid();
    final String url = page.getWebURL().getURL();
    final String domain = page.getWebURL().getDomain();
    final String path = page.getWebURL().getPath();
    final String subDomain = page.getWebURL().getSubDomain();
    final String parentUrl = page.getWebURL().getParentUrl();
    final String anchor = page.getWebURL().getAnchor();

    if(logger.isInfoEnabled()){
	    logger.info("Docid: {}", docid);
	    logger.info("URL: ", url);
	    logger.info("Domain: '{}'", domain);
	    logger.info("Sub-domain: '{}'", subDomain);
	    logger.info("Path: '{}'", path);
	    logger.info("Parent page: {}", parentUrl);
	    logger.info("Anchor text: {}", anchor);
    }

    if (page.getParseData() instanceof HtmlParseData) {
      
    	final HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
    	final String text = htmlParseData.getText();
    	final String html = htmlParseData.getHtml();
    	final Set<WebURL> links = htmlParseData.getOutgoingUrls();

    	if(logger.isInfoEnabled()){
    		logger.info("Text length: {}", text.length());
    		logger.info("Html length: {}", html.length());
    		logger.info("Number of outgoing links: {}", links.size());
    	}
    }

    final Header[] responseHeaders = page.getFetchResponseHeaders();
    if (responseHeaders != null && logger.isInfoEnabled()) {
      logger.info("Response headers:");
      for (final Header header : responseHeaders) {
        logger.info("\t{}: {}", header.getName(), header.getValue());
      }
    }
  }
}
