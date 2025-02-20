# # importing only those functions
# # which are needed 
# from tkinter import *
# from tkinter.ttk import *

# # creating tkinter window
# root = Tk()

# # Adding widgets to the root window
# Label(root, text = 'GeeksforGeeks', font =(
# 'Verdana', 15)).pack(side = TOP, pady = 10)

# # Creating a photoimage object to use image
# photo = PhotoImage(file = r"C:\Users\Zebu\Documents\Computer_Science\Projects\ImageConverter\camera.png")

# # here, image option is used to
# # set image on button
# Button(root, text = 'Click Me !', image = photo).pack(side = TOP)

# mainloop()

from tkinter import *
from tkinter.ttk import *

root = Tk()

def eastereggtxt1():
    print("Easter Eggs!")

image = PhotoImage(file = r'camera.png') # I use easter_eggs as an example

image_button = Button(root, image = image, command = eastereggtxt1)
image_button.grid(row = 18, column = 7)
mainloop()