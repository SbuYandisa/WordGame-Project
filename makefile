JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=WordDictionary.class WordRecord.class Score.class WordPanel.class WordApp.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class








