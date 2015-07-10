package com.mycompany.sonar.reference.profile;

import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.utils.ValidationMessages;

public class SqlProfile extends ProfileDefinition {
	private static final String SQL_PROFILE_NAME = "SqlProfile2";
	private final SqlProfileImporter importer;
	
	public SqlProfile(SqlProfileImporter importer) {
	    this.importer = importer;
	  }
	
	@Override
	  public RulesProfile createProfile(ValidationMessages messages) {
	    //Reader sqlProfile = new InputStreamReader(this.getClass().getResourceAsStream(
	      //"/xml/profile-sql.xml"));
	    RulesProfile profile = importer.importProfile(null, messages);
	    profile.setLanguage("sql");
	    profile.setName(SQL_PROFILE_NAME);
	    return profile;
	  }

}
