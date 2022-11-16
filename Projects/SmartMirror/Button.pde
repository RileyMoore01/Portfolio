//class Button { 

//  public boolean toggled = false;
//  public boolean hasImage;

//  //rectangle to hold buttons x,y, width, height
//  public int x, y, w, h;

//  PImage img;

//  color btnColor;
//  color txtColor;
//  color offColor = color(0);
//  color onColor = color(220);

//  String text = "";
//  int txtSize = 32;
//  public boolean shouldDisplay = false;

//  Button (int x, int y, int w, int h, String s) { 
//    this.x = x;
//    this.y = y;
//    this.w = w;
//    this.h = h;
//    text = s;
//    hasImage = false;
//  }

//  Button (int x, int y, int w, int h, PImage img) { 
//    this.x = x;
//    this.y = y;
//    this.w = w;
//    this.h = h;
//    this.img = img;
//    hasImage = true;
//  } 


//  public void render() {

//    //if its a text button
//    if (!hasImage) {
//      //draw the rectangle
//      fill(btnColor);
//      rect(x, y, w, h);

//      fill(txtColor);
//      textSize(txtSize);
//      text(text, ((x + (w / 2 ) - (textWidth(text) / 2) )), y + h/2 + h*0.15);
//    } else {
//      //if we get here that means we have an image
          
         
//           if(toggled){
//           noTint();
//          image(img, x, y, w, h);
         
//        }else
//        {
//        // noTint();
//         //if image toggled tint
//          tint(192, 192, 192);
//         image(img, x, y, w, h);
//        }

      
//    }
//  } 
  
//  public void setImage(PImage img){
//   this.img = img; 
//  }

//  //change the color of the image based on toggle status
//  public void changeColor() {
    
  

//    //if not toggled, display the image as black, else gray
//    if (!toggled) {
//      for (int i=0; i< img.width; i++) {
//        for (int j=0; j< img.height; j++) {
//          if (img.get(i, j) == onColor) {
//            img.set(i, j, offColor);      //change the colors of the non transparent pixels
//          }//end if
//        }//end for height
//      }//end for width
//    } else {
//      for (int i=0; i< img.width; i++) {
//        for (int j=0; j< img.height; j++) {
//          if (img.get(i, j) == offColor) {
//            img.set(i, j, onColor);        //change the colors of the non transparent pixels
//          }//end if
//        }//end for height
//      }//end for width
//    }//end else
//  }

//  void setColor(int r, int g, int b) {
//    btnColor = color(r, g, b);
//  }

//  void setTextColor(int r, int g, int b) {
//    txtColor = color(r, g, b);
//  }

//  void setText(String s) {
//    text = s;
//  }

//  void setTextSize(int size) {
//    txtSize = size;
//  }
//}
