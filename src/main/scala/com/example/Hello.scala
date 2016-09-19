package com.example

import akka.actor.ActorSystem
import akka.util.Timeout

import scala.concurrent.duration._

object Hello {
  def main(args: Array[String]): Unit = {
    val system =ActorSystem("mySystem")
    implicit val timeout = Timeout(5 seconds)
  }
}
