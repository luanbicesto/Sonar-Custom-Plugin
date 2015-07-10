package com.mycompany.sonar.reference.profile;

import org.sonar.api.resources.AbstractLanguage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.StringUtils;

public class SqlLanguage extends AbstractLanguage{
	public static final String DEFAULT_SOURCE_SUFFIXES = "sql";
	public static final String KEY = "sql";
	
	private String[] fileSuffixes;
	private static final Logger LOG = LoggerFactory.getLogger(SqlLanguage.class);

	  public SqlLanguage() {   
	    super(KEY, "sql");
	    LOG.info("SETTING LANGUAGE TO SQL: " + KEY);
	    fileSuffixes = createStringArray(null, DEFAULT_SOURCE_SUFFIXES);
	  }
	  
	  public String[] getFileSuffixes() {
	      return fileSuffixes;
	  }

	  private String[] createStringArray(String[] values, String defaultValues) {
	    if (values == null || values.length == 0) {
	      return StringUtils.split(defaultValues, ",");
	    }
	    return values;
	  }
}
