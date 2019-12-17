package com.aimyth.argparser

import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex

/**
 * parser util to parse commandline args like "--max-size=100 --userName=Gavin"
 * @param args
 */
@SerialVersionUID(-1L)
abstract class GArgsParser(args:Array[String]) extends Serializable {
  val patten = new Regex("^--(.*?)=(.*)$")
  private val  data: Map[String, String] = args
    .map(_.strip())
    .map(patten.findAllMatchIn).filter(_.hasNext).map(_.next())
    .map(ary=>ary.group(1)->ary.group(2))
    .toMap
  /**
   * private class to build param config
   * you can add new method to support complex param type
   * @param name
   * @param defaultValue
   * @param help
   * @tparam T
   */
  @SerialVersionUID(-1L)
  protected abstract class Param[T](val name:String, val defaultValue:T, val help:String) extends Serializable {
    //how to parse value from string
    protected def parse(value:String):T
    //for print help
    val dataType:String
    //get args value
    val value:T= if(data.contains(name)) parse(data(name)) else defaultValue
  }

  private val argsList: ListBuffer[Param[_]] =ListBuffer[Param[_]]()
  /**
   * add string param
   * @param name
   * @param defaultValue
   * @param help
   * @return
   */
  def StringParam(name:String,defaultValue:String,help:String=""):Param[String]={
    val param = new Param[String](name,defaultValue,help) {
      override protected def parse(value: String): String = value

      override val dataType: String = "String"
    }
    argsList.append(param)
    param
  }
  /**
   * add float param
   * @param name
   * @param defaultValue
   * @param help
   * @return
   */
  def FloatParam(name:String,defaultValue:Float,help:String=""):Param[Float]={
    val param = new Param[Float](name,defaultValue,help) {
      override protected def parse(value: String): Float = value.toFloat

      override val dataType: String = "Float"
    }
    argsList.append(param)
    param
  }
  /**
   * add int param
   * @param name
   * @param defaultValue
   * @param help
   * @return
   */
  def IntParam(name:String,defaultValue:Int,help:String=""):Param[Int]={
    val param = new Param[Int](name,defaultValue,help) {
      override protected def parse(value: String): Int = value.toInt

      override val dataType: String = "Int"
    }
    argsList.append(param)
    param
  }

  /**
   * add long param
   * @param name
   * @param defaultValue
   * @param help
   * @return
   */
  def LongParam(name:String,defaultValue:Long,help:String=""):Param[Long]={
    val param = new Param[Long](name,defaultValue,help) {
      override protected def parse(value: String): Long = value.toInt

      override val dataType: String = "Long"
    }
    argsList.append(param)
    param
  }

  /**
   * list registered param
   * @return
   */
  def listParams():Seq[Param[_]]=argsList


  /**
   * for print
   * @return
   */
  def printParams():String=listParams().map(pc=>{
    s"name:\t${pc.name}\ndata type:\t${pc.dataType}\ndefault value:\t${pc.defaultValue}\nhelp:\t${pc.help}\ncurrent value:\t${pc.value}\n"
  }).mkString("\n")



}
