////import processing.sound.*; //for the sound //<>// //<>//

////Audio beepSound = new Audio(); // to use sound online
////SoundFile beepSound; //to use sound in Processing application

//static int canvasWidth = 1900;
//static int canvasHeight = 1000;

//static int btnWidth = 120;
//static int btnHeight = 120;

//static int widgetWidth = 540;
//static int widgetHeight = 380;

//static int sideOffset = 8;
//static int topOffset = 68;

//static int fingerPrintCounter = 0;

//Button[] buttons = new Button[19];
//PImage[] images = new PImage[29];
//PImage bg;

//Clock clock;
//boolean displayTempInF = true;
//boolean menuOpen = false;
//int menuChoice = 0;
//int lastClicked = 0;
//PFont f2;
//String tempF = "62°F";
//String tempC = "16.6°C";
//PImage back;
//PImage settings;
//boolean set_date = false;
//boolean set_time = false;
//boolean timer_on = false;
//boolean signed_in = false;
//color gray_dark, black, gray, white;
//PFont f;

//String current_language;
//int curr_lang;

//Calendar cal;

//ArrayList<String> news = new ArrayList(); 
//ArrayList<String> socials = new ArrayList(); 

//int index = 0;
//int radius = 85;
//int current_time, saved_time, next;
//String date = "", time = "", sign_off = "";
//String timer = "";

//boolean healthWidgetDisplay = false;
//boolean weatherWidghetDisplay = false;


////------------------------------------------------------------
////                         SETUP                            --
////------------------------------------------------------------
//void setup() 
//{
//  cal = new Calendar(canvasWidth-sideOffset-(int)(3.75*btnWidth), topOffset+ (int)(btnHeight*1.5), (int)(btnWidth*.5), (int)(btnHeight*.5));
//  bg = loadImage("glass2.jpg");
//  size(1900, 1000);
//  image(bg, 0, 0);
//  bg.resize(1900,1000);
//  image(bg,0,0);

//  images[0] = loadImage("icon_menu.png");
//  images[1] = loadImage("icon_account.png");
//  images[2] = loadImage("icon_news.png");
//  images[3] = loadImage("icon_health.png");
//  images[4] = loadImage("icon_reddit.png");
//  images[5] = loadImage("icon_facebook.png");
//  images[6] = loadImage("icon_twitter.png");
//  images[7] = loadImage("icon_tumblr.png");
//  images[8] = loadImage("icon_fox.png");
//  images[9] = loadImage("icon_cnn.png");
//  images[10] = loadImage("icon_onion.png");
//  images[11] = loadImage("icon_bbc.png");
//  images[12] = loadImage("icon_weather.png");
//  images[13] = loadImage("icon_clock.png");
//  images[14] = loadImage("icon_messages.png");
//  images[15] = loadImage("icon_fingerprint.png");
//  images[16] = loadImage("icon_steps.png");
//  images[17] = loadImage("icon_weight.png");
//  images[18] = loadImage("icon_sleep.png");

//  //WIDGETS
//  images[19] = loadImage("widget_weather.png");
//  images[20] = loadImage("widget_facebook.png");
//  images[21] = loadImage("widget_reddit.png");
//  images[22] = loadImage("widget_twitter.png");
//  images[23] = loadImage("widget_tumblr.png");
//  images[24] = loadImage("widget_FOX.png");
//  images[25] = loadImage("widget_CNN.png");
//  images[26] = loadImage("widget_onion.png");
//  images[27] = loadImage("widget_BBC.png");

//  frameRate(30);

//  buttons[0] = new Button(0, 850, btnWidth, btnHeight, images[0]);

//  //Menu Selection
//  buttons[1] = new Button(170, 850, btnWidth, 115, images[1]);
//  buttons[2] = new Button(350, 830, 150, 150, images[2]);
//  buttons[3] = new Button(550, 850, btnWidth, 100, images[3]);

//  //Social Media
//  buttons[4] = new Button(100, 850, btnWidth, btnHeight, images[4]);
//  buttons[5] = new Button(100, 750, btnWidth, btnHeight, images[5]);
//  buttons[6] = new Button(100, 750, btnWidth, btnHeight, images[6]);
//  buttons[7] = new Button(100, 750, btnWidth, btnHeight, images[7]);

