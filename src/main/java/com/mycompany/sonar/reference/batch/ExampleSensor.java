package com.mycompany.sonar.reference.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.config.Settings;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.Project;

import com.mycompany.sonar.reference.ExampleMetrics;
import com.mycompany.sonar.reference.ExamplePlugin;

public class ExampleSensor implements Sensor {

  private static final Logger LOG = LoggerFactory.getLogger(ExampleSensor.class);

  private Settings settings;
  private FileSystem fs;

  /**
   * Use of IoC to get Settings and FileSystem
   */
  public ExampleSensor(Settings settings, FileSystem fs) {
    this.settings = settings;
    this.fs = fs;
  }

  @Override
  public boolean shouldExecuteOnProject(Project project) {
    // This sensor is executed only when there are sql files
    return fs.hasFiles(fs.predicates().hasLanguage("sql"));
  }

  @Override
  public void analyse(Project project, SensorContext sensorContext) {
    // This sensor create a measure for metric MESSAGE on each sql file
    String value = settings.getString(ExamplePlugin.MY_PROPERTY);
    LOG.info(ExamplePlugin.MY_PROPERTY + "=" + value);
    for (InputFile inputFile : fs.inputFiles(fs.predicates().hasLanguage("sql"))) {
    	LOG.info("Sql file available");
    	sensorContext.saveMeasure(inputFile, new Measure<String>(ExampleMetrics.MESSAGE, value));
    }
  }

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }

}
