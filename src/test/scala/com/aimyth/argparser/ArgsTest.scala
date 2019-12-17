package com.aimyth.argparser

object ArgsTest extends App {
  //mock args
  val testArgs = Array(
    "--kafka-bootstraps=kafka1:9092,kafka2:9092",
    "--hours=0.2",
    "--numThreads=5",
    "--hack:String=c!=5",
    "--hack:String2",
    "hack=String3")

  //build and parse args
  val config = new GArgsParser(testArgs) {
    val bootstraps = StringParam("kafka-bootstraps",null)
    val hours = FloatParam("hours",0.3F)
    val numThreads = IntParam("numThreads",1,"how many thread")
    val hackString=StringParam("hack:String",defaultValue = null)
  }

  // get value you want
  val bt: String =config.bootstraps.value
  val hour: Float =config.hours.value
  val nt: Int = config.numThreads.value
  val hackString: String = config.hackString.value




}
