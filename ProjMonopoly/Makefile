JFLAGS = -g
JC = javac
OUT_DIR = out

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java -d $(OUT_DIR)

server: $(OUT_DIR)/src/Server.class
	java -cp $(OUT_DIR) src.Server

player: $(OUT_DIR)/src/Player.class
	java -cp $(OUT_DIR) src.Player

$(OUT_DIR)/src/Server.class: src/Server.java
	$(JC) $(JFLAGS) $< -d $(OUT_DIR)

$(OUT_DIR)/src/Player.class: src/Player.java
	$(JC) $(JFLAGS) $< -d $(OUT_DIR)

clean:
	$(RM) -r $(OUT_DIR)/*
