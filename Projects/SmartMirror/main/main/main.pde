static int canvasWidth = 1900;
static int canvasHeight = 1000;
static int btnWidth = 120;
static int btnHeight = 120;
static int widgetWidth = 540;
static int widgetHeight = 380;
static int sideOffset = 8;
static int topOffset = 68;
static int fingerPrintCounter = 0;

Button[] buttons = new Button[19];
PImage[] images = new PImage[29];
PImage bg;

Clock clock;
boolean displayTempInF = true;
boolean menuOpen = false;
boolean set_date = false;
boolean set_time = false;
boolean timer_on = false;
boolean signed_in = false;
int menuChoice = 0;
int lastClicked = 0;
PFont f;
PFont f2;
String tempF = "42°F";
String tempC = "5.6°C";
PImage back;
color gray_dark, black, gray, white;

Calendar cal;

ArrayList<String> news = new ArrayList(); 
ArrayList<String> socials = new ArrayList(); 

int index = 0;
int radius = 85;
int current_time, saved_time, next;
String date = "", time = "", sign_off = "";
String timer = "";

boolean healthWidgetDisplay = false;
boolean weatherWidghetDisplay = false;

void setup() 
{
  cal = new Calendar(canvasWidth-sideOffset-(int)(3.75*btnWidth), topOffset+ (int)(btnHeight*1.5), (int)(btnWidth*.5), (int)(btnHeight*.5));
  bg = loadImage("glass2.jpg");
  size(1900, 1000);
  image(bg, 0, 0);
  bg.resize(1900,1000);
  image(bg,0,0);

  images[0] = loadImage("icon_menu.png");
  images[1] = loadImage("icon_account.png");
  images[2] = loadImage("icon_news.png");
  images[3] = loadImage("icon_health.png");
  images[4] = loadImage("icon_reddit.png");
  images[5] = loadImage("icon_facebook.png");
  images[6] = loadImage("icon_twitter.png");
  images[7] = loadImage("icon_tumblr.png");
  images[8] = loadImage("icon_fox.png");
  images[9] = loadImage("icon_cnn.png");
  images[10] = loadImage("icon_onion.png");
  images[11] = loadImage("icon_bbc.png");
  images[12] = loadImage("icon_weather.png");
  images[13] = loadImage("icon_clock.png");
  images[14] = loadImage("icon_messages.png");
  images[15] = loadImage("icon_fingerprint.png");
  images[16] = loadImage("icon_steps.png");
  images[17] = loadImage("icon_weight.png");
  images[18] = loadImage("icon_sleep.png");

  //WIDGETS
  images[19] = loadImage("widget_weather.png");
  images[20] = loadImage("widget_facebook.png");
  images[21] = loadImage("widget_reddit.png");
  images[22] = loadImage("widget_twitter.png");
  images[23] = loadImage("widget_tumblr.png");
  images[24] = loadImage("widget_FOX.png");
  images[25] = loadImage("widget_CNN.png");
  images[26] = loadImage("widget_onion.png");
  images[27] = loadImage("widget_BBC.png");

  frameRate(30);

  buttons[0] = new Button(0, 850, btnWidth, btnHeight, images[0]);
  buttons[1] = new Button(170, 850, btnWidth, 115, images[1]);
  buttons[2] = new Button(350, 830, 150, 150, images[2]);
  buttons[3] = new Button(550, 850, btnWidth, 100, images[3]);
  buttons[4] = new Button(100, 850, btnWidth, btnHeight, images[4]);
  buttons[5] = new Button(100, 750, btnWidth, btnHeight, images[5]);
  buttons[6] = new Button(100, 750, btnWidth, btnHeight, images[6]);
  buttons[7] = new Button(100, 750, btnWidth, btnHeight, images[7]);
  buttons[8] = new Button(sideOffset + btnWidth + btnWidth/2, canvasHeight - (topOffset+2*btnHeight), btnWidth, btnHeight, images[8]);
  buttons[9] = new Button(sideOffset + btnWidth + btnWidth/2, canvasHeight - (topOffset+3*btnHeight), btnWidth, btnHeight, images[9]);
  buttons[10] = new Button(sideOffset + btnWidth + btnWidth/2, canvasHeight - (topOffset+4*btnHeight), btnWidth, btnHeight, images[10]);
  buttons[11] = new Button(sideOffset + btnWidth + btnWidth/2, canvasHeight - (topOffset+5*btnHeight), btnWidth, btnHeight, images[11]);
  buttons[12] = new Button(10, 5, btnWidth, btnHeight, images[12]);
  buttons[13] = new Button(1740, 30, btnWidth, btnHeight, "");
  clock = new Clock(1740, 30, btnWidth, btnHeight);
  buttons[14] = new Button(1775, 850, btnWidth, btnHeight, images[14]);
  buttons[15] = new Button(900, 800, btnWidth, btnHeight, images[15]);
  buttons[16] = new Button(900, 800, btnWidth, btnHeight, "");
  buttons[17] = new Button(sideOffset + btnWidth*7, canvasHeight - (topOffset+2*btnHeight), btnWidth, btnHeight, "Yes");
  buttons[18] = new Button(sideOffset + btnWidth*9, canvasHeight - (topOffset+2*btnHeight), btnWidth, btnHeight, "No");

  f2 = createFont("DialogInput.plain", 48, true);

  gray_dark = color(128, 128, 128); 
  black = color(0, 0, 0); //black
  gray = color(192, 192, 192); //gray
  white = color(255, 255, 255); //white

  f = createFont("DialogInput.plain", 48, true);
  saved_time = 0;

  news.add("FOX");
  socials.add("Reddit");
}

