.PHONY: all
all: *.java
	javac *.java

.PHONY: clean
clean:
	rm -rf *.class
