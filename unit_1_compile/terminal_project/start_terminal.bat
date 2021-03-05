echo Main-Class: ua.com.alevel.Test>MANIFEST.MF
mkdir  build\classes
mkdir  build\jar
javac -sourcepath src/ -d build/classes/ src/ua/com/alevel/Test.java
jar cfm build/jar/test_terminal.jar MANIFEST.MF -C build/classes/ .
java -jar build/jar/test_terminal.jar