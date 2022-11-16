//// after doing this class, i realized that it would be best to integrate this with the button class for it to be clickable... but idk...
//class Clock {
//  public float s;
//  public float m;
//  public float h;
//  public int sec;
//  public int min;
//  public int hr;
//  public int iDay;
//  public int iYear;
//  public int iMonth;
//  String sday;
//  String smonth;

//  public int x, y, wdth, hgth;
//  color btnColor = black;
//  color txtColor = gray_dark;
//  String textDate = "";
//  String textTime = "";
//  int txtSize = 15;

//  Clock(int xdim, int ydim, int w, int h)
//  {
//    x = xdim + w/2;
//    y = ydim + h/2;
//    wdth = (int) (w *.9);
//    hgth = (int) (h * .9); 

//    iDay = day();
//    iMonth = month();
//    iYear = year();

//    int temp = day() % 7 + 2;
//    switch(temp) {
//      case 2:
//        sday = "Mon";
//        break;
//      case 3:
//        sday = "Tue";
//        break;
//      case 4:
//        sday = "Wed";
//        break;
//      case 5:
//        sday = "Thurs";
//        break;
//      case 6:
//        sday = "Fri";
//        break;
//      case 7:
//        sday = "Sat";
//        break;
//      case 1:
//        sday = "Sun";
//        break;
//    }

//    switch(iMonth) {
//      case 1:
//        smonth = "Jan";
//        break;
//      case 2:
//        smonth = "Feb";
//        break;
//      case 3:
//        smonth = "Mar";
//        break;
//      case 4:
//        smonth = "Apr";
//        break;
//      case 5:
//        smonth = "May";
//        break;
//      case 6:
//        smonth = "Jun";
//        break;
//      case 7:
//        smonth = "Jul";
//        break;
//      case 8:
//        smonth = "Aug";
//        break;
//      case 9:
//        smonth = "Sep";
//        break;
//      case 10:
//        smonth = "Oct";
//        break;
//      case 11:
//        smonth = "Nov";
//        break;
//      case 12:
//        smonth = "Dec";
//        break;
//    }

//    print ("X: " + x + " Y: " + y + "\n");
//  }

//  public void render()
//  {
//    s = map(second(), 0, 60, 0, TWO_PI) - HALF_PI;
//    m = map(minute(), 0, 60, 0, TWO_PI) - HALF_PI;
//    h = map(hour() % 12, 0, 12, 0, TWO_PI) - HALF_PI;

//    sec = second();
//    min = minute();
//    hr = hour();

//    stroke(128);

//    noFill();

//    //draw the clock shape/object
//    ellipse(x, y, wdth, hgth);

//    // sets the color used for the clock hands
//    stroke(128);
    
//    // second hand
//    strokeWeight(2);
//    line(x, y, cos(s) * 60 + x, sin(s) * 60 + y);

//    // minute hand
//    strokeWeight(3);
//    line(x, y, cos(m) * 45 + x, sin(m) * 45 + y);

//    // hour hand
//    strokeWeight(5);
//    line(x, y, cos(h) * 35 + x, sin(h) * 35 + y);

//    // used for the digital readout of the clock
//    fill(txtColor);
//    textSize(txtSize);

//    // display the day, month, year
//    textDate = sday + ", " + smonth + " " + iDay + ", " + iYear;
//    textTime = hr + ":" + nf(min, 2);

//    fill(128);
//    textSize(38);
//    //text(textDate, canvasWidth-sideOffset-(btnWidth*3), topOffset+(btnHeight/4), 2*btnWidth, btnHeight);
//    text(textDate, 825, 10, 2*btnWidth, btnHeight);
//    textSize(50);
//    text(textTime, 1550, 45, 2*btnWidth, btnHeight);
//  }
//}
