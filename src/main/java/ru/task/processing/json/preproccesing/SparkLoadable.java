package ru.task.processing.json.preproccesing;

import org.apache.spark.sql.SparkSession;

public interface SparkLoadable {
    SparkSession spark = SparkSession
            .builder()
            .appName("Processing Json Dataset")
            .master("local")
            .config("spark.ui.enabled", "false")
            .config("spark.driver.allowMultipleContexts", "false")
            .getOrCreate();
}
