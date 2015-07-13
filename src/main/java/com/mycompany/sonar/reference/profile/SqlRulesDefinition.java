package com.mycompany.sonar.reference.profile;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.squidbridge.rules.ExternalDescriptionLoader;

public class SqlRulesDefinition implements RulesDefinition {

	public static final String REPOSITORY_KEY = "sql_repository";
	public static final String REPOSITORY_NAME = "SQL_Repository";
	
	public SqlRulesDefinition(){
		
	}

  @Override
  public void define(Context context) {
	  NewRepository repository = context
		      .createRepository(REPOSITORY_KEY, SqlLanguage.KEY)
		      .setName(REPOSITORY_NAME);

    
	  RulesDefinitionXmlLoader ruleLoader = new RulesDefinitionXmlLoader();
	  ruleLoader.load(repository, SqlRulesDefinition.class.getResourceAsStream("/xml/rules.xml"), "UTF-8");
	  ExternalDescriptionLoader.loadHtmlDescriptions(repository, "/org/sonar/l10n/views");
	  
	  repository.done();
  }
}
