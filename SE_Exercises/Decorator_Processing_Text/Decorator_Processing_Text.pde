void setup() {
    size(700, 700);
    background(255);
    fill(0);
    textSize(25);
    textAlign(CENTER, CENTER);
    rectMode(CENTER);
}

void draw() {
    background(255);
    Text t = new SimpleText();
    Text bt = new ConcreteBoxedText(t);
    
    if (mousePressed)
      bt.print();
    else t.print();
}

/** Component */
public interface Text {
    public void print();
}

/** Concrete Component */
public class SimpleText implements Text {
    public void print() {
        text("Hello World!", mouseX, mouseY);
    }
}

/** Decorator */
public class BoxedText implements Text {
    private Text st;

    public BoxedText(Text st) {
        this.st = st;
    }

    public void print() {
        st.print();
    }
}

/** Concrete Decorator */
public class ConcreteBoxedText extends BoxedText {
    public ConcreteBoxedText(Text st) {
        super(st);
    }

    public void print() {
        noFill();
        rect(mouseX, mouseY, 200, 50);
        fill(0);
        super.print();
    }
}
