package ru.task.processing.json.analysis;

import org.apache.spark.SparkException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;

import ru.task.processing.json.preproccesing.LoadingPath;
import ru.task.processing.json.preproccesing.SparkLoadable;


import java.io.IOException;

public class AnalysisData implements SparkLoadable {
    public static void main(String[] args) throws IOException, SparkException {
        Dataset<Row> registered_df = spark.read().parquet(LoadingPath.loadingPath("path_datasets_reg"))
                .withColumn("week_reg", functions.weekofyear(functions.col("time")));

        Dataset<Row> app_df = spark.read().parquet(LoadingPath.loadingPath("path_datasets_app"))
                                          .withColumn("week_app", functions.weekofyear(functions.col("time")));

        Long good_users = registered_df
                .join(app_df, app_df.col("initiator_id").equalTo(registered_df.col("initiator_id")), "inner")
                .withColumn("diff_week", functions.expr("week_app - week_reg"))
                .filter("diff_week==1 or diff_week==51").select(app_df.col("initiator_id")).distinct().count();
        Long all_users = registered_df.select("initiator_id").distinct().count();

        Long result =  good_users * 100.0 / all_users;
        System.out.println();
        System.out.println();
        System.out.println("=========================");
        System.out.printf("%d%%       ================", result);
        System.out.println();
        System.out.println("=========================");
        System.out.println();
        System.out.println();

    }
}
