//class Option
//{
//  private boolean[] clicked;
//  private String[][] name;
//  private int x;
//  private int y;
//  private boolean news_picked;
//  private boolean social_picked;
//  private color news_color;
//  private color social_color;
//  private color lang_color;
//  private color enabled_color;
  
//  Option(String[][] s,int xpos, int ypos)
//  {
//    name = s;
//    x = xpos;
//    y = ypos;
//    news_color = gray_dark;
//    social_color = gray_dark;
//    lang_color = gray_dark;
//    enabled_color = gray_dark;
//    news_picked = false;
//    social_picked = false;
//    clicked = new boolean[6];
    
//    for(boolean b : clicked)
//        b = false;
//  }


//  void setOptionClicked(boolean t, int index)
//  {
//    clicked[index] = t;
//  }

//  boolean isClicked(int index)
//  {
//    return clicked[index];
//  }
 

//  void setNewsClicked(boolean t)
//  {
//    news_picked = t;
//  }

//  boolean isNewsClicked()
//  {
//    return news_picked;
//  }
  
//  void setSocialClicked(boolean t)
//  {
//    social_picked = t;
//  }

//  boolean isSocialClicked()
//  {
//    return social_picked;
//  }

//  String getOptionName(int index)
//  {
//    return name[curr_lang][index];
//  }
 
//  void setOptionName(String t)
//  {
//    int length = name[curr_lang][index].length();
//    println("length = " + length);
      
//    if( str(name[curr_lang][index].charAt(length - 1)).equals(":"))
//        name[curr_lang][index] = name[curr_lang][index] + t;
//    else
//     name[curr_lang][index] = name[curr_lang][index].substring(0,name[curr_lang][index].lastIndexOf(" ")+1) + t;
     
//  }
 
 
//  int getX()
//  {
//    return x;
//  }
  
  
//  int getY()
//  {
//    return y;
//  }


//  void setNewsColor(color c)
//  {
//      news_color = c;
//  }
  
//  void setSocialColor(color c)
//  {
//    social_color = c;
//  }
  
//  color getNewsColor()
//  {
//      return news_color;
//  }
  
//  color getSocialColor()
//  {
//    return social_color;
//  }

//  color getLanguageColor()
//  {
//    return lang_color;
//  }
  
//  color getEnabledColor()
//  {
//      return enabled_color;
//  }
  
//  void setEnabledColor(color c)
//  {
//    enabled_color = c;
//  }
  

//}
