package com.alaphi.http4sserver

import cats.effect._
import fs2.{Stream, StreamApp}
import fs2.StreamApp.ExitCode
import org.http4s.server.blaze._

import scala.concurrent.ExecutionContext.Implicits.global

object HelloWorldServer extends StreamApp[IO] {

  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] =
    BlazeBuilder[IO]
      .bindHttp(8080, "0.0.0.0")
      .mountService(HelloWorldRoutes.routes, "/")
      .serve

}


