
import org.apache.spark.sql.functions._

import scala.io.Source

final case class Person(firstName: String, lastName: String, country: String, age: Int)

object Main extends InitializeSpark {
  def main(args: Array[String]) = {
    import spark.implicits._

    val csvLines = Source.fromInputStream(classOf[Person].getClassLoader.getResourceAsStream("people.csv"))
      .getLines().toSeq.toDS()

    val persons = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .option("mode", "DROPMALFORMED")
      .csv(csvLines)
      .as[Person]
    persons.sample(0.2)
    val averageAge = persons.agg(avg("age"))
      .first.get(0).asInstanceOf[Double]

    println(f"Average Age: $averageAge%.2f")

    close
  }
}
