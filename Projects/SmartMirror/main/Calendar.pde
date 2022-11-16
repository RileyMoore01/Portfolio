
////class used for holding and displaying our calandar
//class Calendar
//{

//  int xPos, yPos;
//  int boxWidth = 91;
//  int boxHeight = 80;
//  //int numDay = day();
//  //String day = str(numDay);

//  String[][] dates ={ {"30", "31", "1", "2", "3", "4", "5"}, 
//    {"6", "7", "8", "9", "10", "11", "12"}, 
//    {"13", "14", "15", "16", "17", "18", "19"}, 
//    {"20", "21", "22", "23", "24", "25", "26"}, 
//    {"27", "28", "29", "30", "1", "2", "3"}, 
//    {"4", "5", "6", "7", "8", "9", "10"}, };


//  Calendar(int x, int y, int wdth, int hght) {

//    xPos = x;
//    yPos = y;
//    boxWidth = wdth;
//    boxHeight = hght;
//  }



//  void render() {  

//    strokeWeight(2);
//    noFill();


//    for (int i=0; i < 7; i++) {

//      switch(i) {

//        case(0):
//        text("Sun", xPos +(boxWidth*.2) + (i*boxWidth), yPos - (boxHeight*.5), boxWidth, boxHeight);
//        break;
//        case(1):
//        text("Mon", xPos +(boxWidth*.2) + (i*boxWidth), yPos - (boxHeight*.5), boxWidth, boxHeight);
//        break;
//        case(2):
//        text("Tue", xPos +(boxWidth*.2) + (i*boxWidth), yPos - (boxHeight*.5), boxWidth, boxHeight);
//        break;
//        case(3):
//        text("Wed", xPos +(boxWidth*.2) + (i*boxWidth), yPos - (boxHeight*.5), boxWidth, boxHeight);
//        break;
//        case(4):
//        text("Thu", xPos +(boxWidth*.2) + (i*boxWidth), yPos - (boxHeight*.5), boxWidth, boxHeight);
//        break;
//        case(5):
//        text("Fri", xPos +(boxWidth*.2) + (i*boxWidth), yPos - (boxHeight*.5), boxWidth, boxHeight);
//        break;
//        case(6):
//        text("Sat", xPos +(boxWidth*.2) + (i*boxWidth), yPos - (boxHeight*.5), boxWidth, boxHeight);
//        break;
//      }

//      for (int j=0; j<6; j++) {

//        //draw the squares
//        rect(xPos + (i*boxWidth), yPos + (j*boxHeight), boxWidth, boxHeight);
      
//        //current date circled
//        if (j==4 && i==2) {
//          //fill(230);  
//          ellipse(1593, 405, boxWidth*.8, boxHeight*.8);
//          noFill();
//          //stroke(128);
//        }
        
//        //if(dates[j][i] == day){
//        //  ellipse(xPos + (boxWidth*.25) + (i*boxWidth), yPos + (boxHeight*.4) + (j*boxHeight),boxWidth*.8, boxHeight*.8);
//        //  noFill();
//        //}
        
//        //text of the date
//        text(dates[j][i], xPos + (boxWidth*.25) + (i*boxWidth), yPos + (boxHeight*.4) + (j*boxHeight), boxWidth, boxHeight);
        
//      }
//    }
//  }
//}
