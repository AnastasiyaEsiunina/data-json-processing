package ru.task.processing.json.loading;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.types.DataTypes;
import ru.task.processing.json.preproccesing.LoadingPath;
import ru.task.processing.json.preproccesing.SparkLoadable;
import static org.apache.spark.sql.functions.col;


import java.io.IOException;

public class LoadingData implements SparkLoadable {
    public static void main(String[] args) throws IOException {

        Dataset<Row> main_df = spark.read().json(LoadingPath.loadingPath("path_file"));
        Dataset<Row>  main_df_registered =  main_df.filter("event=='registered'")
                .select(col("timestamp").cast(DataTypes.TimestampType).alias("time"), col("initiator_id").cast(DataTypes.LongType), col("channel"));
        main_df_registered.repartition(1)
                .write().mode(SaveMode.Overwrite).format("parquet")
                .save(LoadingPath.loadingPath("path_datasets_reg"));
        main_df.filter("event=='app_loaded'")
                .select(col("timestamp").cast(DataTypes.TimestampType).alias("time"), col("initiator_id").cast(DataTypes.LongType),  col("device_type"))
                .repartition(1)
                .write().mode(SaveMode.Overwrite).format("parquet")
                .save(LoadingPath.loadingPath("path_datasets_app"));
    }


}
