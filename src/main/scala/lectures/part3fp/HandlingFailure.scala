package lectures.part3fp

import java.util.Random
import scala.util.{Failure, Success, Try}

object HandlingFailure extends App {

  // instead of using multiple try-catch blocks (which makes codes bulky) scala provides Try (capital T)
  //now u only need Try to deal with exceptions...previously we saw options to deal with nulls

  val aSuccess = Success(3) //success and failures are case classes defined inside Try abstract class
  val aFailure = Failure(new RuntimeException("superrrr failuree"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("No STRING FOR U BUSTARD")
  //unsafeMethod is supposed to return String but its returning runtime exception

  //Try object via apply method
  val potentialFailure = Try(unsafeMethod)
  println(potentialFailure) //u will notice my program did not crash even though i called unsafe method
  //we put our unsafeMethod inside Try so it took take care of that.

  //syntactic sugar
  val anotherPotentialFailure = Try {
    //code that might throw
  }

  //utilities
  //to check whether your code inside Try is success or failure

  println(potentialFailure.isSuccess)
  println(potentialFailure.isFailure)

  //like Options Try also have orElse method
  def backupMethod:String = "A Valid result"
  println(Try(unsafeMethod).orElse(Try(backupMethod)))

  //better way is to declare return type as Try only at the begning
  def betterUnsafeMethod: Try[String]= Failure(new RuntimeException())
  def betterBackupMethod: Try[String] = Success("A valid betterrrrr reult")

 println(betterUnsafeMethod.orElse(betterBackupMethod))
  println(betterUnsafeMethod orElse betterBackupMethod)

  //Try also has map, flatMap and filter
  println(aSuccess.map(_*2))
  println(aSuccess.flatMap(x => Success(x*10)))
  println(aSuccess.filter(_ > 10))

  //if map,flatmap can be applied, then it means  for comprehensions will be applied too
  /*
   *EXERCISE
   * LET SAY WE HAVE
   * val hostname = "localhost"
   * val port = "8080"
   * def renderHTML(page:String) = println(page)
   * class Connection{
   *   def get(url:String):String ={
   *      val random = new Random(System.nonoTime)
   *      if (random.nextBoolean) "<html>.....</html>"
   *      else throw new RuntimeException("connection interrupted")
   * }
   * }
   *
   * object HttpService{
   *  val random = new Random(System.nanoTime)
   *  def getConnection(host:String, port:String): Connection = {
   *    if (random.nextBoolean) new Connection
   *    else throw new RuntimeException("someone else took the port")
   * }
   *your task is ..if u get the html page from the connection, then print it to the console...ie) call renderHTML
   * }
   *
   *
   */
  //SOLUTION--I have a get method that should return a string but can throw exception
  // I have a getConnection that should return a connection but can throw exception
  //what I have to do is collect the return type inside Try

  val host = "localhost"
  val port = "8080"

  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime)
      if (random.nextBoolean) "<html>.....</html>"
      else throw new RuntimeException("connection interrupted")
    }
    def getSafe(url:String):Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime)

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean) new Connection
      else throw new RuntimeException("someone else took the port")
    }
    def getSafeConnection(host:String, port:String):Try[Connection] = Try(getConnection(host,port))
  }
  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderHTML) // u may think why renderHTML is not taking argumemt here, but check the functionality of foreach ..it will make left side as a agrument to render html

  // short hand version would be to chain all these calls

  HttpService.getSafeConnection(host, port)
   .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  //for comprehension version
  for {
    connection <- HttpService.getSafeConnection(host, port)
    h <- connection.getSafe("/home")
  } yield renderHTML(h)

  // in imperative style below is the code....
  /*
   *try {
   *  connection = HttpService.getSafeConnection(host, port)
   *  try {
   *    page = connection.getSafe("/home")
   *    renderHTML(page)
   *  }catch (some other exception){
   *
   * }
   * }catch(some exception){
   *
   * }
   */





}