void draw() 
{
  tint(white);
  background(bg);  

  if (buttons[15].toggled && !signed_in) {
    signed_in = true;
  }
  if (timer.equals("00:00") || timer.equals("0:00"))
    signed_in = false;
    
  logic();

  if (signed_in) {
    buttons[0].render();
    buttons[12].render();
  } 
  if (!signed_in)
    buttons[15].render();
  if (buttons[17].toggled) {
    buttons[16].toggled = false;
    textSize(48);
    textAlign(CENTER);
    text("Hold finger to fingerprint icon for 3 seconds", 1400, 500, btnWidth*3, btnHeight);
  }
  if(buttons[18].toggled) {
    buttons[15].toggled = false;
    buttons[16].toggled = false;
    buttons[17].toggled = false; 
  }
  buttons[12].render();
  clock.render();
  if (buttons[13].toggled) {
    textSize(28);
    cal.render();
  } if (buttons[5].toggled) {
    displayLeftWidget(20);
  } if (buttons[4].toggled) {
    displayLeftWidget(21);
  } if (buttons[6].toggled) {
    displayLeftWidget(22);
  } if (buttons[7].toggled) {
    displayLeftWidget(23);
  } if (buttons[8].toggled) {
    displayLeftWidget(24);
  } if (buttons[9].toggled) {
    displayLeftWidget(25);
  } if (buttons[10].toggled) {
    displayLeftWidget(26);
  } if (buttons[11].toggled) {
    displayLeftWidget(27);
  } if (buttons[12].toggled) {
    if (buttons[1].toggled || buttons[2].toggled || buttons[3].toggled) {
    } else {
      image(images[19], 50, 180, widgetWidth, widgetHeight);
      stroke(128);
      noFill();
      rect(50, 180, widgetWidth, widgetHeight, 10);
    }
  }
  textSize(72);
  fill(128); 
  if (displayTempInF)
    text(tempF, 200, 5, 2*btnWidth, btnHeight);
  else
    text(tempC, 200, 5, 2*btnWidth, btnHeight);

  textFont(f, 28);
  text(date, 1500, 40, 2*btnWidth, btnHeight);

  textFont(f, 32);
  text(time, 1500, 40, 2*btnWidth, btnHeight);
  if (menuOpen) {
    println("RENDERING......");
    buttons[1].render();
    buttons[2].render();
    buttons[3].render();
    if (buttons[1].toggled) {
      orderButtons(socials);
      for (int i=4; i < 8; i++) {
        if (buttons[i].shouldDisplay) {
          buttons[i].render();
        }
      }
    }
    if (buttons[2].toggled) {
      orderButtons(news);
      for (int i=8; i < 12; i++) {
        if (buttons[i].shouldDisplay) {
          buttons[i].render();
        }
      }
    }
    if (buttons[3].toggled) {
      stroke(128);
      noFill();
      rect(50, 200, widgetWidth, widgetHeight, 10);
      
      image(images[16], 50, 200, btnWidth, btnHeight);
      textSize(22);
      text("Yesterday:    4673", 60, 350, btnWidth+20, btnHeight*2);
      text("Today:     4268", 60, 500, btnWidth, btnHeight*2);

      image(images[17], 250, 200, btnWidth, btnHeight);
      text("Last week:    160lb", 230, 350, btnWidth+20, btnHeight*2);
      text("This week:    161lb", 230, 500, btnWidth+20, btnHeight*2);

      image(images[18], 450, 200, btnWidth, btnHeight);
      text("Yesterday:   7 hours", 430, 350, btnWidth+20, btnHeight*2);
      text("  Today:      8 hours", 430, 500, btnWidth+20, btnHeight*2);
    }
  }  
}

