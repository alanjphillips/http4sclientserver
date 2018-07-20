package com.alaphi.http4sclient

import cats.effect.IO
import scala.io.StdIn

object HelloWorldApp extends App {

  import HelloWorldClient._

  val sayHello = for {
    _    <- IO { println("Enter your name to send to the HelloWorld Server:") }
    name <- IO { StdIn.readLine }
    json <- hello(name)
    _    <- IO { println(json) }
  } yield ()

  sayHello.unsafeRunSync()

}
