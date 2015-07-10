package com.mycompany.sonar.reference.profile;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.profiles.ProfileImporter;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.rules.RulePriority;
import org.sonar.api.utils.ValidationMessages;
//import org.sonar.plugins.java.Java;

public class SqlProfileImporter extends ProfileImporter {
	private final RuleFinder ruleFinder;
	private static final Logger LOG = LoggerFactory.getLogger(SqlProfileImporter.class);
	
	public SqlProfileImporter(RuleFinder ruleFinder) {
	    super(SqlRulesDefinition.REPOSITORY_KEY, "SqlProfile2");
	    setSupportedLanguages("sql");
	    this.ruleFinder = ruleFinder;
	  }
	
	@Override
	  public RulesProfile importProfile(Reader findbugsConf, ValidationMessages messages) {
	    RulesProfile profile = RulesProfile.create();
	    try {
	      //XStream xStream = FindBugsFilter.createXStream();
	      //FindBugsFilter filter = (FindBugsFilter) xStream.fromXML(findbugsConf);

	      //activateRulesByCategory(profile, filter, messages);
	      //activateRulesByCode(profile, filter, messages);
	      //activateRulesByPattern(profile, filter, messages);

	    	Rule rule = ruleFinder.findByKey(SqlRulesDefinition.REPOSITORY_KEY, "NAME_PACKAGE_INCORRECT");
	    	
	    	if (rule != null) {
	            profile.activateRule(rule, RulePriority.MAJOR);
	          } else {
	            messages.addWarningText("Unable to activate unknown rule : NAME_PACKAGE_INCORRECT");
	          }
	        return profile;
	    } catch (Exception e) {
	      String errorMessage = "The SqlFindBugs configuration file is not valid";
	      messages.addErrorText(errorMessage + " : " + e.getMessage());
	      LOG.error(errorMessage, e);
	      return profile;
	    }
	  }
}