void displayLeftWidget(int imgNum) {
  tint(white);
  image(images[imgNum], 1300, 550, widgetWidth, widgetHeight);
  stroke(128);
  noFill();
  rect(1300, 550, widgetWidth, widgetHeight, 11);
}
void orderButtons(ArrayList<String> list) {
  int size = list.size();
  for (int i=4; i<12; i++) {
    buttons[i].shouldDisplay = false;
  }
  for (int i=0; i < size; i++) {
    Button btn = new Button(5, canvasHeight - (topOffset+(i+2)*btnHeight), btnWidth, btnHeight, images[0]);
    String name = list.get(i);
    switch(name) {
      case "Reddit":
        btn.setImage(images[4]);
        btn.shouldDisplay = true;
        btn.toggled = buttons[4].toggled;
        buttons[4] = btn;
        break;
      case "FOX":
        btn.setImage(images[8]);
        btn.shouldDisplay = true;
        btn.toggled = buttons[8].toggled;
        buttons[8] = btn;
        break;
    }
  }
}
String addToDate(String date, String s) {
  if (date.length() < 10) {  
    date = date + s;
    if (date.length() == 2 || date.length() == 5)
      date = date + "/";
  }
  return date;
}
String addToTime(String time, String s) {
  if (time.length() < 5) {  
    time = time + s;
    if (time.length() == 2)
      time = time + ":";
  }
  return time;
}
boolean isNum(char c) {
  int x = c;
  if (x < 48 && x > 57)
    return false;
  else
    return true;
}
String updateTimer() {
  String t = "";
  String min;
  String sec = timer.substring(timer.indexOf(":") + 1);

  if (timer.lastIndexOf(":") == 2)
    min = timer.substring(0, 2);
  else
    min = timer.substring(0, 1);
  if (sec.length() < 2)
    sec = "0" + sec;
  if (min.equals("00") && sec.equals("00")){
    return "00:00";
  } else if (sec.equals("00")) {
    //println("min2 = " + str(parseInt(min) - 1));
    if (min.equals("01") || min.equals("1"))
      min = "00";
    else if (!min.equals("00") && !min.equals("01"))
      min = str(parseInt(min) - 1);

    sec = "59";
  } else if (!sec.equals("00")) {
    sec = str(parseInt(sec) - 1);
  } else if (!min.equals("00")){
    min = str(parseInt(min) - 1);
  }
  if (parseInt(min) < 10)
    min = "0" + min.charAt(1);
  if (parseInt(sec) < 10)
    sec = "0" + sec;
  t = min + ":" + sec;
  return t;
} 

