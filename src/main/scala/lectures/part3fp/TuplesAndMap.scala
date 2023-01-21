package lectures.part3fp

object TuplesAndMap extends App{
  val aTuple = new Tuple2(2,"hllo, scala") //Tuple2[Int,String] or we can say  (Int,String)

  // u can access members of tuples using _1 and _2 -----_1 is first elemt, _2 is second elem
  println(aTuple._1)
  println(aTuple.copy(_2 = "goodby java"))//it will replace the scond value
  println(aTuple.swap)//it will swap the elem ---("hello, scala", 2)

  //Map
  //Map contains key value pairs...u can consider MAP as a collection of tuples...
  // each tuple denote particular key value pair entry in map
   val aMap:Map[String, Int] =Map()
  val phonebook = Map(("jim",555),("daniel",789)).withDefaultValue(-1)
  val phonebook1 = Map("jim1"->555,"danil1"->789).withDefaultValue("wrong key eneterd") //this is another way to write...use -> sign insyead of tuple
  println(phonebook)
  println(phonebook1)

  // u can query Map using contains method..which takes a key and returns a boolean
  println(phonebook.contains("jim")) //return boolean
  println(phonebook("jim"))//here if u use apply method it will return corrrosponding value
  println(phonebook1("mary"))//if u use key which does not exist then it will crash...to protect against it use .withDefaultValue

  //add a pairing
  val newPairing = "saachin"->5
  val newPairing2 = ("lidhu"->6) //as we know we can add pair using tuple also
  val newPhonebook = phonebook + newPairing2 + newPairing
  println(newPhonebook)

  //functions on map, flatMap and filter
  println(newPhonebook.map(pair => pair._1.toUpperCase -> pair._2))

  //filterKeys
  println(phonebook.view.filterKeys(_.startsWith("j")))

  //mapValues
  println(phonebook.view.mapValues(_*10))//works on values directly, not keys

  //conversions to other collections
  println(phonebook.toList) // map to list
  println(List(("sachin",5),("don",1)).toMap) //list to Map

  //groupBy
  val names = List("aaashsih","sachin","alok","rashul","akul","ram")
  println(names.groupBy(_.charAt(0))) //it will create key value pair..check the output

  /*EXERCISE
   * 1...What will happen if i had 2 original enteries "Jim"->555 and "JIM"->900
   * ans---initially it will get added to map and u can see it,
   * but if u apply some operation like lowercase then resultant results will be duplicate keys
   * so it will reomove one, so make sure ur result of maps dont ocuur in same keys.
   * 2...Design a overly simplifies social network based on Maps
   *     example--
   *          Person =String
   *          --add a person to the network
   *          --remove
   *          --friend(mutual)
   *          --unfriend
   *    also we want some stats like:
   *        --number of friends of a person
   *        --person with most friends
   *        --how many people have no friends
   *        --if there is any social connection between 2 people (direct or not)--mutual friends in short
   *
   *
   */
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person ->Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String,Set[String]]={
    val friendA = network(a)
    val friendB = network(b)

    network + (a ->(friendA + b)) + (b ->(friendB + a))

  }
  def unfriend(network: Map[String,Set[String]], a: String, b:String): Map[String,Set[String]] = {
    val friendA = network(a)
    val friendB = network(b)

    network + (a->(friendA - b)) + (b ->(friendB - a))

  }
  def remove(network: Map[String,Set[String]],person: String): Map[String,Set[String]] ={
    def removeAux(friends:Set[String], networkAcc: Map[String,Set[String]]): Map[String, Set[String]] ={
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }
    val unfriended = removeAux(network(person), network)
    unfriended - person

  }
  val empty: Map[String,Set[String]] = Map()
  val network = add(add(empty,"Bob"),"Mary")
   println(network)
   println(friend(network,"Bob","Mary"))
   println(unfriend(friend(network,"Bob","Mary"),"Bob","Mary"))
   println(remove(friend(network,"Bob","Mary"),"Bob"))

  //lets build a small network with Jim, Bob and Mary
  //Bob and Mary are friends, Bob and Jim are friends but Jim and Mary are not friends
  val people =add(add(add(empty,"Bob"),"Mary"),"Jim")
  val jimBob = friend(people,"Bob","Jim")
  val testNet = friend(jimBob,"Bob","Mary")

  println(testNet)

  def nFriends(network: Map[String,Set[String]],person:String):Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  println(nFriends(testNet,"Bob"))

  def mostFriends(network: Map[String, Set[String]]): String={
    network.maxBy(x=>x._2.size)._1
  }

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
   // network.view.filterKeys(x=>network(x).isEmpty).size --below is the shorted way
    network.view.count(x=>x._2.isEmpty)
  }

  println(nPeopleWithNoFriends(testNet))

  //if there is social connection direct or indirect between a and b
  def socialConection(network: Map[String, Set[String]], a: String, b:String):Boolean ={
    def bfs(target:String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean ={
      if(discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if(person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target,consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }
  println(socialConection(testNet,"Mary","Jim"))
  println(socialConection(network,"Mary","Jim"))

}
