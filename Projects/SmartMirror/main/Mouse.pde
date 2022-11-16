
////does action according to button clicked
//void mouseClicked() 
//{

//  //if the temperature is pressed, change degrees displayed
//  if ( (mouseX > sideOffset+btnWidth  && mouseX < sideOffset+btnWidth*3) &&
//    (mouseY > topOffset && mouseY < topOffset +btnHeight)) {

//    displayTempInF = !displayTempInF;
//  }

//  for (int i=0; i < 19; i++) { 

//    //change the toggled state of the button when clicked
//    if ( (mouseX > buttons[i].x && mouseX <buttons[i].x+buttons[i].w ) &&
//      (mouseY > buttons[i].y && mouseY < buttons[i].y+buttons[i].h)) {


//      lastClicked = i;

//      //if trying to create new account
//      if (i==15 && buttons[17].toggled) {
//        buttons[17].toggled = false;
//        textSize(48);
//        textAlign(CENTER);
//        text("Success", sideOffset + btnWidth*7 + (btnWidth*.1), canvasHeight - (topOffset+3*btnHeight) + (btnHeight*.45), btnWidth*3, btnHeight);
//      } else {
//        buttons[i].toggled = !buttons[i].toggled;
//      }

//      //if the button has an image
//      if (buttons[i].hasImage) {
//        buttons[i].changeColor();
//      }



//      //if social media 
//      if (buttons[1].toggled) {
//        break;
//      }
//    }//end clicked if
//  }//end for


//  /**********************start of mike's mouseCLicked()**********************************/

//  /***************************if numpad button was hit****************************************/
//}