void logic() {
  menuOpen = buttons[0].toggled;
  if (menuOpen)
    buttons[12].toggled = false;
  if (!menuOpen && lastClicked==0) {
    for (int i=1; i< 12; i++) {
      if (buttons[i].toggled) {
        buttons[i].toggled = false;
        buttons[i].changeColor();
      }
    }
  }
  if (!buttons[1].toggled) {
    for (int i=4; i< 8; i++) {
      if (buttons[i].toggled) {
        buttons[i].toggled = false;
        buttons[i].changeColor();
      }
    }
  }
  if (!buttons[2].toggled) {
    for (int i=8; i< 12; i++) {
      if (buttons[i].toggled) {
        buttons[i].toggled = false;
        buttons[i].changeColor();
      }
    }
  }
}
////////////////////////////////////////////
//            OPTION CLASS                //
////////////////////////////////////////////
class Option
{
  private boolean[] clicked;
  private String[][] name;
  private int x;
  private int y;
  private boolean news_picked;
  private boolean social_picked;
  private color news_color;
  private color social_color;
  private color lang_color;
  private color enabled_color;
 
  Option(String[][] s,int xpos, int ypos)
  {
    name = s;
    x = xpos;
    y = ypos;
    news_color = gray_dark;
    social_color = gray_dark;
    lang_color = gray_dark;
    enabled_color = gray_dark;
    news_picked = false;
    social_picked = false;
    clicked = new boolean[6];
    for(boolean b : clicked)
        b = false;
  }
  void setOptionClicked(boolean t, int index){
    clicked[index] = t;
  }
  boolean isClicked(int index){
    return clicked[index];
  }
  void setNewsClicked(boolean t){
    news_picked = t;
  }
  boolean isNewsClicked(){
    return news_picked;
  }
  void setSocialClicked(boolean t){
    social_picked = t;
  }
  boolean isSocialClicked(){
    return social_picked;
  }
  int getX(){
    return x;
  }  
  int getY(){
    return y;
  }
  void setNewsColor(color c){
      news_color = c;
  }
  void setSocialColor(color c){
    social_color = c;
  }
  color getNewsColor(){
      return news_color;
  }
  color getSocialColor(){
    return social_color;
  }
  color getLanguageColor(){
    return lang_color;
  }
  color getEnabledColor(){
      return enabled_color;
  }
  void setEnabledColor(color c){
    enabled_color = c;
  }
}

////////////////////////////////////////////
//            BUTTON CLASS                //
////////////////////////////////////////////
class Button { 
  public boolean toggled = false;
  public boolean hasImage;
  public int x, y, w, h;
  PImage img;
  color btnColor;
  color txtColor;
  color offColor = color(0);
  color onColor = color(220);
  String text = "";
  int txtSize = 32;
  public boolean shouldDisplay = false;

