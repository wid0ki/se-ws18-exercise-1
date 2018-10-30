all: test

%.class: %.java *.java
	javac $<

test: TestSuite.class
	java -ea TinyTestJ.RunTests TestSuite

clean:
	-rm *.class TinyTestJ/*.class