//  //News
//  buttons[8] = new Button(sideOffset + btnWidth + btnWidth/2, canvasHeight - (topOffset+2*btnHeight), btnWidth, btnHeight, images[8]);
//  buttons[9] = new Button(sideOffset + btnWidth + btnWidth/2, canvasHeight - (topOffset+3*btnHeight), btnWidth, btnHeight, images[9]);
//  buttons[10] = new Button(sideOffset + btnWidth + btnWidth/2, canvasHeight - (topOffset+4*btnHeight), btnWidth, btnHeight, images[10]);
//  buttons[11] = new Button(sideOffset + btnWidth + btnWidth/2, canvasHeight - (topOffset+5*btnHeight), btnWidth, btnHeight, images[11]);

//  // Weather
//  buttons[12] = new Button(10, 5, btnWidth, btnHeight, images[12]);

//  // Clock
//  buttons[13] = new Button(1740, 30, btnWidth, btnHeight, "");
//  clock = new Clock(1740, 30, btnWidth, btnHeight);

//  // Settings
//  buttons[14] = new Button(1775, 850, btnWidth, btnHeight, images[14]);

//  //  Fingerprint
//  buttons[15] = new Button(900, 800, btnWidth, btnHeight, images[15]);
//  buttons[16] = new Button(900, 800, btnWidth, btnHeight, "");

//  //create user buttons
//  buttons[17] = new Button(sideOffset + btnWidth*7, canvasHeight - (topOffset+2*btnHeight), btnWidth, btnHeight, "Yes");
//  buttons[18] = new Button(sideOffset + btnWidth*9, canvasHeight - (topOffset+2*btnHeight), btnWidth, btnHeight, "No");

//  f2 = createFont("DialogInput.plain", 48, true);

//  gray_dark = color(128, 128, 128); 
//  black = color(0, 0, 0); //black
//  gray = color(192, 192, 192); //gray
//  white = color(255, 255, 255); //white

//  f = createFont("DialogInput.plain", 48, true);
//  current_language = "English";

//  saved_time = 0;

//  news.add("FOX");
//  socials.add("Reddit");
//}

////------------------------------------------------------------
////                         DRAW                             --
////------------------------------------------------------------
//void draw() 
//{
//  tint(white);
//  background(bg);  

//  if (buttons[15].toggled && !signed_in) {
//    signed_in = true;
//  }

//  if (timer.equals("00:00") || timer.equals("0:00"))
//    signed_in = false;
     
//  logic();

//  if (signed_in) {
//    buttons[0].render();
//    buttons[12].render();
//  }
  
//  if (!signed_in)
//    buttons[15].render();

//  if (buttons[17].toggled) {
//    buttons[16].toggled = false;
//    textSize(48);
//    textAlign(CENTER);
//    text("Hold finger to fingerprint icon for 3 seconds", 1400, 500, btnWidth*3, btnHeight);
//  }
  
//  if(buttons[18].toggled) {
//    buttons[15].toggled = false;
//    buttons[16].toggled = false;
//    buttons[17].toggled = false; 
//  }

//  buttons[12].render();
//  clock.render();
  
//  if (buttons[13].toggled) {
//    textSize(28);
//    cal.render();
//  }
//  if (buttons[5].toggled) {
//    displayLeftWidget(20);
//  }
//  if (buttons[4].toggled) {
//    displayLeftWidget(21);
//  }
//  if (buttons[6].toggled) {
//    displayLeftWidget(22);
//  }
//  if (buttons[7].toggled) {
//    displayLeftWidget(23);
//  }
//  if (buttons[8].toggled) {
//    displayLeftWidget(24);
//  }
//  if (buttons[9].toggled) {
//    displayLeftWidget(25);
//  }
//  if (buttons[10].toggled) {
//    displayLeftWidget(26);
//  }
//  if (buttons[11].toggled) {
//    displayLeftWidget(27);
//  }
//  if (buttons[12].toggled) {
//    if (buttons[1].toggled || buttons[2].toggled || buttons[3].toggled) {
//    } else {
//      image(images[19], 50, 180, widgetWidth, widgetHeight);
//      stroke(128);
//      noFill();
//      rect(50, 180, widgetWidth, widgetHeight, 10);
//    }
//  }

//  textSize(72);
//  fill(128); 

//  if (displayTempInF)
//    text(tempF, 200, 5, 2*btnWidth, btnHeight);
//  else
//    text(tempC, 200, 5, 2*btnWidth, btnHeight);

//  textFont(f, 28);
//  text(date, 1500, 40, 2*btnWidth, btnHeight);