  Button (int x, int y, int w, int h, String s) { 
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    text = s;
    hasImage = false;
  }
  Button (int x, int y, int w, int h, PImage img) { 
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.img = img;
    hasImage = true;
  } 
  public void render() {
    if (!hasImage) {
      fill(btnColor);
      rect(x, y, w, h);
      fill(txtColor);
      textSize(txtSize);
      text(text, ((x + (w / 2 ) - (textWidth(text) / 2) )), y + h/2 + h*0.15);
    } else {
           if(toggled){
           noTint();
          image(img, x, y, w, h);     
        }else {
          tint(192, 192, 192);
         image(img, x, y, w, h);
        } 
    }
  } 
  public void setImage(PImage img){
   this.img = img; 
  }
  public void changeColor() {
    if (!toggled) {
      for (int i=0; i< img.width; i++) {
        for (int j=0; j< img.height; j++) {
          if (img.get(i, j) == onColor) {
            img.set(i, j, offColor);      
          }
        }
      }
    } else {
      for (int i=0; i< img.width; i++) {
        for (int j=0; j< img.height; j++) {
          if (img.get(i, j) == offColor) {
            img.set(i, j, onColor);       
          }
        }
      }
    }
  }
  void setColor(int r, int g, int b) {
    btnColor = color(r, g, b);
  }
  void setTextColor(int r, int g, int b) {
    txtColor = color(r, g, b);
  }
  void setText(String s) {
    text = s;
  }
  void setTextSize(int size) {
    txtSize = size;
  }
}
////////////////////////////////////////////
//            CALENDAR CLASS              //
////////////////////////////////////////////
class Calendar
{
  int xPos, yPos;
  int boxWidth = 91;
  int boxHeight = 80;
  String[][] dates ={ {"30", "31", "1", "2", "3", "4", "5"}, 
    {"6", "7", "8", "9", "10", "11", "12"}, 
    {"13", "14", "15", "16", "17", "18", "19"}, 
    {"20", "21", "22", "23", "24", "25", "26"}, 
    {"27", "28", "29", "30", "1", "2", "3"}, 
    {"4", "5", "6", "7", "8", "9", "10"}, };
  Calendar(int x, int y, int wdth, int hght) {
    xPos = x;
    yPos = y;
    boxWidth = wdth;
    boxHeight = hght;
  }

  void render() {  
    strokeWeight(2);
    noFill();
    for (int i=0; i < 7; i++) {
      switch(i) {
        case(0):
        text("Sun", xPos +(boxWidth*.2) + (i*boxWidth), yPos - (boxHeight*.5), boxWidth, boxHeight);
        break;
        case(1):
        text("Mon", xPos +(boxWidth*.2) + (i*boxWidth), yPos - (boxHeight*.5), boxWidth, boxHeight);
        break;
        case(2):
        text("Tue", xPos +(boxWidth*.2) + (i*boxWidth), yPos - (boxHeight*.5), boxWidth, boxHeight);
        break;
        case(3):
        text("Wed", xPos +(boxWidth*.2) + (i*boxWidth), yPos - (boxHeight*.5), boxWidth, boxHeight);
        break;
        case(4):
        text("Thu", xPos +(boxWidth*.2) + (i*boxWidth), yPos - (boxHeight*.5), boxWidth, boxHeight);
        break;
        case(5):
        text("Fri", xPos +(boxWidth*.2) + (i*boxWidth), yPos - (boxHeight*.5), boxWidth, boxHeight);
        break;
        case(6):
        text("Sat", xPos +(boxWidth*.2) + (i*boxWidth), yPos - (boxHeight*.5), boxWidth, boxHeight);
        break;
      }
      for (int j=0; j<6; j++) {
        rect(xPos + (i*boxWidth), yPos + (j*boxHeight), boxWidth, boxHeight);
        if (j==4 && i==2) { 
          ellipse(1593, 405, boxWidth*.8, boxHeight*.8);
          noFill();
        }
        text(dates[j][i], xPos + (boxWidth*.25) + (i*boxWidth), yPos + (boxHeight*.4) + (j*boxHeight), boxWidth, boxHeight);
        
      }
    }
  }
}
////////////////////////////////////////////
//            CLOCK CLASS                 //
////////////////////////////////////////////
// after doing this class, i realized that it would be best to integrate this with the button class for it to be clickable... but idk...
class Clock {
  public float s;
  public float m;
  public float h;
  public int sec;
  public int min;
  public int hr;
  public int iDay;
  public int iYear;
  public int iMonth;
  public int x, y, wdth, hgth;
  String sday;
  String smonth;
  color btnColor = black;
  color txtColor = gray_dark;
  String textDate = "";
  String textTime = "";
  int txtSize = 15;

