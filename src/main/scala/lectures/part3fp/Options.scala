package lectures.part3fp

import java.time.InstantSource.system
import java.util.Random

// to deal with Nulls use options....if u think ur function ,may return null then make it return type as OPTION
object Options extends App {

  val myFirstOption : Option[Int] =Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  // Options were build to deal with unsafe APIs
  def unSafeMethod:String = null
  //val result = Some(unSafeMethod) //but this is wrong because u might be getting Some(null) which breaks whole point of options
  //Some should always have a valid value insude, so u should never do like above
  //Instead use Options's apply method
  val result = Option(unSafeMethod) //this will deal with nons , u dont need to do null check urself
  println(result)

  //Options is majorly used in Chained methods

  def backupMethod: String =  "A Valid result"
  val chainedResult = Option(unSafeMethod).orElse(Option(backupMethod))// if our prefered method which is unSafeMethod throws Null then return value in backupMetod
  val chainResult1 = Option(unSafeMethod).getOrElse(backupMethod) //OrElse require option value as parameter , but getOrElse is flexible

  println(chainedResult)
  println(chainResult1)

  //below is the better way way to sedign APIs and Functions
  val betterUnsafeMethod: Option[String] = None
  val betterBackupMethod: Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod orElse betterBackupMethod
  val betterChainedResult1 = betterUnsafeMethod.orElse(betterBackupMethod) //above one is syntatic sugar..we know if u have only one parameter then u can write like that

  println(betterChainedResult)
  println(betterChainedResult1)

  //functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) //its UNSAFE...if value is None it will return null pointr exception...so dont use this
  //println(noOption.get) //here u will see limitation of get

  //map,flatMap and filter
  println(myFirstOption.map(_*2))
  println(myFirstOption.filter(_ > 10))
  println(myFirstOption.flatMap(x => Option(x*10)))

  //for comprehensions
  /*use map,flatMap ,filter and for comprehensions in following exercise
    EXERCISE
    Asssume u r given a API from some other programmers

    --u r given a val called config which is network configuration,

    val config: Map[String,String] = Map(
    //here data is fetched from somewhere else amy be from someexternal sorce or config file
    //so it may be possible that some some keys dont have any values.
      "host" -> "176.45.36.1",
      "port" -> "80"
    )

    --and u r given a class called connection
    class Connection {
      def connect = "connected" //this in reality would connect to some server
    }
    object Connection {
      val random = new Random(system.nanoTime())
      def apply(host:String, port:String): Option[Connection] =
        if(random.nextBoolean()) Some(new Connection)
        else None
    }
// now what i wantwd to do is try to establish a connection, if so then print the connect method
   *
   */

  //SOLUTION
  val  config: Map[String,String] = Map(
    "host" -> "176.45.36.1",
    "port" -> "80"
  )
  class Connection{
    def connect = "connected"
  }
  object Connection{
    val random = new Random(System.nanoTime)

    def apply(host:String, port:String):Option[Connection] =

      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  val host = config.get("host") //.get will fetch the Map value in form of option // we are using .get here because we dont know it key has any value or not..otherwise we will get ambiguity
  val host1 = config("host")
  val port = config.get("port")

  println(host)
  println(host1)

  val connection = host.flatMap(h => port.flatMap(p => Connection(h,p)))
  val connectionStatus = connection.map(c => c.connect)
  connectionStatus.foreach(println)
  println(connectionStatus)

  //or below is much shorter way

  host.flatMap(h => port.flatMap(p => Connection(h,p)).map(c =>c.connect)).foreach(println)

  //using for comprehensions
  val connectionStatus1 = for {
    h <- config.get("host")
    p <- config.get("port")
    c <- Connection(h,p)
  }yield c.connect

  connectionStatus1.foreach(println)


}