//  textFont(f, 32);
//  text(time, 1500, 40, 2*btnWidth, btnHeight);

//  if (menuOpen) {
//    println("RENDERING......");
//    buttons[1].render();
//    buttons[2].render();
//    buttons[3].render();

//    if (buttons[1].toggled) {
//      orderButtons(socials);
//      for (int i=4; i < 8; i++) {
//        if (buttons[i].shouldDisplay) {
//          buttons[i].render();
//        }
//      }
//    }

//    if (buttons[2].toggled) {
//      orderButtons(news);
//      for (int i=8; i < 12; i++) {
//        if (buttons[i].shouldDisplay) {
//          buttons[i].render();
//        }
//      }
//    }

//    if (buttons[3].toggled) {
//      stroke(128);
//      noFill();
//      rect(50, 200, widgetWidth, widgetHeight, 10);
      
//      image(images[16], 50, 200, btnWidth, btnHeight);
//      textSize(22);
//      text("Yesterday:    4673", 60, 350, btnWidth+20, btnHeight*2);
//      text("Today:     4268", 60, 500, btnWidth, btnHeight*2);

//      image(images[17], 250, 200, btnWidth, btnHeight);
//      text("Last week:    160lb", 230, 350, btnWidth+20, btnHeight*2);
//      text("This week:    161lb", 230, 500, btnWidth+20, btnHeight*2);

//      image(images[18], 450, 200, btnWidth, btnHeight);
//      text("Yesterday:   7 hours", 430, 350, btnWidth+20, btnHeight*2);
//      text("  Today:      8 hours", 430, 500, btnWidth+20, btnHeight*2);
//    }
//  }  
//}

//void displayLeftWidget(int imgNum) {
//  tint(white);
//  image(images[imgNum], 1300, 550, widgetWidth, widgetHeight);
//  stroke(128);
//  noFill();
//  rect(1300, 550, widgetWidth, widgetHeight, 11);
//}


//void orderButtons(ArrayList<String> list) {
//  int size = list.size();

//  for (int i=4; i<12; i++) {

//    buttons[i].shouldDisplay = false;
//  }

//  for (int i=0; i < size; i++) {

//    Button btn = new Button(5, canvasHeight - (topOffset+(i+2)*btnHeight), btnWidth, btnHeight, images[0]);

//    String name = list.get(i);

//    switch(name) {

//    case "Reddit":
//      btn.setImage(images[4]);
//      btn.shouldDisplay = true;
//      btn.toggled = buttons[4].toggled;
//      buttons[4] = btn;
//      break;
//    case "Facebook":
//      btn.setImage(images[5]);
//      btn.shouldDisplay = true;
//      btn.toggled = buttons[5].toggled;

//      buttons[5] = btn;
//      break;
//    case "Twitter":
//      btn.setImage(images[6]);
//      btn.shouldDisplay = true;
//      btn.toggled = buttons[6].toggled;

//      buttons[6] = btn;
//      break;
//    case "Tumblr":
//      btn.setImage(images[7]);
//      btn.shouldDisplay = true;
//      btn.toggled = buttons[7].toggled;

//      buttons[7] = btn;
//      break;
//    case "FOX":
//      btn.setImage(images[8]);
//      btn.shouldDisplay = true;
//      btn.toggled = buttons[8].toggled;

//      buttons[8] = btn;
//      break;
//    case "CNN":
//      btn.setImage(images[9]);
//      btn.shouldDisplay = true;
//      btn.toggled = buttons[9].toggled;

//      buttons[9] = btn;
//      break;
//    case "The Onion":
//      btn.setImage(images[10]);
//      btn.shouldDisplay = true;
//      btn.toggled = buttons[10].toggled;

//      buttons[10] = btn;
//      break;
//    case "BBC News":
//      btn.setImage(images[11]);
//      btn.shouldDisplay = true;
//      btn.toggled = buttons[11].toggled;

//      buttons[11] = btn;
//      break;
//    }
//  }
//}


///**************************
// * method to show numpad   *
// **************************/
//void show_numpad()
//{

//  fill(gray);
//  ellipse(1100, 600, 170, 170);
//  textFont(f, 70);
//  fill(black);
//  text("7", 1080, 620);

//  fill(gray);
//  ellipse(1330, 600, 170, 170);
//  textFont(f, 70);
//  fill(black);
//  text("8", 1310, 620);