  Clock(int xdim, int ydim, int w, int h){
    x = xdim + w/2;
    y = ydim + h/2;
    wdth = (int) (w *.9);
    hgth = (int) (h * .9); 
    iDay = day();
    iMonth = month();
    iYear = year();

    int temp = day() % 7 + 2;
    switch(temp) {
      case 2:
        sday = "Mon";
        break;
      case 3:
        sday = "Tue";
        break;
      case 4:
        sday = "Wed";
        break;
      case 5:
        sday = "Thurs";
        break;
      case 6:
        sday = "Fri";
        break;
      case 7:
        sday = "Sat";
        break;
      case 1:
        sday = "Sun";
        break;
    }
    switch(iMonth) {
      case 1:
        smonth = "Jan";
        break;
      case 2:
        smonth = "Feb";
        break;
      case 3:
        smonth = "Mar";
        break;
      case 4:
        smonth = "Apr";
        break;
      case 5:
        smonth = "May";
        break;
      case 6:
        smonth = "Jun";
        break;
      case 7:
        smonth = "Jul";
        break;
      case 8:
        smonth = "Aug";
        break;
      case 9:
        smonth = "Sep";
        break;
      case 10:
        smonth = "Oct";
        break;
      case 11:
        smonth = "Nov";
        break;
      case 12:
        smonth = "Dec";
        break;
    }
    print ("X: " + x + " Y: " + y + "\n");
  }
  public void render()
  {
    s = map(second(), 0, 60, 0, TWO_PI) - HALF_PI;
    m = map(minute(), 0, 60, 0, TWO_PI) - HALF_PI;
    h = map(hour() % 12, 0, 12, 0, TWO_PI) - HALF_PI;
    sec = second();
    min = minute();
    hr = hour();

    stroke(128);
    noFill();
    ellipse(x, y, wdth, hgth);
    stroke(128);
    strokeWeight(2);
    line(x, y, cos(s) * 60 + x, sin(s) * 60 + y);
    strokeWeight(3);
    line(x, y, cos(m) * 45 + x, sin(m) * 45 + y);
    strokeWeight(5);
    line(x, y, cos(h) * 35 + x, sin(h) * 35 + y);
    fill(txtColor);
    textSize(txtSize);

    textDate = sday + ", " + smonth + " " + iDay + ", " + iYear;
    textTime = hr + ":" + nf(min, 2);

    fill(128);
    textSize(38);
    text(textDate, 825, 10, 2*btnWidth, btnHeight);
    textSize(50);
    text(textTime, 1550, 45, 2*btnWidth, btnHeight);
  }
}
////////////////////////////////////////////
//            MOUSE CLASS                 //
////////////////////////////////////////////
void mouseClicked() 
{
  if ( (mouseX > sideOffset+btnWidth  && mouseX < sideOffset+btnWidth*3) &&
    (mouseY > topOffset && mouseY < topOffset +btnHeight)) {
    displayTempInF = !displayTempInF;
  }
  for (int i=0; i < 19; i++) { 
    if ( (mouseX > buttons[i].x && mouseX <buttons[i].x+buttons[i].w ) &&
      (mouseY > buttons[i].y && mouseY < buttons[i].y+buttons[i].h)) {
       lastClicked = i;
      if (i==15 && buttons[17].toggled) {
        buttons[17].toggled = false;
        textSize(48);
        textAlign(CENTER);
        text("Success", sideOffset + btnWidth*7 + (btnWidth*.1), canvasHeight - (topOffset+3*btnHeight) + (btnHeight*.45), btnWidth*3, btnHeight);
      } else {
        buttons[i].toggled = !buttons[i].toggled;
      }
      if (buttons[i].hasImage) {
        buttons[i].changeColor();
      } 
      if (buttons[1].toggled) {
        break;
      }
    }
  }
}
