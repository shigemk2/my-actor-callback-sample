package com.example

import akka.actor.Actor

class MyActor extends Actor {
  def receive = {
    case s: String => {
      sender ! s
    }
    case _ => {
    }
  }

}
