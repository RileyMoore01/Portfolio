import controlP5.*;
ControlP5 cp5;
Timer startTimer;

//Floor level global prop.
color floorColor, baseColor, currentColor;

int colour = #ce2fa9;
boolean isPressed = false;
char level = '1';
Timer startTimer;

//Button Declartions
Button floor1, floor2, floor3, floor4;
Button doorOpen, doorClose, alarm;

void setup() {
 size(500,800); //Set up GUI
 floorColor = color(255);
 baseColor = color(102);
 currentColor = baseColor;
 startTimer = new Timer(10);
  
 cp5 = new ControlP5(this);  //Initailize ControlP5
 //Add new cp5 control buttons
 floor1 = cp5.addButton("L1").setSize(40,20).setPosition(175,350);
 floor2 = cp5.addButton("L2").setSize(40,20).setPosition(175,400);
 floor3 = cp5.addButton("L3").setSize(40,20).setPosition(175,450);
 floor4 = cp5.addButton("L4").setSize(40,20).setPosition(175,500);
 doorOpen = cp5.addButton("open").setSize(40,20).setPosition(250,350);
 doorClose = cp5.addButton("close").setSize(40,20).setPosition(250,400);
 alarm = cp5.addButton("alarm").setSize(40,20).setPosition(250,450);
  
  //Event listeners for each button
  floor1.onRelease(new CallbackListener()
  {
    public void controlEvent(CallbackEvent theEvent)
    {
      println("clicked");
      colour = #FF0000;
      floor1.setColorBackground(colour); 
    }
  }
  );
  
  floor2.onRelease(new CallbackListener()
  {
    public void controlEvent(CallbackEvent theEvent)
    {
      println("clicked");
      colour = #FF0000;
      floor2.setColorBackground(colour); 
    }
  }
  );
  
  floor3.onRelease(new CallbackListener()
  {
    public void controlEvent(CallbackEvent theEvent)
    {
      println("clicked");
      colour = #FF0000;
      floor3.setColorBackground(colour); 
    }
  }
  );
  
  floor4.onRelease(new CallbackListener()
  {
    public void controlEvent(CallbackEvent theEvent)
    {
      println("clicked");
      colour = #FF0000;
      floor4.setColorBackground(colour); 
    }
  }
  );
  
  doorOpen.onRelease(new CallbackListener()
  {
    public void controlEvent(CallbackEvent theEvent)
    {
      println("clicked");
      colour = #FF0000;
      doorOpen.setColorBackground(colour); 
    }
  }
  );
  
  doorClose.onRelease(new CallbackListener()
  {
    public void controlEvent(CallbackEvent theEvent)
    {
      println("clicked");
      colour = #FF0000;
      doorClose.setColorBackground(colour); 
    }
  }
  );
  
  alarm.onRelease(new CallbackListener()
  {
    public void controlEvent(CallbackEvent theEvent)
    {
      println("clicked");
      colour = #FF0000;
      alarm.setColorBackground(colour); 
    }
  }
  );
}

void draw() {
  background(0);
  
  //Fill in the rectangles on the screen
  fill(102);
  rect(185, 75, 100, 100);  //Level display container
  fill(255);
  rect(208, 80, 50, 50);  //Level display box
  fill(255, 0, 0);
  rect(185, 200, 100, 100); //Fire Operations box
  fill(102);
  rect(185, 600, 100, 100);  //Help box
  
  //Declare and fill text colors
  fill(255);
  textSize(13);
  text("Fire Operation", 190, 220);
  textSize(10);
  text("HELP", 190, 620);
  fill(255, 0, 0);
  textSize(45);
  text(level, 220, 120);
}

void f1() {
  println("hello world");
}
