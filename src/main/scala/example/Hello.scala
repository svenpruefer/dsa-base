package example

/**
  * Main entry point of HelloWorld :-)
  */
object Hello extends Greeting with App {
    println(greeting)
}

trait Greeting {
    lazy val greeting: String = "hello"
}
