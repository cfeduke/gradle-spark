import org.apache.log4j.{Level, LogManager, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.sql.{SQLContext, SparkSession}

trait InitializeSpark {
  val spark: SparkSession = SparkSession.builder()
                            .appName("Spark Example")
                            .master("local[*]")
                            .getOrCreate()

  val sc: SparkContext = spark.sparkContext
  val sqlContext: SQLContext = spark.sqlContext

  private def init(): Unit = {
    sc.setLogLevel("ERROR")
    Logger.getLogger("org").setLevel(Level.ERROR)
    Logger.getLogger("akka").setLevel(Level.ERROR)
    LogManager.getRootLogger.setLevel(Level.ERROR)
  }

  init()
  
  def close(): Unit = {
    spark.close()
  }
}