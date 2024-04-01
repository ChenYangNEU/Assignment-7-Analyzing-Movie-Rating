import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
object Analyze extends App{
    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("Analyze Rating")
      .getOrCreate()

    val filePath = "imdb.csv"

    val df = spark.read
      .option("header", "true")
      .csv(filePath)

    val ratingMean = df.agg(avg("Rating_from_10")).first().getDouble(0)
    val std = df.agg(stddev("Rating_from_10")).first().getDouble(0)

    println(s"Mean Rating: $ratingMean")
    println(s"Standard Deviation:  $std")

    spark.stop()

}
