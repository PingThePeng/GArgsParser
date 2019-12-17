#GArgsParser

An scala commandline args parser. Parse args like:

`--name=GargsParser`

Build:
```scala
val config = new GArgsParser(testArgs) {
    val bootstraps = StringParam("kafka-bootstraps",null)
    val hours = FloatParam("hours",0.3F)
    val numThreads = IntParam("numThreads",1,"how many thread")
    val hackString=StringParam("hack:String",defaultValue = null)
  }
```

Get args:
```scala
  val bt: String =config.bootstraps.value

```

Support `Int`,`Long`,`Float`,`String`

No time to upload to maven repository ,So may be just copy the code... 

