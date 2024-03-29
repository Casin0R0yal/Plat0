JFLAGS = -g
JC = javac
OUT_DIR = out

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java -d $(OUT_DIR)

plato: $(OUT_DIR)/src/Plato.class
	java -cp $(OUT_DIR) src.Plato

$(OUT_DIR)/%.class: src/%.java
	$(JC) $(JFLAGS) $< -d $(OUT_DIR)

clean:
	$(RM) -r $(OUT_DIR)/*	
	make -C Monopoly/ProjMonopoly clean && make -C Connect4 clean && make -C uno/src clean && make -C Solitaire/src clean