//  fill(gray);
//  ellipse(1560, 600, 170, 170);
//  textFont(f, 70);
//  fill(black);
//  text("9", 1540, 620);

//  /******* second row ****************/
//  fill(gray);
//  ellipse(1100, 800, 170, 170);
//  textFont(f, 70);
//  fill(black);
//  text("4", 1080, 820);

//  fill(gray);
//  ellipse(1330, 800, 170, 170);
//  textFont(f, 70);
//  fill(black);
//  text("5", 1310, 820);

//  fill(gray);
//  ellipse(1560, 800, 170, 170);
//  textFont(f, 70);
//  fill(black);
//  text("6", 1540, 820);

//  /******* third row ****************/
//  fill(gray);
//  ellipse(1100, 1000, 170, 170);
//  textFont(f, 70);
//  fill(black);
//  text("1", 1080, 1020);

//  fill(gray);
//  ellipse(1330, 1000, 170, 170);
//  textFont(f, 70);
//  fill(black);
//  text("2", 1310, 1020);

//  fill(gray);
//  ellipse(1560, 1000, 170, 170);
//  textFont(f, 70);
//  fill(black);
//  text("3", 1540, 1020);

//  /******* fourth row ****************/
//  fill(gray);
//  rect(1000, 1130, 210, 150);
//  textFont(f, 65);
//  fill(black);
//  text("clear", 1015, 1215);

//  fill(gray);
//  ellipse(1330, 1200, 170, 170);
//  textFont(f, 70);
//  fill(black);
//  text("0", 1310, 1220);

//  fill(gray);
//  rect(1460, 1130, 210, 150);
//  textFont(f, 65);
//  fill(black);
//  text("done", 1475, 1215);
//}


//String addToDate(String date, String s) {
//  if (date.length() < 10) {  
//    date = date + s;
//    if (date.length() == 2 || date.length() == 5)
//      date = date + "/";
//  }
//  return date;
//}



//String addToTime(String time, String s) {
//  if (time.length() < 5) {  
//    time = time + s;
//    if (time.length() == 2)
//      time = time + ":";
//  }
//  return time;
//}


//boolean isNum(char c)
//{
//  int x = c;
//  //println("x = " + x );
//  if (x < 48 && x > 57)
//    return false;
//  else
//    return true;
//}


//String updateTimer()
//{
//  String t = "";
//  String min;
//  String sec = timer.substring(timer.indexOf(":") + 1);

//  if (timer.lastIndexOf(":") == 2)
//    min = timer.substring(0, 2);
//  else
//    min = timer.substring(0, 1);

//  if (sec.length() < 2)
//    sec = "0" + sec;

//  //println("min = " + min);
//  if (min.equals("00") && sec.equals("00"))
//  {
//    return "00:00";
//  } else if (sec.equals("00"))
//  {
//    //println("min2 = " + str(parseInt(min) - 1));
//    if (min.equals("01") || min.equals("1"))
//      min = "00";
//    else if (!min.equals("00") && !min.equals("01"))
//      min = str(parseInt(min) - 1);

//    sec = "59";
//  } else if (!sec.equals("00"))
//  {
//    //println("x = " + parseInt(sec));
//    sec = str(parseInt(sec) - 1);
//  } else if (!min.equals("00"))
//  {
//    min = str(parseInt(min) - 1);
//  }

//  if (parseInt(min) < 10)
//    min = "0" + min.charAt(1);

//  if (parseInt(sec) < 10)
//    sec = "0" + sec;

//  t = min + ":" + sec;

//  return t;
//} 


//void logic() {
//  menuOpen = buttons[0].toggled;

//  if (menuOpen)
//    buttons[12].toggled = false;
//  if (!menuOpen && lastClicked==0) {
//    for (int i=1; i< 12; i++) {
//      if (buttons[i].toggled) {
//        buttons[i].toggled = false;
//        buttons[i].changeColor();
//      }
//    }
//  }
//  if (!buttons[1].toggled) {
//    for (int i=4; i< 8; i++) {
//      if (buttons[i].toggled) {
//        buttons[i].toggled = false;
//        buttons[i].changeColor();
//      }
//    }
//  }
//  if (!buttons[2].toggled) {
//    for (int i=8; i< 12; i++) {
//      if (buttons[i].toggled) {
//        buttons[i].toggled = false;
//        buttons[i].changeColor();
//      }
//    }
//  }
//}
